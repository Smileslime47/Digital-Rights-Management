import axios, {AxiosInstance} from "axios";
import Constant from "~/constant/Constant";
import {ElMessage} from "element-plus";
import HttpResponse from "~/modules/HttpResponse.ts";
import routeTo from "~/route/routeTo.ts";

const httpService: AxiosInstance = axios.create({
    baseURL: Constant.Property.BASE_URL, //基础公共URL
    timeout: 10000,              //超时配置
    withCredentials: false,      //跨域携带cookie
})

httpService.interceptors.request.use(
    //发生请求拦截
    (config) => {
        //如果开启 token 认证
        config.headers["Authorization"] = "Bearer "+localStorage.getItem(Constant.Authentication.TOKEN_STORAGE); // 请求头携带 token

        // // 设置请求头
        // if (!config.headers["content-type"]) { // 如果没有设置请求头
        //     if (config.method === 'post') {
        //         config.headers["content-type"] = "application/json"; // 默认类型
        //         // config.data = qs.stringify(config.data); // 序列化,比如表单数据
        //     } else {
        //         config.headers["content-type"] = "application/x-www-form-urlencoded"; // post 请求
        //     }
        // }
        return config;
    },
    //请求错误拦截
    (error) => {
        Promise.reject(error)
    }
);

httpService.interceptors.response.use(
    (response) => {
        let httpResponse = response.data as HttpResponse
        if (httpResponse.status.code == 200) {
            return httpResponse.data;
        }
        switch (httpResponse.status.code) {
            case 400: {
                ElMessage.error(httpResponse.status.msg)
                return Promise.reject();
            }
            case 401: {
                ElMessage.error("Sign in please.")
                routeTo.login()
                return Promise.reject();
            }
            case 403: {
                ElMessage.error(httpResponse.status.msg)
                return Promise.reject();
            }
            default :{
                ElMessage.error(httpResponse.status.msg)
                return Promise.reject();
            }
        }
    },
    (error) => {
        // 超出 2xx 范围的状态码都会触发该函数。
        return Promise.reject(error);
    }
);

export default httpService