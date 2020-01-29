<template>
  <div>
    <v-select :items="items" :label="labname"></v-select>
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
