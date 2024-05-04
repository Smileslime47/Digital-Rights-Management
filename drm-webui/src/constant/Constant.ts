const Constant = {
    Global: {
        NULL_NUMBER_PLACEHOLDER: 0,
        NULL_STRING_PLACEHOLDER: "undefined",
        DEFAULT_PAGE_SIZE: 10,
        DEFAULT_PAGE: "1",
    },
    NoticeFilter: {
        ALL: "all",
        UNREAD: "unread",
        READ: "read",
        ARCHIVED: "archived"
    },
    PendingStatus: {
        PENDING: "PENDING",
        CONFIRMED: "CONFIRMED",
        REJECTED: "REJECTED"
    },
    Authentication: {
        TOKEN_STORAGE: "token",
        USER_ID_CLAIM: "user_id",
        GROUP_ID_CLAIM: "group_id",
        USER_NICKNAME_CLAIM: "user_nickname",
        EXPIRE_TIME_CLAIM: "expire_time"
    },
    RespondField: {
        //------------------------------数据实体------------------------------//
        USER: "user",
        GROUP: "group",
        NOTICE: "notice",
        RIGHT: "right",
        LICENSE: "license",
        RECEIPT: "receipt",

        //------------------------------Flag字段------------------------------//
        SELF_PROFILE: "self",
        SUCCESS: "success",

        //------------------------------零散数据------------------------------//
        ADDRESS: "address",
        BALANCE: "balance",
        COST: "cost",
        COUNT: "count",
        HASH: "hash",
        NAME: "name"
    },
    Property: {
        BASE_URL: import.meta.env.VITE_BASE_URL,
        ASSETS_URL: import.meta.env.VITE_ASSETS_URL,
    },
    Api: {
        DEBUG: {
            USER: "/debug/user",
            STATUS: "/debug/error_status",
        },
        USER: {
            ROOT: "/user",
            LOGIN: "/user/login",
            REGISTER: "/user/register",
            CHANGE_PWD: "/user/change-password",
        },
        GROUP: {
            ROOT: "/group",
        },
        RECEIPT: {
            ROOT: "/receipt",
        },
        NOTICE: {
            ROOT: "/notice",
            COUNT: "/notice/count",
        },
        PENDING_RIGHT: {
            ROOT: "/pending-right",
            BY_KEYPAIR: "/pending-license/by-keypair",
            DEPLOY: "/pending-right/deploy",
            VERIFY: "/pending-right/verify",
            CONFIRM: "/pending-right/verify/confirm",
            REJECT: "/pending-right/verify/reject"
        },
        PENDING_LICENSE: {
            ROOT: "/pending-license",
            BY_USER: "/pending-license/by-user",
            BY_RIGHT: "/pending-license/by-right",
            DEPLOY: "/pending-license/deploy",
            CONFIRM: "/pending-license/verify/confirm",
            REJECT: "/pending-license/verify/reject"
        },
        CHAIN: {
            ACCOUNT: {
                NEW_ACCOUNT: "/chain/account/new-account",
                GET_BALANCE: "/chain/account/balance",
                GET_BY_USER: "/chain/account/by-user",
                CHARGE: "/chain/account/charge",
            },
            RIGHT: {
                ROOT: "/chain/right",
                DEPLOYER: "/chain/right/deployer",
                SEARCH: "/chain/right/search"
            },
            LICENSE: {
                ROOT: "/chain/license",
                BY_DEPLOYER: "/chain/license/by-deployer",
                BY_RIGHT: "/chain/license/by-right",
            }
        },
        IPFS: {
            ROOT: "/ipfs",
            BY_RIGHT: "/ipfs/by-right",
            BY_LICENSE: "/ipfs/by-license",
        }
    }
}
export default Constant