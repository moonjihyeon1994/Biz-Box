import Vue from 'vue'
import VueRouter from 'vue-router'
import Signup from '@/components/signup/Signup.vue'
import Login from '@/components/login/Login.vue'
import Home from '@/components/home/Home.vue'
import BizMap from '@/components/bizmap/BizMap'

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
    path: '/bizmap',
    name: 'bizmap',
    component: BizMap
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
