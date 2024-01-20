import {RouteRecordRaw} from "vue-router";

const routes: RouteRecordRaw[] = [
    {path: '/', redirect: 'Home'},
    {path: '/home', name: 'Home', component: () => import('~/pages/HomePage.vue')},
    {path: '/debug', name:'Debug', component: () => import('~/pages/Debugpage.vue')},
    {path: '/login', name:'Login', component: () => import('~/pages/LoginPage.vue')},
]

export default routes