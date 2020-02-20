<template>
  <div class="main-content" v-if="seen">
    <div id='title'>상권 종합평가</div>

    <div id='bz-score-box'>
      <loading :loading='loadingStatus' :transparent='true'></loading>
      <div id='bz-score-box-pretext'>{{ sgName }}의 종합 점수는...</div>
      <div id='bz-score-box-score'>{{ totalScore }}</div>
      <div id='bz-score-box-posttext'>입니다.</div>
    </div>

    <div id='each-point-box'>
      <loading :loading='loadingStatus' :transparent='true'></loading>
      <div class='bz-each-title'>상권 항목별 점수
        <span class="icon-question" @click="popup">
          <v-icon size="15">mdi-help-circle-outline</v-icon>
          <span v-show="popflag" class="icon-popup-tri" />
        </span>
        <span v-show="popflag" class="icon-popup">
          성장성: 매출증감률(10점 만점)+예상성장률(10점 만점)<br>
          안정성: 변동성(10점 만점)+운영 연수(5점 만점)+휴/폐업률(5점 만점)<br>
          영업력: 공급대비 수요(10점)+분기별 매출 편차(5점)+요일별 매출 편차(5점)<br>
          구매력: 상권 매출규모(10점)+건당 결제금액(10점)<br>
          집객력: 유동인구(10점)+주거인구(5점)+직장인구(5점)
        </span>
      </div>
      <scoring @childs-event='parentsMethod' :clickEvent='clickEvent' @childs-loading-event='loadingMethod'></scoring>
    </div>
  </div>
</template>

<script>
import scoring from './scoring/scoring.vue'
import Loading from '@/components/bizmap/loading/Loading.vue'
import axios from '@/js/http-commons.js'

export default {
  components: {
    scoring,
    Loading
  },
  props: {
    clickEvent: Boolean
  },
  data () {
    return {
      popflag: false,
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
      large: [],
      mid: [],
      population: {
        '주거': '',
        '직장': '',
        '유동': ''
      },
      chartdata: null,
      chartoptions: null,
      totalScore: null,
      sgName: '',
      loadingStatus: true,
      list: ['음식', '카페', '주점']
    }
  },
  mounted () {
    axios.get('/storecountByLarge/' + this.key + '/100')
      .then(res => {
        const JsonLarge = res.data
        let totalCount = 0
        if (JsonLarge.소매 !== undefined) {
          this.stores.소매 = JsonLarge.소매
          totalCount += Number(JsonLarge.소매)
        }
        if (JsonLarge.음식 !== undefined) {
          this.stores.음식 = JsonLarge.음식
          totalCount += Number(JsonLarge.음식)
        }
        if (JsonLarge.생활서비스 !== undefined) {
          this.stores.서비스 = JsonLarge.생활서비스
          totalCount += Number(JsonLarge.생활서비스)
        }
        if (JsonLarge.숙박 !== undefined) {
          this.stores.숙박 = JsonLarge.숙박
          totalCount += Number(JsonLarge.숙박)
        }
        if (JsonLarge.관광여가오락 !== undefined) {
          this.stores.오락 = JsonLarge.관광여가오락
          totalCount += Number(JsonLarge.관광여가오락)
        }
        this.stores.전체 = totalCount.toString()
      })
  },
  methods: {
    popup () {
      this.popflag = !this.popflag
    },
    parentsMethod (score, name) {
      this.totalScore = score
      this.sgName = name
      this.$store.state.sgName = name
    },
    loadingMethod (status) {
      this.loadingStatus = status
    }
  }
}

</script>

<style scoped src='./Total.scss' lang='scss'></style>
<style scoped lang="scss">

$color1: rgb(232, 113, 91);
$color2: rgb(15, 66, 95);

.Content {
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
.icon-popup-tri {
  left: 39%;
  top: 20px;
}
.icon-popup {
  top: 25px;
  width: 525px;
  font-size: 11px;
}
</style>