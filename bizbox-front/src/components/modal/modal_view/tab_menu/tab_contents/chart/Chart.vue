<template>
  <div id='bz-container'>
    <div id='title'>업종별 차트</div>

    <div id='chart-box'>
      <div id='chart-box-flex-container'>
        <div class='select-box-wrapper'>
          <p>대분류</p>
          <select-box />
        </div>
        <div class='select-box-wrapper'>
          <p>중분류</p>
          <select-box />
        </div>
      </div>

      <div class='bz-each-title'>매출 차트</div>

      <div class='bz-each-title'>창업/폐업 현황</div>
      <OpenCloseTable></OpenCloseTable>
    </div>
  </div>
</template>

<script>
import SelectBox from './selectbox/SelectBox'
import OpenCloseTable from './businessStatus/OpenClose.vue'

export default {
  components: {
    SelectBox,
    OpenCloseTable
  }
}
</script>

<style scoped src='./Chart.scss' lang='scss'></style>
