<template>
  <div>
    <div class="section-modal">
      <h2 class="bz-each-title">
        연도별 상권 변화 지표
      </h2>
      <popByAge />
      <h2 class="bz-each-title">
        시간별 유동인구
      </h2>
      <popByTime />
     <h2 class="bz-each-title">
        연령별 소비 
      </h2>
      <salByAge />
     <h2 class="bz-each-title">
        성별 매출
      </h2>
      <salBySex />
     <h2 class="bz-each-title">
        연도별 상권 변화 지표
      </h2>
      <commercialAreaChangeByYear />
      <h2 class="bz-each-title">
        거리별 상권 현황
      </h2>
      <storeCountByRange />
    </div>
  </div>
</template>

<script>
import popByAge from './graphs/PopulationByAgeChart'
import popByTime from './graphs/PopulationByTimeChart'
import salByAge from './graphs/SalesByAgeChart'
import salBySex from './graphs/SalesBySexChart'
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
