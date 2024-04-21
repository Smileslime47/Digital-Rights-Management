package moe._47saikyo.drm.blockchain.annotation

/**
 * TransactionManager占位符
 *
 * 标记这里传入的TransactionManager对象是一个占位符
 * 对于该TransactionManager对象的调用，不会真正执行交易，只是为了满足web3j的合约对象加载要求
 * 因此不会产生实际的gas消耗
 *
 * @author 刘一邦
 */
@Target(AnnotationTarget.EXPRESSION)
@Retention(AnnotationRetention.SOURCE)
annotation class TxManagerPlaceholder