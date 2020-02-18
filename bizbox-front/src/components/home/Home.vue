<template>
  <div class="biz-container">
    <div>
      <start></start>
      <News></News>
      <about></about>
      <biz-us></biz-us>
    </div>
    <HomeFooter></HomeFooter>
  </div>
</template>

<script>
import Start from './start/Start'
import News from './news/News'
import HomeFooter from './homefooter/HomeFooter'
import About from './about/About'
import BizUs from './bizus/BizUs'

export default {
  components: {
    Start,
    News,
    HomeFooter,
    About,
    BizUs
  }
}
</script>

<style scoped src="./home.css"></style>
