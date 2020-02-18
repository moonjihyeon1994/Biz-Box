<template>
  <div>
      <div id="search">
      <div id="searchOptions">
        <button @click="setOpt1()" v-bind:style="btnStyle1" :disabled="loadingStatus">연령별 유동인구</button>
        <button @click="setOpt2()" v-bind:style="btnStyle2" :disabled="loadingStatus">시간별 유동인구</button>
        <button @click="setOpt3()" v-bind:style="btnStyle3" :disabled="loadingStatus">요일별 유동인구</button>
        <button @click="setOpt4()" v-bind:style="btnStyle4" :disabled="loadingStatus">연도별 상권 변화 지표</button>
      </div>
      <input type="text" placeholder="검색하세요." v-model="key" @keyup.enter="getData()" />
      <button id="search-btn" @click="getData()">검색</button>
    </div>
    <p id="search-result">검색된 결과 : {{ road }}</p>
    <hr />
    <div id="chart1">
      <div id="back" :style="allowDiv"></div>
      <spinner :loading="loadingStatus"></spinner>
      <bar-chart
        v-if="searchOption === 1 || searchOption === 4"
        :chart-data="chartdata"
        :options="chartoptions"
        width='500px'
        height='300px'
      ></bar-chart>
      <line-chart
        v-if="searchOption === 2 || searchOption === 3"
        :chart-data="chartdata"
        :options="chartoptions"
        width='500px'
        height='300px'
      ></line-chart>
    </div>
    <div id="point">
      <p>{{ point }}</p>
    </div>
  </div>
</template>

<script>
import BarChart from '@/lib/BarChart'
import LineChart from '@/lib/LineChart'
import axios from '@/js/http-commons'
import Spinner from '@/components/common/Spinner'

export default {
  components: {
    BarChart,
    LineChart,
    Spinner
  },
  data () {
    return {
      chartdata: null,
      chartoptions: null,
      result: null,
      road: '',
      key: '',
      searchOption: 1,
      title: '연령별 유동인구',
      point: 0,
      btnStyle1: {
        backgroundColor: '#d9d9d9',
        cursor: 'pointer'
      },
      btnStyle2: {
        backgroundColor: 'white',
        cursor: 'pointer'
      },
      btnStyle3: {
        backgroundColor: 'white',
        cursor: 'pointer'
      },
      btnStyle4: {
        backgroundColor: 'white',
        cursor: 'pointer'
      },
      chartStyle: {
        display: 'contents'
      },
      loadingStatus: false,
      allowDiv: {
        display: 'none'
      }
    }
  },
  methods: {
    setOpt1 () {
      this.chartdata = null
      this.chartoptions = null

      this.searchOption = 1
      this.title = '연령별 유동인구'
      this.btnStyle1.backgroundColor = '#d9d9d9'
      this.btnStyle2.backgroundColor = 'white'
      this.btnStyle3.backgroundColor = 'white'
      this.btnStyle4.backgroundColor = 'white'

      if (this.key !== '') {
        this.getData()
      }
    },
    setOpt2 () {
      this.chartdata = null
      this.chartoptions = null

      this.searchOption = 2
      this.title = '시간별 유동인구'
      this.btnStyle1.backgroundColor = 'white'
      this.btnStyle2.backgroundColor = '#d9d9d9'
      this.btnStyle3.backgroundColor = 'white'
      this.btnStyle4.backgroundColor = 'white'

      if (this.key !== '') {
        this.getData()
      }
    },
    setOpt3 () {
      this.chartdata = null
      this.chartoptions = null

      this.searchOption = 3
      this.title = '요일별 유동인구'
      this.btnStyle1.backgroundColor = 'white'
      this.btnStyle2.backgroundColor = 'white'
      this.btnStyle3.backgroundColor = '#d9d9d9'
      this.btnStyle4.backgroundColor = 'white'

      if (this.key !== '') {
        this.getData()
      }
    },
    setOpt4 () {
      this.chartdata = null
      this.chartoptions = null

      this.searchOption = 4
      this.title = '연도별 상권 변화 지표'
      this.btnStyle1.backgroundColor = 'white'
      this.btnStyle2.backgroundColor = 'white'
      this.btnStyle3.backgroundColor = 'white'
      this.btnStyle4.backgroundColor = '#d9d9d9'

      if (this.key !== '') {
        this.getData()
      }
    },
    getData () {
      this.loadingStatus = true
      this.allowDiv.display = 'block'
      this.btnStyle1.cursor = 'not-allowed'
      this.btnStyle2.cursor = 'not-allowed'
      this.btnStyle3.cursor = 'not-allowed'
      this.btnStyle4.cursor = 'not-allowed'

      if (this.searchOption === 1) {
        axios
          .get('/population/getPopulationByLocation/' + this.key)
          .then(res => {
            this.result = res.data.pbl
            this.road = this.result.f
            this.point = res.data.point
          })
          .finally(() => {
            this.chartdata = {
              labels: ['10대', '20대', '30대', '40대', '50대', '60대 이상'],
              datasets: [
                {
                  label: '전체',
                  backgroundColor: '#365673',
                  data: [
                    this.result.j,
                    this.result.k,
                    this.result.l,
                    this.result.m,
                    this.result.n,
                    this.result.o
                  ]
                },
                {
                  label: '남자',
                  backgroundColor: '#74ddf7',
                  data: [
                    this.result.p,
                    this.result.q,
                    this.result.r,
                    this.result.s,
                    this.result.t,
                    this.result.u
                  ]
                },
                {
                  label: '여자',
                  backgroundColor: '#ff6390',
                  data: [
                    this.result.v,
                    this.result.w,
                    this.result.x,
                    this.result.y,
                    this.result.z,
                    this.result.aa
                  ]
                }
              ]
            }

            this.chartoptions = {
              responsive: true,
              maintainAspectRatio: true,
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
            }

            this.loadingStatus = false
            this.allowDiv.display = 'none'
            this.btnStyle1.cursor = 'pointer'
            this.btnStyle2.cursor = 'pointer'
            this.btnStyle3.cursor = 'pointer'
            this.btnStyle4.cursor = 'pointer'
          })
      } else if (this.searchOption === 2) {
        axios
          .get('/population/getPopulationByTime/' + this.key)
          .then(res => {
            this.result = res.data.pbt
            this.road = this.result.f
            this.point = res.data.point
          })
          .finally(() => {
            this.chartdata = {
              labels: [
                '24~06시',
                '06~11시',
                '11~14시',
                '14~17시',
                '17~21시',
                '21~24시'
              ],
              datasets: [
                {
                  label: '정보',
                  fill: false,
                  borderColor: 'red',
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
            }

            this.chartoptions = {
              responsive: true,
              maintainAspectRatio: true,
              scales: {
                yAxes: [
                  {
                    ticks: {
                      beginAtZero: false
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
            }

            this.loadingStatus = false
            this.allowDiv.display = 'none'
            this.btnStyle1.cursor = 'pointer'
            this.btnStyle2.cursor = 'pointer'
            this.btnStyle3.cursor = 'pointer'
            this.btnStyle4.cursor = 'pointer'
          })
      } else if (this.searchOption === 3) {
        axios
          .get('/population/getPopulationByTime/' + this.key)
          .then(res => {
            this.result = res.data.pbt
            this.road = this.result.f
            this.point = res.data.point
          })
          .finally(() => {
            this.chartdata = {
              labels: [
                '월요일',
                '화요일',
                '수요일',
                '목요일',
                '금요일',
                '토요일',
                '일요일'
              ],
              datasets: [
                {
                  label: '정보',
                  fill: false,
                  borderColor: 'red',
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
            }

            this.chartoptions = {
              responsive: true,
              maintainAspectRatio: true,
              scales: {
                yAxes: [
                  {
                    ticks: {
                      beginAtZero: false
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
            }

            this.loadingStatus = false
            this.allowDiv.display = 'none'
            this.btnStyle1.cursor = 'pointer'
            this.btnStyle2.cursor = 'pointer'
            this.btnStyle3.cursor = 'pointer'
            this.btnStyle4.cursor = 'pointer'
          })
      } else {
        axios
          .get('/change/getHistory/' + this.key)
          .then(res => {
            this.result = res.data.cblist
            this.road = this.result[0].d
            this.point = res.data.point
          })
          .finally(() => {
            this.chartdata = {
              labels: ['2014', '2015', '2016', '2017', '2018', '2019'],
              datasets: [
                {
                  label: '운영 영업 개월 평균',
                  backgroundColor: '#74ddf7',
                  data: [this.result[0].g, this.result[1].g, this.result[2].g, this.result[3].g, this.result[4].g, this.result[5].g]
                },
                {
                  label: '폐업 영업 개월 평균',
                  backgroundColor: '#ff6390',
                  data: [this.result[0].h, this.result[1].h, this.result[2].h, this.result[3].h, this.result[4].h, this.result[5].h]
                }
              ]
            }

            this.chartoptions = {
              responsive: true,
              maintainAspectRatio: true,
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
            }

            this.loadingStatus = false
            this.allowDiv.display = 'none'

            this.btnStyle1.cursor = 'pointer'
            this.btnStyle2.cursor = 'pointer'
            this.btnStyle3.cursor = 'pointer'
            this.btnStyle4.cursor = 'pointer'
          })
      }
    }
  }
}
</script>

<style scoped lang="scss">
#chart1 {
  position: relative;
  width: 500px;
  height: 300px;
  overflow: hidden;
}

#back {
  position: absolute;
  z-index: 2;
  width: 100%;
  height: 100%;
  background-color: rgb(255, 255, 255);
}

#point {
  border: 1px solid black;
  border-radius: 5px;
  width: 500px;
  height: 40px;
  line-height: 40px;
  top: 5px;
  font-size: 24px;
  margin-top: 10px;
  background-color: white;
}

#searchOptions {
  margin: auto;

  button {
    font-size: 16px;
    display: inline-block;
    border: 1px solid black;
    border-radius: 5px;
    width: 200px;
    height: 30px;
    margin-right: 10px;
    background-color: white;
    transition: 0.1s all ease;

    &:hover {
      font-weight: bold;
    }
  }
}

#searchOptions button:hover{
    background-color: #E38FE3;
}

#search input {
  border: 1px solid black;
  border-radius: 5px 0 0 5px;
  width: 200px;
  height: 30px;
  margin-top: 10px;
  padding: 5px;
  background-color: white;
}

#search-btn {
  border-radius: 0 5px 5px 0;
  width: 50px;
  height: 30px;
  background-color: rgb(172, 172, 172);
}

#search-result {
  margin-top: 5px;
}
</style>
