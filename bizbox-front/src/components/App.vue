<template>
  <div id="app">
    <NavBar></NavBar>
    <div class="container">
      <router-view />
    </div>
  </div>
</template>
<script>
import NavBar from '@/components/common/NavBar.vue'
export default {
  name: 'app',
  components: {
    NavBar
  }
}
</script>
<style src="@/assets/css/reset.css"></style>
<style src="@/components/common/common.css"></style>
