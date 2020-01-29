<template>
  <div id="app">
    <Nav></Nav>
      <router-view />
  </div>
</template>
<script>
import Nav from '@/components/common/Nav.vue'
export default {
  name: 'app',
  components: {
    Nav
  }
}
</script>
<style src="@/components/common/common.css"></style>
