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
    page: (path: string) => loadAndTo(path),
    home: () => loadAndTo("/home"),
    debug: () => loadAndTo("/Debug"),
    login: () => loadAndTo("/login"),
    profile: (id: Number) => loadAndTo("/profile/" + id),
    notice: (filter: String, page: String) => loadAndTo("/notice/" + filter + "/" + page),
    changePassword: () => loadAndTo("/change-password"),
    chainAccount: () => loadAndTo("/chain/account"),
    chainVerify: (page: String) => loadAndTo("/chain/verify/" + page),
    createRight: () => loadAndTo("/chain/right/create"),
    fresh: () => router.go(0)
}

export default routeTo