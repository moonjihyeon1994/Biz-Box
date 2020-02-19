<template>
  <div>
    <div class="section-modal">
      <h2 class="bz-each-title">
        연도별 상권 변화 지표
        <span class="icon-question" @click="popup">
          <v-icon size="15">mdi-help-circle-outline</v-icon>
          <span v-show="popflag" class="icon-popup-tri" />
        </span>
        <span v-show="popflag" class="icon-popup">공공데이터 상권 관련 데이터를 분석해서 생성한 정보입니다.</span>
      </h2>
      <popByAge />
      <h2 class="bz-each-title">
        시간별 유동인구
        <span class="icon-question" @click="popup">
          <v-icon size="15">mdi-help-circle-outline</v-icon>
          <span v-show="popflag" class="icon-popup-tri" />
        </span>
        <span v-show="popflag" class="icon-popup">공공데이터 상권 관련 데이터를 분석해서 생성한 정보입니다.</span>
      </h2>
      <popByTime />
     <h2 class="bz-each-title">
        연령별 소비 
        <span class="icon-question" @click="popup">
          <v-icon size="15">mdi-help-circle-outline</v-icon>
          <span v-show="popflag" class="icon-popup-tri" />
        </span>
        <span v-show="popflag" class="icon-popup">공공데이터 상권 관련 데이터를 분석해서 생성한 정보입니다.</span>
      </h2>
      <salByAge />
     <h2 class="bz-each-title">
        성별 매출
        <span class="icon-question" @click="popup">
          <v-icon size="15">mdi-help-circle-outline</v-icon>
          <span v-show="popflag" class="icon-popup-tri" />
        </span>
        <span v-show="popflag" class="icon-popup">공공데이터 상권 관련 데이터를 분석해서 생성한 정보입니다.</span>
      </h2>
      <salBySex />
     <h2 class="bz-each-title">
        연도별 상권 변화 지표
        <span class="icon-question" @click="popup">
          <v-icon size="15">mdi-help-circle-outline</v-icon>
          <span v-show="popflag" class="icon-popup-tri" />
        </span>
        <span v-show="popflag" class="icon-popup">공공데이터 상권 관련 데이터를 분석해서 생성한 정보입니다.</span>
      </h2>
      <commercialAreaChangeByYear />
      <h2 class="bz-each-title">
        거리별 상권 현황
        <span class="icon-question" @click="popup">
          <v-icon size="15">mdi-help-circle-outline</v-icon>
          <span v-show="popflag" class="icon-popup-tri" />
        </span>
        <span v-show="popflag" class="icon-popup">공공데이터 상권 관련 데이터를 분석해서 생성한 정보입니다.</span>
      </h2>
      <storeCountByRange />
    </div>
  </div>
</template>

<script>
import popByAge from './graphs/population_by_age_chart'
import popByTime from './graphs/population_by_time_chart'
import salByAge from './graphs/sales_by_age_chart'
import salBySex from './graphs/sales_by_sex_chart'
import commercialAreaChangeByYear from './graphs/CommercialAreaChangeByYear'
import storeCountByRange from './graphs/StoreCountByRange'
export default {
  data () {
    return {
      popflag: false
    }
  },
  components: {
    popByAge,
    popByTime,
    salByAge,
    salBySex,
    commercialAreaChangeByYear,
    storeCountByRange
  },
  methods: {
    popup() {
      this.popflag = !this.popflag
    }
  }
}
</script>

<style scoped>
/* .section-modal {
  display: grid;
  grid-gap: 5px;
  grid-template-columns: repeat(2, 550px);
  grid-template-rows: repeat(auto-fit, 1fr);
} */
@media screen and (max-width: 1477px) {
  .section-modal {
    display: grid;
    grid-gap: 5px;
    grid-template-columns: 500px;
    grid-template-rows: repeat(auto-fit, 1fr);
  }
}
.bz-each-title {
  font-size: 1.25em;
  font-weight: bold;
  padding-left: 15px;
}
</style>
