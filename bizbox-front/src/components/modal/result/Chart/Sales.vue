<template>
  <div>
      <div id="search">
      <div id="searchOptions">
        <button @click="setOpt1()" v-bind:style="btnStyle1" :disabled="loadingStatus">연령별 매출</button>
        <button @click="setOpt2()" v-bind:style="btnStyle2" :disabled="loadingStatus">시간별 매출</button>
        <button @click="setOpt3()" v-bind:style="btnStyle3" :disabled="loadingStatus">요일별 매출</button>
        <button @click="setOpt4()" v-bind:style="btnStyle4" :disabled="loadingStatus">성별에 따른 매출</button>
      </div>
      <input type="text" placeholder="검색하세요." v-model="key" @keyup.enter="getData()" />
      <button id="search-btn" @click="getData()">검색</button>
    </div>
    <p id="search-result">검색된 결과 : {{ road }}</p>
    <hr />
    <div id="chart1">
      <div id="back" :style="allowDiv"></div>
      <spinner :loading="loadingStatus"></spinner>
      <horizontal-bar-chart
        v-if="searchOption === 1"
        :chart-data="chartdata"
        :options="chartoptions"
        width='500px'
        height='300px'
      ></horizontal-bar-chart>
      <line-chart
        v-if="searchOption === 2"
        :chart-data="chartdata"
        :options="chartoptions"
        width='500px'
        height='300px'
      ></line-chart>
      <pie-chart v-if="searchOption === 3 || searchOption == 4"
        :chart-data="chartdata"
        :options="chartoptions"
        width='500px'
        height='300px'></pie-chart>
    </div>
  </div>
</template>

<script>
import HorizontalBarChart from '@/lib/HorizontalBarChart'
import PieChart from '@/lib/PieChart'
import LineChart from '@/lib/LineChart'
import axios from '@/js/http-commons'
import Spinner from '@/components/common/Spinner'

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
      this.allowDiv.display = 'block'
      this.btnStyle1.cursor = 'not-allowed'
      this.btnStyle2.cursor = 'not-allowed'
      this.btnStyle3.cursor = 'not-allowed'
      this.btnStyle4.cursor = 'not-allowed'

      if (this.searchOption === 1) {
        let sumOf10 = 0
        let sumOf20 = 0
        let sumOf30 = 0
        let sumOf40 = 0
        let sumOf50 = 0
        let sumOf60 = 0

        axios
          .get('/sales/' + this.key)
          .then(res => {
            this.result = res.data
            this.road = res.data[0].d
            this.point = res.data.point

            for (let index = 0; index < this.result.length; index++) {
              sumOf10 += Number(this.result[index].z)
              sumOf20 += Number(this.result[index].aa)
              sumOf30 += Number(this.result[index].ab)
              sumOf40 += Number(this.result[index].ac)
              sumOf50 += Number(this.result[index].ad)
              sumOf60 += Number(this.result[index].ae)
            }

            sumOf10 /= 100000000
            sumOf20 /= 100000000
            sumOf30 /= 100000000
            sumOf40 /= 100000000
            sumOf50 /= 100000000
            sumOf60 /= 100000000
          })
          .finally(() => {
            this.chartdata = {
              labels: ['10대', '20대', '30대', '40대', '50대', '60대 이상'],
              datasets: [
                {
                  label: '전체',
                  backgroundColor: '#365673',
                  data: [
                    sumOf10.toFixed(2),
                    sumOf20.toFixed(2),
                    sumOf30.toFixed(2),
                    sumOf40.toFixed(2),
                    sumOf50.toFixed(2),
                    sumOf60.toFixed(2)
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
        let sum1 = 0
        let sum2 = 0
        let sum3 = 0
        let sum4 = 0
        let sum5 = 0
        let sum6 = 0

        axios
          .get('/sales/' + this.key)
          .then(res => {
            this.result = res.data
            this.road = res.data[0].d
            this.point = res.data.point

            for (let index = 0; index < this.result.length; index++) {
              sum1 += Number(this.result[index].r)
              sum2 += Number(this.result[index].s)
              sum3 += Number(this.result[index].t)
              sum4 += Number(this.result[index].u)
              sum5 += Number(this.result[index].v)
              sum6 += Number(this.result[index].w)
            }

            sum1 /= 100000000
            sum2 /= 100000000
            sum3 /= 100000000
            sum4 /= 100000000
            sum5 /= 100000000
            sum6 /= 100000000
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
                    sum1.toFixed(2),
                    sum2.toFixed(2),
                    sum3.toFixed(2),
                    sum4.toFixed(2),
                    sum5.toFixed(2),
                    sum6.toFixed(2)
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
      } else if (this.searchOption === 3) {
        let sum1 = 0
        let sum2 = 0
        let sum3 = 0
        let sum4 = 0
        let sum5 = 0
        let sum6 = 0
        let sum7 = 0

        axios
          .get('/sales/' + this.key)
          .then(res => {
            this.result = res.data
            this.road = res.data[0].d
            this.point = res.data.point

            for (let index = 0; index < this.result.length; index++) {
              sum1 += Number(this.result[index].k)
              sum2 += Number(this.result[index].l)
              sum3 += Number(this.result[index].m)
              sum4 += Number(this.result[index].n)
              sum5 += Number(this.result[index].o)
              sum6 += Number(this.result[index].p)
              sum7 += Number(this.result[index].q)
            }

            sum1 /= 100000000
            sum2 /= 100000000
            sum3 /= 100000000
            sum4 /= 100000000
            sum5 /= 100000000
            sum6 /= 100000000
            sum7 /= 100000000
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
                    sum1.toFixed(2),
                    sum2.toFixed(2),
                    sum3.toFixed(2),
                    sum4.toFixed(2),
                    sum5.toFixed(2),
                    sum6.toFixed(2),
                    sum7.toFixed(2)
                  ],
                  backgroundColor: ['#eb4034', '#ffe373', '#89ff45', '#73ffde', '#5ca0ff', '#d152ff', '#ff63d0']
                }
              ]
            }

            this.chartoptions = {
              responsive: true,
              maintainAspectRatio: true
            }

            this.loadingStatus = false
            this.allowDiv.display = 'none'
            this.btnStyle1.cursor = 'pointer'
            this.btnStyle2.cursor = 'pointer'
            this.btnStyle3.cursor = 'pointer'
            this.btnStyle4.cursor = 'pointer'
          })
      } else {
        let sumWoman = 0
        let sumMan = 0

        axios.get('/sales/' + this.key)
          .then(res => {
            this.result = res.data
            this.road = res.data[0].d

            for (let index = 0; index < this.result.length; index++) {
              sumWoman += Number(this.result[index].y)
              sumMan += Number(this.result[index].x)
            }

            sumWoman /= 100000000
            sumMan /= 100000000
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
              maintainAspectRatio: true
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
[v-cloak] {
    display: none;
}

#chart1 {
  position: relative;
  width: 500px;
  height: 300px;
  overflow: hidden;
  margin-bottom: 100px;
}

#back {
  position: absolute;
  z-index: 2;
  width: 100%;
  height: 100%;
  background-color: rgb(255, 255, 255);
}

#back:hover {
  cursor: not-allowed;
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
