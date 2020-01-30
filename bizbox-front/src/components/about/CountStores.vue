<template>
  <div>
    <div id="search">
      <div id="searchOptions">
        <button @click="setOpt1()" v-bind:style="btnStyle1">반경 300m</button>
        <button @click="setOpt2()" v-bind:style="btnStyle2">반경 500m</button>
      </div>
      <input type="text" placeholder="검색하세요." v-model="key" @keyup.enter="getData()"/>
      <button id="search-btn" @click="getData()">검색</button>
    </div>
    <hr />
    <v-list>
      <v-list-item>
        <v-icon large>mdi-store</v-icon>소매: {{ info.소매 }}
      </v-list-item>
      <v-list-item>
        <v-icon large>mdi-school-outline</v-icon>학문/교육: {{ info.학문교육 }}
      </v-list-item>
      <v-list-item>
        <v-icon large>mdi-hotel</v-icon>숙박: {{ info.숙박 }}
      </v-list-item>
      <v-list-item>
        <v-icon large>mdi-vuejs</v-icon>생활서비스: {{ info.생활서비스 }}
      </v-list-item>
      <v-list-item>
        <v-icon large>mdi-silverware-fork-knife</v-icon>음식: {{ info.음식 }}
      </v-list-item>
      <v-list-item>
        <v-icon large>mdi-sign-real-estate</v-icon>부동산: {{ info.부동산 }}
      </v-list-item>
      <v-list-item>
        <v-icon large>mdi-hospital-box-outline</v-icon>의료: {{ info.의료 }}
      </v-list-item>
      <v-list-item>
        <v-icon large>mdi-gamepad-variant-outline</v-icon>관광/여가/오락: {{ info.관광여가오락 }}
      </v-list-item>
    </v-list>
  </div>
</template>

<script>
import axios from '../../js/http-commons'

export default {
  data () {
    return {
      result: '',
      key: '',
      searchOption: 1,
      info: {
        '소매': '',
        '학문교육': '',
        '숙박': '',
        '생활서비스': '',
        '음식': '',
        '부동산': '',
        '의료': '',
        '관광여가오락': ''
      },
      btnStyle1: {
        backgroundColor: '#d9d9d9'
      },
      btnStyle2: {
        backgroundColor: 'white'
      },
      btnStyle3: {
        backgroundColor: 'white'
      },
      btnStyle4: {
        backgroundColor: 'white'
      }
    }
  },
  methods: {
    setOpt1 () {
      this.searchOption = 1
      this.btnStyle1.backgroundColor = '#d9d9d9'
      this.btnStyle2.backgroundColor = 'white'
      if (this.key !== '') {
        this.getData()
      }
    },
    setOpt2 () {
      this.searchOption = 2
      this.btnStyle1.backgroundColor = 'white'
      this.btnStyle2.backgroundColor = '#d9d9d9'
      if (this.key !== '') {
        this.getData()
      }
    },
    getData () {
      if (this.searchOption === 1) {
        axios.get('/storecount/' + this.key + '/300').then(res => {
          var jsonlarge = res.data.large
          this.info.소매 = jsonlarge.소매
          this.info.학문교육 = jsonlarge.학문교육
          this.info.숙박 = jsonlarge.숙박
          this.info.생활서비스 = jsonlarge.생활서비스
          this.info.음식 = jsonlarge.음식
          this.info.부동산 = jsonlarge.부동산
          this.info.의료 = jsonlarge.의료
          this.info.관광여가오락 = jsonlarge.관광여가오락
        }).catch(err => alert(err, '검색어를 확인해주세요'))
      } else if (this.searchOption === 2) {
        axios.get('/storecount/' + this.key + '/500').then(res => {
          var jsonlarge = res.data.large
          this.info.소매 = jsonlarge.소매
          this.info.학문교육 = jsonlarge.학문교육
          this.info.숙박 = jsonlarge.숙박
          this.info.생활서비스 = jsonlarge.생활서비스
          this.info.음식 = jsonlarge.음식
          this.info.부동산 = jsonlarge.부동산
          this.info.의료 = jsonlarge.의료
          this.info.관광여가오락 = jsonlarge.관광여가오락
        }).catch(err => alert(err, '검색어를 확인해주세요'))
      }
    }
  }
}
</script>

<style scoped>
[v-cloak] {
    display: none;
}

#chart1 {
  width: 600px;
}

#point {
  border: 1px solid black;
  border-radius: 5px;
  width: 600px;
  height: 50px;
  line-height: 50px;
  top: 5px;
  font-size: 24px;
  margin-top: 10px;
  margin-bottom: 50px;
  background-color: white;
}

#searchOptions {
  margin: auto;
}

#searchOptions button {
  font-size: 16px;
  display: inline-block;
  border: 1px solid black;
  border-radius: 5px;
  width: 200px;
  height: 30px;
  margin-right: 10px;
  background-color: white;
  transition: 0.2s all ease;
}

#searchOptions button:hover{
  background-color: rgb(224, 224, 224);
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
