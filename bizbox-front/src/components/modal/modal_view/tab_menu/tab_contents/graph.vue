<template>
  <div>
    <div class="section-modal">
      <popByAge />
      <popByTime />
    <!-- </div>
    <div class="section-modal"> -->
      <salByAge />
      <salBySex />
    </div>
  </div>
</template>

<script>
import popByAge from './graphs/population_by_age_chart'
import popByTime from './graphs/population_by_time_chart'
import salByAge from './graphs/sales_by_age_chart'
import salBySex from './graphs/sales_by_sex_chart '
export default {
  components: {
    popByAge,
    popByTime,
    salByAge,
    salBySex
  }
}
</script>

<style scoped>
@media screen and (max-width: 992px){
  .section-modal {
    display: grid;
    grid-template-columns: 1fr
  }
}
.section-modal {
  display: grid;
  grid-template-columns: 1fr 1fr;
}
</style>
