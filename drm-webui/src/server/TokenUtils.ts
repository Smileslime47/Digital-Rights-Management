import User from "~/modules/User.ts";
import Group from "~/modules/Group.ts";
import httpService from "~/server/http.ts";
import Constant from "~/constant/Constant.ts";
import {CoroutineLock} from "~/server/CoroutineLock.ts";

/**
 * 缓存类，用于在路由未刷新时
 * 如果页面多次查询当前登陆用户信息，则只发送一次请求
 */
class TokenUtils {
    private static lock: CoroutineLock = new CoroutineLock()

    private static user: User = new User()
    private static group: Group = new Group()
    private static noticeCnt: number = 0
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

        await httpService.get(
            Constant.Api.USER_API,
            {
                params: {
                    id: userLocalId
                }
            }
        ).then((data) => {
            this.user = data[Constant.RespondField.USER] as User
        })
        await httpService.get(
            Constant.Api.GROUP_API,
            {
                params: {
                    id: this.user.permissionId
                }
            }
        ).then((data) => {
            this.group = data[Constant.RespondField.GROUP] as Group
        })
        await httpService.get(
            Constant.Api.NOTICE_API + Constant.Api.NOTICE_COUNT,
            {
                params: {
                    filter: Constant.NoticeFilter.UNREAD
                }
            }
        ).then((data) => {
            this.noticeCnt = data
        })
        return true
    }

    public static async getUser(routeNow: string) {
        await this.lock.lockCoroutine()

        if (this.routeCache.length === 0 || !this.initialized) {
            this.routeCache = routeNow
            if (await this.flushData()) {
                this.initialized = true
            }
        }

        this.lock.unlockCoroutine()
        return this.user
    }

    public static async getGroup(routeNow: string) {
        await this.lock.lockCoroutine()

        if (this.routeCache.length === 0 || !this.initialized) {
            this.routeCache = routeNow
            if (await this.flushData()) {
                this.initialized = true
            }
        }

        this.lock.unlockCoroutine()
        return this.group
    }

    public static async getNoticeCnt(routeNow: string) {
        await this.lock.lockCoroutine()

        if (this.routeCache.length === 0 || !this.initialized) {
            this.routeCache = routeNow
            if (await this.flushData()) {
                this.initialized = true
            }
        }

        this.lock.unlockCoroutine()
        return this.noticeCnt
    }
}

export default TokenUtils