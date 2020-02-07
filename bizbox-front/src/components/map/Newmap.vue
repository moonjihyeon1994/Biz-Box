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
</template>

<script>
import Dong from '../../assets/json/New.json'
import condition from '@/components/bizmap/kakaomap/SearchCondition.vue'
import axios from '../../js/http-commons'

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
      circles: [],
      countResult: '',
      searchX: '',
      searchY: '',
      range: '',
      CountInfo: {
        소매: '',
        학문교육: '',
        숙박: '',
        생활서비스: '',
        음식: '',
        부동산: '',
        의료: '',
        관광여가오락: ''
      }
    }
  },
  mounted() {
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
        fillColor: '#fff',
        fillOpacity: 0.2
      })

      kakao.maps.event.addListener(polygon, 'mouseover', mouseEvent => {
        // 각 폴리곤에 마우스 오버 이벤트 등록
        let Name = name
        let position = mouseEvent.LatLng
        polygon.setOptions({
          fillColor: '#09f'
        })
        vm.overlay(Name, position)
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
  },
  watch: {
    ifchanege: function(newVal, oldVal) {}
  },
  components: {
    condition
  },
  methods: {
    overlay (name, position) {
      var customOverlay = new kakao.maps.CustomOverlay({
        position: position,
        content: '<div class="area">' + name + '</div>'
      })
      customOverlay.setMap(this.map)
    },
    panTo () {
      // 지도 중심을 부드럽게 이동시킵니다
      var moveLatLon = new kakao.maps.LatLng(33.45058, 126.574942) // 이동할 위도 경도 위치를 생성합니다
      this.map.panTo(moveLatLon)
    },
    // -- 동이름으로 검색-----------------------------------------------------------------------------
    serch (name) {
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
    CircleMouseClick (mouseEvent) {
      // 지도에 클릭 이벤트를 등록

      this.removeCircles()
      if (this.$store.state.mode === 1) {
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
        this.range = radius
        var html = this.getBoxHTML() // 커스텀 오버레이에 표시할 반경 정보입니다
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
    getBoxHTML () {
      this.getData()
      console.log(this.CountInfo)
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
        '<div class="v-sheet theme--light elevation-14" style="width:300px;height:150px;margin:auto;display:block;text-align:center;" id="mapSheet">'
      content +=
        '    <div style="padding-top:10px;display:flex;justify-content:space-around;">'
      content += '    <div style="width:70px;height:70px;">'
      content +=
        '        <img style="margin:auto;width:40px;height:40px;display:block;" src="/img/logo.82b9c7a5.png">' +
        '<span style="width:100%;">' +
        소매
      content += '</span></div>'
      content += '    <div style="width:70px;height:70px;">'
      content +=
        '        <img style="margin:auto;width:40px;height:40px;display:block;" src="/img/logo.82b9c7a5.png">' +
        '<span style="width:100%;">' +
        학문교육
      content += '</span></div>'
      content += '    <div style="width:70px;height:70px;">'
      content +=
        '        <img style="margin:auto;width:40px;height:40px;display:block;" src="/img/logo.82b9c7a5.png">' +
        '<span style="width:100%;">' +
        숙박
      content += '</span></div>'
      content += '    <div style="width:70px;height:70px;">'
      content +=
        '        <img style="margin:auto;width:40px;height:40px;display:block;" src="/img/logo.82b9c7a5.png">' +
        '<span style="width:100%;">' +
        생활서비스
      content += '</span></div>'
      content += '</div>'
      content += '    <div style="display:flex;justify-content:space-around;">'
      content += '    <div style="width:70px;height:70px;">'
      content +=
        '        <img style="margin:auto;width:40px;height:40px;display:block;" src="/img/logo.82b9c7a5.png">' +
        '<span style="width:100%;">' +
        음식
      content += '</span></div>'
      content += '    <div style="width:70px;height:70px;">'
      content +=
        '        <img style="margin:auto;width:40px;height:40px;display:block;" src="/img/logo.82b9c7a5.png">' +
        '<span style="width:100%;">' +
        부동산
      content += '</span></div>'
      content += '    <div style="width:70px;height:70px;">'
      content +=
        '        <img style="margin:auto;width:40px;height:40px;display:block;" src="/img/logo.82b9c7a5.png">' +
        '<span style="width:100%;">' +
        의료
      content += '</span></div>'
      content += '    <div style="width:70px;height:70px;">'
      content +=
        '        <img style="margin:auto;width:40px;height:40px;display:block;" src="/img/logo.82b9c7a5.png">' +
        '<span style="width:100%;">' +
        관광여가오락
      content += '</span></div>'
      content += '</div>'
      content += '</div>'
      return content
    }, // 범위내 상가정보 받아오는 매서드
    getData () {
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
          var jsonlarge = res.data.large
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
    removeCircles () {
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
