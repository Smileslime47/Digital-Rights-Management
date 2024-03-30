const Constant = {
    Global: {
        NULL_NUMBER_PLACEHOLDER: 0,
        NULL_STRING_PLACEHOLDER: "undefined",
        DEFAULT_PAGE_SIZE:10,
        DEFAULT_PAGE:"1",
    },
    NoticeFilter: {
        ALL:"all",
        UNREAD: "unread",
        READ: "read",
        ARCHIVED: "archived"
    },
    PendingStatus:{
        PENDING:"PENDING",
        CONFIRMED:"CONFIRMED",
        REJECTED:"REJECTED"
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
        RIGHT:"right",
        BALANCE:"balance",
        COUNT:"count"
    },
    Property: {
        BASE_URL: import.meta.env.VITE_BASE_URL,
        ASSETS_URL: import.meta.env.VITE_ASSETS_URL,
    },
    Api: {
        DEBUG:{
            USER: "/debug/user",
            STATUS: "/debug/error_status",
        },
        USER:{
            ROOT: "/user",
            LOGIN: "/user/login",
            REGISTER: "/user/register",
            CHANGE_PWD: "/user/change-password",
        },
        GROUP:{
            ROOT: "/group",
        },
        NOTICE:{
            ROOT: "/notice",
            COUNT: "/notice/count",
        },
        CHAIN:{
            ACCOUNT:{
                NEW_ACCOUNT: "/chain/account/new-account",
                GET_BALANCE: "/chain/account/balance",
                GET_BY_USER: "/chain/account/by-user",
                CHARGE: "/chain/account/charge",
            },
            RIGHT:{
                ROOT:"/chain/right",
                VERIFY:"/chain/right/verify",
                CONFIRM:"/chain/right/verify/confirm",
                REJECT:"/chain/right/verify/reject"
            }
        },
    }
}
export default Constant