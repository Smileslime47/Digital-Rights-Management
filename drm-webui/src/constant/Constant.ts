const Constant = {
    Global: {
        NULL_NUMBER_PLACEHOLDER: 0,
        NULL_STRING_PLACEHOLDER: "undefined",
        DEFAULT_PAGE_SIZE:10,
    },
    NoticeFilter: {
        ALL:"all",
        UNREAD: "unread",
        READ: "read",
        ARCHIVED: "archived"
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
        NOTICE:"notice",
        SELF_PROFILE: "self",
        SUCCESS: "success",
        ADDRESS: "address",
        BALANCE:"balance",
        COUNT:"count"
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

        CHAIN_ACCOUNT_API: "/account",
        CHAIN_GET_BY_USER:"/by-user",

        ACCOUNT_NEW_ACCOUNT: "/new-account",
        ACCOUNT_GET_BALANCE: "/balance",
        ACCOUNT_CHARGE: "/charge",

        GROUP_API: "/group",

        NOTICE_API:"/notice",
        NOTICE_COUNT:"/count",
    }
}
export default Constant