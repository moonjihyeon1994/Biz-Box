import Vue from 'vue'
import Vuex from 'vuex'
import auth from './modules/auth'

Vue.use(Vuex)

const store = new Vuex.Store({
  state: {
    Map: {
      center: {
        lat: 37.505691, lng: 127.0298106
      }
    }
  },
  actions: {
    'ChangeCenter': store => {
      store.commit('ChangeCenter', name)
    }
  },
  mutations: {
    'ChangeCenter': (state, payload) => {
      state.Map.center.lat = payload
    }
  },
  // mutations: {
  // },
  // actions: {
  // },
  modules: {
    auth
  }
})

export default store
