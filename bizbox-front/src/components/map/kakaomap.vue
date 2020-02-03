<template>
  <!-- 지도를 표시할 div 입니다 -->
  <div class="mapContainer">
    <vue-daum-map
      class="map"
      :appKey="this.appKey"
      :center.sync="this.center"
      :level.sync="this.level"
      :mapTypeId="this.mapTypeId"
      :libraries="this.libraries"
      @load="this.onLoad"
    />
  </div>
</template>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=0574c7ce26ff4134a0dc5f831d6edd37&libraries=services"></script>
<script>
import VueDaumMap from 'vue-daum-map'

export default {
  data: () => ({
    appKey: '8227ff53a7c3554b706e45589733cb41', // 테스트용 appkey
    center: { lat: 37.5013068, lng: 127.037471 }, // 지도의 중심 좌표
    level: 3, // 지도의 레벨(확대, 축소 정도),
    mapTypeId: VueDaumMap.MapTypeId.NORMAL, // 맵 타입
    libraries: [], // 추가로 불러올 라이브러리
    map: null // 지도 객체. 지도가 로드되면 할당됨.
  }),
  methods: {
    // 지도가 로드 완료되면 load 이벤트 발생
    onLoad(map) {
      this.map = map
    }
  },
  components: {
    VueDaumMap
  }
}
</script>

<style>
.mapContainer {
  position: fixed;
  top: -50%;
  left: -50%;
  width: 200%;
  height: 200%;
}
.map {
    position: absolute;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    margin: auto;
    min-width: 50%;
    min-height: 50%;
}
</style>
