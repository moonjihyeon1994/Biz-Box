<template>
  <div class="container searchbar">
    <v-container>
      <div class="flex-column-1 search-item">
        <v-select label="시/도" :items="items1"></v-select>
      </div>
      <div class="flex-column-1 search-item">
        <v-select label="구/군" :items="items2"></v-select>
      </div>
      <div class="flex-column-3 search-item">
        <input type="text" placeholder="주소를 입력하세요" />
      </div>
    </v-container>
  </div>
</template>

<script>
export default {
  data: () => ({
    items1: ['서울', '부산', '울산'],
    items2: ['강남구', '강서구', '강북구']
  })
}
</script>

<style scoped src="./SearchBar.css"></style>
