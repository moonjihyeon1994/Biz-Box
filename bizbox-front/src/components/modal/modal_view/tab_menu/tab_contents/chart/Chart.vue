<template>
  <div id='bz-container'>
    <div id='title'>상세 차트</div>

    <div id='chart-box'>
      <div id='chart-box-flex-container'>
        <div class='select-box-wrapper'>
          <p>대분류</p>
          <select-box :list='listL' @large-event='parentsMethodL' />
        </div>
        <div class='select-box-wrapper'>
          <p>중분류</p>
          <select-box :list='listR' :init='isInit' @mid-event='parentsMethodM'/>
        </div>
      </div>

      <div class='bz-each-title'>매출 차트</div>
      <div>
        <line-chart chartWidth='500px' chartHeight='200px' :large='large' :mid='mid' />
      </div>

      <div class='bz-each-title'>창업/폐업 현황</div>

      <div class='bz-each-title'>원클릭 컨설팅</div>
<<<<<<< HEAD
=======

>>>>>>> frontend
    </div>
  </div>
</template>

<script>
import SelectBox from './selectbox/SelectBox'
import ListJson from '@/assets/json/Select.json'
import LineChart from './linechart/LineChart'

export default {
  components: {
    SelectBox,
    LineChart
  },
  data () {
    return {
      listL: ListJson.large,
      listR: [],
      isInit: false,
      large: '전체',
      mid: '전체'
    }
  },
  methods: {
    parentsMethodL (selected) {
      const array = ListJson.mid
      for (let index = 0; index < array.length; index++) {
        if (array[index].name === selected) {
          this.listR = array[index].data
          this.isInit = true
          this.large = selected
          return
        }
      }
      this.large = '전체'
      this.listR = []
    },
    parentsMethodM (selected) {
      this.mid = selected
    }
  }
}
</script>

<style scoped src='./Chart.scss' lang='scss'></style>
