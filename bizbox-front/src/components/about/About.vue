<template>
  <div class="about">
    <h1>This is an about page</h1>
    <v-row>
      <resultList></resultList>
    </v-row>
  </div>
</template>
<script>
import resultList from '@/components/about/resultList.vue'
export default {
  components: {
    resultList
  }
}
</script>
