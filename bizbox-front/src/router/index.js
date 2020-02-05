import Vue from 'vue'
import VueRouter from 'vue-router'
import Signup from '@/components/signup/Signup.vue'
import Login from '@/components/login/Login.vue'
import Home from '@/components/home/Home.vue'
<<<<<<< HEAD
import About from '@/components/about/About.vue'
import Result from '@/components/result/Result.vue'
import Login from '@/components/common/Login.vue'
import Signup from '@/components/common/Signup.vue'
import ResultSearch from '@/components/result/ResultSearch.vue'
import map from '@/components/map/Newmap.vue'
=======
import BizMap from '@/components/bizmap/BizMap'
import Modal from '@/components/modal/Modal.vue'

>>>>>>> 3cf1ed59ee00b2a0ef67a06e86d5054f92bc03d8
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
<<<<<<< HEAD
    path: '/result2',
    name: 'newResult',
    component: ResultSearch
  },
  {
    path: '/map',
    name: 'newResult',
    component: map
=======
    path: '/bizmap',
    name: 'bizmap',
    component: BizMap
  },
  {
    path: '/modal',
    name: 'modal',
    component: Modal
>>>>>>> 3cf1ed59ee00b2a0ef67a06e86d5054f92bc03d8
  }
  // 바꿈
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
