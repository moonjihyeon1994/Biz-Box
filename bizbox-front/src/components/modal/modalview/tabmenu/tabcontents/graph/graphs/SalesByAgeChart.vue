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
    <p class="point-content-area Content" style="font-size: 1.2em;
    font-weight: bold;">
      <span class="point-title" v-if="isOk">{{maxAgeMaker}}</span>
      <span class="point-percent" v-if="isOk">{{percentMaker}}</span>
      <span class="point-normal" v-if="isOk">소비가 가장 많아요.</span>
       <span class="point-normal" v-if="!isOk">데이터 업데이트 예정입니다.</span>
    </p>
    <div id="chart">
      <loading :loading="loadingStatus" :transparent='true'></loading>
      <horizontal-bar-chart
        :chart-data="chartdata"
        :options="chartoptions"
        width="480px"
        height="300px"
      ></horizontal-bar-chart>
    </div>
  </div>
</template>

<script>
import HorizontalBarChart from '@/lib/HorizontalBarChart'
import axios from '@/js/http-commons'
import Loading from '@/components/common/loading/Loading'
import './Graphs.css'
import { eventBus } from '@/js/bus'
export default {
  components: {
    HorizontalBarChart,
    Loading
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
      loadingStatus: false,
      allowDiv: {
        display: 'none'
      },
      maxIndex: 0,
      barColors: ['#365673', '#365673', '#365673', '#365673', '#365673', '#365673']
    }
  },
  computed: {
    percentMaker: function () {
      if (this.result == null) return
      let totalNum = Math.max.apply(null, this.data)
      return '(' + totalNum.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',') + '원' + ')'
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
    },
    lng () {
      return this.$store.state.Coords.lng
    },
    lat () {
      return this.$store.state.Coords.lat
    }
  },
  mounted () {
    this.draw()
    eventBus.$on('clickmap', name => {
      this.draw()
       this.isOk=true
      this.getData()
    })
  },
  methods: {
    popup () {
      this.popflag = !this.popflag
    },
    draw () {
      this.searchOption = 1
      this.title = '연령별 매출'
    },
    getData () {
      this.loadingStatus = true
      this.allowDiv.display = 'block'

      let sumOf10 = 0
      let sumOf20 = 0
      let sumOf30 = 0
      let sumOf40 = 0
      let sumOf50 = 0
      let sumOf60 = 0

      axios
        // .get('/sales/' + this.key)
       // .get('/predict/findBusiness/' + this.$store.state.Coords.lng + '/' + this.$store.state.Coords.lat)
        .get('/predict/findBusiness2/' + this.$store.state.place )
        .then(res => {
          if(res.data['2018'].length===0){
            this.isOk=false
          }
          else{this.isOk=true}
          this.result = res.data['2018']

          for (let index = 0; index < this.result.length; index++) {
            sumOf10 += Number(this.result[index].agrde_10_selng_amt)
            sumOf20 += Number(this.result[index].agrde_20_selng_amt)
            sumOf30 += Number(this.result[index].agrde_30_selng_amt)
            sumOf40 += Number(this.result[index].agrde_40_selng_amt)
            sumOf50 += Number(this.result[index].agrde_50_selng_amt)
            sumOf60 += Number(this.result[index].agrde_60_above_selng_amt)
          }
          this.data = [sumOf10, sumOf20, sumOf30, sumOf40, sumOf50, sumOf60]
          sumOf10 /= 100000000
          sumOf20 /= 100000000
          sumOf30 /= 100000000
          sumOf40 /= 100000000
          sumOf50 /= 100000000
          sumOf60 /= 100000000
        })
        // .then(() => {
        //   let maxData = this.data[0]
        //   for (let index = 1; index < this.data.length; index++) {
        //     if (maxData < this.data[index]) {
        //       maxData = this.data[index]
        //       this.maxIndex = index
        //     }
        //   }
        // })
        // .then(() => {
        //   this.barColors[this.maxIndex] = '#ff4d4d'
        // })
        .finally(() => {
          this.chartdata = {
            labels: ['10대', '20대', '30대', '40대', '50대', '60대 이상'],
            datasets: [
              {
                label: '단위(억원)',
                // backgroundColor: this.barColors,
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
            responsive: false,
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

          this.loadingStatus = false
          this.allowDiv.display = 'none'
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

$color1: rgb(232, 113, 91);
$color2: rgb(15, 66, 95);

.Content {
  width: 100%;
  padding: 10px 20px;
  margin: 20px 0;
  background-color: $color2;
  border-radius: 5px;
  color: $color1;
  box-shadow: 2px 3px 5px rgba(0, 0, 0, 0.5);
  text-align: center;

  .strong {
    color: rgb(223, 223, 223);
  }

  h2 {
    font-size: 1.2em;
    font-weight: bold;
  }
}
</style>
