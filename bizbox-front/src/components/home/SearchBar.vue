<template>
  <div class="row">
    <div class="col-s-12 col-2"><Select :idx="items1.idx" :items="items1.item"></Select></div>
    <div class="col-s-12 col-2"><Select :idx="items2.idx" :items="items2.item"></Select></div>
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
