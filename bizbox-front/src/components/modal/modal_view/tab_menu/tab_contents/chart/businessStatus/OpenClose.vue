<template>
  <div v-if="seen">
    <h2>해당 상권 내 검색 업종의 폐업률은 {{ businessStatus.clper }}입니다.</h2>
  </div>
</template>

<script>
import axios from '@/js/http-commons.js'

export default {
  data () {
    return {
      sgName: '', // emit
      seen: false,
      openCloseData: '',
      subCategory: '', // emit
      businessStatus: {
        totEnd: 0,
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
    if (vm.subCategory !== '전체') {
      let requestOpenCloseUrl = '/' + vm.sgName
      axios.get(requestOpenCloseUrl)
        .then((res) => {
          vm.openCloseData = res.data[vm.subCategory]
          vm.businessStatus.clsum = 0
        })
        .then(() => {
          vm.businessStatus.totEnd = Number(vm.openCloseData.totcnt[10])
          for (let index = 0; index < vm.sopenCloseData.clcnt.length; index++) {
            vm.businessStatus.clsum += Number(vm.sopenCloseData.clcnt[index])
          }
        })
        .then(() => {
          vm.businessStatus.clper = Number((vm.businessStatus.clsum * 100 / (vm.businessStatus.clsum + vm.businessStatus.totEnd)).toFixed(1))
        })
        .then(() => {
          vm.seen = true
        })
    }
  }
}
</script>
