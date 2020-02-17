<template>
  <div v-if="seen">
    <h2>main Target : {{ summary.mainTarget }}</h2>
    <h2>main Time : {{ summary.mainTime }}</h2>
  </div>
</template>

<script>
import axios from '@/js/http-commons.js'

export default {
  data () {
    return {
      sgName: '', // emit
      subCategory: '', // emit
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
  updated () {
    let vm = this
    vm.seen = false
    if (vm.subCategory !== '전체') {
      let requestSummaryUrl = '/' + 'x' + 'y' + vm.subCategory
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
    } else {
      vm.seen = false
    }
  }
}
</script>
