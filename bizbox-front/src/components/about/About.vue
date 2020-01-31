<template>
  <div class="about">
    <v-row>
      <v-col>
        <countStores></countStores>
      </v-col>
      <v-col>
        <recommendation></recommendation>
      </v-col>
    </v-row>
  </div>
</template>
<script>
import countStores from '@/components/about/CountStores.vue'
import recommendation from '@/components/about/Recommendation.vue'

export default {
  components: {
    countStores,
    recommendation
  }
}
</script>
