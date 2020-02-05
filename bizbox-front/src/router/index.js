import Vue from 'vue'
import VueRouter from 'vue-router'
import Signup from '@/components/signup/Signup.vue'
import Login from '@/components/login/Login.vue'
import Home from '@/components/home/Home.vue'
import ResultSearch from '@/components/result/ResultSearch.vue'
import Map from '@/components/map/KakaoMap.vue'
import BizMap from '@/components/bizmap/BizMap'
import Modal from '@/components/modal/Modal.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'home',
    component: Home
  },
  {
    path: '/login',
    name: 'login',
    component: Login
  },
  {
    path: '/signup',
    name: 'signup',
    component: Signup
  },
  {
    path: '/result2',
    name: 'newResult',
    component: ResultSearch
  },
  {
    path: '/map',
    name: 'mapcd .',
    component: Map
  },
  {
    path: '/bizmap',
    name: 'bizmap',
    component: BizMap
  },
  {
    path: '/modal',
    name: 'modal',
    component: Modal
  }
  // 바꿈
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
