<template>
  <div class="biz-container">
    <div class="mt"></div>
    <div class="margin-hor">
      <h2>Menu</h2>
      <div class="inner-item">
        <MyPageBtn></MyPageBtn>
      </div>
      <h2>Biz Report</h2>
      <div class="inner-item">
        <v-tabs v-model="tab" color="orange" mobile-break-point="700px">
          <v-tab v-for="item in items" :key="item.tab">{{ item.tab }}</v-tab>
        </v-tabs>
        <v-tabs-items v-model="tab">
          <v-tab-item v-for="item in items" :key="item.tab">
            <BizReport :content="item.content"></BizReport>
          </v-tab-item>
        </v-tabs-items>
      </div>
    </div>
  </div>
</template>

<script>
import BizReport from './bizreport/BizReport'
import MyPageBtn from './mypagebtn/MyPageBtn'
export default {
  components: {
    BizReport,
    MyPageBtn
  },
  data () {
    return {
      tab: null,
      items: [
        {
          tab: '역삼 1호점',
          content: {
            nickname: '역삼 1호점',
            lat: 37.50133,
            lng: 127.03967,
            open: '2013-03-01',
            close: '2019-02-21'
          }
        },
        {
          tab: '역삼 2호점',
          content: {
            nickname: '역삼 2호점',
            lat: 37.499381,
            lng: 127.033718,
            open: '2019-11-14',
            close: null
          }
        }
      ]
    }
  }
}
</script>

<style scoped src="./MyPage.css"></style>
