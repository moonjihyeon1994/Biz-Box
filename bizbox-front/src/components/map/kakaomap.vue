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
    <div class="map" id="map"></div>
  </div>
</template>
<script>
import condition from '@/components/map/searchCondition.vue'

export default {
  data: () => {
    return {
      map: null,
      searchWord: null
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
              '<div style="width:150px;text-align:center;padding:6px;background-color:navy;color:white;border-radius:3px;">' + word + '</div>'
          })
          infowindow.open(searchedMap, marker)

          // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
          searchedMap.setCenter(coords)
        }
      })
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
</style>
