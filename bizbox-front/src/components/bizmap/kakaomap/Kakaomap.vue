<template>
  <div class="mapContainer">
    <v-card>
      <v-toolbar class="searchBox">
        <v-text-field
          Elevation="14"
          hide-details
          v-on:keyup.enter="submit"
          v-model.trim="searchWord"
          single-line
          clearable
          label="Search..."
        ></v-text-field>
        <v-btn v-on:click="submit" icon>
          <v-icon>mdi-magnify</v-icon>
        </v-btn>
      </v-toolbar>
    </v-card>
    <condition></condition>
    <div class="map" id="map" v-on:click="makeCircle"></div>
  </div>
</template>
<script>
import condition from '@/components/bizmap/kakaomap/SearchCondition.vue'
export default {
  data: () => {
    return {
      map: null,
      searchWord: null,
      state: 1
    }
  },
  components: {
    condition
  },
  mounted () {
    this.makeMap()
  },
  methods: {
    makeMap: function () {
      var container = document.getElementById('map') // 지도를 담을 영역의 DOM 레퍼런스
      var options = {
        // 지도를 생성할 때 필요한 기본 옵션
        // eslint-disable-next-line no-undef
        center: new kakao.maps.LatLng(37.5012583, 127.0395225), // 지도의 중심좌표.
        level: 3 // 지도의 레벨(확대, 축소 정도)
      }
      // eslint-disable-next-line no-undef
      this.map = new kakao.maps.Map(container, options)
    },
    submit: function (event) {
      // 주소-좌표 변환 객체를 생성합니다
      // eslint-disable-next-line no-undef
      let geocoder = new kakao.maps.services.Geocoder()
      // eslint-disable-next-line no-unused-vars
      // 주소로 좌표를 검색합니다
      // eslint-disable-next-line no-unused-vars
      let searchMap = this.map
      let word = this.searchWord
      geocoder.addressSearch(this.searchWord, function (result, status) {
        // 정상적으로 검색이 완료됐으면
        // eslint-disable-next-line no-undef
        if (status === kakao.maps.services.Status.OK) {
          // eslint-disable-next-line no-undef
          let coords = new kakao.maps.LatLng(result[0].y, result[0].x)
          let searchedMap = searchMap
          // 결과값으로 받은 위치를 마커로 표시합니다
          // eslint-disable-next-line no-undef
          let marker = new kakao.maps.Marker({
            map: searchedMap,
            position: coords
          })

          // 인포윈도우로 장소에 대한 설명을 표시합니다
          // eslint-disable-next-line no-undef
          let infowindow = new kakao.maps.InfoWindow({
            content:
              '<div style="width:160px;text-align:center;padding:6px;background-color:navy;border:0;color:white;border-radius:3px;">' +
              word +
              '</div>'
          })
          infowindow.open(searchedMap, marker)

          // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
          searchedMap.setCenter(coords)
        }
      })
    },
    makeCircle: function () {
      let drawingFlag = false // 원이 그려지고 있는 상태를 가지고 있을 변수입니다
      let centerPosition // 원의 중심좌표 입니다
      let drawingCircle // 그려지고 있는 원을 표시할 원 객체입니다
      let drawingLine // 그려지고 있는 원의 반지름을 표시할 선 객체입니다
      let drawingOverlay // 그려지고 있는 원의 반경을 표시할 커스텀오버레이 입니다

      let circles = []

      let vm = this
      // 지도에 클릭 이벤트를 등록합니다
      kakao.maps.event.addListener(this.map, 'click', function (mouseEvent) {
        removeCircles()
        // 클릭 이벤트가 발생했을 때 원을 그리고 있는 상태가 아니면 중심좌표를 클릭한 지점으로 설정합니다
        if (!vm.drawingFlag) {
          // 상태를 그리고있는 상태로 변경합니다
          vm.drawingFlag = true
          // 원이 그려질 중심좌표를 클릭한 위치로 설정합니다
          vm.centerPosition = mouseEvent.latLng
          // 그려지고 있는 원의 반경을 표시할 선 객체를 생성합니다
          if (!vm.drawingLine) {
            // eslint-disable-next-line no-undef
            vm.drawingLine = new kakao.maps.Polyline({
              strokeWeight: 3, // 선의 두께입니다
              strokeColor: '#00a0e9', // 선의 색깔입니다
              strokeOpacity: 0, // 선의 불투명도입니다 0에서 1 사이값이며 0에 가까울수록 투명합니다
              strokeStyle: 'solid' // 선의 스타일입니다
            })
          }

          // 그려지고 있는 원을 표시할 원 객체를 생성합니다
          if (!vm.drawingCircle) {
            vm.drawingCircle = new kakao.maps.Circle({
              strokeWeight: 1, // 선의 두께입니다
              strokeColor: '#00a0e9', // 선의 색깔입니다
              strokeOpacity: 0, // 선의 불투명도입니다 0에서 1 사이값이며 0에 가까울수록 투명합니다
              strokeStyle: 'solid', // 선의 스타일입니다
              fillColor: '#00a0e9', // 채우기 색깔입니다
              fillOpacity: 0.2 // 채우기 불투명도입니다
            })
          }

          // 그려지고 있는 원의 반경 정보를 표시할 커스텀오버레이를 생성합니다
          if (!vm.drawingOverlay) {
            // eslint-disable-next-line no-undef
            vm.drawingOverlay = new kakao.maps.CustomOverlay({
              xAnchor: 0,
              yAnchor: 0,
              zIndex: 1
            })
          }
        }
      })

      // 지도에 마우스무브 이벤트를 등록합니다
      // 원을 그리고있는 상태에서 마우스무브 이벤트가 발생하면 그려질 원의 위치와 반경정보를 동적으로 보여주도록 합니다
      // eslint-disable-next-line no-undef
      kakao.maps.event.addListener(this.map, 'mousemove', function (mouseEvent) {
        // 마우스무브 이벤트가 발생했을 때 원을 그리고있는 상태이면
        if (vm.drawingFlag) {
          // 마우스 커서의 현재 위치를 얻어옵니다
          var mousePosition = mouseEvent.latLng

          // 그려지고 있는 선을 표시할 좌표 배열입니다. 클릭한 중심좌표와 마우스커서의 위치로 설정합니다
          var linePath = [vm.centerPosition, mousePosition]
          // 그려지고 있는 선을 표시할 선 객체에 좌표 배열을 설정합니다
          vm.drawingLine.setPath(linePath)
          // 원의 반지름을 선 객체를 이용해서 얻어옵니다
          var length = vm.drawingLine.getLength()
          if (length > 0) {
            // 그려지고 있는 원의 중심좌표와 반지름입니다
            var circleOptions = {
              center: vm.centerPosition,
              radius: length
            }
            // 그려지고 있는 원의 옵션을 설정합니다
            vm.drawingCircle.setOptions(circleOptions)

            // 반경 정보를 표시할 커스텀오버레이의 내용입니다
            var radius = Math.round(vm.drawingCircle.getRadius())
            let content =
                '<div class="overlay" style="background-color:white">반경 <span class="number">' +
                radius +
                '</span>m</div>'

            // 반경 정보를 표시할 커스텀 오버레이의 좌표를 마우스커서 위치로 설정합니다
            vm.drawingOverlay.setPosition(mousePosition)

            // 반경 정보를 표시할 커스텀 오버레이의 표시할 내용을 설정합니다
            vm.drawingOverlay.setContent(content)

            // 그려지고 있는 원을 지도에 표시합니다
            vm.drawingCircle.setMap(vm.map)

            // 그려지고 있는 선을 지도에 표시합니다
            vm.drawingLine.setMap(vm.map)

            // 그려지고 있는 원의 반경정보 커스텀 오버레이를 지도에 표시합니다
            vm.drawingOverlay.setMap(vm.map)
          } else {
            vm.drawingCircle.setMap(null)
            vm.drawingLine.setMap(null)
            vm.drawingOverlay.setMap(null)
          }
        }
      })

      // 지도에 마우스 오른쪽 클릭이벤트를 등록합니다
      // 원을 그리고있는 상태에서 마우스 오른쪽 클릭 이벤트가 발생하면
      // 마우스 오른쪽 클릭한 위치를 기준으로 원과 원의 반경정보를 표시하는 선과 커스텀 오버레이를 표시하고 그리기를 종료합니다
      // eslint-disable-next-line no-undef
      kakao.maps.event.addListener(this.map, 'rightclick', function (mouseEvent) {
        if (vm.drawingFlag) {
          // 마우스로 오른쪽 클릭한 위치입니다
          var rClickPosition = mouseEvent.latLng

          // 원의 반경을 표시할 선 객체를 생성합니다
          // eslint-disable-next-line no-undef
          var polyline = new kakao.maps.Polyline({
            path: [vm.centerPosition, rClickPosition], // 선을 구성하는 좌표 배열입니다. 원의 중심좌표와 클릭한 위치로 설정합니다
            strokeWeight: 1, // 선의 두께 입니다
            strokeColor: '#00a0e9', // 선의 색깔입니다
            strokeOpacity: 0, // 선의 불투명도입니다 0에서 1 사이값이며 0에 가까울수록 투명합니다
            strokeStyle: 'solid' // 선의 스타일입니다
          })
          // 원 객체를 생성합니다
          // eslint-disable-next-line no-undef
          var circle = new kakao.maps.Circle({
            center: vm.centerPosition, // 원의 중심좌표입니다
            radius: polyline.getLength(), // 원의 반지름입니다 m 단위 이며 선 객체를 이용해서 얻어옵니다
            strokeWeight: 0, // 선의 두께입니다
            strokeColor: '#00a0e9', // 선의 색깔입니다
            strokeOpacity: 0, // 선의 불투명도입니다 0에서 1 사이값이며 0에 가까울수록 투명합니다
            strokeStyle: 'solid', // 선의 스타일입니다
            fillColor: '#00a0e9', // 채우기 색깔입니다
            fillOpacity: 0.2 // 채우기 불투명도입니다
          })
          var radius = Math.round(circle.getRadius()) // 원의 반경 정보를 얻어옵니다
          // eslint-disable-next-line no-unused-vars
          var html = getBoxHTML(radius) // 커스텀 오버레이에 표시할 반경 정보입니다
          // 반경정보를 표시할 커스텀 오버레이를 생성합니다
          // eslint-disable-next-line no-undef
          var radiusOverlay = new kakao.maps.CustomOverlay({
            content: html, // 표시할 내용입니다
            position: rClickPosition, // 표시할 위치입니다. 클릭한 위치로 설정합니다
            xAnchor: 0.5,
            yAnchor: 1,
            zIndex: 1
          })
          // 원을 지도에 표시합니다
          circle.setMap(vm.map)
          // 선을 지도에 표시합니다
          polyline.setMap(vm.map)

          // 반경 정보 커스텀 오버레이를 지도에 표시합니다
          radiusOverlay.setMap(vm.map)
          // 배열에 담을 객체입니다. 원, 선, 커스텀오버레이 객체를 가지고 있습니다
          // eslint-disable-next-line no-unused-vars
          let radiusObj = {
            polyline: polyline,
            circle: circle,
            overlay: radiusOverlay
          }
          // 배열에 추가합니다
          // 이 배열을 이용해서 "모두 지우기" 버튼을 클릭했을 때 지도에 그려진 원, 선, 커스텀오버레이들을 지웁니다
          circles.push(radiusObj)
          // 그리기 상태를 그리고 있지 않는 상태로 바꿉니다
          vm.drawingFlag = false

          // 중심 좌표를 초기화 합니다
          vm.centerPosition = null
          // 그려지고 있는 원, 선, 커스텀오버레이를 지도에서 제거합니다
          vm.drawingCircle.setMap(null)
          vm.drawingLine.setMap(null)
          vm.drawingOverlay.setMap(null)
        }
      })

      // 지도에 표시되어 있는 모든 원과 반경정보를 표시하는 선, 커스텀 오버레이를 지도에서 제거합니다
      // eslint-disable-next-line no-unused-vars
      function removeCircles () {
        for (var i = 0; i < circles.length; i++) {
          circles[i].circle.setMap(null)
          circles[i].polyline.setMap(null)
          circles[i].overlay.setMap(null)
        }
        circles = []
      }

      // 마우스 우클릭 하여 원 그리기가 종료됐을 때 호출하여
      // HTML Content를 만들어 리턴하는 함수입니다
      function getBoxHTML (distance) {
        var content = '<div class="v-sheet theme--light elevation-14" style="margin-leftauto;display:block;text-align:center;" id="mapSheet">'
        content += '    <div style="display:flex;justify-content:space-around;">'
        content += '    <div style="width:70px;height:70px;">'
        content +=
          '        <img style="width:40px;height:40px;display:block;" src="/img/logo.82b9c7a5.png">' + '<span style="width:100%;">' + 20
        content +=
          '</span></div>'
        content +=
          '</div>'
        content += '</div>'
        return content
      }
    }
  }
}
</script>

<style scoped lang="scss">
.mapContainer {
  width: 1400px;
  height: 900px;
  .map {
    position: absolute;
    margin: auto;
    z-index: 1;
    width: 100%;
    height: 100%;
  }
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
.overlay {
  background-color: pink;
  width: 300px;
  height: 180px;
}
</style>
