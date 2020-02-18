<template>
  <div id='bz-container'>
    <line-chart id='chart' :chart-data="chartdata" :options="chartoptions" :width='chartWidth' :height='chartHeight'></line-chart>
  </div>
</template>

<script>
import LineChart from '@/lib/LineChart'
import axios from '@/js/http-commons.js'
import { eventBus } from '@/js/bus'

export default {
  components: {
    LineChart
  },
  props: {
    chartWidth: String,
    chartHeight: String,
    large: String,
    mid: String
  },
  data () {
    return {
      chartdata: null,
      chartoptions: null
    }
  },
  methods: {
    renderChart () {
      this.$emit('loadingEvent', true)
      axios.get('/predict/findBusiness/' + this.$store.state.Coords.lng + '/' + this.$store.state.Coords.lat)
        .then(res => {
          let year2016 = {}
          let year2017 = {}
          let year2018 = {}

          const arr2016 = res.data[2016]
          const arr2017 = res.data[2017]
          const arr2018 = res.data[2018]

          year2016 = this.getData(arr2016, this.large, this.mid)
          year2017 = this.getData(arr2017, this.large, this.mid)
          year2018 = this.getData(arr2018, this.large, this.mid)
          console.log(year2016)
          console.log(year2017)
          console.log(year2018)

          this.chartdata = {
            labels: [
              '2016-1',
              '2016-2',
              '2016-3',
              '2016-4',
              '2017-1',
              '2017-2',
              '2017-3',
              '2017-4',
              '2018-1',
              '2018-2',
              '2018-3'
            ],
            datasets: [
              {
                label: '매출',
                fill: false,
                borderColor: 'red',
                data: [
                  year2016.first,
                  year2016.second,
                  year2016.third,
                  year2016.fourth,
                  year2017.first,
                  year2017.second,
                  year2017.third,
                  year2017.fourth,
                  year2018.first,
                  year2018.second,
                  year2018.third
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
        }).finally(() => {
          this.$emit('loadingEvent', false)
        })
    },
    getData (arr, large, mid) {
      let data = {
        first: 0,
        second: 0,
        third: 0,
        fourth: 0
      }

      if (large === '전체') {
        for (let index = 0; index < arr.length; index++) {
          if (arr[index].stdr_qu_cd === '1') {
            data.first += Number(arr[index].thsmon_selng_amt)
          } else if (arr[index].stdr_qu_cd === '2') {
            data.second += Number(arr[index].thsmon_selng_amt)
          } else if (arr[index].stdr_qu_cd === '3') {
            data.third += Number(arr[index].thsmon_selng_amt)
          } else {
            data.fourth += Number(arr[index].thsmon_selng_amt)
          }
        }
      } else {
        for (let index = 0; index < arr.length; index++) {
          if (arr[index].svc_induty_cd_nm === mid && arr[index].stdr_qu_cd === '1') {
            data.first += Number(arr[index].thsmon_selng_amt)
          } else if (arr[index].svc_induty_cd_nm === mid && arr[index].stdr_qu_cd === '2') {
            data.second += Number(arr[index].thsmon_selng_amt)
          } else if (arr[index].svc_induty_cd_nm === mid && arr[index].stdr_qu_cd === '3') {
            data.third += Number(arr[index].thsmon_selng_amt)
          } else if (arr[index].svc_induty_cd_nm === mid && arr[index].stdr_qu_cd === '4') {
            data.fourth += Number(arr[index].thsmon_selng_amt)
          }
        }
      }
      return data
    }
  },
  mounted () {
    this.renderChart()

    eventBus.$on('clickmap', name => {
      this.renderChart()
    })

    eventBus.$on('cateSelected', () => {
      this.renderChart()
    })

    eventBus.$on('clickDetail', () => {
      this.renderChart()
    })
  }
}
</script>

<style scoped src='./LineChart.scss' lang='scss'></style>
