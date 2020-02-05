<template>
  <div class="container">
    <hr>
    <chart></chart>
    <hr>
  </div>
</template>

<script>
// @ is an alias to /src
import Chart from './Chart'

export default {
  name: 'result',
  components: {
    Chart
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.container {
  width: 100%;
}
</style>
