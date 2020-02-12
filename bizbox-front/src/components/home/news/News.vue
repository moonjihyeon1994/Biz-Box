<template>
  <div class="biz-container">
    <h3><b>오늘의 뉴스</b></h3>
    <v-carousel height="70px"  :show-arrows="arrowAble" hide-delimiters vertical cycle interval="4000">
      <v-carousel-item
        v-for="news in newses"
        :key="news.title"
        class="news-container"
      >
        <a :href="news.url" target="_blank">{{ news.title }}</a>
      </v-carousel-item>
    </v-carousel>
  </div>
</template>

<script>
import axios from '../../../js/http-commons'
export default {
  data () {
    return {
      newses: [],
      arrowAble: false
    }
  },
  methods: {
    getNews () {
      axios
        .get('/crawling/naver')
        .then(res => {
          this.newses = res.data.headlines
        })
        .catch(err => {
          alert(err, '불러울 뉴스 데이터가 없습니다')
        })
    }
  },
  mounted () {
    this.getNews()
  }
}
</script>

<style scoped src="./News.css"></style>
