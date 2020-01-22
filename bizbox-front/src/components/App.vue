<template>
  <div id="app">
    <v-tabs>
      <v-tab link to="/" class="link1">BizBox</v-tab>
      <v-tab link to="/result" class="link1">Result</v-tab>
      <v-tab link to="/about" class="link1">About</v-tab>
    </v-tabs>
    <div class="container">
      <router-view />
    </div>
  </div>
</template>
<script>
export default {
  name: 'app'
}
</script>
<style src="@/assets/css/reset.css"></style>
<style src="@/components/common/nav.css"></style>
<style src="@/components/common/common.css"></style>
