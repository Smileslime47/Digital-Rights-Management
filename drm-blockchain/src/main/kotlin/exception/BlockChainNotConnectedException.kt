package moe._47saikyo.exception

/**
 * 以太坊未连接异常
 *
 * @author 刘一邦
 */
class BlockChainNotConnectedException(override val message:String?): DrmException(message)