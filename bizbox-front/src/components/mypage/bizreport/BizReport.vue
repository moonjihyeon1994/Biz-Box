<template>
  <div class="biz-report">
    <div class="map-pic">
      <div id="my-pos"></div>
      <h3>Where at(위치를 카카오맵으로 표시)</h3>
      위도: {{ content.lat }} 경도: {{ content.lng }}
    </div>
    <div class="biz-info">
      <h3>INFO (가게에 대한 정보)</h3>
      개업일: {{ content.open }}
      <line-chart
        :chart-data="chartdata"
        :options="chartoptions"
        width="500px"
        height="300px"
      ></line-chart>
    </div>
    <div class="btn-set">
      <button class="btn-item">정보 수정</button>
      <button class="btn-item">폐업</button>
    </div>
  </div>
</template>

<script>
import LineChart from '@/lib/LineChart'
export default {
  data () {
    return {
      mapContainer: null,
      labels: [
        '19년 9월',
        '19년 10월',
        '19년 11월',
        '19년 12월',
        '20년 1월',
        '20년 2월'
      ],
      data: [4839023, 5838293, 4937194, 3937869, 4928493, 30458283]
    }
  },
  props: {
    content: Object
  },
  components: {
    LineChart
  }
}
</script>

<style scoped src="./BizReport.css"></style>
