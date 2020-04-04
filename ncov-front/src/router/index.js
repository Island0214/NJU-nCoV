import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Info from '../views/Info.vue'
import News from "../components/news/News";
import Epidemic from '../views/Epidemic.vue'

import RequestSarsData from "../components/RequestSarsData";
import Sars from "../views/Sars.vue"

Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        name: 'Home',
        component: Home
    },
    {
        path: '/about',
        name: 'About',
        // route level code-splitting
        // this generates a separate chunk (about.[hash].js) for this route
        // which is lazy-loaded when the route is visited.
        component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
    },
    {
        path: '/requestSarsData',
        name: 'RequestSarsData',
        component: RequestSarsData
    },
    {
        path: '/news',
        name: 'News',
        component: News
    },
    {
        path: '/news2',
        name: 'News2',
        component: Info
    },
    {
        path: '/epidemic',
        name: 'Epidemic',
        component: Epidemic
    },
    {
        path: '/sars',
        name: 'Sars',
        component: Sars
    },
    // for test
    {
        path: '/sarsTest',
        name: 'RequestSarsData',
        component: RequestSarsData
    }
]

const router = new VueRouter({
    routes
})

export default router
