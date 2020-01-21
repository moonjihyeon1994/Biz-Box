<template>
  <div>
    <h1>문요한의 차트 및 그래프 테스트 컴포넌트</h1>
    <hr />

    <div id="columnchart_values" style="width: 900px; height: 300px;"></div>
  </div>
</template>

<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script>
import { Bar } from "vue-chartjs";

export default {
  extends: Bar,
  mounted() {
    this.renderChart(data, options);
  }
};
</script>



<style>
#columnchart_values {
  border: 1px solid black;
}
</style>