<template>
  <div class="mapContainer">
   <div  class="a ssearchbox" >
    <input v-model="name" type="text" placeholder="검색어입력">
    <button  v-on:click="asdf">검색</button>
    <textarea id="result" v-model="message"></textarea>
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
    let name = ''
    let Message = ''
    let pp = null
    this.message = Message
    var container = document.getElementById('map')

    var options = {
      center: new kakao.maps.LatLng(37.505691, 127.0298106),
      level: 3
    }
    this.geocoder = new kakao.maps.services.Geocoder()// 코더생성
    this.map = new kakao.maps.Map(container, options)// 맵 생성
    let Map = this.map
    let vm=this
    for (var i = 0, len = data.length; i < len; i++) {
      this.coordinates = data[i].geometry.coordinates
      name = data[i].properties.ADM_DR_NM
      // console.log(data)
      // console.log(data[0].geometry.coordinates)
      displayArea(vm.pp, this.map, this.coordinates[0][0], name, this.coordinates[0][0].length)
    }

    // this.polygon = []

    // 다각형을 생상하고 이벤트를 등록하는 함수입니다
    function displayArea (polygon, Mmap, coordinates, name, length) {
      let path = []
      let points = []
      // console.log(coordinates)
      for (let i = 0; i < length; i++) {
        path.push(new kakao.maps.LatLng(coordinates[i][1], coordinates[i][0]))
      }
      polygon = new kakao.maps.Polygon({
        map: Mmap, // 다각형을 표시할 지도 객체
        path: path,
        strokeWeight: 3,
        strokeColor: '#004c80',
        strokeOpacity: 0.6,
        fillColor: '#fff',
        fillOpacity: 0.4
      })
      kakao.maps.event.addListener(polygon, 'mouseover', () => {
      // 지도 영역정보를 얻어옵니다
        polygon.setOption({
          fillColor : '#09f'
      })
      
    })
    }
    this.marker = new kakao.maps.Marker({// 마커생성
      map: this.map,
      position: new kakao.maps.LatLng(37.505691, 127.0298106)
    })
    this.infowindow = new kakao.maps.InfoWindow({
      content:
             this.name })
    this.ifchanege = this.map.getCenter()
    // console.log(kakao)
    kakao.maps.event.addListener(this.map, 'bounds_changed', () => {
      // 지도 영역정보를 얻어옵니다
      var bounds = Map.getBounds()
      // 영역정보의 남서쪽 정보를 얻어옵니다
      var swLatlng = bounds.getSouthWest()
      // 영역정보의 북동쪽 정보를 얻어옵니다
      var neLatlng = bounds.getNorthEast()
      this.message = '영역좌표는 남서쪽 위도, 경도는  ' + swLatlng.toString()  + '북동쪽 위도, 경도는  ' + neLatlng.toString() + '입니다 <'
      // this.message = '영역좌표는 남서쪽 위도, 경도는  ' + swLatlng.toString()  + '북동쪽 위도, 경도는  ' + neLatlng.toString() + '입니다 <'
    })
    console.log(this.polygon)
    console.log(pp)
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
    panTo () {
      // 이동할 위도 경도 위치를 생성합니다
      var moveLatLon = new kakao.maps.LatLng(33.45058, 126.574942)
      // 지도 중심을 부드럽게 이동시킵니다
      // 만약 이동할 거리가 지도 화면보다 크면 부드러운 효과 없이 이동합니다
      this.map.panTo(moveLatLon)
    },
    asdf (name) {
      // eslint-disable-next-line no-unused-vars
      let Ifchange = this.ifchanege
      let Name = this.name
      let Map = this.map
      let Marker = this.marker
      let InfoWindow = this.infowindow
      this.geocoder.addressSearch(this.name, function (
        result,
        status
      ) {
        // 정상적으로 검색이 완료됐으면
        if (status === kakao.maps.services.Status.OK) {
          var coords = new kakao.maps.LatLng(result[0].y, result[0].x)// 결과값으로 받은 위치를 마커의 위치로 적용
          Marker.setPosition(coords)

          // 인포윈도우로 장소에 대한 설명을 표시합니다
          // var infowindow = new kakao.maps.InfoWindow({
          //   content:
          //    Name
          // })
          InfoWindow.close()
          InfoWindow.setContent(Name)
          InfoWindow.open(Map, Marker)
          Ifchange = Map.getCenter()
          // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
          Map.setCenter(coords)
        }
      })
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
