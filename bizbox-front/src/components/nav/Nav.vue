<template>
  <nav>
    <v-app-bar app :color="navColor" flat>
      <v-toolbar-title style="width: 300px">
        <span>BizBox</span>
      </v-toolbar-title>
      <v-spacer />
      <!-- Basic Nav items -->
      <v-list dense v-for="(nav, index) in navList" :key="'N' + index">
        <v-list-item link :to="nav.routeto">
          <v-list-item-content>
            <v-list-item-title>{{ nav.title }}</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list>
      <!-- Logout -->
      <v-list dense v-if="isLoggedIn">
        <v-list-item link>
          <v-list-item-content @click.prevent="logout">
            <v-list-item-title>Logout</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list>
      <!-- Login Button + Modal -->
      <v-list dense v-else>
        <v-list-item link>
          <v-list-item-content @click="popupDialog">
            <v-list-item-title>Login</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list>
    </v-app-bar>
  </nav>
</template>

<script>
import { mapActions, mapGetters } from 'vuex'
export default {
  name: 'app',
  data: () => ({
    navList: [
      { routeto: '/', icon: 'mdi-card-search-outline', title: 'Home' },
      { routeto: '/bizmap', icon: 'mdi-information-outline', title: 'Search' },
      { routeto: '/modal', icon: 'mdi-information-outline', title: 'Modal' }
    ]
  }),
  props: {
    navColor: {
      type: String
    }
  },
  computed: {
    ...mapGetters(['isLoggedIn'])
  },
  methods: {
    ...mapActions(['logout', 'popupDialog'])
  }
}
</script>

<style scoped src="./Nav.css"></style>
