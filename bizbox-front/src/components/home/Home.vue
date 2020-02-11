<template>
  <div class="biz-container">
    <div>
      <start></start>
      <!-- <about></about> -->
      <biz-us></biz-us>
      <News></News>
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

<style scoped src="./Home.css"></style>
