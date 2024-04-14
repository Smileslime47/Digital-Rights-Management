package moe._47saikyo.annotation

import moe._47saikyo.BlockChain

/**
 * Solidity View函数调用
 *
 * 被标记为View的合约函数在调用时不会消耗任何gas
 *
 * 由于web3j的solidity wrapper生成的合约对象，在加载对象(如Right.load)时强制要求传入TransactionManager对象
 * 但是在调用View函数时，不会消耗任何gas，考虑到TransactionManager需要传入用户密钥认证，且涉及到余额变动时才有意义
 * 因此在调用View函数时，传入任意一个TransactionManager对象即可，如[BlockChain.bankTxManager]
 *
 * 考虑到用户的交互体验，不建议在调用view函数时要求用户输入密码认证来获取用户自身的TransactionManager
 *
 * @author 刘一邦
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.SOURCE)
annotation class ViewFunction