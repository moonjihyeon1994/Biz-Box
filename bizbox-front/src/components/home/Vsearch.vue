<template>
  <div class="container">
    <Vselect :items="items1" :labname="labname1" class="flex-column-1"></Vselect>
    <Vselect :items="items2" :labname="labname2" class="flex-column-1"></Vselect>
  </div>
</template>

<script>
import Vselect from '@/components/common/Vselect.vue'
export default {
  data: () => ({
    labname1: '시/도',
    items1: ['서울', '부산', '울산'],
    labname2: '구/군',
    items2: ['강남구', '강서구', '강북구']
  }),
  components: {
    Vselect
  }
}
</script>

<style scoped src="./Vsearch.css"></style>
