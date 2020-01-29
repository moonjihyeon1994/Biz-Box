<template>
  <v-app id="inspire">
    <v-app-bar app dark>
      <v-toolbar-title style="width: 300px">
        <span>Biz Box</span>
      </v-toolbar-title>
      <v-spacer />
      <v-list dense v-for="nav in navList" :key="nav.routeto">
        <v-list-item link :to="nav.routeto">
          <v-list-item-content>
            <v-list-item-title>{{ nav.title }}</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list>
    </v-app-bar>
    <div class="container">
      <router-view />
    </div>
  </v-app>
</template>
<script>
export default {
  name: 'app',
  data: () => ({
    drawer: null,
    navList: [
      { routeto: '/', icon: 'mdi-card-search-outline', title: 'Home' },
      { routeto: '/result', icon: 'mdi-information-outline', title: 'Result' },
      { routeto: '/about', icon: 'mdi-account', title: 'Login' }
    ]
  })
}
</script>

<style src="@/components/common/common.css"></style>
<style src="@/components/common/Navbar.css"></style>
