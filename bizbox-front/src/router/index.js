import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '@/components/login/Login.vue'
import Home from '@/components/home/Home.vue'
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
