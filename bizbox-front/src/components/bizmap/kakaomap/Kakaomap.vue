<template>
  <div class="mapContainer">
    <Loading :loading="loadingStatus"></Loading>
    <div class="flip-card">
      <div class="flip-card-inner">
        <div class="flip-card-front">
          <v-card>
            <v-toolbar class="searchBox">
              <v-text-field
                Elevation="14"
                hide-details
                v-on:keyup.enter="serch"
                v-model.trim="name"
                single-line
                clearable
                label="Search..."
              ></v-text-field>
              <v-btn v-on:click="serch" icon>
                <v-icon>mdi-magnify</v-icon>
              </v-btn>
            </v-toolbar>
          </v-card>
          <condition v-on:myevent="myevent"></condition>
          <div class="ssss" v-show="isonececlick">
            <div class="info" id="graph-info">
              <canvas class="chart" id="horizontalbarChart"></canvas>
            </div>
          </div>
          <!-- <div class="map" id="map"></div> -->
          <v-btn class="addbt" v-on:click="addMyStore" icon>
            현재 위치에 내 점포 추가하기
            <v-icon>mdi-magnify</v-icon>
          </v-btn>
        </div>
        <div class="flip-card-back">
          <div class="storeform">
            <div class="add-form">
              <input type="text" placeholder="상호명" v-model="storeName" required />
              <input type="text" placeholder="대분류" v-model="Clarge" required />
              <input type="text" placeholder="중분류" v-model="Cmiddle" required />
              <input type="text" placeholder="소분류" v-model="Csmall" required />
              <button @click="storeAdd">add</button>
              <p class="message">
                잘못 누르셨나요?
                <a @click="addMyStore">되돌아가기</a>
              </p>
            </div>
          </div>
        </div>
      </div>
    </div>
    <Detail v-show="showModal" @close="showModal = false; unDetail()" :clickEvent="showModal">
      <!-- 마커 클릭시 모달 표시되는 부분입니다 -->
    </Detail>
    <div class="map" id="map"></div>
  </div>
</template>

<script>
import Detail from '@/components/modal/modal_view/modal_view.vue'
import '@/components/modal/Modal.css'
import Dong from '@/assets/json/newcolor.json'
import color from '@/assets/json/color.json'
import condition from '@/components/bizmap/kakaomap/SearchCondition.vue'
import axios from '@/js/http-commons'
import { eventBus } from '@/js/bus'
import { mapGetters } from 'vuex'
import axi from 'axios'
import Loading from '@/components/bizmap/loading/Loading.vue'
import './AddStore.scss'
import markerimg from '@/assets/map-icon/marker.png'

export default {
  data: () => {
    return {
      Polylist: [],
      Color: '',
      filpStyle: {
        transform: 'rotateY(180deg)'
      },
      markerImg: markerimg,
      storeName: '',
      Clarge: '',
      Cmiddle: '',
      Csmall: '',
      info: null,
      member_marker: [],
      showModal: false,
      points: [],
      polygon: null,
      geocoder: null,
      html: null,
      map: null,
      name: '역삼동',
      clusterer: null,
      marker: null,
      infowindow: null,
      message: null,
      ifchanege: null,
      addListener: null,
      mode: null, // 여기 반경 하고 싶으면 asdf 로
      ChangeBusinessTable: null, // 오버레이 테이블
      rClickPosition: null,
      radiusOverlay: null,
      circle: null,
      polyline: null,
      radiusObj: null,
      hashover: false,
      drawingFlag: false, // 원이 그려지고 있는 상태를
      hashover: false,
      centerPosition: false, // 원의 중심좌표
      drawingCircle: false, // 그려지고 있는 원을 표시할 원 객체
      drawingLine: false, // 그려지고 있는 원의 반지름을 표시할 선 객체
      drawingOverlay: false, // 그려지고 있는 원의 반경을 표시할 커스텀오버레이
      customOverlay: false,
      wasDrawing: false,
      isonececlick: false,
      loadingStatus: false,
      circles: [],
      countResult: '',
      searchX: '',
      searchY: '',
      range: '',
      coords: '',
      ME: '',
      CountInfo: {
        소매: '',
        학문교육: '',
        숙박: '',
        생활서비스: '',
        음식: '',
        부동산: '',
        의료: '',
        관광여가오락: ''
      },
      isClicked: false
    }
  },
  mounted() {
    let data = Dong // 좌표 저장할 배열
    let coordinates = [] // 행정 구 이름
    let name = '멀티캠퍼스'
    let color = ''
    let Message = ''
    this.message = Message
    var container = document.getElementById('map')
    var options = {
      center: new kakao.maps.LatLng(37.505691, 127.0298106),
      level: 6
    }
    // --코더 생성------------------------------------------------------------------------------------
    this.geocoder = new kakao.maps.services.Geocoder() // 코더생성
    // --맵 생성--------------------------------------------------------------------------------------
    this.map = new kakao.maps.Map(container, options) // 맵 생성
    kakao.maps.event.addListener(this.map, 'bounds_changed', () => {
      var bounds = Map.getBounds() // 지도 영역정보
      var swLatlng = bounds.getSouthWest() // 영역정보의 남서쪽 정보
      var neLatlng = bounds.getNorthEast() // 영역정보의 북동쪽 정보
      this.message =
        '영역좌표 :' + swLatlng.toString() + '/' + neLatlng.toString() + ''
    })
    let Map = this.map
    let vm = this
    // 오버레이 생성--------------------------------------------------------
    this.customOverlay = new kakao.maps.CustomOverlay({})
    this.info = new kakao.maps.CustomOverlay({})
    // --마커 생성--------------------------------------------------------------------
    this.marker = new kakao.maps.Marker({
      map: this.map
      //position: new kakao.maps.LatLng(37.505691, 127.0298106) // 최초 표시되는 마커의 위치
    })
    this.drawMarker()

    kakao.maps.event.addListener(this.marker, 'click', function() {
      // 마커(자세히 보기) 클릭 시 모달창 이벤트 호출
      vm.detail()
      vm.changeModal()
    })

    // --인포윈도우 생성---------------------------------------------------------------
    this.infowindow = new kakao.maps.InfoWindow({
      content: 'name'
    })
    // -------------------------------------------------------------------------------------

    // 반경 그리는 이벤트 시작-------------------------------------------------------------------
    kakao.maps.event.addListener(this.map, 'click', function(mouseEvent) {
      vm.CircleMouseClick(mouseEvent)
      // vm.Controlllevel(vm.points)
    }) // 지도에 클릭 이벤트를 등록
    kakao.maps.event.addListener(this.map, 'mousemove', function(mouseEvent) {
      vm.CircleMoveClick(mouseEvent)
    }) // 지도에 무브 이벤트를 등록
    axios
      .get('/population/getPopulationByTime/' + this.name)
      .then(res => {})
      .finally(() => {})
    // for (var i = 0, len = data.length; i < len; i++) {
    //   var nname = data[i].properties.ADM_DR_NM
    //   console.log(nname)
    //   for (var j = 0, Len = color.colorlist.length; j < Len; j++) {
    //     if (nname === color.colorlist[j].name) {
    //       data[i].properties.color = color.colorlist[j].color
    //       console.log(color.colorlist[j].color)
    //     }
    //   }
    // }
    // var jsonData = JSON.stringify(data)
    // var a = document.createElement("a")
    // var file = new Blob([jsonData], { type: 'text/plain' })
    // a.href = URL.createObjectURL(file)
    // a.download = 'cc.txt'
    // a.click()
    for (var i = 0, len = data.length; i < len; i++) {
      // 동의 경계면 좌표를 받아서 다각형 생성 함수에 전달
      this.coordinates = data[i].geometry.coordinates
      name = data[i].properties.ADM_DR_NM
      if (data[i].properties.color === '정체') {
        color = '#fca103'
      }
      if (data[i].properties.color === '상권확장') {
        color = '#039dfc'
      }
      if (data[i].properties.color === '상권축소') {
        color = '#fc1803'
      }
      if (data[i].properties.color === '다이나믹') {
        color = '#03fc24'
      }
      displayArea(
        vm.pp,
        this.map,
        this.coordinates[0][0],
        name,
        this.coordinates[0][0].length,
        this.customOverlay,
        color
      )
    }

    function displayArea(
      polygon,
      Mmap,
      coordinates,
      name,
      length,
      customOverlay,
      color
    ) {
      let mode = vm.$store.state.mode
      let path = []
      let points = []
      for (let i = 0; i < length; i++) {
        // 좌표의 개수만큼 반복문을 돌며 경계 바운더리 생성(path생성)
        var point = new Object()
        point.x = coordinates[i][1]
        point.y = coordinates[i][0]
        points.push(point)
        path.push(new kakao.maps.LatLng(coordinates[i][1], coordinates[i][0]))
      }
      polygon = new kakao.maps.Polygon({
        // 생성된 path를 이용하여 폴리곤(다각형) 생성
        map: Mmap, // 다각형을 표시할 지도 객체
        path: path,
        strokeWeight: 2, // 선두깨
        strokeColor: '#004c80', // 선색
        strokeOpacity: 0.4, // 선 투명도
        fillColor: color,
        fillOpacity: 0.13
      })

      kakao.maps.event.addListener(polygon, 'mouseover', mouseEvent => {
        // 각 폴리곤에 마우스 오버 이벤트 등록
        let Name = name
        let position = mouseEvent.latLng
        polygon.setOptions({
          //fillColor: '#ffbf00',
          fillOpacity: 0.5
        })
        customOverlay.setContent(
          '<div class="area" style="font-size: 16px; border-radius: 3px; background: #fff; top: -5px; border: 1px solid #888; position: absolute; left:30px; padding:2px;">' +
            name +
            '</div>'
        )
        customOverlay.setPosition(position)
        customOverlay.setMap(Mmap)
      })
      kakao.maps.event.addListener(polygon, 'mousemove', mouseEvent => {
        // 각 폴리곤에 마우스 오버 이벤트 등록
        let position = mouseEvent.latLng
        customOverlay.setPosition(position)
      })
      kakao.maps.event.addListener(polygon, 'mouseout', () => {
        //  각 폴리곤에 마우스 아웃 이벤트 등록
        polygon.setOptions({
          fillColor: color,
          fillOpacity: 0.13
        })
        customOverlay.setMap(null)
      })
      kakao.maps.event.addListener(polygon, 'click', mouseEvent => {
        //if (vm.$store.state.mode === 0) {
        if (vm.$store.state.mode !== 1) {
          vm.unDetail()
          vm.setPolygon(polygon) // 현재 선택된 폴리곤 기억
          vm.eventbus(name)
          vm.saveMouseEvent(mouseEvent.latLng, 0)
          let Name = name
          let coords = ''
          vm.setSerchkey(name) // 클릭된 영역의 동이름을 기억하는 메서드
          vm.setColor(color) // 현재 선택된 폴리곤의 색 기억
          let Marker = vm.marker
          coords = new kakao.maps.LatLng(vm.ME.getLat(), vm.ME.getLng()) // 결과값으로 받은 위치를 마커의 위치로 적용
          Marker.setPosition(coords)
          var imageSrc =
            'https://post-phinf.pstatic.net/MjAxODEwMjlfMjIy/MDAxNTQwNzg4MzE3MjY5.LLHhYLh1j1_nHjfolzukFd3SgwPeusVXJFmUJ3voADcg.ir556-ycrlzdjx1QZ14LA73RHXamNw3Z6-abjpyrEvsg.GIF/%EC%9E%90%EC%84%B8%ED%9E%88%EB%B3%B4%EA%B8%B0.gif?type=w500_q75' // https://image.flaticon.com/icons/svg/1322/1322263.svg
          // 돋보기 모양 https://cdn.icon-icons.com/icons2/1744/PNG/512/3643762-find-glass-magnifying-search-zoom_113420.png
          var imageSize = new kakao.maps.Size(55, 55) // 마커이미지의 크기입니다
          var imageOption = { offset: new kakao.maps.Point(27, 69) } // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.
          var markerImage = new kakao.maps.MarkerImage(
            imageSrc,
            imageSize,
            imageOption
          )
          var content =
            '<div style="text-align: center; color:white;margin-top:10px; padding:2px; border:0px; background-color: #fff;border-radius: 3px; background: coral;">' +
            Name +
            '</div>'
          vm.marker.setImage(markerImage)
          vm.marker.setPosition(coords)
          vm.info.setContent(content)
          vm.info.setPosition(coords)
          vm.info.setMap(Map)
          Map.setCenter(coords)
          if (vm.$store.state.mode === 2) {
            vm.makeOverlay2(name)
          }
          if (vm.$store.state.mode === 3) {
            vm.makeOverlay3(name)
          }
          if (vm.$store.state.mode === 4) {
            vm.makeOverlay4(name)
          }
          if (vm.$store.state.mode === 5) {
            vm.makeOverlay5(name)
          }
          if (vm.$store.state.mode === 6) {
            vm.makeOverlay6(name)
          }
          if (vm.$store.state.mode === 7) {
            vm.makeOverlay7(name)
          }
          if (vm.$store.state.mode === 8) {
            vm.makeOverlay8(name)
          }
        }
      })
    }
  },
  computed: {
    ...mapGetters(['isLoggedIn']),
    loginChange() {
      return this.$store.state.auth.token
    }
  },
  watch: {
    loginChange() {
      if (this.$store.state.auth.token) {
        this.drawMarker()
      }
    }
  },
  components: {
    condition,
    Detail,
    Loading
  },
  methods: {
    islogin(token) {
      this.drawMarker()
    },
    setColor(color) {
      this.Color = color
    },
    unDetail() {
      this.map.setLevel(6, { anchor: this.ME })
      this.showModal = false
      if (this.polygon != null) {
        this.polygon.setOptions({ fillOpacity: 0.13 })
      }
    },
    detail() {
      this.map.setLevel(3, { anchor: this.ME })
      this.polygon.setOptions({ fillOpacity: 0 })
    },
    drawMarker() {
      if (this.$store.state.auth.token) {
        var imageSrc = this.markerImg
        var imageSize = new kakao.maps.Size(40, 40) // 마커이미지의 크기입니다
        var imageOption = { offset: new kakao.maps.Point(27, 69) } // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.
        var markerImage = new kakao.maps.MarkerImage(
          imageSrc,
          imageSize,
          imageOption
        )
        axi
          .post(
            'http://70.12.246.137:8080/user/info',
            {
              email: 'asdfasdfa',
              pw: 'asdfasdfasdf'
            },
            {
              headers: {
                jwt: this.$store.state.auth.token
              }
            }
          )
          .then(res => {
            res.data.data.forEach(element => {
              this.$store.state.auth.mylist.push(element)
            })
          })
          .then(() => {
            let posi = this.getmystore()
            for (let index = 0; index < posi.length; index++) {
              let marker = new kakao.maps.Marker({
                map: this.map,
                position: posi[index], // 최초 표시되는 마커의 위치
                image: markerImage
              }).setMap(this.map)
              this.member_marker.push(marker)
            }
          })
        // 로그인하면 자신이 등록한 점포 위치 나옴
      }
    },
    storeAdd() {
      if (!this.storeName || !this.Clarge || !this.Cmiddle || !this.Csmall) {
        alert('항목을 빠짐없이 기입해주세요')
        return
      }
      axios
        .post(
          '/user/addStore',
          {
            email: sessionStorage.getItem('login_user_email'),
            store_name: this.storeName,
            category_large: this.Clarge,
            category_middle: this.Cmiddle,
            category_small: this.Csmall,
            lat: this.$store.state.Coords.lat,
            lot: this.$store.state.Coords.lng
          },
          {
            headers: {
              jwt: this.$store.state.auth.token
            }
          }
        )
        .then(res => {})
        .finally(() => {
          this.addMyStore()
          this.storeName = null
          this.Clarge = null
          this.Cmiddle = null
          this.Csmall = null
          this.drawMarker()
        })
    },
    addMyStore() {
      if (!sessionStorage.getItem('login_user_email')) {
        alert('로그인후 이용 가능합니다.')
        return
      }
      let cardinner = document.getElementsByClassName('flip-card-inner')
      if (cardinner[0].className.includes('firstflip')) {
        cardinner[0].className = cardinner[0].className.replace(
          ' firstflip',
          ''
        )
        cardinner[0].className += ' secondflip'
      } else {
        cardinner[0].className = cardinner[0].className.replace(
          ' secondflip',
          ''
        )
        cardinner[0].className += ' firstflip'
      }
    },
    getmystore() {
      let list = this.$store.state.auth.mylist
      let posi = []
      for (let index = 0; index < list.length; index++) {
        let position = new kakao.maps.LatLng(list[index].lat, list[index].lot)
        posi.push(position)
      }
      return posi
    },
    eventbus(name) {
      if (this.showModal) { eventBus.$emit('clickmap', name) }
    },
    myevent() {
      this.saveMouseEvent(this.ME, 1)
      let name = this.$store.state.modalsearch
      if (this.$store.state.mode === 2) {
        this.makeOverlay2(name)
      }
      if (this.$store.state.mode === 3) {
        this.makeOverlay3(name)
      }
      if (this.$store.state.mode === 4) {
        this.makeOverlay4(name)
      }
      if (this.$store.state.mode === 5) {
        this.makeOverlay5(name)
      }
      if (this.$store.state.mode === 6) {
        this.makeOverlay6(name)
      }
      if (this.$store.state.mode === 7) {
        this.makeOverlay7(name)
      }
      if (this.$store.state.mode === 8) {
        this.makeOverlay8(name)
      }
    },
    saveMouseEvent(mouseEvent, flag) {
      // 마우스 커서의 위치를 저장하는 메서드
      if (this.isonececlick === false && flag === 1) {
        // 최초 페이지 로드후 클릭이 일어났지는지 유무를 확인하는 변수
        this.isonececlick = true
      }
      this.ME = mouseEvent
      this.$store.state.Coords.lat = this.ME.getLat() // 모달에 전달할 xy 좌표
      this.$store.state.Coords.lng = this.ME.getLng() //
    },
    saveMouseEvent2(coords, flag) {
      // 마우스 커서의 위치를 저장하는 메서드
      if (this.isonececlick === false && flag === 1) {
        // 최초 페이지 로드후 클릭이 일어났지는지 유무를 확인하는 변수
        this.isonececlick = true
      }
      this.ME = coords
      this.$store.state.Coords.lat = this.ME.getLat() // 모달에 전달할 xy 좌표
      this.$store.state.Coords.lng = this.ME.getLng() //
    },
    setSerchkey(name) {
      // 마우스 커서위치의 동이름을 저장하는 메서드
      this.$store.state.modalsearch = name
    },
    setPolygon(polygon) {
      this.polygon = polygon
    },
    add() {
      this.showAdd = !this.showAdd
    },
    changeModal() {
      this.showModal = !this.showModal
      eventBus.$emit('clickDetail')
    },
    ClickMove() {
      if (this.$store.state.mode === 0) {
        //  각 폴리곤에 마우스 아웃 이벤트 등록
        // vm.points = vm.centroid(points)
        let Name = name
        let Marker = this.marker
        let InfoWindow = this.infowindow
        this.geocoder.addressSearch(Name, function(result, status) {
          // 정상적으로 검색이 완료되면
          if (status === kakao.maps.services.Status.OK) {
            var coords = new kakao.maps.LatLng(result[0].y, result[0].x) // 결과값으로 받은 위치를 마커의 위치로 적용
            Marker.setPosition(coords)
            InfoWindow.close()
            InfoWindow.setContent(Name)
            InfoWindow.open(Map, Marker)
            Map.setCenter(coords)
          }
        })
      }
    },
    centroid(points) {
      var i, j, len, p1, p2, f, area, x, y
      area = x = y = 0
      for (i = 0, len = points.length, j = len - 1; i < len; j = i++) {
        p1 = points[i]
        p2 = points[j]

        f = p1.y * p2.x - p2.y * p1.x
        x += (p1.x + p2.x) * f
        y += (p1.y + p2.y) * f
        area += f * 3
      }

      return new kakao.maps.LatLng(x / area, y / area)
    },
    panTo() {
      // 지도 중심을 부드럽게 이동시킵니다
      var moveLatLon = new kakao.maps.LatLng(33.45058, 126.574942) // 이동할 위도 경도 위치를 생성합니다
      this.map.panTo(moveLatLon)
    },
    // -- 동이름으로 검색-----------------------------------------------------------------------------
    serch(name) {
      let Name = this.name
      // eslint-disable-next-line no-unused-vars
      let vm = this
      let Marker = this.marker
      this.geocoder.addressSearch(this.name, function(result, status) {
        // 정상적으로 검색이 완료되면
        if (status === kakao.maps.services.Status.OK) {
          var coords = new kakao.maps.LatLng(result[0].y, result[0].x) // 결과값으로 받은 위치를 마커의 위치로 적용
          Marker.setPosition(coords)
          vm.map.setCenter(coords) // 새로 세팅된 센터 값으로 맵 세팅
          vm.eventbus(Name)
          vm.saveMouseEvent2(coords, 0)
          vm.setSerchkey(Name) // 클릭된 영영ㄱ의 동이름을 기억하는 메서드
          // vm.setColor(color)
          var imageSrc =
            'https://post-phinf.pstatic.net/MjAxODEwMjlfMjIy/MDAxNTQwNzg4MzE3MjY5.LLHhYLh1j1_nHjfolzukFd3SgwPeusVXJFmUJ3voADcg.ir556-ycrlzdjx1QZ14LA73RHXamNw3Z6-abjpyrEvsg.GIF/%EC%9E%90%EC%84%B8%ED%9E%88%EB%B3%B4%EA%B8%B0.gif?type=w500_q75' // https://image.flaticon.com/icons/svg/1322/1322263.svg
          // 돋보기 모양 https://cdn.icon-icons.com/icons2/1744/PNG/512/3643762-find-glass-magnifying-search-zoom_113420.png
          var imageSize = new kakao.maps.Size(55, 55) // 마커이미지의 크기입니다
          var imageOption = { offset: new kakao.maps.Point(27, 69) } // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.
          var markerImage = new kakao.maps.MarkerImage(
            imageSrc,
            imageSize,
            imageOption
          )
          var content =
            '<div style="text-align: center; color:white;margin-top:10px; padding:2px; border:0px; background-color: #fff;border-radius: 3px; background: coral;">' +
            Name +
            '</div>'
          Marker.setImage(markerImage)
          Marker.setPosition(coords)
          vm.info.setContent(content)
          vm.info.setPosition(coords)
          vm.info.setMap(vm.map)
        }
        if (vm.$store.state.mode === 2) {
          vm.makeOverlay2(Name)
        }
        if (vm.$store.state.mode === 3) {
          vm.makeOverlay3(Name)
        }
        if (vm.$store.state.mode === 4) {
          vm.makeOverlay4(Name)
        }
        if (vm.$store.state.mode === 5) {
          vm.makeOverlay5(Name)
        }
        if (vm.$store.state.mode === 6) {
          vm.makeOverlay6(Name)
        }
        if (vm.$store.state.mode === 7) {
          vm.makeOverlay7(Name)
        }
        if (vm.$store.state.mode === 8) {
          vm.makeOverlay8(Name)
        }
      })
    },
    async CircleMouseClick(mouseEvent) {
      // 지도에 클릭 이벤트를 등록
      this.removeCircles()
      if (this.$store.state.mode === 1 && this.map.getLevel() < 5) {
        if (this.ChangeBusinessTable !== null) {
          // overay 삭제 매서드
          this.ChangeBusinessTable.setMap(null)
        }
        if (!this.drawingFlag) {
          // 클릭 이벤트가 발생했을 때 원을 그리고 있는 상태가 아니면 중심좌표를 클릭한 지점으로 설정
          this.drawingFlag = true
          this.centerPosition = mouseEvent.latLng // 원이 그려질 중심좌표를 클릭한 위치로 설정합니다

          this.searchX = this.centerPosition.Ga
          this.searchY = this.centerPosition.Ha

          if (!this.drawingLine) {
            // 그려지고 있는 원의 반경을 표시할 선 객체를 생성합니다
            this.drawingLine = new kakao.maps.Polyline({
              strokeWeight: 3, // 선의 두께
              strokeColor: '#00a0e9', // 선의 색깔
              strokeOpacity: 0, // 선의 불투명도
              strokeStyle: 'solid' // 선의 스타일
            })
          }
          if (!this.drawingCircle) {
            // 그려지고 있는 원을 표시할 원 객체를 생성합니다
            this.drawingCircle = new kakao.maps.Circle({
              strokeWeight: 1,
              strokeColor: '#00a0e9',
              strokeOpacity: 0,
              strokeStyle: 'solid',
              fillColor: '#00a0e9',
              fillOpacity: 0.2
            })
          }
          if (!this.drawingOverlay) {
            // 그려지고 있는 원의 반경 정보를 표시할 커스텀오버레이를 생성합니다
            this.drawingOverlay = new kakao.maps.CustomOverlay({
              xAnchor: 0,
              yAnchor: 0,
              zIndex: 1
            })
          }
        }
      } else if (this.$store.state.mode === 1 && this.map.getLevel() >= 5) {
        alert('맵을 확대하고 이용해주세요')
      }
      if (this.drawingFlag) {
        if (this.hashover) {
          this.rClickPosition = mouseEvent.latLng // 마우스로 오른쪽 클릭한 위치입니다
          this.polyline = new kakao.maps.Polyline({
            // 원의 반경을 표시할 선 객체를 생성합니다
            path: [this.centerPosition, this.rClickPosition], // 선을 구성하는 좌표 배열입니다. 원의 중심좌표와 클릭한 위치로 설정합니다
            strokeWeight: 1, // 선의 두께 입니다
            strokeColor: '#00a0e9', // 선의 색깔입니다
            strokeOpacity: 0, // 선의 불투명도입니다 0에서 1 사이값이며 0에 가까울수록 투명합니다
            strokeStyle: 'solid' // 선의 스타일입니다
          })
          this.circle = new kakao.maps.Circle({
            // 원 객체를 생성합니다
            center: this.centerPosition, // 원의 중심좌표입니다
            radius: this.polyline.getLength(), // 원의 반지름입니다 m 단위 이며 선 객체를 이용해서 얻어옵니다
            strokeWeight: 0, // 선의 두께입니다
            strokeColor: '#00a0e9', // 선의 색깔입니다
            strokeOpacity: 0, // 선의 불투명도입니다 0에서 1 사이값이며 0에 가까울수록 투명합니다
            strokeStyle: 'solid', // 선의 스타일입니다
            fillColor: '#00a0e9', // 채우기 색깔입니다
            fillOpacity: 0.2 // 채우기 불투명도입니다
          })
          var radius = Math.round(this.circle.getRadius()) // 원의 반경 정보를 얻어옵니다
          this.range = radius
          await this.getDataCircle()

          this.centerPosition = null // 중심 좌표를 초기화 합니다
          this.drawingCircle.setMap(null) // 그려지고 있는 원, 선, 커스텀오버레이를 지도에서 제거합니다
          this.drawingLine.setMap(null)
          this.drawingOverlay.setMap(null)
        }
      }
      if (this.drawingFlag) {
        if (this.hashover) {
          this.rClickPosition = mouseEvent.latLng // 마우스로 오른쪽 클릭한 위치입니다
          this.polyline = new kakao.maps.Polyline({
            // 원의 반경을 표시할 선 객체를 생성합니다
            path: [this.centerPosition, this.rClickPosition], // 선을 구성하는 좌표 배열입니다. 원의 중심좌표와 클릭한 위치로 설정합니다
            strokeWeight: 1, // 선의 두께 입니다
            strokeColor: '#00a0e9', // 선의 색깔입니다
            strokeOpacity: 0, // 선의 불투명도입니다 0에서 1 사이값이며 0에 가까울수록 투명합니다
            strokeStyle: 'solid' // 선의 스타일입니다
          })
          this.circle = new kakao.maps.Circle({
            // 원 객체를 생성합니다
            center: this.centerPosition, // 원의 중심좌표입니다
            radius: this.polyline.getLength(), // 원의 반지름입니다 m 단위 이며 선 객체를 이용해서 얻어옵니다
            strokeWeight: 0, // 선의 두께입니다
            strokeColor: '#00a0e9', // 선의 색깔입니다
            strokeOpacity: 0, // 선의 불투명도입니다 0에서 1 사이값이며 0에 가까울수록 투명합니다
            strokeStyle: 'solid', // 선의 스타일입니다
            fillColor: '#00a0e9', // 채우기 색깔입니다
            fillOpacity: 0.2 // 채우기 불투명도입니다
          })
          var radius = Math.round(this.circle.getRadius()) // 원의 반경 정보를 얻어옵니다
          this.range = radius
          await this.getDataCircle()

          this.centerPosition = null // 중심 좌표를 초기화 합니다
          this.drawingCircle.setMap(null) // 그려지고 있는 원, 선, 커스텀오버레이를 지도에서 제거합니다
          this.drawingLine.setMap(null)
          this.drawingOverlay.setMap(null)
        }
      }
    },
    CircleMoveClick(mouseEvent) {
      if (this.drawingFlag) {
        // 마우스무브 이벤트가 발생했을 때 원을 그리고있는 상태이면
        this.hashover = true
        var mousePosition = mouseEvent.latLng // 마우스 커서의 현재 위치를 얻어옵니다
        var linePath = [this.centerPosition, mousePosition] // 그려지고 있는 선을 표시할 좌표 배열입니다. 클릭한 중심좌표와 마우스커서의 위치로 설정합니다
        this.drawingLine.setPath(linePath) // 그려지고 있는 선을 표시할 선 객체에 좌표 배열을 설정합니다
        var length = this.drawingLine.getLength() // 원의 반지름을 선 객체를 이용해서 얻어옵니다
        if (length > 0) {
          var circleOptions = {
            // 그려지고 있는 원의 중심좌표와 반지름입니다
            center: this.centerPosition,
            radius: length
          }
          this.drawingCircle.setOptions(circleOptions) // 그려지고 있는 원의 옵션을 설정합니다
          var radius = Math.round(this.drawingCircle.getRadius()) // 반경 정보를 표시할 커스텀오버레이의 내용입니다
          let content =
            '<div class="overlay" style="background-color:white">반경 <span class="number">' +
            radius +
            '</span>m</div>'
          this.drawingOverlay.setPosition(mousePosition) // 반경 정보를 표시할 커스텀 오버레이의 좌표를 마우스커서 위치로 설정합니다
          this.drawingOverlay.setContent(content) // 반경 정보를 표시할 커스텀 오버레이의 표시할 내용을 설정합니다
          this.drawingCircle.setMap(this.map) // 그려지고 있는 원을 지도에 표시합니다
          this.drawingLine.setMap(this.map) // 그려지고 있는 선을 지도에 표시합니다
          this.drawingOverlay.setMap(this.map) // 그려지고 있는 원의 반경정보 커스텀 오버레이를 지도에 표시합니다
        } else {
          this.drawingCircle.setMap(null)
          this.drawingLine.setMap(null)
          this.drawingOverlay.setMap(null)
        }
      }
    },
    // 원 html 만들기
    getOverlay(html, rClickPosition) {
      let radiusOverlay = new kakao.maps.CustomOverlay({
        // 반경정보를 표시할 커스텀 오버레이를 생성합니다
        content: html, // 표시할 내용입니다
        position: rClickPosition, // 표시할 위치입니다. 클릭한 위치로 설정합니다
        xAnchor: 0.5,
        yAnchor: 1,
        zIndex: 1
      })
      return radiusOverlay
    },

    // ------------------------------------------------
    // 표 만들기 시간별 유동인구
    // ------------------------------------------------
    async makeOverlay2(Name) {
      this.name = Name
      if (this.name == null) return
      // === 요소 삭제했다가 추가 ==
      var header = document.getElementById('horizontalbarChart') // 제거하고자 하는 엘리먼트
      header.parentNode.removeChild(header)
      var node = document.createElement('canvas')
      var para = document.getElementById('graph-info').appendChild(node)
      para.id = 'horizontalbarChart'
      para.style = 'height: 190px; width: 350px;'

      // =======================
      await axios
        .get('/population/getPopulationByTime/' + this.name)
        .then(res => {
          this.result = res.data.pbt
          this.road = this.result.f
          this.point = res.data.point
        })
        .finally(() => {
          var ctx = document
            .getElementById('horizontalbarChart')
            .getContext('2d')
          var horizontalbarChart = new Chart(ctx, {
            type: 'line',
            data: {
              labels: [
                '24~06시',
                '06~11시',
                '11~14시',
                '14~17시',
                '17~21시',
                '21~24시'
              ],
              datasets: [
                {
                  label: '정보',
                  fill: false,
                  borderColor: 'navy',
                  data: [
                    this.result.j,
                    this.result.k,
                    this.result.l,
                    this.result.m,
                    this.result.n,
                    this.result.o
                  ]
                }
              ]
            },
            options: {
              responsive: false,
              maintainAspectRatio: false,
              scales: {
                yAxes: [
                  {
                    ticks: {
                      beginAtZero: false
                    },
                    gridLines: {
                      display: true
                    }
                  }
                ],
                xAxes: [
                  {
                    gridLines: {
                      display: true
                    }
                  }
                ]
              }
            }
          })
        })
    },
    // ------------------------------------------------
    // 표 만들기 상권 변화 지수
    // ------------------------------------------------
    async makeOverlay3(Name) {
      this.name = Name
      // === 요소 삭제했다가 추가 ==
      var header = document.getElementById('horizontalbarChart') // 제거하고자 하는 엘리먼트
      header.parentNode.removeChild(header)
      var node = document.createElement('canvas')
      var para = document.getElementById('graph-info').appendChild(node)
      para.id = 'horizontalbarChart'
      para.style = 'height: 190px; width: 350px;'
      // =======================
      axios
        .get('/change/getHistory/' + this.name)
        .then(res => {
          this.result = res.data.cblist
          this.road = this.result[0].d
          this.point = res.data.point
        })
        .finally(() => {
          var ctx = document
            .getElementById('horizontalbarChart')
            .getContext('2d')
          var horizontalbarChart = new Chart(ctx, {
            type: 'bar',
            data: {
              labels: ['2014', '2015', '2016', '2017', '2018', '2019'],
              datasets: [
                {
                  label: '운영 영업 개월 평균',
                  backgroundColor: '#74ddf7',
                  data: [
                    this.result[0].g,
                    this.result[1].g,
                    this.result[2].g,
                    this.result[3].g,
                    this.result[4].g,
                    this.result[5].g
                  ]
                },
                {
                  label: '폐업 영업 개월 평균',
                  backgroundColor: '#ff6390',
                  data: [
                    this.result[0].h,
                    this.result[1].h,
                    this.result[2].h,
                    this.result[3].h,
                    this.result[4].h,
                    this.result[5].h
                  ]
                }
              ]
            },
            options: {
              responsive: false,
              maintainAspectRatio: false,
              scales: {
                yAxes: [
                  {
                    ticks: {
                      beginAtZero: true
                    },
                    gridLines: {
                      display: true
                    }
                  }
                ],
                xAxes: [
                  {
                    gridLines: {
                      display: true
                    }
                  }
                ]
              }
            }
          })
        })
    },
    // -----------------------------------------------
    // 연령별 매출 정보
    // ------------------------------------------------
    async makeOverlay4(Name) {
      this.name = Name
      // === 요소 삭제했다가 추가 ==
      var header = document.getElementById('horizontalbarChart') // 제거하고자 하는 엘리먼트
      header.parentNode.removeChild(header)
      var node = document.createElement('canvas')
      var para = document.getElementById('graph-info').appendChild(node)
      para.id = 'horizontalbarChart'
      para.style = 'height: 190px; width: 350px;'
      // =======================

      let sumOf10 = 0
      let sumOf20 = 0
      let sumOf30 = 0
      let sumOf40 = 0
      let sumOf50 = 0
      let sumOf60 = 0

      axios
        .get('/sales/' + this.name)
        .then(res => {
          this.result = res.data
          this.road = res.data[0].d
          this.point = res.data.point

          for (let index = 0; index < this.result.length; index++) {
            sumOf10 += Number(this.result[index].z)
            sumOf20 += Number(this.result[index].aa)
            sumOf30 += Number(this.result[index].ab)
            sumOf40 += Number(this.result[index].ac)
            sumOf50 += Number(this.result[index].ad)
            sumOf60 += Number(this.result[index].ae)
          }

          sumOf10 /= 100000000
          sumOf20 /= 100000000
          sumOf30 /= 100000000
          sumOf40 /= 100000000
          sumOf50 /= 100000000
          sumOf60 /= 100000000
        })
        .finally(() => {
          var ctx = document
            .getElementById('horizontalbarChart')
            .getContext('2d')
          var horizontalbarChart = new Chart(ctx, {
            type: 'horizontalBar',
            data: {
              labels: ['10대', '20대', '30대', '40대', '50대', '60대 이상'],
              datasets: [
                {
                  label: '전체',
                  backgroundColor: '#365673',
                  data: [
                    sumOf10.toFixed(2),
                    sumOf20.toFixed(2),
                    sumOf30.toFixed(2),
                    sumOf40.toFixed(2),
                    sumOf50.toFixed(2),
                    sumOf60.toFixed(2)
                  ]
                }
              ]
            },
            options: {
              responsive: false,
              maintainAspectRatio: false,
              scales: {
                yAxes: [
                  {
                    ticks: {
                      beginAtZero: true
                    },
                    gridLines: {
                      display: true
                    }
                  }
                ],
                xAxes: [
                  {
                    gridLines: {
                      display: true
                    }
                  }
                ]
              }
            }
          })
        })
    },
    // ------------------------------------------------
    // 표 만들기 연령별 유동인구
    // ------------------------------------------------
    async makeOverlay5(Name) {
      this.name = Name
      // === 요소 삭제했다가 추가 ==
      var header = document.getElementById('horizontalbarChart') // 제거하고자 하는 엘리먼트
      header.parentNode.removeChild(header)
      var node = document.createElement('canvas')
      var para = document.getElementById('graph-info').appendChild(node)
      para.id = 'horizontalbarChart'
      para.style = 'height: 190px; width: 350px;'
      // =======================
      axios
        .get('/population/getPopulationByLocation/' + this.name)
        .then(res => {
          this.result = res.data.pbl
          this.road = this.result.f
          this.point = res.data.point
        })
        .finally(() => {
          var ctx = document
            .getElementById('horizontalbarChart')
            .getContext('2d')
          var horizontalbarChart = new Chart(ctx, {
            type: 'bar',
            data: {
              labels: ['10대', '20대', '30대', '40대', '50대', '60대 이상'],
              datasets: [
                {
                  label: '전체',
                  backgroundColor: '#365673',
                  data: [
                    this.result.j,
                    this.result.k,
                    this.result.l,
                    this.result.m,
                    this.result.n,
                    this.result.o
                  ]
                },
                {
                  label: '남자',
                  backgroundColor: '#74ddf7',
                  data: [
                    this.result.p,
                    this.result.q,
                    this.result.r,
                    this.result.s,
                    this.result.t,
                    this.result.u
                  ]
                },
                {
                  label: '여자',
                  backgroundColor: '#ff6390',
                  data: [
                    this.result.v,
                    this.result.w,
                    this.result.x,
                    this.result.y,
                    this.result.z,
                    this.result.aa
                  ]
                }
              ]
            },
            options: {
              responsive: false,
              maintainAspectRatio: false,
              scales: {
                yAxes: [
                  {
                    ticks: {
                      beginAtZero: true
                    },
                    gridLines: {
                      display: true
                    }
                  }
                ],
                xAxes: [
                  {
                    gridLines: {
                      display: true
                    }
                  }
                ]
              }
            }
          })
        })
    },
    // ------------------------------------------------
    // 표 만들기 요일별 유동인구
    // ------------------------------------------------
    async makeOverlay6(Name) {
      this.name = Name
      // === 요소 삭제했다가 추가 ==
      var header = document.getElementById('horizontalbarChart') // 제거하고자 하는 엘리먼트
      header.parentNode.removeChild(header)
      var node = document.createElement('canvas')
      var para = document.getElementById('graph-info').appendChild(node)
      para.id = 'horizontalbarChart'
      para.style = 'height: 190px; width: 350px;'
      // =======================
      axios
        .get('/population/getPopulationByTime/' + this.name)
        .then(res => {
          this.result = res.data.pbt
          this.road = this.result.f
          this.point = res.data.point
        })
        .finally(() => {
          var ctx = document
            .getElementById('horizontalbarChart')
            .getContext('2d')
          var horizontalbarChart = new Chart(ctx, {
            type: 'radar',
            data: {
              labels: [
                '월요일',
                '화요일',
                '수요일',
                '목요일',
                '금요일',
                '토요일',
                '일요일'
              ],
              datasets: [
                {
                  label: '정보',
                  fill: false,
                  borderColor: 'orange',
                  data: [
                    this.result.p,
                    this.result.q,
                    this.result.r,
                    this.result.s,
                    this.result.t,
                    this.result.u,
                    this.result.v
                  ]
                }
              ]
            },
            options: {
              responsive: false,
              maintainAspectRatio: false,
              scales: {
                yAxes: [
                  {
                    ticks: {
                      beginAtZero: false
                    },
                    gridLines: {
                      display: true
                    }
                  }
                ],
                xAxes: [
                  {
                    gridLines: {
                      display: true
                    }
                  }
                ]
              }
            }
          })
        })
    },
    // ------------------------------------------------
    // 표 만들기 시간별 매출정보
    // ------------------------------------------------
    async makeOverlay7(Name) {
      this.name = Name
      // === 요소 삭제했다가 추가 ==
      var header = document.getElementById('horizontalbarChart') // 제거하고자 하는 엘리먼트
      header.parentNode.removeChild(header)
      var node = document.createElement('canvas')
      var para = document.getElementById('graph-info').appendChild(node)
      para.id = 'horizontalbarChart'
      para.style = 'height: 190px; width: 350px;'
      // =======================
      let sum1 = 0
      let sum2 = 0
      let sum3 = 0
      let sum4 = 0
      let sum5 = 0
      let sum6 = 0
      axios
        .get('/sales/' + this.name)
        .then(res => {
          this.result = res.data
          this.road = res.data[0].d
          this.point = res.data.point

          for (let index = 0; index < this.result.length; index++) {
            sum1 += Number(this.result[index].r)
            sum2 += Number(this.result[index].s)
            sum3 += Number(this.result[index].t)
            sum4 += Number(this.result[index].u)
            sum5 += Number(this.result[index].v)
            sum6 += Number(this.result[index].w)
          }

          sum1 /= 100000000
          sum2 /= 100000000
          sum3 /= 100000000
          sum4 /= 100000000
          sum5 /= 100000000
          sum6 /= 100000000
        })
        .finally(() => {
          var ctx = document
            .getElementById('horizontalbarChart')
            .getContext('2d')
          var horizontalbarChart = new Chart(ctx, {
            type: 'line',
            data: {
              labels: [
                '24~06시',
                '06~11시',
                '11~14시',
                '14~17시',
                '17~21시',
                '21~24시'
              ],
              datasets: [
                {
                  label: '정보',
                  fill: false,
                  borderColor: 'orange',
                  data: [
                    sum1.toFixed(2),
                    sum2.toFixed(2),
                    sum3.toFixed(2),
                    sum4.toFixed(2),
                    sum5.toFixed(2),
                    sum6.toFixed(2)
                  ]
                }
              ]
            },
            options: {
              responsive: false,
              maintainAspectRatio: false,
              scales: {
                yAxes: [
                  {
                    ticks: {
                      beginAtZero: true
                    },
                    gridLines: {
                      display: true
                    }
                  }
                ],
                xAxes: [
                  {
                    gridLines: {
                      display: true
                    }
                  }
                ]
              }
            }
          })
        })
    },
    // ------------------------------------------------
    // 표 만들기 요일별 매출 정보
    // ------------------------------------------------
    async makeOverlay8(Name) {
      this.name = Name
      // === 요소 삭제했다가 추가 ==
      var header = document.getElementById('horizontalbarChart') // 제거하고자 하는 엘리먼트
      header.parentNode.removeChild(header)
      var node = document.createElement('canvas')
      var para = document.getElementById('graph-info').appendChild(node)
      para.id = 'horizontalbarChart'
      para.style = 'height: 190px; width: 350px;'
      // =======================

      let sum1 = 0
      let sum2 = 0
      let sum3 = 0
      let sum4 = 0
      let sum5 = 0
      let sum6 = 0
      let sum7 = 0

      axios
        .get('/sales/' + this.name)
        .then(res => {
          this.result = res.data
          this.road = res.data[0].d
          this.point = res.data.point
          for (let index = 0; index < this.result.length; index++) {
            sum1 += Number(this.result[index].k)
            sum2 += Number(this.result[index].l)
            sum3 += Number(this.result[index].m)
            sum4 += Number(this.result[index].n)
            sum5 += Number(this.result[index].o)
            sum6 += Number(this.result[index].p)
            sum7 += Number(this.result[index].q)
          }

          sum1 /= 100000000
          sum2 /= 100000000
          sum3 /= 100000000
          sum4 /= 100000000
          sum5 /= 100000000
          sum6 /= 100000000
          sum7 /= 100000000
        })
        .finally(() => {
          var ctx = document
            .getElementById('horizontalbarChart')
            .getContext('2d')
          var horizontalbarChart = new Chart(ctx, {
            type: 'polarArea',
            data: {
              labels: [
                '월요일',
                '화요일',
                '수요일',
                '목요일',
                '금요일',
                '토요일',
                '일요일'
              ],
              datasets: [
                {
                  fill: true,
                  data: [
                    sum1.toFixed(2),
                    sum2.toFixed(2),
                    sum3.toFixed(2),
                    sum4.toFixed(2),
                    sum5.toFixed(2),
                    sum6.toFixed(2),
                    sum7.toFixed(2)
                  ],
                  backgroundColor: [
                    '#eb4034',
                    '#ffe373',
                    '#89ff45',
                    '#73ffde',
                    '#5ca0ff',
                    '#d152ff',
                    '#ff63d0'
                  ]
                }
              ]
            },
            options: {
              responsive: true,
              maintainAspectRatio: true
            }
          })
        })
    },
    async getBoxHTML() {
      let 소매 = this.CountInfo.소매
      if (소매 === undefined) {
        소매 = 0
      }
      let 학문교육 = this.CountInfo.학문교육
      if (학문교육 === undefined) {
        학문교육 = 0
      }
      let 숙박 = this.CountInfo.숙박
      if (숙박 === undefined) {
        숙박 = 0
      }
      let 생활서비스 = this.CountInfo.생활서비스
      if (생활서비스 === undefined) {
        생활서비스 = 0
      }
      let 음식 = this.CountInfo.음식
      if (음식 === undefined) {
        음식 = 0
      }
      let 부동산 = this.CountInfo.부동산
      if (부동산 === undefined) {
        부동산 = 0
      }
      let 의료 = this.CountInfo.의료
      if (의료 === undefined) {
        의료 = 0
      }
      let 관광여가오락 = this.CountInfo.관광여가오락
      if (관광여가오락 === undefined) {
        관광여가오락 = 0
      }
      var content =
        '<div class="overlaybox"' +
        '    style="position:relative;background:#023359;' +
        '      width:470px; height:250px;border-radius:10px;">' +
        '<div class="v-sheet theme--light elevation-14" style="position:relative;top:50%;transform:translateY(-50%);width:450px;height:230px;margin:auto;display:block;text-align:center;" id="mapSheet">'
      content +=
        '    <div style="padding-top:10px;display:flex;justify-content:space-around;">'
      content += '    <div style="width:80px;height:93px;">'
      content +=
        '        <img style="margin:auto;width:80px;height:80px;display:block;" src="/img/store.5c0694f4.png">' +
        '<span style="width:100%;">' +
        소매
      content += '</span></div>'
      content += '    <div style="width:80px;height:93px;">'
      content +=
        '        <img style="margin:auto;width:80px;height:80px;display:block;" src="/img/school.10da6a9d.png">' +
        '<span style="width:100%;">' +
        학문교육
      content += '</span></div>'
      content += '    <div style="width:80px;height:93px;">'
      content +=
        '        <img style="margin:auto;width:80px;height:80px;display:block;" src="/img/hotel.531ff90a.png">' +
        '<span style="width:100%;">' +
        숙박
      content += '</span></div>'
      content += '    <div style="width:80px;height:93px;">'
      content +=
        '        <img style="margin:auto;width:80px;height:80px;display:block;" src="/img/service.b9012c84.png">' +
        '<span style="width:100%;">' +
        생활서비스
      content += '</span></div>'
      content += '</div>'
      content +=
        '    <div style="display:flex;justify-content:space-around;padding-top:10px;">'
      content += '    <div style="width:80px;height:93px;">'
      content +=
        '        <img style="margin:auto;width:80px;height:80px;display:block;" src="/img/food.40e9a510.png">' +
        '<span style="width:100%;">' +
        음식
      content += '</span></div>'
      content += '    <div style="width:80px;height:93px;">'
      content +=
        '        <img style="margin:auto;width:80px;height:80px;display:block;" src="/img/estate.19bdddbd.png">' +
        '<span style="width:100%;">' +
        부동산
      content += '</span></div>'
      content += '    <div style="width:80px;height:93px;">'
      content +=
        '        <img style="margin:auto;width:80px;height:80px;display:block;" src="/img/hospital.85ccdf18.png">' +
        '<span style="width:100%;">' +
        의료
      content += '</span></div>'
      content += '    <div style="width:80px;height:93px;">'
      content +=
        '        <img style="margin:auto;width:80px;height:80px;display:block;" src="/img/game.b8da8874.png">' +
        '<span style="width:100%;">' +
        관광여가오락
      content += '</span></div>'
      content += '</div>'
      content += '</div>'
      content += '</div>'

      return content
    }, // 범위내 상가정보 받아오는 매서드
    async getDataCircle() {
      this.loadingStatus = true
      axios
        .get(
          '/storecountByxy/' +
            this.searchX +
            '/' +
            this.searchY +
            '/' +
            this.range
        )
        .then(res => {
          var jsonlarge = res.data
          this.CountInfo.소매 = jsonlarge.소매
          this.CountInfo.학문교육 = jsonlarge.학문교육
          this.CountInfo.숙박 = jsonlarge.숙박
          this.CountInfo.생활서비스 = jsonlarge.생활서비스
          this.CountInfo.음식 = jsonlarge.음식
          this.CountInfo.부동산 = jsonlarge.부동산
          this.CountInfo.의료 = jsonlarge.의료
          this.CountInfo.관광여가오락 = jsonlarge.관광여가오락
        })
        .catch(err => alert(err, '검색어를 확인해주세요'))
        .finally(() => {
          this.getBoxHTML()
            .then(res => {
              this.html = res
              this.radiusOverlay = this.getOverlay(
                this.html,
                this.rClickPosition
              )
              this.radiusOverlay.setMap(this.map) // 반경 정보 커스텀 오버레이를 지도에 표시합니다
              this.circle.setMap(this.map) // 원을 지도에 표시합니다
              this.polyline.setMap(this.map) // 선을 지도에 표시합니다
            })
            .finally(() => {
              let vm = this
              vm.loadingStatus = false
              vm.hashover = false
              vm.$store.state.mode = 0
              vm.drawingFlag = false // 그리기 상태를 그리고 있지 않는 상태로 바꿉니다
              vm.loadingStatus = false
              vm.hashover = false
              this.radiusObj = {
                // 배열에 담을 객체입니다. 원, 선, 커스텀오버레이 객체를 가지고 있습니다
                polyline: vm.polyline,
                circle: vm.circle,
                overlay: vm.radiusOverlay
              }
              this.circles.push(vm.radiusObj) // 이 배열을 이용해서 "모두 지우기" 버튼을 클릭했을 때 지도에 그려진 원, 선, 커스텀오버레이들을 지웁니다
            })
        })
    },
    getData3() {
      axios
        .get('/change/getHistory' + '/' + this.name)
        .then(res => {
          var jsonlarge = res.data
          this.CountInfo.소매 = jsonlarge.소매
          this.CountInfo.학문교육 = jsonlarge.학문교육
          this.CountInfo.숙박 = jsonlarge.숙박
          this.CountInfo.생활서비스 = jsonlarge.생활서비스
          this.CountInfo.음식 = jsonlarge.음식
          this.CountInfo.부동산 = jsonlarge.부동산
          this.CountInfo.의료 = jsonlarge.의료
          this.CountInfo.관광여가오락 = jsonlarge.관광여가오락
        })
        .catch(err => alert(err, '검색어를 확인해주세요'))
    },
    removeCircles() {
      for (var i = 0; i < this.circles.length; i++) {
        this.circles[i].circle.setMap(null)
        this.circles[i].polyline.setMap(null)
        this.circles[i].overlay.setMap(null)
      }
      this.circles = []
    }
  }
}
</script>
<style scoped lang="scss">
.map {
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  margin: auto;
  min-width: 50%;
  min-height: 50%;
  z-index: 0;
}
.mapContainer {
  position: absolute;
  top: -25%;
  left: -50%;
  width: 200%;
  height: 125%;
}
.ssearchbox {
  position: fixed;
  z-index: 1;
  top: 100px;
  left: 100px;
}
.a {
  height: 50px;
  width: 380px;
  background: white;
  border: 1px solid coral;
}
input {
  font-size: 18px;
  width: 360px;
  float: left;
  border: 0px;
  outline: none;
}
button {
  color: white;
  width: 70px;
  height: 100%;
  background: coral;
  float: right;
  border: 0px;
  outline: none;
}
.searchBox {
  display: inline-block;
  z-index: 2;
  position: fixed;
  width: 370px;
  height: 180px;
  top: 100px;
  left: 50px;
  border-radius: 3px;
}
.bt {
  display: inline-block;
  z-index: 2;
  position: fixed;
  width: 380px;
  height: 20px;
  top: 396px;
  left: 360px;
  border-radius: 3px;
}
.area {
  width: 30px;
  height: 30px;
  position: absolute;
  background-color: #fff;
  border: 1px solid #888;
  border-radius: 3px;
  font-size: 12px;
  top: -5px;
  left: 50%;
  padding: 2px;
}
:-ms-input-placeholder {
  color: tomato;
}
.ssss {
  width: 460px;
  height: 260px;
  z-index: 2;
  position: fixed;
  top: 400px;
  left: 50px;
  border-radius: 3px;
}
.addbt {
  display: inline-block;
  z-index: 2;
  position: fixed;
  width: 370px;
  height: 40px;
  top: 350px;
  left: 49px;
  border-radius: 3px;
}
.addinput {
  color: tomato;
}
// element.style {
//   background: white;
// }
#graph-info {
  width: 360px !important;
  height: 190px !important;
  background: white !important;
  border-radius: 3px;
}
</style>
