<template>
  <div>W
      <div id='chartComp'>
        <chart></chart>
      </div>
  </div>
</template>

<script>
import Chart from './Chart'

export default {
  components: {
    Chart
  },
  data () {
    return {

    }
  }
}
</script>

<style scoped>
#chartComp {
    width: 500px;
    height: 300px;
}
</style>
