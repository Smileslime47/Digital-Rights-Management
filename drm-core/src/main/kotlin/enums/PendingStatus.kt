package moe._47saikyo.enums

import moe._47saikyo.constant.GlobalConstant

enum class PendingStatus {
    PENDING,
    CONFIRMED,
    DEPLOYED,
    REJECTED,

    ;

    companion object {
        fun fromString(value: String): PendingStatus {
            return when (value) {
                GlobalConstant.PENDING_STATUS_PENDING -> PENDING
                GlobalConstant.PENDING_STATUS_CONFIRMED -> CONFIRMED
                GlobalConstant.PENDING_STATUS_DEPLOYED -> DEPLOYED
                GlobalConstant.PENDING_STATUS_REJECTED -> REJECTED
                else -> throw IllegalArgumentException("Invalid value $value")
            }
        }

        fun toString(value: PendingStatus): String {
            return when (value) {
                PENDING -> GlobalConstant.PENDING_STATUS_PENDING
                CONFIRMED -> GlobalConstant.PENDING_STATUS_CONFIRMED
                DEPLOYED -> GlobalConstant.PENDING_STATUS_DEPLOYED
                REJECTED -> GlobalConstant.PENDING_STATUS_REJECTED
            }
        }
    }
}