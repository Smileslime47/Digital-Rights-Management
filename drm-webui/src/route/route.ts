import {RouteRecordRaw} from "vue-router";

const routes: RouteRecordRaw[] = [
    {path: '/', redirect: 'Home'},
    {path: '/home', name: 'Home', component: () => import('~/pages/HomePage.vue')},
    {path: '/debug', name: 'Debug', component: () => import('~/pages/Debugpage.vue')},
    {path: '/login', name: 'Login', component: () => import('~/pages/LoginPage.vue')},
    {path: '/profile/:id', name: 'Profile', component: () => import('~/pages/ProfilePage.vue')},
    {path:'/notice/:filter/:page', name: 'Notice', component: () => import('~/pages/NoticePage.vue')},
    {path: '/change-password', name: 'ChangePassword', component: () => import('~/pages/PasswordPage.vue')},
    {path: '/chain/account', name: 'ChainAccount', component: () => import('~/pages/ChainAccountPage.vue')},
    {path: '/chain/right', name: 'ChainRight', component: () => import('~/pages/ChainRightPage.vue')},
]

export default routes