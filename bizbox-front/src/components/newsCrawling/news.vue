<template>
<div>
    <div class="newsrow" v-for="news in newses" :key="news.title">
        <span>
              "<a :href="news.url" target="_blank">
                {{news.title}}
              </a>"
        </span>
    </div>
</div>
</template>

<script>
import axios from '../../js/http-commons'

export default {
    data(){
        return{
            newses: []
        }
    },
    mounted(){
        this.getNews()
    },
    methods: {  
        getNews(){  
            axios
            .get('/crawling/naver')
            .then(res=>{
                this.newses = res.data.headlines;
            })
            .catch(err=>{
                alert(err, '불러올 뉴스 데이터가 없습니다.')
            })
        }       
    }
}
</script>

<style>
.newsrow {
        margin: 10px;
       }
.newsrow a {
        text-decoration: none;
        color: black;
        size: 2em;
        text-shadow: 0 0 12px;
      }
.newsrow span {
        size: 10px;
      }
</style>