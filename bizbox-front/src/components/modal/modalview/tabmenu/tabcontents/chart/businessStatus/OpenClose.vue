<template>
  <div v-if="seen">
    <h2>해당 상권 내 검색 업종의 <span class='strong'>폐업률은 {{ businessStatus.clper }}</span>입니다.</h2>
    <h2>현재 해당 상권 <span class='strong'>경쟁 업체의 수는 {{ businessStatus.totend }}</span>입니다.</h2>

    <input type="hidden" v-model='subCategory'>
  </div>
</template>

<script>
import axios from '@/js/http-commons.js'

export default {
  data () {
    return {
      seen: false,
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
  methods: {
    getData () {
      let vm = this
      vm.seen = false
      if (vm.subCategory !== '전체') {
        let requestOpenCloseUrl = '/getOpenClose/' + this.sgName + '/' + this.subCategory
        axios.get(requestOpenCloseUrl)
          .then((res) => {
            console.log('===================================')
            console.log(res)
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
