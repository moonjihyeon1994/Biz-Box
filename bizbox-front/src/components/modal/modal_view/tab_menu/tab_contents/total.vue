<template>
  <div class="container-fluid">
    <h1>{{ userinfo }}</h1><br>
    <div class="row">
      <div class="col-xs-12 col-md-6">
        <div id="map_box"></div>
      </div>
      <div class="col-xs-2 col-md-1"></div>
      <div class="col-xs-4 col-md-2">
        <span class="dot"></span>
      </div>
    </div>
    <br>
    <div class="row">
      <div class="result_table">
        {{ result.summary }}
      </div>
    </div>
    <br>
    <div class="row">
      <div class="result_charts"></div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  data: () => {
    return {
      userinfo: sessionStorage.login_user_email,
      key: '오류동',
      result: {
        summary: '',
        score: 0
      }
    }
  },
  created () {
    const getUrl = 'http://70.12.247.78:8080/suggestion/Industry/' + this.key
    axios.get(getUrl)
      .then(res => {
        this.result.summary = res.data.bl.name
      })
  }
}

</script>

<style>
.container-fluid {
  padding-bottom: 500px;
  border: 1px solid black;
}
#map_box {
  border: 1px solid black;
  /* mapImageUrl 가져와야함 */
  background-image: url('http://sg.sbiz.or.kr/repos/analyFiles/sgAnaly/20200204/4350175/map0.jpg');
  background-size: contain;
}
#map_box::after {
  content: '';
  display: block;
  padding-bottom: 100%;
}
.dot {
  display: inline-block;
  height: 50px;
  width: 50px;
  border: 1px solid black;
  border-radius: 50%;
  background-color: aqua;
}
</style>
