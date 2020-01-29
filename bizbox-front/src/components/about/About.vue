<template>
  <div class="about">
    <h1>This is an about page</h1>
    <!-- <v-row>
      <resultList></resultList>
    </v-row> -->
    <v-row>
      <Login></Login>
    </v-row>
  </div>
</template>
<script>
// import resultList from '@/components/about/resultList.vue'
import Login from '@/components/common/Login.vue'
export default {
  components: {
    // resultList,
    Login
  }
}
</script>
