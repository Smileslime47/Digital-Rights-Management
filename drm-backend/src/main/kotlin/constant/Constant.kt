package moe._47saikyo.constant

/**
 * 常量类
 *
 * @author 刘一邦
 * @since 2024/01/20
 */
object Constant {
    //默认页容量
    const val DEFAULT_PAGE_SIZE = 10

    object Authentication {
        const val TOKEN_STORAGE = "token"
        const val USER_ID_CLAIM = "user_id"
        const val GROUP_ID_CLAIM = "group_id"
        const val EXPIRE_TIME_CLAIM = "expire_time"

        const val DEFAULT_GROUP_ID = 0L
        const val DEFAULT_EXPIRE_TIME = 3600000L
        const val REMEMBER_ME_EXPIRE_TIME = DEFAULT_EXPIRE_TIME * 24

        const val NEED_LOGIN = "need_login"
        const val NEED_BLOCK_ACCOUNT = "need_block_account"
        const val PERMISSION_CREATE_CHAIN_ACCOUNT = "permission_create_chain_account"
        const val PERMISSION_CREATE_RIGHT = "permission_create_right"
        const val PERMISSION_VERIFY_RIGHT = "permission_verify_right"
    }

    object RespondField {
        const val USER = "user"
        const val GROUP = "group"
        const val NOTICE = "notice"
        const val SELF_PROFILE = "self"
        const val SUCCESS = "success"
        const val ADDRESS = "address"
        const val RIGHT = "right"
        const val BALANCE = "balance"
        const val COUNT = "count"
        const val PRICE = "price"
    }

    object PropertyUrl {
        const val DATASOURCE_DRIVER = "exposed.datasource.driverClassName"
        const val DATASOURCE_URL = "exposed.datasource.jdbcUrl"
        const val DATASOURCE_USERNAME = "exposed.datasource.username"
        const val DATASOURCE_PASSWORD = "exposed.datasource.password"

        const val JWT_SECRET = "JWT.secret"
        const val JWT_ISSUER = "JWT.issuer"
        const val JWT_AUDIENCE = "JWT.audience"
        const val JWT_SUBJECT = "JWT.subject"

        const val CHAIN_SOCKET = "chain.socket"
        const val CHAIN_PASSWORD = "chain.password"
        const val CHAIN_WALLET_FILE = "chain.walletFile"
        const val CHAIN_ID = "chain.chainId"
        const val CHAIN_MANAGER = "chain.manager"
        const val CHAIN_GAS_PRICE = "chain.gasPrice"
        const val CHAIN_GAS_LIMIT = "chain.gasLimit"
    }
}