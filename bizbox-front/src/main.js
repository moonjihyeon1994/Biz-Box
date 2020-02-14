import Vue from 'vue'
import App from '@/components/App.vue'
import router from './router'
import store from './store'
import vuetify from './plugins/vuetify'
import ScrollAnimation from './directives/scrollanimation'
import Animation from './directives/animation'

Vue.directive('scrollanimation', ScrollAnimation)
Vue.directive('animation', Animation)

Vue.config.productionTip = false

new Vue({
  router,
  store,
  vuetify,
  render: h => h(App)
}).$mount('#app')
