<template>
  <div class="about">
    <h1>This is an about page</h1>
    <v-row>
      <countStores></countStores>
    </v-row>
  </div>
</template>
<script>
import countStores from '@/components/about/CountStores.vue'
export default {
  components: {
    countStores
  }
}
</script>
