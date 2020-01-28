<template>
  <div class="row">
    <SearchBar class="col-12"></SearchBar>
  </div>
</template>

<script>
import SearchBar from './SearchBar.vue'
export default {
  name: 'Home',
  components: {
    SearchBar
  }
}
</script>
<style scoped src="./home.css"></style>
