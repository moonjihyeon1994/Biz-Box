<template>
  <div>
    <popByAge/>
    <popByTime/>
    <salByAge/>
    <salBySex/>
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

<style></style>
