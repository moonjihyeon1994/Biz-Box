import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '@/components/home2/Home.vue'
import About from '@/components/about/About.vue'
import Result from '@/components/result/Result.vue'
import Login from '@/components/common/Login.vue'
import Signup from '@/components/common/Signup.vue'
import ResultSearch from '@/components/result/ResultSearch.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'home2',
    component: Home
  },
  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: About
  },
  {
    path: '/result',
    name: 'result',
    component: Result
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
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
