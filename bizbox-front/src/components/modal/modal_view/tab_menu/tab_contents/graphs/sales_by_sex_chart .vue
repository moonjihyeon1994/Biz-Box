<template>
  <div id="chart1">
    <span>여기의 성비는{{sumWoman}}</span>
    <div id="back" :style="allowDiv"></div>
    <spinner :loading="loadingStatus"></spinner>
    <pie-chart
      :chart-data="chartdata"
      :options="chartoptions"
      width="500px"
      height="300px"
    ></pie-chart>
  </div>
</template>

<script>
import PieChart from '@/lib/PieChart'
import axios from '@/js/http-commons'
import Spinner from '../../../../result/Spinner'
export default {
  components: {
    PieChart,
    Spinner
  },
  data () {
    return {
      chartdata: null,
      chartoptions: null,
      result: null,
      road: '',
      key: '오류동',
      searchOption: 1,
      title: '연령별 매출',
      point: 0,
      sumWoman: 0,
      sumMan: 0,
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
  mounted () {
    this.draw()
  },
  methods: {
    draw () {
      this.chartdata = null
      this.chartoptions = null

      this.searchOption = 4
      this.title = '성별 매출'
      this.btnStyle1.backgroundColor = 'white'
      this.btnStyle2.backgroundColor = 'white'
      this.btnStyle3.backgroundColor = 'white'
      this.btnStyle4.backgroundColor = '#d9d9d9'

      if (this.key !== '') {
        this.getData()
      }
    },
    getData () {
      this.loadingStatus = true
      this.allowDiv.display = 'block'
      this.btnStyle1.cursor = 'not-allowed'
      this.btnStyle2.cursor = 'not-allowed'
      this.btnStyle3.cursor = 'not-allowed'
      this.btnStyle4.cursor = 'not-allowed'

      // let sumWoman = 0
      // let sumMan = 0

      axios
        .get('/sales/' + this.key)
        .then(res => {
          this.result = res.data
          this.road = res.data[0].d

          for (let index = 0; index < this.result.length; index++) {
            this.sumWoman += Number(this.result[index].y)
            this.sumMan += Number(this.result[index].x)
          }

          this.sumWoman /= 100000000
          this.sumMan /= 100000000
        })
        .finally(() => {
          this.chartdata = {
            labels: ['여자', '남자'],
            datasets: [
              {
                fill: true,
                data: [this.sumWoman.toFixed(2), this.sumMan.toFixed(2)],
                backgroundColor: ['#ff2483', '#1c8aff']
              }
            ]
          }

          this.chartoptions = {
            responsive: true,
            maintainAspectRatio: true
          }

          this.loadingStatus = false
          this.allowDiv.display = 'none'
          this.btnStyle1.cursor = 'pointer'
          this.btnStyle2.cursor = 'pointer'
          this.btnStyle3.cursor = 'pointer'
          this.btnStyle4.cursor = 'pointer'
        })
    }
  }
}
</script>

<style scoped lang="scss">
[v-cloak] {
    display: none;
}

#chart1 {
  position: relative;
  width: 500px;
  height: 300px;
  overflow: hidden;
  margin-bottom: 100px;
}

#back {
  position: absolute;
  z-index: 2;
  width: 100%;
  height: 100%;
  background-color: rgb(255, 255, 255);
}

#back:hover {
  cursor: not-allowed;
}

#searchOptions {
  margin: auto;

  button {
    font-size: 16px;
    display: inline-block;
    border: 1px solid black;
    border-radius: 5px;
    width: 200px;
    height: 30px;
    margin-right: 10px;
    background-color: white;
    transition: 0.1s all ease;

    &:hover {
      font-weight: bold;
    }
  }
}

#search input {
  border: 1px solid black;
  border-radius: 5px 0 0 5px;
  width: 200px;
  height: 30px;
  margin-top: 10px;
  padding: 5px;
  background-color: white;
}

#search-btn {
  border-radius: 0 5px 5px 0;
  width: 50px;
  height: 30px;
  background-color: rgb(172, 172, 172);
}

#search-result {
  margin-top: 5px;
}
</style>
