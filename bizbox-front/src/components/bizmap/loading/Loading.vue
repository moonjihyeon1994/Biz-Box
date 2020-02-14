<template>
  <div id="bz-loading-container" v-if="loading">
    <div id="bz-blur-background" :style='bg'></div>
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
      bg: {
        backgroundColor: 'rgba(0,0,0,0.1)',
        filter: 'blur(2px)'
      }
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
