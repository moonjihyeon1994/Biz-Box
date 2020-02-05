<template>
<!-- 지도를 표시할 div 입니다 -->
 <div>
<span @click="this.onLoad">지도</span>
<vue-daum-map
      :appKey="this.appKey"
      :center.sync="this.center"
      :level.sync="this.level"
      :mapTypeId="this.mapTypeId"
      :libraries="this.libraries"
      @load="this.onLoad"
      style="width:500px;height:400px;"/>
<<<<<<< HEAD:bizbox-front/src/components/map/kakaomap.vue
 </div>
</template>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=0574c7ce26ff4134a0dc5f831d6edd37&libraries=services"></script>
<script>
import VueDaumMap from 'vue-daum-map'x
=======
</div>

</template>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=0574c7ce26ff4134a0dc5f831d6edd37&libraries=services"></script>
<script>
import VueDaumMap from 'vue-daum-map'

>>>>>>> 3cf1ed59ee00b2a0ef67a06e86d5054f92bc03d8:bizbox-front/src/components/bizmap/kakaomap/Kakaomap.vue
export default {
    data: () => ({
        appKey: '8227ff53a7c3554b706e45589733cb41', // 테스트용 appkey
        center: {lat:33.450701, lng:126.570667}, // 지도의 중심 좌표
        level: 3, // 지도의 레벨(확대, 축소 정도),
        mapTypeId: VueDaumMap.MapTypeId.NORMAL, // 맵 타입
        libraries: [], // 추가로 불러올 라이브러리
        map: null // 지도 객체. 지도가 로드되면 할당됨.
    }),
    methods: {
        // 지도가 로드 완료되면 load 이벤트 발생
        onLoad (map) {
            alert("로드됨");
            this.map = map
        }
    }
}
</script>

<style>
<<<<<<< HEAD:bizbox-front/src/components/map/kakaomap.vue
</style>
=======

</style>
>>>>>>> 3cf1ed59ee00b2a0ef67a06e86d5054f92bc03d8:bizbox-front/src/components/bizmap/kakaomap/Kakaomap.vue
