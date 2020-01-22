<template>
  <div id="app">
    <h1>Graph, Chart 테스트 컴포넌트</h1>
    <hr />

    <div id="search">
      <div id="searchOptions">
        <button @click="setOpt1()">연령별 유동인구</button>
        <button @click="setOpt2()">시간별 유동인구</button>
        <button @click="setOpt3()">요일별 유동인구</button>
        <button @click="setOpt4()">연도별 상권 변화 지표</button>
        <h2>{{ title }}</h2>
      </div>
      <input type="text" placeholder="검색하세요." v-model="key" @keyup.enter="getData()" />
      <button @click="getData()">검색</button>
    </div>
    <p>{{ road }}</p>
    <hr />
    <div id="chart1">
      <bar-chart
        v-if="searchOption===1 || searchOption === 4"
        :chart-data="chartdata"
        :options="chartoptions"
      ></bar-chart>
      <line-chart
        v-if="searchOption === 2 || searchOption === 3"
        :chart-data="chartdata"
        :options="chartoptions"
      ></line-chart>
    </div>
  </div>
</template>

<script>
import BarChart from "./BarChart";
import LineChart from "./LineChart";
import axios from "../../js/http-commons";

export default {
  components: {
    BarChart,
    LineChart
  },
  data() {
    return {
      chartdata: null,
      chartoptions: null,
      result: null,
      road: "",
      key: "",
      searchOption: 1,
      title: "연령별 유동인구"
    };
  },
  methods: {
    setOpt1() {
      this.searchOption = 1;
      this.title = "연령별 유동인구";

      if (this.key !== null) {
        this.getData();
      }
    },
    setOpt2() {
      this.searchOption = 2;
      this.title = "시간별 유동인구";

      if (this.key !== null) {
        this.getData();
      }
    },
    setOpt3() {
      this.searchOption = 3;
      this.title = "요일별 유동인구";

      if (this.key !== null) {
        this.getData();
      }
    },
    setOpt4() {
      this.searchOption = 4;
      this.title = "연도별 상권 변화 지표";

      if (this.key !== null) {
        this.getData();
      }
    },
    getData() {
      if (this.searchOption === 1) {
        axios
          .get("/population/getPopulationByLocation/" + this.key)
          .then(res => {
            this.result = res.data;
            this.road = this.result.f;
          })
          .catch(err => {
            alert(err);
          })
          .finally(() => {
            this.chartdata = {
              labels: ["10대", "20대", "30대", "40대", "50대", "60대 이상"],
              datasets: [
                {
                  label: "정보",
                  backgroundColor: [
                    "rgba(48, 109, 255, 0.6)",
                    "rgba(255, 73, 23, 0.6)",
                    "rgba(164, 245, 140, 0.6)",
                    "rgba(255, 243, 23, 0.6)",
                    "rgba(1, 99, 132, 0.6)",
                    "rgba(34, 199, 232, 0.6)"
                  ],
                  data: [
                    this.result.j,
                    this.result.k,
                    this.result.l,
                    this.result.m,
                    this.result.n,
                    this.result.o
                  ]
                }
              ]
            };

            this.chartoptions = {
              responsive: true,
              maintainAspectRatio: false,
              scales: {
                yAxes: [
                  {
                    ticks: {
                      beginAtZero: true
                    },
                    gridLines: {
                      display: true
                    }
                  }
                ],
                xAxes: [
                  {
                    gridLines: {
                      display: true
                    }
                  }
                ]
              }
            };
          });
      } else if (this.searchOption === 2) {
        axios
          .get("/population/getPopulationByTime/" + this.key)
          .then(res => {
            this.result = res.data;
            this.road = this.result.f;
          })
          .catch(err => {
            alert(err);
          })
          .finally(() => {
            this.chartdata = {
              labels: [
                "24~06시",
                "06~11시",
                "11~14시",
                "14~17시",
                "17~21시",
                "21~24시"
              ],
              datasets: [
                {
                  label: "정보",
                  fill: false,
                  borderColor: "red",
                  data: [
                    this.result.j,
                    this.result.k,
                    this.result.l,
                    this.result.m,
                    this.result.n,
                    this.result.o
                  ]
                }
              ]
            };

            this.chartoptions = {
              responsive: true,
              maintainAspectRatio: false,
              scales: {
                yAxes: [
                  {
                    ticks: {
                      beginAtZero: true
                    },
                    gridLines: {
                      display: true
                    }
                  }
                ],
                xAxes: [
                  {
                    gridLines: {
                      display: true
                    }
                  }
                ]
              }
            };
          });
      } else if (this.searchOption === 3) {
        axios
          .get("/population/getPopulationByTime/" + this.key)
          .then(res => {
            this.result = res.data;
            this.road = this.result.f;
          })
          .catch(err => {
            alert(err);
          })
          .finally(() => {
            this.chartdata = {
              labels: [
                "월요일",
                "화요일",
                "수요일",
                "목요일",
                "금요일",
                "토요일",
                "일요일"
              ],
              datasets: [
                {
                  label: "정보",
                  fill: false,
                  borderColor: "red",
                  data: [
                    this.result.p,
                    this.result.q,
                    this.result.r,
                    this.result.s,
                    this.result.t,
                    this.result.u,
                    this.result.v
                  ]
                }
              ]
            };

            this.chartoptions = {
              responsive: true,
              maintainAspectRatio: false,
              scales: {
                yAxes: [
                  {
                    ticks: {
                      beginAtZero: true
                    },
                    gridLines: {
                      display: true
                    }
                  }
                ],
                xAxes: [
                  {
                    gridLines: {
                      display: true
                    }
                  }
                ]
              }
            };
          });
      } else {
        axios
          .get("/change/getHistory/" + this.key)
          .then(res => {
            this.result = res.data.cblist;
            this.road = res;
          })
          .catch(err => {
            alert(err);
          })
          .finally(() => {
            this.chartdata = {
              labels: ["2014", "2015", "2016", "2017", "2018", "2019"],
              datasets: [
                {
                  label: "운영 영업 개월 평균",
                  backgroundColor: [
                    "rgba(48, 109, 255, 0.6)",
                    "rgba(255, 73, 23, 0.6)",
                    "rgba(164, 245, 140, 0.6)",
                    "rgba(255, 243, 23, 0.6)",
                    "rgba(1, 99, 132, 0.6)",
                    "rgba(34, 199, 232, 0.6)"
                  ],
                  data: [1, 2, 3, 4, 5, 6]
                },
                {
                  label: "폐업 영업 개월 평균",
                  backgroundColor: [
                    "rgba(48, 109, 255, 0.6)",
                    "rgba(255, 73, 23, 0.6)",
                    "rgba(164, 245, 140, 0.6)",
                    "rgba(255, 243, 23, 0.6)",
                    "rgba(1, 99, 132, 0.6)",
                    "rgba(34, 199, 232, 0.6)"
                  ],
                  data: [1, 2, 3, 4, 5, 6]
                }
              ]
            };

            this.chartoptions = {
              responsive: true,
              maintainAspectRatio: false,
              scales: {
                yAxes: [
                  {
                    ticks: {
                      beginAtZero: true
                    },
                    gridLines: {
                      display: true
                    }
                  }
                ],
                xAxes: [
                  {
                    gridLines: {
                      display: true
                    }
                  }
                ]
              }
            };
          });
      }
    }
  }
};
</script>

<style scoped>
#chart1 {
  width: 600px;
}
</style>