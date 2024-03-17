import router from "./router";
import {ElLoading} from 'element-plus'

//切换界面时显示加载过渡
const loadAndTo = ((path: string) => {
    const loadingInstance = ElLoading.service()
    router.push(path).then(() => {
        loadingInstance.close()
    })
})


const routeTo = {
    home: () => loadAndTo("/home"),
    debug: () => loadAndTo("/Debug"),
    login: () => loadAndTo("/login"),
    profile: (id: Number) => loadAndTo("/profile/" + id),
    changePassword: () => loadAndTo("/change-password"),
    chainAccount: () => loadAndTo("/chain-account"),
    fresh: () => router.go(0)
}

export default routeTo