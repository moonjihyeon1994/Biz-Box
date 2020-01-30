<template>
  <div class="select">
    <select name="slct" id="slct" v-model="selected">
      <option selected disabled>{{ idx }}</option>
      <option v-for="item in items" :key="item">{{ item }}</option>
    </select>
  </div>
</template>

<script>
export default {
  data () {
    return {
      selected: this.idx
    }
  },
  props: {
    idx: String,
    items: Array
  }
}
</script>

<style scoped src="@/components/common/Select.css"></style>
