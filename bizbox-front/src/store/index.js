import Vue from 'vue'
import Vuex from 'vuex'
import auth from './modules/auth'
import map from './modules/map'

Vue.use(Vuex)

const store = new Vuex.Store({
  // state: {
  // },
  // mutations: {
  // },
  // actions: {
  // },
  modules: {
    auth,
    map
  }
})

export default store
