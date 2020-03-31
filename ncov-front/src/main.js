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
// axios.defaults.baseURL = 'https://lab.isaaclin.cn/nCoV/api/'

// In Debug
// axios.defaults.baseURL = 'http://127.0.0.1:8444/api/'

// In Deployment
axios.defaults.baseURL = 'http://116.62.131.37:8444/api/'

Vue.prototype.$axios = axios

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
