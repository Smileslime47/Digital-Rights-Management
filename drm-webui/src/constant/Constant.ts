const Constant = {
    Global: {
        NULL_NUMBER_PLACEHOLDER: 0,
        NULL_STRING_PLACEHOLDER: "undefined",
    },
    Authentication: {
        TOKEN_STORAGE: "token",
        USER_ID_CLAIM: "user_id",
        GROUP_ID_CLAIM: "group_id",
        EXPIRE_TIME_CLAIM: "expire_time"
    },
    RespondField:{
        USER:"user",
        GROUP:"group",
        SELF_PROFILE:"self",
        SUCCESS:"success",
    },
    Property: {
        BASE_URL: import.meta.env.VITE_BASE_URL,
        ASSETS_URL: import.meta.env.VITE_ASSETS_URL,
    },
    Api: {
        DEBUG_API: "/debug",
        DEBUG_USER: "/user",
        DEBUG_STATUS: "/error_status",

        USER_API: "/user",
        USER_LOGIN: "/login",
        USER_REGISTER: "/register",

        GROUP_API:"/group",
    }
}
export default Constant