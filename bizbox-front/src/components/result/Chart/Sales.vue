<template>
  <div>
      <div id="search">
      <div id="searchOptions">
        <button @click="setOpt1()" v-bind:style="btnStyle1">연령별 매출</button>
        <button @click="setOpt2()" v-bind:style="btnStyle2">시간별 매출</button>
        <button @click="setOpt3()" v-bind:style="btnStyle3">요일별 매출</button>
        <button @click="setOpt4()" v-bind:style="btnStyle4">성별 매출</button>
      </div>
      <input type="text" placeholder="검색하세요." v-model="key" @keyup.enter="getData()" />
      <button id="search-btn" @click="getData()">검색</button>
    </div>
    <p id="search-result">검색된 결과 : {{ road }}</p>
    <hr />
    <div id="chart1">
      <spinner :loading="loadingStatus"></spinner>
      <horizontal-bar-chart
        v-if="searchOption === 1"
        :chart-data="chartdata"
        :options="chartoptions"
      ></horizontal-bar-chart>
      <line-chart
        v-if="searchOption === 2"
        :chart-data="chartdata"
        :options="chartoptions"
      ></line-chart>
      <pie-chart v-if="searchOption === 3 || searchOption == 4"
        :chart-data="chartdata"
        :options="chartoptions"></pie-chart>
    </div>
    <div id="point">
      <p>{{ point }}</p>
    </div>
  </div>
</template>

<script>
import HorizontalBarChart from '../js/HorizontalBarChart'
import PieChart from '../js/PieChart'
import LineChart from '../js/LineChart'
import axios from '../../../js/http-commons'
import Spinner from '../Spinner'

export default {
  components: {
    LineChart,
    HorizontalBarChart,
    PieChart,
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
      title: '연령별 매출',
      point: 0,
      btnStyle1: {
        backgroundColor: '#d9d9d9'
      },
      btnStyle2: {
        backgroundColor: 'white'
      },
      btnStyle3: {
        backgroundColor: 'white'
      },
      btnStyle4: {
        backgroundColor: 'white'
      },
      loadingStatus: false
    }
  },
  methods: {
    setOpt1 () {
      this.chartdata = null
      this.chartoptions = null

      this.searchOption = 1
      this.title = '연령별 매출'
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
      this.title = '시간별 매출'
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
      this.title = '요일별 매출'
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
      this.title = '성별 매출'
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

      if (this.searchOption === 1) {
        axios
          .get('/sales/' + this.key)
          .then(res => {
            this.result = res.data
            this.road = res.data
            this.point = res.data.point
          })
          .catch(err => {
            alert(err, '검색어를 확인해주세요.')
          })
          .finally(() => {
            this.chartdata = {
              labels: ['10대', '20대', '30대', '40대', '50대', '60대 이상'],
              datasets: [
                {
                  label: '전체',
                  backgroundColor: '#365673',
                  data: [

                  ]
                }
              ]
            }

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
            }
          })
      } else if (this.searchOption === 2) {
        axios
          .get('/sales/' + this.key)
          .then(res => {
            this.result = res.data
            this.road = this.key
            this.point = res.data.point
          })
          .catch(err => {
            alert(err, '검색어를 확인해주세요.')
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
                  ]
                }
              ]
            }

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
            }
          })
      } else if (this.searchOption === 3) {
        axios
          .get('/sales/' + this.key)
          .then(res => {
            this.result = res.data
            this.road = this.key
            this.point = res.data.point
          })
          .catch(err => {
            alert(err, '검색어를 확인해주세요.')
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
                  fill: true,
                  data: [
                  ],
                  backgroundColor: ['#eb4034', '#ffe373', '#89ff45', '#73ffde', '#5ca0ff', '#d152ff', '#ff63d0']
                }
              ]
            }

            this.chartoptions = {
              responsive: true,
              maintainAspectRatio: false
            }
          })
      } else {
        let sumWoman = 0
        let sumMan = 0

        axios.get('/sales/' + this.key)
          .then(res => {
            this.result = res.data
            this.road = this.key

            for (let index = 0; index < this.result.length; index++) {
              sumWoman += Number(this.result[index].y)
              sumMan += Number(this.result[index].x)
            }

            sumWoman /= 100000000
            sumMan /= 100000000
          }).catch(err => {
            alert(err)
          }).finally(() => {
            this.chartdata = {
              labels: [
                '여자',
                '남자'
              ],
              datasets: [
                {
                  fill: true,
                  data: [
                    sumWoman.toFixed(2),
                    sumMan.toFixed(2)
                  ],
                  backgroundColor: ['#ff2483', '#1c8aff']
                }
              ]
            }

            this.chartoptions = {
              responsive: true,
              maintainAspectRatio: false
            }
          })
      }

      this.loadingStatus = false
    }
  }
}
</script>

<style scoped>
[v-cloak] {
    display: none;
}

#chart1 {
  width: 600px;
}

#point {
  border: 1px solid black;
  border-radius: 5px;
  width: 600px;
  height: 50px;
  line-height: 50px;
  top: 5px;
  font-size: 24px;
  margin-top: 10px;
  margin-bottom: 50px;
  background-color: white;
}

#searchOptions {
  margin: auto;
}

#searchOptions button {
  font-size: 16px;
  display: inline-block;
  border: 1px solid black;
  border-radius: 5px;
  width: 200px;
  height: 30px;
  margin-right: 10px;
  background-color: white;
  transition: 0.2s all ease;
}

#searchOptions button:hover{
  background-color: rgb(224, 224, 224);
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
