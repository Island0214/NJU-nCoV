import axios from 'axios'; // 引入axios
import QS from 'qs'; // 引入qs模块，用来序列化post类型的数据，后面会提到
// vant的toast提示框组件，大家可根据自己的ui组件更改。
//import store from '@/store/index';
axios.defaults.baseURL = '/api';
// 环境的切换
//if (process.env.NODE_ENV === 'development') {    
//   axios.defaults.baseURL = '/api';} 
//else if (process.env.NODE_ENV === 'debug') {    
//    axios.defaults.baseURL = '/api';
//} 
//else if (process.env.NODE_ENV === 'production') {    
//    axios.defaults.baseURL = '/api';
//}


axios.defaults.timeout = 10000;

axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=UTF-8';


axios.interceptors.request.use(config => {
  // loading
  return config
}, error => {
  return Promise.reject(error)
})

axios.interceptors.response.use(response => {
  return response
}, error => {
  return Promise.resolve(error.response)
})

export function get(url, params){
  console.log("GET URL: "+url);
    return new Promise((resolve, reject) =>{        
        axios.get(url, {            
            params: params        
        }).then(res => {
            resolve(res.data);
        }).catch(err =>{
            reject(err.data)        
    })    
});}


export function post(url, params) {
    return new Promise((resolve, reject) => {
         axios.post(url, QS.stringify(params))
        .then(res => {
            resolve(res.data);
        })
        .catch(err =>{
            reject(err.data)
        })
    });}
