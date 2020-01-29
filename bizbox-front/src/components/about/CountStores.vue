<template>
  <div>
    <div id="search">
      <input type="text" placeholder="검색하세요." v-model="key" @keyup.enter="getData()"/>
      <button id="search-btn" @click="getData()">검색</button>
    </div>
  </div>
</template>

<script>
import axios from '../../js/http-commons'

export default {
  data () {
    return {
      result: '',
      key: '',
      info: {
        '소매': '',
        '학문교육': '',
        '숙박': '',
        '생활서비스': '',
        '음식': '',
        '부동산': '',
        '의료': '',
        '관광여가오락': ''
      }
    }
  },
  methods: {
    getData () {
      this.loadingStatus = true
      axios.get('/storecount/' + this.key + '/300')
        .then(res => {
          var jsonlarge = res.data.large
          this.info.소매 = jsonlarge.소매
          this.info.학문교육 = jsonlarge.학문교육
          this.info.숙박 = jsonlarge.숙박
          this.info.생활서비스 = jsonlarge.생활서비스
          this.info.음식 = jsonlarge.음식
          this.info.부동산 = jsonlarge.부동산
          this.info.의료 = jsonlarge.의료
          this.info.관광여가오락 = jsonlarge.관광여가오락
        })
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
