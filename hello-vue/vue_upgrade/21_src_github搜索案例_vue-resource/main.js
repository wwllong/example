import Vue from 'vue'
import App from './App.vue'
// npm i vue-resource
import VueResource from 'vue-resource'
Vue.use(VueResource)

Vue.config.productionTip = false

new Vue({
  render: h => h(App),
  beforeCreate() {
    Vue.prototype.$bus = this
  }
}).$mount('#app')
