<template>
  <form class="container searchform" action="/result">
    <Vselect
      :items="items1"
      :labname="labname1"
      v-model="select1"
      class="flex-column-1"
    ></Vselect>
    <Vselect
      :items="items2"
      :labname="labname2"
      v-model="select2"
      class="flex-column-1"
    ></Vselect>
    <v-text-field
      class="flex-column-1"
      dark
      placeholder="도로명 주소"
    ></v-text-field>
    <button>검색하기</button>
  </form>
</template>

<script>
import Vselect from '@/components/common/Vselect.vue'
export default {
  data: () => ({
    select1: null,
    labname1: '시/도',
    items1: ['서울', '부산', '울산'],
    select2: null,
    labname2: '구/군',
    items2: ['강남구', '강서구', '강북구']
  }),
  components: {
    Vselect
  }
}
</script>

<style scoped src="./SearchForm.css"></style>
