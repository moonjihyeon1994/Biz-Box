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
  data () {
    return {
    }
  },
  props: {
    loading: {
      type: Boolean,
      required: true
    },
    transparent: {
      type: Boolean
    }
  },
  mounted () {
    if (this.transparent) {
      this.bg.backgroundColor = 'rgba(245, 245, 245)'
      this.bg.filter = 'none'
    }
  }
}
</script>

<style scoped src='./Loading.scss' lang='scss'></style>
