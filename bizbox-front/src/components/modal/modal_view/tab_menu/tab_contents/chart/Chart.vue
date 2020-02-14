<template>
  <div id='bz-container'>
    <line-chart id='chart' :chart-data="chartdata" :options="chartoptions"
      width='200px' height='100px'>
    </line-chart>
  </div>
</template>

<script>
import LineChart from '@/lib/LineChart'

export default {
  components: {
    LineChart
  },
  data () {
    return {
      chartdata: null,
      chartoptions: null
    }
  }
}
</script>

<style>

</style>
