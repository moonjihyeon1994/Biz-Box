<template>
  <div>
    <v-tabs grow>
      <v-tab link to="/" class="link1">BizBox</v-tab>
      <v-tab link to="/result" class="link1">Result</v-tab>
      <v-tab link to="/about" class="link1">About</v-tab>
      <v-tab link to="/" class="link1">Login</v-tab>
    </v-tabs>
  </div>
</template>

<script>
export default {
  name: 'NavBar'
}
</script>

<style scoped src="@/components/common/NavBar.css"></style>
