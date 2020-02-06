<template>
  <div class="mapContainer">
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
    <condition></condition>
    <div class="map" id="map"></div>
  </div>
  <!-- <div class="mapContainer">
   <div  class="a ssearchbox" >
    <input v-model="name" type="text" placeholder="검색어입력">
    <button  v-on:click="serch">검색</button>
    <textarea style="width:300px;" id="result" v-model="message"></textarea>
  </div>
    <div class="map" id="map" style="width:500px;height:400px;"></div>
  </div>-->
</template>

<script>
import Dong from '../../assets/json/New.json'
import condition from '@/components/bizmap/kakaomap/SearchCondition.vue'
export default {
  data: () => {
    return {
      polygon: null,
      geocoder: null,
      map: null,
      name: null,
      clusterer: null,
      marker: null,
      infowindow: null,
      message: null,
      ifchanege: null,
      addListener: null,
      mode: null, // 여기 반경 하고 싶으면 asdf 로
      drawingFlag: false, // 원이 그려지고 있는 상태를
      centerPosition: false, // 원의 중심좌표
      drawingCircle: false, // 그려지고 있는 원을 표시할 원 객체
      drawingLine: false, // 그려지고 있는 원의 반지름을 표시할 선 객체
      drawingOverlay: false, // 그려지고 있는 원의 반경을 표시할 커스텀오버레이
      circles: []
    }
  },
  mounted () {
    let data = Dong.features // 좌표 저장할 배열
    let coordinates = [] // 행정 구 이름
    let name = '멀티캠퍼스'
    let Message = ''
    let pp = null
    this.message = Message
    var container = document.getElementById('map')

    var options = {
      center: new kakao.maps.LatLng(37.505691, 127.0298106),
      level: 3
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
    //let Mode = this.$store.state.mode
    // --마커 생성--------------------------------------------------------------------
    this.marker = new kakao.maps.Marker({
      // 마커생성
      map: this.map,
      position: new kakao.maps.LatLng(37.505691, 127.0298106) // 최초 표시되는 마커의 위치
    })

    // --인포윈도우 생성---------------------------------------------------------------
    this.infowindow = new kakao.maps.InfoWindow({
      content: 'name'
    })
    // -------------------------------------------------------------------------------------

    // 반경 그리는 이벤트 시작-------------------------------------------------------------------
    kakao.maps.event.addListener(this.map, 'click', function(mouseEvent) {
      vm.CircleMouseClick(mouseEvent)
    }) // 지도에 클릭 이벤트를 등록
    kakao.maps.event.addListener(this.map, 'mousemove', function(mouseEvent) {
      vm.CircleMoveClick(mouseEvent)
    }) // 지도에 무브 이벤트를 등록
    kakao.maps.event.addListener(this.map, 'rightclick', function(mouseEvent) {
      vm.RightMouseClick(mouseEvent)
    })
    // -------------------------------------------------------------------------------------


    // --폴리곤 생성---------------------------------------------------------------------------------------
    for (var i = 0, len = data.length; i < len; i++) {
      // 동의 경계면 좌표를 받아서 다각형 생성 함수에 전달
      this.coordinates = data[i].geometry.coordinates
      name = data[i].properties.ADM_DR_NM
      displayArea(
        vm.pp,
        this.map,
        this.coordinates[0][0],
        name,
        this.coordinates[0][0].length
      )
    }

    function displayArea(polygon, Mmap, coordinates, name, length) {
      // 다각형을 생성 ,이벤트를 등록
      let mode = vm.$store.state.mode
      let path = []
      for (let i = 0; i < length; i++) {
        // 좌표의 개수만큼 반복문을 돌며 경계 바운더리 생성(path생성)
        path.push(new kakao.maps.LatLng(coordinates[i][1], coordinates[i][0]))
      }
      polygon = new kakao.maps.Polygon({
        // 생성된 path를 이용하여 폴리곤(다각형) 생성
        map: Mmap, // 다각형을 표시할 지도 객체
        path: path,
        strokeWeight: 3, // 선두깨
        strokeColor: '#004c80', // 선색
        strokeOpacity: 0.6, // 선 투명도
        fillColor: '#fff',
        fillOpacity: 0.4
      })

      kakao.maps.event.addListener(polygon, 'mouseover', () => {
        // 각 폴리곤에 마우스 오버 이벤트 등록
        polygon.setOptions({
          fillColor: '#09f'
        })
      })
      kakao.maps.event.addListener(polygon, 'mouseout', () => {
        //  각 폴리곤에 마우스 아웃 이벤트 등록
        polygon.setOptions({
          fillColor: '#fff'
        })
      })
      kakao.maps.event.addListener(polygon, 'click', () => {
        if (vm.$store.state.mode === 0) {
          //  각 폴리곤에 마우스 아웃 이벤트 등록
          let Name = name
          let Marker = vm.marker
          let InfoWindow = vm.infowindow
          vm.geocoder.addressSearch(Name, function(result, status) {
            // 정상적으로 검색이 완료되면
            if (status === kakao.maps.services.Status.OK) {
              console.log(Name)
              var coords = new kakao.maps.LatLng(result[0].y, result[0].x) // 결과값으로 받은 위치를 마커의 위치로 적용
              Marker.setPosition(coords)
              InfoWindow.close()
              InfoWindow.setContent(Name)
              InfoWindow.open(Map, Marker)
              Map.setCenter(coords)
            }
          })
        }
      })
    }
    this.ifchanege = this.map.getCenter()
    // var resultDiv = document.getElementById('result')
    // resultDiv.innerHTML = Message
  },
  watch: {
    ifchanege: function(newVal, oldVal) {}
  },
  components: {
    condition
  },
  methods: {
    panTo() {
      // 지도 중심을 부드럽게 이동시킵니다
      var moveLatLon = new kakao.maps.LatLng(33.45058, 126.574942) // 이동할 위도 경도 위치를 생성합니다
      this.map.panTo(moveLatLon)
    },
    // -- 동이름으로 검색-----------------------------------------------------------------------------
    serch(name) {
      let Ifchange = this.ifchanege
      let Name = this.name
      let Map = this.map
      let Marker = this.marker
      let InfoWindow = this.infowindow
      this.geocoder.addressSearch(this.name, function(result, status) {
        // 정상적으로 검색이 완료되면
        if (status === kakao.maps.services.Status.OK) {
          var coords = new kakao.maps.LatLng(result[0].y, result[0].x) // 결과값으로 받은 위치를 마커의 위치로 적용
          Marker.setPosition(coords)
          InfoWindow.close() // 전에 있던 인포위도우 클로즈
          InfoWindow.setContent(Name) //  인포위도우 내용 세팅
          InfoWindow.open(Map, Marker) // 마커위에 인포위도우 열림
          Map.setCenter(coords) // 새로 세팅된 센터 값으로 맵 세팅
        }
      })
    },
    CircleMouseClick(mouseEvent) {
      // 지도에 클릭 이벤트를 등록

      this.removeCircles()
      if (this.$store.state.mode === 1) {
        if (!this.drawingFlag) {
          // 클릭 이벤트가 발생했을 때 원을 그리고 있는 상태가 아니면 중심좌표를 클릭한 지점으로 설정
          this.drawingFlag = true
          this.centerPosition = mouseEvent.latLng // 원이 그려질 중심좌표를 클릭한 위치로 설정합니다
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
      }
    },
    CircleMoveClick (mouseEvent) {
      if (this.drawingFlag) {
        // 마우스무브 이벤트가 발생했을 때 원을 그리고있는 상태이면
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
    RightMouseClick (mouseEvent) {
      if (this.drawingFlag) {
        var rClickPosition = mouseEvent.latLng // 마우스로 오른쪽 클릭한 위치입니다
        var polyline = new kakao.maps.Polyline({
          // 원의 반경을 표시할 선 객체를 생성합니다
          path: [this.centerPosition, rClickPosition], // 선을 구성하는 좌표 배열입니다. 원의 중심좌표와 클릭한 위치로 설정합니다
          strokeWeight: 1, // 선의 두께 입니다
          strokeColor: '#00a0e9', // 선의 색깔입니다
          strokeOpacity: 0, // 선의 불투명도입니다 0에서 1 사이값이며 0에 가까울수록 투명합니다
          strokeStyle: 'solid' // 선의 스타일입니다
        })
        var circle = new kakao.maps.Circle({
          // 원 객체를 생성합니다
          center: this.centerPosition, // 원의 중심좌표입니다
          radius: polyline.getLength(), // 원의 반지름입니다 m 단위 이며 선 객체를 이용해서 얻어옵니다
          strokeWeight: 0, // 선의 두께입니다
          strokeColor: '#00a0e9', // 선의 색깔입니다
          strokeOpacity: 0, // 선의 불투명도입니다 0에서 1 사이값이며 0에 가까울수록 투명합니다
          strokeStyle: 'solid', // 선의 스타일입니다
          fillColor: '#00a0e9', // 채우기 색깔입니다
          fillOpacity: 0.2 // 채우기 불투명도입니다
        })
        var radius = Math.round(circle.getRadius()) // 원의 반경 정보를 얻어옵니다
        var html = this.getBoxHTML(radius) // 커스텀 오버레이에 표시할 반경 정보입니다
        var radiusOverlay = new kakao.maps.CustomOverlay({
          // 반경정보를 표시할 커스텀 오버레이를 생성합니다
          content: html, // 표시할 내용입니다
          position: rClickPosition, // 표시할 위치입니다. 클릭한 위치로 설정합니다
          xAnchor: 0.5,
          yAnchor: 1,
          zIndex: 1
        })

        circle.setMap(this.map) // 원을 지도에 표시합니다
        polyline.setMap(this.map) // 선을 지도에 표시합니다
        radiusOverlay.setMap(this.map) // 반경 정보 커스텀 오버레이를 지도에 표시합니다
        let radiusObj = {
          // 배열에 담을 객체입니다. 원, 선, 커스텀오버레이 객체를 가지고 있습니다
          polyline: polyline,
          circle: circle,
          overlay: radiusOverlay
        }
        this.circles.push(radiusObj) // 이 배열을 이용해서 "모두 지우기" 버튼을 클릭했을 때 지도에 그려진 원, 선, 커스텀오버레이들을 지웁니다
        this.drawingFlag = false // 그리기 상태를 그리고 있지 않는 상태로 바꿉니다
        this.centerPosition = null // 중심 좌표를 초기화 합니다
        this.drawingCircle.setMap(null) // 그려지고 있는 원, 선, 커스텀오버레이를 지도에서 제거합니다
        this.drawingLine.setMap(null)
        this.drawingOverlay.setMap(null)
      }
      this.$store.state.mode = 0
    },
    getBoxHTML (distance) {
      var content =
        '<div class="v-sheet theme--light elevation-14" style="margin-leftauto;display:block;text-align:center;" id="mapSheet">'
      content += '    <div style="display:flex;justify-content:space-around;">'
      content += '    <div style="width:70px;height:70px;">'
      content +=
        '        <img style="width:40px;height:40px;display:block;" src="/img/logo.82b9c7a5.png">' +
        '<span style="width:100%;">' +
        20
      content += '</span></div>'
      content += '</div>'
      content += '</div>'
      return content
    },
    removeCircles() {
      for (var i = 0; i < this.circles.length; i++) {
        this.circles[i].circle.setMap(null)
        this.circles[i].polyline.setMap(null)
        this.circles[i].overlay.setMap(null)
      }
      this.circles = []
    }

    // -----------------------------------------------------------------------------------------
    // 뱐경 그리기 함수 시작-----------------------------------------------------------------------------------------
    // makeCircle: function() {
    //   // if (this.$store.state.mode === 1)
    //   let drawingFlag = false // 원이 그려지고 있는 상태를
    //   let centerPosition // 원의 중심좌표
    //   let drawingCircle // 그려지고 있는 원을 표시할 원 객체
    //   let drawingLine // 그려지고 있는 원의 반지름을 표시할 선 객체
    //   let drawingOverlay // 그려지고 있는 원의 반경을 표시할 커스텀오버레이

    //   let circles = []

    //   let vm = this
    //   kakao.maps.event.addListener(this.map, 'click', function(mouseEvent) {
    //     // 지도에 클릭 이벤트를 등록
    //     removeCircles()
    //     if (!vm.drawingFlag) {
    //       // 클릭 이벤트가 발생했을 때 원을 그리고 있는 상태가 아니면 중심좌표를 클릭한 지점으로 설정
    //       vm.drawingFlag = true
    //       vm.centerPosition = mouseEvent.latLng // 원이 그려질 중심좌표를 클릭한 위치로 설정합니다
    //       if (!vm.drawingLine) {
    //         // 그려지고 있는 원의 반경을 표시할 선 객체를 생성합니다
    //         vm.drawingLine = new kakao.maps.Polyline({
    //           strokeWeight: 3, // 선의 두께
    //           strokeColor: '#00a0e9', // 선의 색깔
    //           strokeOpacity: 0, // 선의 불투명도
    //           strokeStyle: 'solid' // 선의 스타일
    //         })
    //       }
    //       if (!vm.drawingCircle) {
    //         // 그려지고 있는 원을 표시할 원 객체를 생성합니다
    //         vm.drawingCircle = new kakao.maps.Circle({
    //           strokeWeight: 1,
    //           strokeColor: '#00a0e9',
    //           strokeOpacity: 0,
    //           strokeStyle: 'solid',
    //           fillColor: '#00a0e9',
    //           fillOpacity: 0.2
    //         })
    //       }
    //       if (!vm.drawingOverlay) {
    //         // 그려지고 있는 원의 반경 정보를 표시할 커스텀오버레이를 생성합니다
    //         vm.drawingOverlay = new kakao.maps.CustomOverlay({
    //           xAnchor: 0,
    //           yAnchor: 0,
    //           zIndex: 1
    //         })
    //       }
    //     }
    //   })
    //   kakao.maps.event.addListener(this.map, 'mousemove', function(
    //     // 지도에 마우스무브 이벤트를 등록합니다
    //     mouseEvent
    //   ) {
    //     if (vm.drawingFlag) {
    //       // 마우스무브 이벤트가 발생했을 때 원을 그리고있는 상태이면
    //       var mousePosition = mouseEvent.latLng // 마우스 커서의 현재 위치를 얻어옵니다
    //       var linePath = [vm.centerPosition, mousePosition] // 그려지고 있는 선을 표시할 좌표 배열입니다. 클릭한 중심좌표와 마우스커서의 위치로 설정합니다
    //       vm.drawingLine.setPath(linePath) // 그려지고 있는 선을 표시할 선 객체에 좌표 배열을 설정합니다
    //       var length = vm.drawingLine.getLength() // 원의 반지름을 선 객체를 이용해서 얻어옵니다
    //       if (length > 0) {
    //         var circleOptions = {
    //           // 그려지고 있는 원의 중심좌표와 반지름입니다
    //           center: vm.centerPosition,
    //           radius: length
    //         }
    //         vm.drawingCircle.setOptions(circleOptions) // 그려지고 있는 원의 옵션을 설정합니다
    //         var radius = Math.round(vm.drawingCircle.getRadius()) // 반경 정보를 표시할 커스텀오버레이의 내용입니다
    //         let content =
    //           '<div class="overlay" style="background-color:white">반경 <span class="number">' +
    //           radius +
    //           '</span>m</div>'
    //         vm.drawingOverlay.setPosition(mousePosition) // 반경 정보를 표시할 커스텀 오버레이의 좌표를 마우스커서 위치로 설정합니다
    //         vm.drawingOverlay.setContent(content) // 반경 정보를 표시할 커스텀 오버레이의 표시할 내용을 설정합니다
    //         vm.drawingCircle.setMap(vm.map) // 그려지고 있는 원을 지도에 표시합니다
    //         vm.drawingLine.setMap(vm.map) // 그려지고 있는 선을 지도에 표시합니다
    //         vm.drawingOverlay.setMap(vm.map) // 그려지고 있는 원의 반경정보 커스텀 오버레이를 지도에 표시합니다
    //       } else {
    //         vm.drawingCircle.setMap(null)
    //         vm.drawingLine.setMap(null)
    //         vm.drawingOverlay.setMap(null)
    //       }
    //     }
    //   })
    //   kakao.maps.event.addListener(this.map, 'rightclick', function(
    //     // 지도에 마우스 오른쪽 클릭이벤트를 등록합니다
    //     // 마우스 오른쪽 클릭한 위치를 기준으로 원과 원의 반경정보를 표시하는 선과 커스텀 오버레이를 표시하고 그리기를 종료합니다
    //     mouseEvent
    //   ) {
    //     // alert(vm.$store.state.mode)
    //     // vm.$store.state.mode = 0
    //     if (vm.drawingFlag) {
    //       var rClickPosition = mouseEvent.latLng // 마우스로 오른쪽 클릭한 위치입니다
    //       var polyline = new kakao.maps.Polyline({
    //         // 원의 반경을 표시할 선 객체를 생성합니다
    //         path: [vm.centerPosition, rClickPosition], // 선을 구성하는 좌표 배열입니다. 원의 중심좌표와 클릭한 위치로 설정합니다
    //         strokeWeight: 1, // 선의 두께 입니다
    //         strokeColor: '#00a0e9', // 선의 색깔입니다
    //         strokeOpacity: 0, // 선의 불투명도입니다 0에서 1 사이값이며 0에 가까울수록 투명합니다
    //         strokeStyle: 'solid' // 선의 스타일입니다
    //       })
    //       var circle = new kakao.maps.Circle({
    //         // 원 객체를 생성합니다
    //         center: vm.centerPosition, // 원의 중심좌표입니다
    //         radius: polyline.getLength(), // 원의 반지름입니다 m 단위 이며 선 객체를 이용해서 얻어옵니다
    //         strokeWeight: 0, // 선의 두께입니다
    //         strokeColor: '#00a0e9', // 선의 색깔입니다
    //         strokeOpacity: 0, // 선의 불투명도입니다 0에서 1 사이값이며 0에 가까울수록 투명합니다
    //         strokeStyle: 'solid', // 선의 스타일입니다
    //         fillColor: '#00a0e9', // 채우기 색깔입니다
    //         fillOpacity: 0.2 // 채우기 불투명도입니다
    //       })
    //       var radius = Math.round(circle.getRadius()) // 원의 반경 정보를 얻어옵니다
    //       var html = getBoxHTML(radius) // 커스텀 오버레이에 표시할 반경 정보입니다
    //       var radiusOverlay = new kakao.maps.CustomOverlay({
    //         // 반경정보를 표시할 커스텀 오버레이를 생성합니다
    //         content: html, // 표시할 내용입니다
    //         position: rClickPosition, // 표시할 위치입니다. 클릭한 위치로 설정합니다
    //         xAnchor: 0.5,
    //         yAnchor: 1,
    //         zIndex: 1
    //       })

    //       circle.setMap(vm.map) // 원을 지도에 표시합니다
    //       polyline.setMap(vm.map) // 선을 지도에 표시합니다
    //       radiusOverlay.setMap(vm.map) // 반경 정보 커스텀 오버레이를 지도에 표시합니다
    //       let radiusObj = {
    //         // 배열에 담을 객체입니다. 원, 선, 커스텀오버레이 객체를 가지고 있습니다
    //         polyline: polyline,
    //         circle: circle,
    //         overlay: radiusOverlay
    //       }
    //       circles.push(radiusObj) // 이 배열을 이용해서 "모두 지우기" 버튼을 클릭했을 때 지도에 그려진 원, 선, 커스텀오버레이들을 지웁니다
    //       vm.drawingFlag = false // 그리기 상태를 그리고 있지 않는 상태로 바꿉니다
    //       vm.centerPosition = null // 중심 좌표를 초기화 합니다
    //       vm.drawingCircle.setMap(null) // 그려지고 있는 원, 선, 커스텀오버레이를 지도에서 제거합니다
    //       vm.drawingLine.setMap(null)
    //       vm.drawingOverlay.setMap(null)
    //     }
    //   })

    //   // 지도에 표시되어 있는 모든 원과 반경정보를 표시하는 선, 커스텀 오버레이를 지도에서 제거합니다
    //   // eslint-disable-next-line no-unused-vars
    //   // eslint-disable-next-line no-inner-declarations
    //   function removeCircles() {
    //     for (var i = 0; i < circles.length; i++) {
    //       circles[i].circle.setMap(null)
    //       circles[i].polyline.setMap(null)
    //       circles[i].overlay.setMap(null)
    //     }
    //     circles = []
    //   }

    //   // 마우스 우클릭 하여 원 그리기가 종료됐을 때 호출하여
    //   // HTML Content를 만들어 리턴하는 함수입니다
    //   // eslint-disable-next-line no-inner-declarations
    //   function getBoxHTML(distance) {
    //     var content =
    //       '<div class="v-sheet theme--light elevation-14" style="margin-leftauto;display:block;text-align:center;" id="mapSheet">'
    //     content +=
    //       '    <div style="display:flex;justify-content:space-around;">'
    //     content += '    <div style="width:70px;height:70px;">'
    //     content +=
    //       '        <img style="width:40px;height:40px;display:block;" src="/img/logo.82b9c7a5.png">' +
    //       '<span style="width:100%;">' +
    //       20
    //     content += '</span></div>'
    //     content += '</div>'
    //     content += '</div>'
    //     return content
    //   }
    // }
    // 반경 그리기 함수 끝-----------------------------------------------------------------------------------------
  }
}
</script>
<style scoped>
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
  top: -50%;
  left: -50%;
  width: 200%;
  height: 200%;
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
  width: 300px;
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
  width: 300px;
  height: 180px;
  top: 100px;
  left: 50px;
  border-radius: 3px;
}
:-ms-input-placeholder {
  color: tomato;
}
</style>
