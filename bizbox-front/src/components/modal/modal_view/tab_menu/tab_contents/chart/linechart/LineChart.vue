<template>
  <div id='bz-container' :style='chartStyle'>
    <line-chart id='chart' :chart-data="chartdata" :options="chartoptions">
    </line-chart>
  </div>
</template>

<script>
import LineChart from '@/lib/LineChart'
import axios from '@/js/http-commons'

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
      chartoptions: null,
      chartStyle: {
        width: this.chartWidth,
        height: this.chartHeight
      },
      lat: this.$store.state.Coords.lat,
      lng: this.$store.state.Coords.lng
    }
  },
  methods: {
    renderChart () {
      axios.get('/predict/findBusiness/127.050826/37.507118')
        .then(res => {
          console.log(res.data)
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
  }
}
</script>

<style scoped src='./LineChart.scss' lang='scss'></style>
