<template>
  <div class="secition-content">
    <div class="secition-content-title-area">
      <h2 class="section-content-title">
        성별 매출
        <span class="icon-question" @click="popup"><v-icon size=15>mdi-help-circle-outline</v-icon>
        <span v-show="popflag" class="icon-popup-tri"/></span>
        <span v-show="popflag" class="icon-popup">공공데이터 상권 관련 데이터를 분석해서 생성한 정보입니다.</span>
      </h2>
      <div class="section-content-update">2020-02-05 업데이트</div>
    </div>
    <p class="point-content-area Content" style="font-size: 1.2em;
    font-weight: bold;">
      <span class="point-title">{{maxAgeMaker}}</span>
      <span class="point-percent">{{percentMaker}}</span>
      <span class="point-normal">소비가 가장 많아요.</span>
    </p>
    <div id="chart">
      <loading :loading="loadingStatus" :transparent='true'></loading>
      <pie-chart
        :chart-data="chartdata"
        :options="chartoptions"
        width="500px"
        height="300px"
      ></pie-chart>
    </div>
  </div>
</template>

<script>
import PieChart from '@/lib/PieChart'
import axios from '@/js/http-commons'
import Loading from '@/components/common/loading/Loading'
import './Graphs.css'
import { eventBus } from '@/js/bus'
export default {
  components: {
    PieChart,
    Loading
  },
  data () {
    return {
      totalWoman: 0,
      totalMan: 0,
      popflag: false,
      chartdata: null,
      chartoptions: null,
      result: null,
      road: '',
      key: this.$store.state.modalsearch,
      searchOption: 1,
      title: '성별 매출',
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
  computed: {
    percentMaker: function () {
      if (this.result == null) return
      let woman = this.totalWoman
      let man = this.totalMan
      if (woman >= man) {
        return '(' + woman.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',') + '원)'
      } else {
        return '(' + man.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',') + '원)'
      }
    },
    maxAgeMaker: function () {
      if (this.result == null) return
      if (this.sumWoman >= this.sumMan) {
        return '여성'
      } else {
        return '남성'
      }
    }
  },
  mounted () {
    this.draw()
    eventBus.$on('clickmap', name => {
      this.key = name
      this.draw()
    })
  },
  methods: {
    popup () {
      this.popflag = !this.popflag
    },
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

      axios
        .get('/predict/findBusiness/127.050826/37.507118')
        .then(res => {
          this.result = res.data['2018']
          // this.road = res.data[0].d

          for (let index = 0; index < this.result.length; index++) {
            this.sumWoman += Number(this.result[index].fml_selng_amt)
            this.sumMan += Number(this.result[index].ml_selng_amt)
          }
        })
        .then(() => {
          this.totalWoman = this.sumWoman
          this.totalMan = this.sumMan

          this.sumWoman /= 100000000
          this.sumMan /= 100000000
        })
        .finally(() => {
          this.chartdata = {
            labels: ['여성', '남성'],
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
        })
    }
  }
}
</script>

<style scoped lang="scss">
[v-cloak] {
    display: none;
}

#chart {
  position: relative;
  width: 500px;
  height: 300px;
  overflow: hidden;
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


$color1: rgb(232, 113, 91);
$color2: rgb(15, 66, 95);

.Content {
  width: 100%;
  padding: 10px 20px;
  margin: 20px 0;
  background-color: $color2;
  border-radius: 5px;
  color: $color1;
  box-shadow: 2px 3px 5px rgba(0, 0, 0, 0.5);
  text-align: center;

  .strong {
    color: rgb(223, 223, 223);
  }

  h2 {
    font-size: 1.2em;
    font-weight: bold;
  }
}
</style>
