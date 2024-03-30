import User from "~/modules/User.ts";
import Group from "~/modules/Group.ts";
import Constant from "~/constant/Constant.ts";
import {CoroutineLock} from "~/server/CoroutineLock.ts";
import {httpServiceIgnoreStatus} from "~/server/http.ts";

/**
 * 缓存类，用于在路由未刷新时
 * 如果页面多次查询当前登陆用户信息，则只发送一次请求
 */
class TokenUtils {
    private static lock: CoroutineLock = new CoroutineLock()

    private static user: User = new User()
    private static group: Group = new Group()
    private static noticeCnt: number = 0
    private static chainAddress: string = ""

    private static initialized: boolean = false
    public static routeCache: string = ""

    public static async flushData(): Promise<Boolean> {
        //是否登陆
        let userLocalId = localStorage.getItem(Constant.Authentication.USER_ID_CLAIM)
        if (userLocalId == null) return false

        //判断登陆凭证是否过期
        let userExpireTime = localStorage.getItem(Constant.Authentication.EXPIRE_TIME_CLAIM)
        if (userExpireTime == null) return false;
        let expireTime = Number.parseInt(userExpireTime)
        let nowTime = new Date().getTime()
        if (nowTime - expireTime > 0) {
            localStorage.clear()
            return false
        }

        //获取用户信息
        await httpServiceIgnoreStatus.get(
            Constant.Api.USER.ROOT,
            {params: {id: userLocalId}}
        ).then((data) => this.user = data[Constant.RespondField.USER] as User)

        //获取用户组信息
        await httpServiceIgnoreStatus.get(
            Constant.Api.GROUP.ROOT,
            {params: {id: this.user.permissionId}}
        ).then((data) => this.group = data[Constant.RespondField.GROUP] as Group)

        //获取未读通知数量
        await httpServiceIgnoreStatus.get(
            Constant.Api.NOTICE.COUNT,
            {params: {filter: Constant.NoticeFilter.UNREAD}}
        ).then((data) => this.noticeCnt = data[Constant.RespondField.COUNT])

        //获取链地址
        await httpServiceIgnoreStatus.get(
            Constant.Api.CHAIN.ACCOUNT.GET_BY_USER,
            {params: {id: userLocalId}}
        ).then((data) => this.chainAddress = data[Constant.RespondField.ADDRESS])

        return true
    }

    public static async checkCache(routeNow: string) {
        await this.lock.lockCoroutine()

        if (this.routeCache.length === 0 || !this.initialized) {
            this.routeCache = routeNow
            if (await this.flushData()) {
                this.initialized = true
            }
        }

        this.lock.unlockCoroutine()
    }

    public static async getUser(routeNow: string) {
        await this.checkCache(routeNow)
        if (this.initialized)
            return this.user
        else
            return null
    }

    public static async getGroup(routeNow: string) {
        await this.checkCache(routeNow)
        if (this.initialized)
            return this.group
        else
            return null
    }

    public static async getNoticeCnt(routeNow: string) {
        await this.checkCache(routeNow)
        if (this.initialized)
            return this.noticeCnt
        else
            return null
    }

    public static async getChainAddress(routeNow: string) {
        await this.checkCache(routeNow)
        if (this.initialized)
            return this.chainAddress
        else
            return null
    }
}

export default TokenUtils