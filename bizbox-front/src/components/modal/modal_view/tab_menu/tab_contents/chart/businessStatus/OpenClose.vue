<template>
  <div v-if="seen">
    <h2>해당 상권 내 검색 업종의 폐업률은 {{ businessStatus.clper }}입니다.</h2>
    <h2>현재 해당 상권 경쟁 업체의 수는 {{ businessStatus.totend }}입니다.</h2>

    <input type="hidden" v-model='subCategory'>
  </div>
</template>

<script>
import axios from '@/js/http-commons.js'

export default {
  data () {
    return {
      sgName: '테헤란로81길', // emit
      seen: false,
      subCategory: '일식음식점', // emit
      businessStatus: {
        totend: 0,
        clsum: 0,
        clper: 0
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
      let requestOpenCloseUrl = 'http://70.12.246.38:80/getOpenClose/' + vm.sgName + '/' + vm.subCategory
      axios.get(requestOpenCloseUrl)
        .then((res) => {
          // console.log(res)
          vm.businessStatus.totend = res.data.totalEnd
          vm.businessStatus.clsum = res.data.closeCnt
          vm.businessStatus.clper = Number((Number(res.data.closeCnt) * 100 / (Number(res.data.closeCnt) + Number(res.data.totalEnd))).toFixed(1))
        })
        .finally(() => {
          vm.seen = true
        })
    } else {
      vm.seen = false
    }
  }
}
</script>
