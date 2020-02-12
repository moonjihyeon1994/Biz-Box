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
    },
    mode: 0,
    level: false,
    modalsearch: ''
  },
  actions: {
    'ChangeCenter': (store, payload) => {
      store.commit('ChangeCenter', { xy: payload.xy })
    },
    'ChangeMode': (store, payload) => {
      console.log(payload.mode)
      store.commit('ChangeMode', { mode: payload.mode })
    }
    // 'GetCenter': store => {
    //   store.commit('GetCenter', );
    // }
  },
  mutations: {
    'ChangeCenter': (state, payload) => {
      state.Map.center.lat = payload.x
      state.Map.center.lng = payload.y
    },
    'ChangeMode': (state, payload) => {
      state.mode = payload.mode
      console.log( state.mode)
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
