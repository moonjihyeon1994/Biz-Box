<template>
  <div class="container">
    <div class="flex-item-1">
    <Vsearch></Vsearch>
    </div>
    <div class="flex-item-1">
    <AddressList></AddressList>
    </div>
  </div>
</template>

<script>
import Vsearch from './Vsearch.vue'
import AddressList from './AddressList'
export default {
  name: 'Home',
  components: {
    Vsearch,
    AddressList
  }
}
</script>
<style scoped src="./home.css"></style>
