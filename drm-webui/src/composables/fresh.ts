import {RouteLocationNormalized} from "vue-router";

//在路由更新及初始加载时执行更新策略
const fresh = async (action: (route: RouteLocationNormalized) => void) => {
    onMounted(async () => action(useRoute()))
    onBeforeRouteUpdate(async (to, _) => action(to))
}

export default fresh