<template>
  <div class=tabcontainer>
    <div class="tab">
      <button class="tablinks" @click="openCity($event, 'London')" id="defaultOpen">보고서</button>
      <button class="tablinks" @click="openCity($event, 'Paris')">그래프</button>
      <button class="tablinks" @click="openCity($event, 'Tokyo')">Tokyo</button>
    </div>

    <!-- Tab content -->
    <div id="London" class="tabcontent">
      <h3>보고서</h3>
      <p>보여주자</p>
    </div>

    <div id="Paris" class="tabcontent">
      <h3>귀찬다</h3>
      <p>아주많이</p>
    </div>

    <div id="Tokyo" class="tabcontent">
      <h3>Tokyo</h3>
      <p>Tokyo is the capital of Japan.</p>
    </div>
  </div>
</template>

<script>
export default {
  mounted () {
    document.getElementById('defaultOpen').click()
  },
  methods: {
    openCity (evt, cityName) {
      // Declare all variables
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
  background-color: #f1f1f1;
  border-radius: 10px 10px 0px 0px;
}

/* Style the buttons that are used to open the tab content */
.tab button {
  background-color: inherit;
  float: left;
  border: none;
  outline: none;
  cursor: pointer;
  padding: 14px 16px;
  transition: 0.3s;
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
