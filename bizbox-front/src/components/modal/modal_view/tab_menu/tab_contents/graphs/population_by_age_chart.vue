<template>
  <div id="chart1">
    연령별 인구
    <div id="back" :style="allowDiv"></div>
    <spinner :loading="loadingStatus"></spinner>
    <bar-chart
      :chart-data="chartdata"
      :options="chartoptions"
      width="300px"
      height="100px"
    ></bar-chart>
  </div>
</template>

<script>
import BarChart from '@/lib/BarChart'
import axios from '@/js/http-commons'
import Spinner from '../../../../result/Spinner'
export default {
  components: {
    BarChart,
    Spinner
  },
  data () {
    return {
      chartdata: null,
      chartoptions: null,
      result: null,
      road: '',
      key: '오류동',
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
  mounted () {
    this.draw()
  },
  methods: {
    draw () {
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
    getData () {
      this.loadingStatus = true
      this.allowDiv.display = 'block'
      this.btnStyle1.cursor = 'not-allowed'
      this.btnStyle2.cursor = 'not-allowed'
      this.btnStyle3.cursor = 'not-allowed'
      this.btnStyle4.cursor = 'not-allowed'

      axios
        .get('/population/getPopulationByLocation/' + this.key)
        .then(res => {
          console.log(res)
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

#searchOptions button:hover {
  background-color: #e38fe3;
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
