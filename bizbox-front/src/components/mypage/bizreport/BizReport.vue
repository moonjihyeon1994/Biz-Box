<template>
  <div class="biz-report">
    <div class="biz-info">
      <bar-chart
        :chart-data="chartdata"
        :options="chartoptions"
        :height="gheight"
      ></bar-chart>
    </div>
    <div class="btn-set">
      <button class="btn-item">정보 수정</button>
      <button class="btn-item">폐업</button>
    </div>
  </div>
</template>

<script>
import BarChart from '@/lib/BarChart'
export default {
  data () {
    return {
      gheight: 200,
      chartdata: null,
      chartoptions: null,
      labels: [
        '19년 9월',
        '19년 10월',
        '19년 11월',
        '19년 12월',
        '20년 1월',
        '20년 2월'
      ],
      title: '종합 보고서',
      compmoney: [4839023, 5838293, 4937194, 3937869, 4928493, 3458283],
      building: [2300000, 2300000, 2300000, 2500000, 2500000, 2500000],
      getmoney: [14532532, 13532923, 15293852, 14704932, 15028342, 14920394],
      mymoney: [0, 0, 0, 0, 0, 0],
      point: 0,
      btnStyle1: {
        backgroundColor: '#d9d9d9',
        cursor: 'pointer'
      },
      btnStyle2: {
        backgroundColor: 'white',
        cursor: 'pointer'
      },
      btnStyle3: {
        backgroundColor: 'white',
        cursor: 'pointer'
      },
      btnStyle4: {
        backgroundColor: 'white',
        cursor: 'pointer'
      },
      loadingStatus: false,
      allowDiv: {
        display: 'none'
      }
    }
  },
  props: {
    content: Object
  },
  components: {
    BarChart
  },
  methods: {
    calcMoney () {
      for (let idx = 0; idx < this.labels.length; idx++) {
        this.mymoney[idx] =
          this.getmoney[idx] - this.building[idx] - this.compmoney[idx]
      }
      this.chartdata = {
        labels: this.labels,
        datasets: [
          {
            label: '순이익',
            backgroundColor: 'lightgreen',
            data: this.mymoney
          },
          {
            label: '매출액',
            backgroundColor: '#74ddf7',
            data: this.getmoney
          },
          {
            label: '비용',
            backgroundColor: '#ff6390',
            data: this.compmoney
          },
          {
            label: '건물 임대료',
            backgroundColor: '#365673',
            data: this.building
          }
        ]
      }
      this.chartoptions = {
        responsive: true,
        maintainAspectRatio: true,
        scales: {
          yAxes: [
            {
              ticks: {
                beginAtZero: true
              },
              gridLines: {
                display: true
              }
            }
          ],
          xAxes: [
            {
              gridLines: {
                display: true
              }
            }
          ]
        }
      }
      this.loadingStatus = false
      this.allowDiv.display = 'none'
      this.btnStyle1.cursor = 'pointer'
      this.btnStyle2.cursor = 'pointer'
      this.btnStyle3.cursor = 'pointer'
      this.btnStyle4.cursor = 'pointer'
    }
  },
  mounted () {
    this.calcMoney()
  }
}
</script>

<style scoped src="./BizReport.css"></style>
