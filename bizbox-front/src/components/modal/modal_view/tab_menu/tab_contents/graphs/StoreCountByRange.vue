<template>
  <div class="secition-content">
    <div class="secition-content-title-area">
      <h2 class="section-content-title">
        거리별 상권 현황
        <span class="icon-question" @click="popup">
          <v-icon size="15">mdi-help-circle-outline</v-icon>
          <span v-show="popflag" class="icon-popup-tri"/></span>
        <span v-show="popflag" class="icon-popup">공공데이터 상권 관련 데이터를 분석해서 생성한 정보입니다.</span>
      </h2>
      <div class="section-content-update">2020-02-05 업데이트</div>
    </div>
    <p class="sub-content-title-area">
      <span class="sub-content-title">
      <v-icon size="15">mdi-map-marker</v-icon>{{ key }}</span>
    </p>
    <ul class="list-distance">
      <li class=""><button>500m</button></li>
      <li class=""><button>1km</button></li>
      <li class="on"><button>2km</button></li>
      <li class=""><button>3km</button></li>
    </ul>
    <div class="content-inside">
      만들자
    </div>
  </div>
</template>

<script>
// import axios from '@/js/http-commons'
// import Spinner from '../../../../result/Spinner'
import './graphs.css'
export default {
  components: {
    // Spinner
  },
  data () {
    return {
      popflag: false,
      chartdata: null,
      chartoptions: null,
      result: null,
      road: '',
      key: '오류동',
      searchOption: 1,
      title: '연도별 상권 변화 지표',
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
      chartStyle: {
        display: 'contents'
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
      let preYear = this.result[4].g
      let thisYear = this.result[5].g * 2
      let percent = ((thisYear - preYear) / preYear) * 100
      return '(' + Math.round(percent * 100) / 100 + '%' + ')'
    },
    maxAgeMaker: function () {
      if (this.result == null) return
      let preYear = this.result[4].g
      let thisYear = this.result[5].g * 2
      if (preYear >= thisYear) {
        return '하강'
      } else {
        return '상승'
      }
    }
  },
  mounted () {},
  methods: {
    popup () {
      console.log('popup')
      this.popflag = !this.popflag
    }
  }
}
</script>

<style scoped lang="scss">
#chart1 {
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

#point {
  border: 1px solid black;
  border-radius: 5px;
  width: 500px;
  height: 40px;
  line-height: 40px;
  top: 5px;
  font-size: 24px;
  margin-top: 10px;
  background-color: white;
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

#searchOptions button:hover {
  background-color: #e38fe3;
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
