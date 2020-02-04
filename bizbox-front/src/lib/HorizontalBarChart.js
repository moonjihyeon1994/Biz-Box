import { HorizontalBar, mixins } from 'vue-chartjs'
const { reactiveProp } = mixins

export default {
  extends: HorizontalBar,
  mixins: [reactiveProp],
  props: ['options', 'chart-data', 'width', 'height'],
  mounted () {
    this.renderChart(this.chartData, this.options)
  }
}
