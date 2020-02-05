<template>
  <div class="flex-column-1">
    <div class="container searchbox">
      <h1><strong>비즈박스</strong>에서<br>상권정보를 검색하세요</h1>
      <SearchForm></SearchForm>
    </div>
  </div>
</template>

<script>
import SearchForm from './SearchForm/SearchForm.vue'
export default {
  components: {
    SearchForm
  }
}
</script>

<style scoped src="./Search.css"></style>
