<template>
  <div class="biz-container">
    <div class="biz-item-1">
      <start></start>
      <about></about>
      <biz-us></biz-us>
    </div>
    <div class="flex-item-1">
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
