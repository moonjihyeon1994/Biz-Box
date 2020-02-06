<template>
  <div>
    <div class="section-modal">
      <popByAge />
      <popByTime />
      <salByAge />
      <salBySex />
      <commercialAreaChangeByYear />
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
  components: {
    popByAge,
    popByTime,
    salByAge,
    salBySex,
    commercialAreaChangeByYear,
    storeCountByRange
  }
}
</script>

<style scoped>
.section-modal {
  display: grid;
  grid-gap: 5px;
  grid-template-columns: repeat(2, 550px);
  grid-template-rows: repeat(auto-fit, 1fr);
}
@media screen and (max-width: 1477px) {
  .section-modal {
    display: grid;
    grid-gap: 5px;
    grid-template-columns: 500px;
    grid-template-rows: repeat(auto-fit, 1fr);
  }
}
</style>
