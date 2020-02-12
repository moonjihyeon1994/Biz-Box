<template>
  <div class="secition-content">
    <div class="secition-content-title-area">
      <h2 class="section-content-title">
        연령별 소비
        <span class="icon-question" @click="popup"><v-icon size=15>mdi-help-circle-outline</v-icon>
        <span v-show="popflag" class="icon-popup-tri"/></span>
        <span v-show="popflag" class="icon-popup">공공데이터 상권 관련 데이터를 분석해서 생성한 정보입니다.</span>
      </h2>
      <div class="section-content-update">2020-02-05 업데이트</div>
    </div>
    <p class="point-content-area">
      <span class="point-title">{{maxAgeMaker}}</span>
      <span class="point-percent">{{percentMaker}}</span>
      <span class="point-normal">소비가 가장 많아요.</span>
    </p>
    <div id="chart">
      <div id="back" :style="allowDiv"></div>
      <spinner :loading="loadingStatus"></spinner>
      <horizontal-bar-chart
        :chart-data="chartdata"
        :options="chartoptions"
        width="500px"
        height="300px"
      ></horizontal-bar-chart>
    </div>
  </div>
</template>

<script>
import HorizontalBarChart from '@/lib/HorizontalBarChart'
import axios from '@/js/http-commons'
import Spinner from '../../../../result/Spinner'
import './graphs.css'
export default {
  components: {
    HorizontalBarChart,
    Spinner
  },
  data () {
    return {
      data: null,
      popflag: false,
      chartdata: null,
      chartoptions: null,
      result: null,
      road: '',
      key: this.$store.state.modalsearch,
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
  computed: {
    percentMaker: function () {
      if (this.result == null) return
      let totalNum = Math.max.apply(null, this.data)
      return '(' + totalNum + '원' + ')'
    },
    maxAgeMaker: function () {
      if (this.result == null) return
      let labels = ['10대', '20대', '30대', '40대', '50대', '60대 이상']
      let maxAge = -1
      let idx = 0
      for (let index = 0; index < this.data.length; index++) {
        if (maxAge < this.data[index]) {
          maxAge = this.data[index]
          idx = index
        }
      }
      return labels[idx]
    }
  },
  mounted () {
    this.draw()
  },
  methods: {
    popup () {
      console.log('popup')
      this.popflag = !this.popflag
    },
    draw () {
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
    getData () {
      this.loadingStatus = true
      this.allowDiv.display = 'block'
      this.btnStyle1.cursor = 'not-allowed'
      this.btnStyle2.cursor = 'not-allowed'
      this.btnStyle3.cursor = 'not-allowed'
      this.btnStyle4.cursor = 'not-allowed'

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
          this.data = [sumOf10, sumOf20, sumOf30, sumOf40, sumOf50, sumOf60]
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
    }
  }
}
</script>

<style scoped lang="scss">
[v-cloak] {
    display: none;
}

#chart {
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
