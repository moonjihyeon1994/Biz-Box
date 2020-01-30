<template>
  <div id='home' class="mar-0">
    <div class="searchbox">
    <Vsearch></Vsearch>
    </div>
  </div>
</template>

<script>
import Vsearch from './Vsearch.vue'
export default {
  name: 'Home',
  components: {
    Vsearch
  }
}
</script>
<style scoped src="./home.css"></style>
