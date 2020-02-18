<template>
  <div v-if="seen">
    <h2><span class='strong'>{{ summary.mainTarget }}</span>를 주요 고객 대상으로 삼으세요.</h2>
    <h2><span class='strong'>{{ summary.mainTime }}</span>시간대를 노리세요.</h2>

    <input type="hidden" v-model='subCategory'>
  </div>
</template>

<script>
import axios from '@/js/http-commons.js'
import { eventBus } from '@/js/bus'

export default {
  data () {
    return {
      seen: false,
      receivedData: '',
      summary: {
        mainTarget: '10대',
        mainTime: '00시 ~ 06시'
      }
    }
  },
  created () {
    this.seen = false
  },
  computed: {
    sgName () {
      return this.$store.state.sgName
    },
    subCategory () {
      return this.$store.state.subCategory
    }
  },
  watch: {
    sgName () {
      this.getData()
    },
    subCategory () {
      this.getData()
    }
  },
  methods: {
    getData () {
      let vm = this // vm.$store.state.Coords.lat(lng)
      vm.seen = false
      if (vm.subCategory !== '전체') {
        let requestSummaryUrl = '/predict/predict_business/' + vm.$store.state.Coords.lng + '/' + vm.$store.state.Coords.lat + '/' + vm.subCategory
        axios.get(requestSummaryUrl)
          .then((res) => {
            vm.receivedData = res.data.prediclist[2]
          })
          .then(() => {
            let selng0006 = Number(vm.receivedData.tmzon_00_06_selng_co)
            let selng0611 = Number(vm.receivedData.tmzon_06_11_selng_co)
            let selng1114 = Number(vm.receivedData.tmzon_11_14_selng_co)
            let selng1417 = Number(vm.receivedData.tmzon_14_17_selng_co)
            let selng1721 = Number(vm.receivedData.tmzon_17_21_selng_co)
            let selng2124 = Number(vm.receivedData.tmzon_21_24_selng_co)
            let times = ['00시 ~ 06시', '06시 ~ 11시', '11시 ~ 14시', '14시 ~ 17시', '17시 ~ 21시', '21시 ~ 24시']
            let countListByTime = [selng0006, selng0611, selng1114, selng1417, selng1721, selng2124]
            let maxByTime = selng0006
            for (let index = 1; index < countListByTime.length; index++) {
              if (maxByTime < countListByTime[index]) {
                maxByTime = countListByTime[index]
                vm.summary.mainTime = times[index]
              }
            }
            let selng10 = Number(vm.receivedData.agrde_10_selng_co)
            let selng20 = Number(vm.receivedData.agrde_20_selng_co)
            let selng30 = Number(vm.receivedData.agrde_30_selng_co)
            let selng40 = Number(vm.receivedData.agrde_40_selng_co)
            let selng50 = Number(vm.receivedData.agrde_50_selng_co)
            let selng60 = Number(vm.receivedData.agrde_60_selng_co)
            let ages = ['10대', '20대', '30대', '40대', '50대', '60대 이상']
            let countListByAge = [selng10, selng20, selng30, selng40, selng50, selng60]
            let maxByAge = selng10
            for (let index = 1; index < countListByAge.length; index++) {
              if (maxByAge < countListByAge[index]) {
                maxByAge = countListByAge[index]
                vm.summary.mainTarget = ages[index]
              }
            }
          })
          .finally(() => {
            vm.seen = true
          })
      } else {
        vm.seen = false
      }
    }
  }
}
</script>

<style lang="scss" scoped>
$color1: rgb(232, 113, 91);
$color2: rgb(15, 66, 95);

div {
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
