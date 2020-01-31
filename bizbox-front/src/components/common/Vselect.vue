<template>
  <div class="m-8px">
    <v-select dense :items="items" :label="labname" dark full-width></v-select>
  </div>
</template>

<script>
export default {
  props: {
    items: Array,
    labname: String
  }
}
</script>

<style scoped src="./Vselect.css"></style>
