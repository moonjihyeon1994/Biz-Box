<template>
  <div id="bz-loading-container" v-if="loading">
    <div id="bz-blur-background"></div>
    <div class="lds">
      <div></div>
      <div></div>
      <div></div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    loading: {
      type: Boolean,
      required: true
    }
  }
}
</script>

<style scoped src='./Loading.scss' lang='scss'></style>
