<template>
  <div class="container searchbar">
    <div class="container box">
      <div class="flex-column-1 search-item"><Select :idx="items1.idx" :items="items1.item"></Select></div>
      <div class="flex-column-1 search-item"><Select :idx="items2.idx" :items="items2.item"></Select></div>
      <div class="flex-column-3 search-item"><input type="text" placeholder="주소를 입력하세요"></div>
    </div>
  </div>
</template>

<script>
import Select from '@/components/common/Select.vue'
export default {
  data () {
    return {
      items1: {
        idx: '시/도',
        item: ['서울', '부산', '울산']
      },
      items2: {
        idx: '구/군',
        item: ['강남구', '강서구', '강북구']
      }
    }
  },
  components: {
    Select
  }
}
</script>

<style scoped src="./SearchBar.css"></style>
