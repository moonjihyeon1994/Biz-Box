<template>
  <div class="biz-container">
    <div class="flex-item-1">
      <start></start>
      <about></about>
      <h1>hello</h1>
    </div>
    <div class="flex-item-1">
      <h1>hello</h1>
    </div>
    <div class="flex-item-1">
      <h1>hello</h1>
    </div>
    <div class="flex-item-1">
      <h1>hello</h1>
    </div>
  </div>
</template>

<script>
import Start from './start/Start'
import About from './about/About'

export default {
  components: {
    Start,
    About
  }
}
</script>

<style scoped src="./Home.css"></style>
