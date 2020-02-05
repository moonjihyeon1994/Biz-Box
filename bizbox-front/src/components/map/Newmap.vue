<template>
  <div class="mapContainer">
   <div  class="a ssearchbox" >
    <input v-model="name" type="text" placeholder="검색어입력">
    <button  v-on:click="serch">검색</button>
    <textarea style="width:300px;" id="result" v-model="message"></textarea>
  </div>
    <div class="map" id="map" style="width:500px;height:400px;"></div>
  </div>
</template>

<script>
import Dong from '../../assets/json/New.json' 
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
      addListener: null
    }
  },
  mounted () {
    let data = Dong.features //좌표 저장할 배열
    let coordinates = []      //행정 구 이름
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
    this.geocoder = new kakao.maps.services.Geocoder()// 코더생성
    // --맵 생성--------------------------------------------------------------------------------------
    this.map = new kakao.maps.Map(container, options)// 맵 생성
    kakao.maps.event.addListener(this.map, 'bounds_changed', () => {
      var bounds = Map.getBounds() // 지도 영역정보
      var swLatlng = bounds.getSouthWest()// 영역정보의 남서쪽 정보
      var neLatlng = bounds.getNorthEast()// 영역정보의 북동쪽 정보
      this.message = '영역좌표 :' + swLatlng.toString() + '/' + neLatlng.toString() + ''
    })
    let Map = this.map
    let vm = this
    // --마커 생성--------------------------------------------------------------------
    this.marker = new kakao.maps.Marker({// 마커생성
      map: this.map,
      position: new kakao.maps.LatLng(37.505691, 127.0298106) // 최초 표시되는 마커의 위치
    })

    // --인포윈도우 생성---------------------------------------------------------------
    this.infowindow = new kakao.maps.InfoWindow({
      content: 'name' })
    // -------------------------------------------------------------------------------

    // --폴리곤 생성---------------------------------------------------------------------------------------
    for (var i = 0, len = data.length; i < len; i++) { // 동의 경계면 좌표를 받아서 다각형 생성 함수에 전달
      this.coordinates = data[i].geometry.coordinates
      name = data[i].properties.ADM_DR_NM
      displayArea(vm.pp, this.map, this.coordinates[0][0], name, this.coordinates[0][0].length)
    }
    function displayArea (polygon, Mmap, coordinates, name, length) { // 다각형을 생성 ,이벤트를 등록
      let path = []
      for (let i = 0; i < length; i++) { // 좌표의 개수만큼 반복문을 돌며 경계 바운더리 생성(path생성)
        path.push(new kakao.maps.LatLng(coordinates[i][1], coordinates[i][0]))
      }
      polygon = new kakao.maps.Polygon({ // 생성된 path를 이용하여 폴리곤(다각형) 생성
        map: Mmap, // 다각형을 표시할 지도 객체
        path: path,
        strokeWeight: 3, // 선두깨
        strokeColor: '#004c80', // 선색
        strokeOpacity: 0.6, // 선 투명도
        fillColor: '#fff',
        fillOpacity: 0.4
      })

      kakao.maps.event.addListener(polygon, 'mouseover', () => { // 각 폴리곤에 마우스 오버 이벤트 등록
        polygon.setOptions({
          fillColor: '#09f'
        })
      })
      kakao.maps.event.addListener(polygon, 'mouseout', () => { //  각 폴리곤에 마우스 아웃 이벤트 등록
        polygon.setOptions({
          fillColor: '#fff'
        })
      })
      kakao.maps.event.addListener(polygon, 'click', () => { //  각 폴리곤에 마우스 아웃 이벤트 등록
        let Name = name
        let Marker = vm.marker
        let InfoWindow = vm.infowindow
        vm.geocoder.addressSearch(Name, function (
          result,
          status
        ) {
        // 정상적으로 검색이 완료되면
          if (status === kakao.maps.services.Status.OK) {
            console.log(Name)
            var coords = new kakao.maps.LatLng(result[0].y, result[0].x)// 결과값으로 받은 위치를 마커의 위치로 적용
            Marker.setPosition(coords)
            InfoWindow.close()
            InfoWindow.setContent(Name)
            InfoWindow.open(Map, Marker)
            Map.setCenter(coords)
          }
        })
      })
    }
    this.ifchanege = this.map.getCenter()
    var resultDiv = document.getElementById('result')
    resultDiv.innerHTML = Message
  },
  watch: {
    ifchanege: function (newVal, oldVal) {
    }
  },
  components:{
  },
  methods: {
    panTo () { // 지도 중심을 부드럽게 이동시킵니다
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
      this.geocoder.addressSearch(this.name, function (
        result,
        status
      ) {
        // 정상적으로 검색이 완료되면
        if (status === kakao.maps.services.Status.OK) {
          var coords = new kakao.maps.LatLng(result[0].y, result[0].x)// 결과값으로 받은 위치를 마커의 위치로 적용
          Marker.setPosition(coords)
          InfoWindow.close() // 전에 있던 인포위도우 클로즈
          InfoWindow.setContent(Name) //  인포위도우 내용 세팅
          InfoWindow.open(Map, Marker) // 마커위에 인포위도우 열림
          Map.setCenter(coords) // 새로 세팅된 센터 값으로 맵 세팅
        }
      })
    }
    // -----------------------------------------------------------------------------------------
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
.a{
    height: 50px;
    width: 380px;
    background: white;
    border:1px solid coral;
}
input{
    font-size: 18px;
    width: 300px;
    float: left;
    border: 0px;
    outline: none;
}
button{
    color: white;
    width: 70px;
    height: 100%;
    background: coral;
    float: right;
    border: 0px;
    outline: none;
}
:-ms-input-placeholder{
    color:tomato;
}
</style>
