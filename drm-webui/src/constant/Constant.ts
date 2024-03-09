const Constant = {
    Global: {
        NULL_NUMBER_PLACEHOLDER:0,
        NULL_STRING_PLACEHOLDER:"undefined",
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
        USER_LOGIN:"/login"
    }
}
export default Constant