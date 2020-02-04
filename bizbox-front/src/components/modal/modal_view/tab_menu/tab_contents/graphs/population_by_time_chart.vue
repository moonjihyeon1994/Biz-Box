<template>
  <div id="chart1">
    시간별 유동인구
    <div id="back" :style="allowDiv"></div>
    <spinner :loading="loadingStatus"></spinner>
    <line-chart
      :chart-data="chartdata"
      :options="chartoptions"
      width="500px"
      height="300px"
    ></line-chart>
  </div>
</template>

<script>
import LineChart from '@/lib/LineChart'
import axios from '@/js/http-commons'
import Spinner from '../../../../result/Spinner'
export default {
  components: {
    LineChart,
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
    getData () {
      this.loadingStatus = true
      this.allowDiv.display = 'block'
      this.btnStyle1.cursor = 'not-allowed'
      this.btnStyle2.cursor = 'not-allowed'
      this.btnStyle3.cursor = 'not-allowed'
      this.btnStyle4.cursor = 'not-allowed'

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
