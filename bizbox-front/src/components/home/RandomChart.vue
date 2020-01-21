<template>
  <div class="small">
    <line-chart :chart-data="datacollection"></line-chart>
    <button @click="fillData()">Randomize</button>
    <hr />
    <div>
      <h1>도로명 주소 검색</h1>
      <span>검색어를 입력하세요.</span>
      <input type="text" v-model="key" @keyup.enter="getData()" />
      <p>
        <span>결과 :</span>
        {{ result }}
      </p>
    </div>
  </div>
</template>

<script>
import LineChart from "./LineChart.js";
import axios from "../../js/http-commons";

export default {
  components: {
    LineChart
  },
  data() {
    return {
      datacollection: null,
      key: "",
      result: ""
    };
  },
  mounted() {
    this.fillData();
  },
  methods: {
    fillData() {
      this.datacollection = {
        labels: [this.getRandomInt(), this.getRandomInt()],
        datasets: [
          {
            label: "Data One",
            backgroundColor: "#f87979",
            data: [this.getRandomInt(), this.getRandomInt()]
          },
          {
            label: "Data One",
            backgroundColor: "#f87979",
            data: [this.getRandomInt(), this.getRandomInt()]
          }
        ]
      };
    },
    getRandomInt() {
      return Math.floor(Math.random() * (50 - 5 + 1)) + 5;
    },
    getData() {
      axios.get("/population/getPopulationByTime/" + this.key).then(res => {
        this.result = res;
      });
    }
  }
};
</script>

<style>
.small {
  max-width: 600px;
  margin: 150px auto;
}
</style>