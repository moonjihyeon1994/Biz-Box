<template>
  <div class="main-content" v-if="seen">
    <div id='title'>상권 종합평가</div>

    <div id='bz-score-box'>
      <loading :loading='loadingStatus' transparent='true'></loading>
      <div id='bz-score-box-pretext'>{{ sgName }}의 종합 점수는...</div>
      <div id='bz-score-box-score'>{{ totalScore }}</div>
      <div id='bz-score-box-posttext'>입니다.</div>
    </div>

    <div id='each-point-box'>
      <loading :loading='loadingStatus' :transparent='true'></loading>
      <div class='bz-each-title'>상권 항목별 점수</div>
      <scoring @childs-event='parentsMethod'></scoring>
    </div>

    <div id='chart-box'>
      <div class='bz-each-title'>상권 매출 차트</div>
      <select-box id='sel-box'></select-box>
      <chart id='line-chart'></chart>
    </div>
  </div>
</template>

<script>
import scoring from './scoring/scoring.vue'
import Chart from './chart/Chart.vue'
import Loading from '@/components/bizmap/loading/Loading.vue'
import SelectBox from './selectbox/SelectBox.vue'
import axios from 'axios'

export default {
  components: {
    scoring,
    Chart,
    Loading,
    SelectBox
  },
  data () {
    return {
      key: this.$store.state.modalsearch,
      x: this.$store.state.Coords.lat,
      y: this.$store.state.Coords.lng,
      seen: true,
      lightcolor: {
        backgroundColor: '#14bdfb'
      },

      result: {
        score: '90점',
        prospect: '상권 좋음'
      },

      // table settings
      headers: [
        {
          text: '상권 정보',
          align: 'left',
          sortable: false,
          value: 'name'
        },
        { text: '' }
      ],

      // table header data
      stores: {
        '전체': '0',
        '음식': '0',
        '서비스': '0',
        '소매': '0',
        '숙박': '0',
        '오락': '0'
      },
      population: {
        '주거': '',
        '직장': '',
        '유동': ''
      },
      chartdata: null,
      chartoptions: null,
      totalScore: null,
      sgName: '',
      loadingStatus: true
    }
  },
  // created () {
  //   const storeCountUrl = '/storecount/' + this.key + '/1000'
  //   axios.get(storeCountUrl)
  //     .then(res => {
  //       console.log('success to get storeCount')
  //       const JsonLarge = res.data.large
  //       let totalCount = 0
  //       if (JsonLarge.소매 !== undefined) {
  //         this.stores.소매 = JsonLarge.소매
  //         totalCount += Number(JsonLarge.소매)
  //       }
  //       if (JsonLarge.음식 !== undefined) {
  //         this.stores.음식 = JsonLarge.음식
  //         totalCount += Number(JsonLarge.음식)
  //       }
  //       if (JsonLarge.생활서비스 !== undefined) {
  //         this.stores.서비스 = JsonLarge.생활서비스
  //         totalCount += Number(JsonLarge.생활서비스)
  //       }
  //       if (JsonLarge.숙박 !== undefined) {
  //         this.stores.숙박 = JsonLarge.숙박
  //         totalCount += Number(JsonLarge.숙박)
  //       }
  //       if (JsonLarge.관광여가오락 !== undefined) {
  //         this.stores.오락 = JsonLarge.관광여가오락
  //         totalCount += Number(JsonLarge.관광여가오락)
  //       }
  //       this.stores.전체 = totalCount.toString()
  //     })
  // },
  methods: {
    parentsMethod (score, name) {
      this.totalScore = score
      this.sgName = name
      this.loadingStatus = false
    }
  }
}

</script>

<style scoped src='./Total.scss' lang='scss'></style>
