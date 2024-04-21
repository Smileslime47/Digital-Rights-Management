package moe._47saikyo.drm.backend.constant

/**
 * 常量类
 *
 * @author 刘一邦
 * @since 2024/01/20
 */
object Constant {
    //默认页容量
    const val DEFAULT_PAGE_SIZE = 10

    //鉴权组件常量
    object Authentication {
        //------------------------------JWT Claim------------------------------//
        const val TOKEN_STORAGE = "token"
        const val USER_ID_CLAIM = "user_id"
        const val GROUP_ID_CLAIM = "group_id"
        const val EXPIRE_TIME_CLAIM = "expire_time"

        //------------------------------JWT 默认值------------------------------//
        const val DEFAULT_GROUP_ID = 0L
        const val DEFAULT_EXPIRE_TIME = 3600000L
        const val REMEMBER_ME_EXPIRE_TIME = DEFAULT_EXPIRE_TIME * 24

        //------------------------------权限名------------------------------//
        const val NEED_LOGIN = "need_login"
        const val NEED_BLOCK_ACCOUNT = "need_block_account"
        const val PERMISSION_CREATE_CHAIN_ACCOUNT = "permission_create_chain_account"
        const val PERMISSION_CREATE_RIGHT = "permission_create_right"
        const val PERMISSION_VERIFY_RIGHT = "permission_verify_right"
        const val PERMISSION_CREATE_LICENSE = "permission_create_license"
        const val PERMISSION_VERIFY_LICENSE = "permission_verify_license"
    }

    //Http响应表单字段
    object RespondField {
        //------------------------------数据实体------------------------------//
        //用户
        const val USER = "user"
        //用户组
        const val GROUP = "group"
        //通知
        const val NOTICE = "notice"
        //版权
        const val RIGHT = "right"
        //授权
        const val LICENSE = "license"

        //------------------------------Flag字段------------------------------//
        //是否为自我数据查询
        const val SELF_PROFILE = "self"
        //操作是否成功
        const val SUCCESS = "success"

        //------------------------------零散数据------------------------------//
        //地址
        const val ADDRESS = "address"
        //余额
        const val BALANCE = "balance"
        //费用
        const val COST = "cost"
        //数量
        const val COUNT = "count"
        //哈希地址
        const val HASH = "hash"
        //名称
        const val NAME = "name"
    }

    //属性文件字段
    object PropertyUrl {
        //------------------------------数据库------------------------------//
        const val DATASOURCE_DRIVER = "exposed.datasource.driverClassName"
        const val DATASOURCE_URL = "exposed.datasource.jdbcUrl"
        const val DATASOURCE_USERNAME = "exposed.datasource.username"
        const val DATASOURCE_PASSWORD = "exposed.datasource.password"

        //------------------------------JWT------------------------------//
        const val JWT_SECRET = "JWT.secret"
        const val JWT_ISSUER = "JWT.issuer"
        const val JWT_AUDIENCE = "JWT.audience"
        const val JWT_SUBJECT = "JWT.subject"

        //------------------------------区块链------------------------------//
        const val CHAIN_SOCKET = "chain.socket"
        const val CHAIN_PASSWORD = "chain.password"
        const val CHAIN_WALLET_FILE = "chain.walletFile"
        const val CHAIN_ID = "chain.chainId"
        const val CHAIN_MANAGER = "chain.manager"
        const val CHAIN_GAS_PRICE = "chain.gasPrice"
        const val CHAIN_GAS_LIMIT = "chain.gasLimit"

        //------------------------------IPFS------------------------------//
        const val IPFS_ADDRESS = "IPFS.address"
    }
}