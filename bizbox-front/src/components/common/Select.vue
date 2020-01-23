<template>
  <div class="select">
    <select name="slct" id="slct">
      <option selected disabled>{{ idx }}</option>
      <option v-for="item in items" :key="item">{{ item }}</option>
    </select>
  </div>
</template>

<script>
export default {
  props: {
    idx: String,
    items: Array
  }
}
</script>

<style scoped src="@/components/common/Select.css"></style>
