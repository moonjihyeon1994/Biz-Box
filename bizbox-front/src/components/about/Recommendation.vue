<template>
  <div>
    <div id="search">
      <h2>추천받기</h2>
      <input type="text" placeholder="검색하세요." v-model="key" @keyup.enter="getData()"/>
      <button id="search-btn" @click="getData()">검색</button>
    </div>
    <hr />
    <v-list>
      <v-list-item>
        name: {{ info.name }}
      </v-list-item>
      <v-list-item>
        value: {{ info.value }}
      </v-list-item>
      <v-list-item>
        salespderstore: {{ info.salespderstore }}
      </v-list-item>
      <v-list-item>
        salestotal: {{ info.salestotal }}
      </v-list-item>
      <v-list-item>
        businesschane: {{ info.businesschane }}
      </v-list-item>
      <v-list-item>
        businesstiming: {{ info.businesstiming }}
      </v-list-item>
      <v-list-item>
        Maincustomer: {{ info.maincustomer }}
      </v-list-item>
      <v-list-item>
        result: {{ info.result }}
      </v-list-item>
    </v-list>
  </div>
</template>

<script>
import axios from '../../js/http-commons'

export default {
  data () {
    return {
      key: '',
      info: {
        'name': '',
        'value': '',
        'salespderstore': '',
        'salestotal': '',
        'businesschane': '',
        'businesstiming': '',
        'maincustomer': '',
        'result': ''
      }
    }
  },
  methods: {
    getData () {
      if (this.key !== '') {
        axios.get('/suggestion/Industry/' + this.key).then(res => {
          // console.log(res)
          console.log(res.data.bl)
          this.info.name = res.data.bl.name
          this.info.value = res.data.bl.value
          this.info.salespderstore = res.data.bl.salespderstore
          this.info.salestotal = res.data.bl.salestotal
          this.info.businesschane = res.data.bl.businesschane
          this.info.businesstiming = res.data.bl.businesstiming
          this.info.maincustomer = res.data.bl.maincustomer
          // this.info.result = res.data.result
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
