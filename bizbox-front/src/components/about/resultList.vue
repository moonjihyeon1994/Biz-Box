<template>
<v-col cols="6">
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
</v-col>
  </template>

<script>
import axios from '../../js/http-commons'

export default {
  name: 'resultList',
  data () {
    return {
      info: {
        '소매': '',
        '학문교육': '',
        '숙박': '',
        '생활서비스': '',
        '음식': '',
        '부동산': '',
        '의료': '',
        '관광여가오락': ''
        // '선택업종': ''
      }
    }
  },
  mounted () {
    axios.get('/storecount/%EA%B2%BD%EC%9D%B8%EB%A1%9C248-14/500')
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
        // console.log(res.data.small)
        // this.info.선택업종 = res.data.small
      })
  }
}
</script>
