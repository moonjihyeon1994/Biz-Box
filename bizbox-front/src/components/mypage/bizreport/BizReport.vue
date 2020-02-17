<template>
  <div class="biz-report">
    <div class="map-pic">
      <div id="my-pos"></div>
      <h3>Where at(위치를 카카오맵으로 표시)</h3>
      위도: {{ content.lat }}
      경도: {{ content.lng }}
    </div>
    <div class="biz-info">
      <h3>INFO (가게에 대한 정보)</h3>
      개업일: {{ content.open }}
    </div>
    <div class="btn-set">
      <button class="btn-item">정보 수정</button>
      <button class="btn-item">폐업</button>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      mapContainer: null
    }
  },
  props: {
    content: Object
  },
  methods: {
    getMap: function() {
      var container = document.getElementById('mypos')
      var mapOptions = {
        center: new kakao.map.LatLng(this.content.lat, this.content.lng),
        level: 3
      }
      this.mapContainer = new kakao.maps.Map(container, mapOptions)
    }
  },
  mounted() {
    this.getMap()
  }
}
</script>

<style scoped src="./BizReport.css"></style>
