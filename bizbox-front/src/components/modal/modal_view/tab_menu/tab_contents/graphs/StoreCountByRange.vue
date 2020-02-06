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
      <li><button class="list-distance-items" @click="checkBtn(0)">500m</button></li>
      <li><button class="list-distance-items" @click="checkBtn(1)">750m</button></li>
      <li><button class="list-distance-items" @click="checkBtn(2)">1km</button></li>
      <li><button class="list-distance-items" @click="checkBtn(3)">2km</button></li>
    </ul>
    <div class="category-list-select">
      <select>
        <option value="none">거리</option>
        <option value="item" v-for="item in largeItems" :key="item" >{{item}}</option>
      </select>
    </div>
    <div class="slider-content">
      <div class="slidecontainer">
        <input type="range" min="100" max="2000" value="1000" class="slider" id="myRange">
        <p class="slidecontainer-text"><span id="demo"></span>m</p>
      </div>
    </div>
    <div class="content-inside">
      만들자
      {{range}}
      <button @click="getData">눌러줘</button>
      {{result}}
    </div>
  </div>
</template>

<script>
import axios from '@/js/http-commons'
// import Spinner from '../../../../result/Spinner'
import './graphs.css'
import largeScale from '@/assets/json/largeScale.json'
export default {
  components: {
    // Spinner
  },
  data () {
    return {
      largeItems: largeScale.large,
      popflag: false,
      range: 0,
      result: null,
      road: '',
      key: '경인로 248-14',
      searchOption: 1,
      title: '연도별 상권 변화 지표',
      point: 0
    }
  },
  computed: {
    asdf: function () {
      return ''
    }
  },
  mounted () {
    this.slider()
  },
  methods: {
    slider () {
      let slider = document.getElementById('myRange')
      let output = document.getElementById('demo')
      output.innerHTML = slider.value

      slider.oninput = (res) => {
        output.innerHTML = res.target.value
        console.log(res)
        this.range = res.target.value
        // console.log('asdf' + this.range)
      }
    },
    popup () {
      console.log('popup')
      this.popflag = !this.popflag
    },
    checkBtn (e) {
      let listDistanceItems = document.getElementsByClassName('list-distance-items')
      for (let i = 0; i < listDistanceItems.length; i++) {
        listDistanceItems[i].className = listDistanceItems[i].className.replace(' click', '')
      }
      listDistanceItems[e].className += ' click'
      if (e === 0) this.range = 500
      if (e === 1) this.range = 1000
      if (e === 2) this.range = 2000
      if (e === 3) this.range = 3000
    },
    getData () {
      console.log(this.range)
      axios
        .get('/storecount/' + this.key + '/' + this.range)
        .then(res => {
          console.log(res.data)
          this.result = res.data
        })
        .finally(() => {})
    }
  }
}
</script>

<style scoped lang="scss">

.slidecontainer {
  width: 100%; /* Width of the outside container */
}

.slidecontainer-text {
  clear: both;
  position: relative;
  top: 5px;
}

.slider {
  -webkit-appearance: none;
  appearance: none;
  width: 100%;
  height: 20px;
  background: #d3d3d3;
  outline: none;
  opacity: 0.7;
  -webkit-transition: .2s;
  transition: opacity .2s;
  border-radius: 9px;
}

.slider:hover {
  opacity: 1;
}

.slider::-webkit-slider-thumb {
  -webkit-appearance: none;
  appearance: none;
  width: 25px;
  height: 25px;
  background: rgb(216, 74, 162);
  cursor: pointer;
  border-radius: 100%;
}

.slider::-moz-range-thumb {
  width: 25px;
  height: 25px;
  background: rgb(216, 74, 162);
  cursor: pointer;
  border-radius: 100%;
}

button {
  outline: none;
}
button.click {
  background-color: #fe2c54;
  color: white;
}

#chart1 {
  position: relative;
  width: 500px;
  height: 300px;
  overflow: hidden;
}

</style>
