<template>
  <div id="app">
    <h1>유동인구</h1>
    <hr />
    <floating-population></floating-population>
    <hr>
    <h1>매출</h1>
    <hr>
    <sales></sales>
  </div>
</template>

<script>
import FloatingPopulation from './Chart/FloatingPopulation'
import Sales from './Chart/Sales'

export default {
  components: {
    FloatingPopulation,
    Sales
  }
}
</script>

<style scoped>

</style>
