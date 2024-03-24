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
    RespondField: {
        USER: "user",
        GROUP: "group",
        SELF_PROFILE: "self",
        SUCCESS: "success",
        ADDRESS: "address",
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
        USER_CHANGE_PWD: "/change-password",

        CHAIN_API: "/chain",
        CHAIN_ACCOUNT_API:"/account",

        ACCOUNT_NEW_ACCOUNT: "/new-account",
        ACCOUNT_GET_BALANCE:"/balance",
        ACCOUNT_CHARGE:"/charge",

        GROUP_API: "/group",
    }
}
export default Constant