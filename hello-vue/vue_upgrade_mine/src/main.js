import Vue from 'vue'
import App from './App.vue'

Vue.config.productionTip = false

new Vue({
    render: h => h(App),
    mounted() {
        setTimeout(() => {
            // root 被销毁后，其子组件的事件也被销毁
            // this.$destroy()
        }, 3000)
    }
}).$mount('#app')
