<template>
  <div id="app">
    <div id="nav">
      <router-link to="/" class="link1">Home</router-link>
      <router-link to="/about" class="link1">About</router-link>
      <router-link to="/result" class="link1">Result</router-link>
    </div>
    <router-view />
  </div>
</template>
<style src="@/assets/css/reset.css"></style>
<style src="@/components/common/common.css"></style>
<style src="@/components/common/nav.css"></style>
