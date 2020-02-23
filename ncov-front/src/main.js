import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import './plugins/element.js'
import './index.less'
import './element-variables.scss'
import echarts from 'echarts'

Vue.prototype.$echarts = echarts
Vue.config.productionTip = false


var axios = require('axios')
axios.defaults.baseURL = 'https://lab.isaaclin.cn/nCoV/api/'

Vue.prototype.$axios = axios

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
