<template>
  <div class="tabcontainer">
    <div class="tab">
      <button class="tablinks" @click="openContents($event, 'Total')" id="defaultOpen">
        보고서
      </button>
      <button class="tablinks" @click="openContents($event, 'Graph')">
        그래프
      </button>
    </div>

    <div id="Total" class="tabcontent">
      <totalView></totalView>
    </div>

    <div id="Graph" class="tabcontent">
      <graphItem />
    </div>
  </div>
</template>

<script>
import graphItem from './tab_contents/graph.vue'
import totalView from './tab_contents/total.vue'
export default {
  components: {
    graphItem,
    totalView
  },
  mounted () {
    document.getElementById('defaultOpen').click()
  },
  methods: {
    openContents (evt, cityName) {
      if (cityName === 'Graph') {
        this.$store.state.opencontents = 0
      } else if (cityName === 'Total') { this.$store.state.opencontents = 1 }
      let i, tabcontent, tablinks
      // Get all elements with class="tabcontent" and hide them
      tabcontent = document.getElementsByClassName('tabcontent')
      for (i = 0; i < tabcontent.length; i++) {
        tabcontent[i].style.display = 'none'
      }

      // Get all elements with class="tablinks" and remove the class "active"
      tablinks = document.getElementsByClassName('tablinks')
      for (i = 0; i < tablinks.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(' active', '')
      }

      // Show the current tab, and add an "active" class to the button that opened the tab
      document.getElementById(cityName).style.display = 'block'
      evt.currentTarget.className += ' active'
    }
  }
}
</script>

<style scoped src='./TabMenu.scss' lang='scss'></style>
