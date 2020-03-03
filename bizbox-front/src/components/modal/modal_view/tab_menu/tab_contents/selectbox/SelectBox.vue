<template>
  <div id='bz-select-box'>
    <div id='bz-select-box-selected'  @click='toggleBox' >{{ selected }}</div>
    <div id='bz-select-box-list' :style='listStyle' v-animation>
      <ul>
        <li @click='selectItem(-1)'>전체</li>
        <li v-for='(item, num) in list' :key='item.name' @click='selectItem(num)'>{{ item.name }}</li>
      </ul>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      selected: '전체',
      listStyle: {
        display: 'none'
      },
      name: null
    }
  },
  props: {
    list: []
  },
  methods: {
    toggleBox () {
      if (this.listStyle.display === 'block') {
        this.listStyle.display = 'none'
      } else {
        this.listStyle.display = 'block'
      }
    },
    selectItem (num) {
      if (num === -1) {
        this.selected = '전체'
      } else {
        this.selected = this.list[num].name
      }
      this.listStyle.display = 'none'
    }
  }
}
</script>

<style src='./SelectBox.scss' lang='scss'></style>
