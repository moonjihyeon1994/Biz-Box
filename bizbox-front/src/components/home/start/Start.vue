<template>
  <div id="bz-container">
    <div id='bz-styleBox'></div>
    <div id='bz-styleText' v-scrollanimation><b>BIZBOX</b></div>
    <div id='bz-styleTextSub' v-scrollanimation>당신의 비즈니스, <br>BIZBOX와 함께라면 성공할 수 있습니다.</div>
    <div id='bz-startBox' v-scrollanimation>
      <div id='bz-startBox-text'>간단하게 시작하기</div>
      <router-link to="/bizmap">
      <div id='bz-startBox-start'>시 작</div>
      </router-link>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {

    }
  }
}
</script>

<style scoped src='./Start.scss' lang='scss'></style>
