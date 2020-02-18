<template>
  <div id='bz-container'>
    <div id='title'>상세 차트</div>

    <div id='chart-box'>
      <div id='chart-box-flex-container'>
        <div class='select-box-wrapper'>
          <p>대분류</p>
          <select-box :list='listL' @large-event='parentsMethodL' type='large' />
        </div>
        <div class='select-box-wrapper'>
          <p>중분류</p>
          <select-box :list='listR' :init='isInit' @mid-event='parentsMethodM' type='mid'/>
        </div>
      </div>

      <div class='bz-each-title'>매출 차트</div>
      <div id='chart-wrapper'>
        <loading :loading='loadingStatus' :transparent='true'/>
        <line-chart chartWidth='500px' chartHeight='300px' :large='large' :mid='mid' @loadingEvent='chartLoading'/>
      </div>

      <div class='bz-each-title'>창업/폐업 현황</div>

      <div class='bz-each-title'>원클릭 컨설팅</div>
    </div>
  </div>
</template>

<script>
import SelectBox from './selectbox/SelectBox'
import LineChart from './linechart/LineChart'
import ListJson from '@/assets/json/Select.json'
import Loading from '@/components/common/loading/Loading'

export default {
  components: {
    SelectBox,
    LineChart,
    Loading
  },
  data () {
    return {
      listL: ListJson.large,
      listR: [],
      isInit: false,
      large: '전체',
      mid: '전체',
      loadingStatus: false
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
        this.isInit = true
        this.large = selected
      }
      this.large = '전체'
      this.listR = []
    },
    parentsMethodM (selected, state) {
      this.isInit = state
      this.mid = selected
    },
    chartLoading (status) {
      this.loadingStatus = status
    }
  }
}
</script>

<style scoped src='./Chart.scss' lang='scss'></style>
