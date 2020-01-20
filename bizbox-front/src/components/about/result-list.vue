<template>
  <div v-if="types.length">
    <ul>
      <li v-for="item in types" :key="item.name">
        {{ item.name }} {{item.count}}
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  data () {
    return {
      types: [{ 'name': 'pizza', 'count': 30 }, { 'name': 'korean', 'count': 100 }, { 'name': 'sushi', 'count': 50 }]
    }
  }
}
</script>
