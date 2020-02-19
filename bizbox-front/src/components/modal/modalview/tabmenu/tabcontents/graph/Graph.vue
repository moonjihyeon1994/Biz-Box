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
import popByAge from './graphs/PopulationByAgeChart'
import popByTime from './graphs/PopulationByTimeChart'
import salByAge from './graphs/SalesByAgeChart'
import salBySex from './graphs/SalesBySexChart'
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
</style>
