<template>
  <div class="biz-container">
    <div class="biz-item-1">
      <start></start>
      <h1>hello</h1>
    </div>
    <div class="flex-item-1">
      <h1>hello</h1>
    </div>
    <div class="flex-item-1">
      <h1>hello</h1>
    </div>
    <div class="flex-item-1">
      <News></News>
    </div>
  </div>
</template>

<script>
import Start from './start/Start'
import News from './news/News'

export default {
  components: {
    Start,
    News
  }
}
</script>

<style scoped src="./Home.css"></style>
