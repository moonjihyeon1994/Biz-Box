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

<style>
.tab {
  overflow: hidden;
  border: 1px solid #ccc;
  border-top: 0px solid transparent;
  border-left: 0px solid transparent;
  border-right: 0px;
  /* background-color: #f1f1f1; */
  border-radius: 3px 3px 0px 0px;
  clear: both;
  margin: 0;
}
.tab div {
  border: 0;
}
/* Style the buttons that are used to open the tab content */
.tab button {
  background-color: #f1f1f1;
  float: left;
  border: 1px solid #ccc;
  outline: none;
  cursor: pointer;
  padding: 5px;
  transition: 0.3s;
  width: 80px;
  margin: 0;
}
.close-button{
  float: right;
  width: 0;
  margin: 0;
  padding: 0;
}
/* Change background color of buttons on hover */
.tab button:hover {
  background-color: #ddd;
}

/* Create an active/current tablink class */
.tab button.active {
  background-color: #ccc;
}

/* Style the tab content */
.tabcontent {
  display: none;
  padding: 6px 12px;
  border: 1px solid #ccc;
  border-top: none;
  /* height: 80%; */
}

.tabcontent p {
  display: block;
}
.v-application p {
  margin-bottom: 0px !important;
}
</style>
