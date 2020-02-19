import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import './plugins/element.js'
import './index.less'
import './element-variables.scss'

Vue.config.productionTip = false


var axios = require('axios')
axios.defaults.baseURL = 'http://localhost:8443/api'

Vue.prototype.$axios = axios

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
