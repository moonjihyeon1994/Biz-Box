<template>
  <form class="container searchform" action="/result">
    <Vselect
      :items="items1"
      :labname="labname1"
      v-model="select1"
      class="flex-item-1"
    ></Vselect>
    <Vselect
      :items="items2"
      :labname="labname2"
      v-model="select2"
      class="flex-item-1"
    ></Vselect>
    <v-text-field
      class="flex-item-1"
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
    items1: [
      '서울특별시', '대전광역시', '부산광역시', '인천광역시', '울산광역시', '광주광역시', '대구광역시'
      // { name: '서울특별시', able: true },
      // { name: '대전광역시', able: false },
      // { name: '부산광역시', able: false },
      // { name: '인천광역시', able: false },
      // { name: '울산광역시', able: false },
      // { name: '광주광역시', able: false },
      // { name: '대구광역시', able: false }
    ],
    select2: null,
    labname2: '구/군',
    items2: ['']
  }),
  components: {
    Vselect
  }
}
</script>

<style scoped src="./SearchForm.css"></style>
