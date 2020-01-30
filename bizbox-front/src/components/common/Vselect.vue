<template>
  <div class="m-8px">
    <v-select :items="items" :label="labname" dark></v-select>
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
