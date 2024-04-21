package moe._47saikyo.drm.blockchain.annotation

/**
 * 该类或方法仅供内部使用，不对外暴露
 *
 * @param reason 该类或方法仅供内部使用的原因，以及替代方案
 * @author 刘一邦
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.SOURCE)
annotation class Internal(val reason: String = "")
