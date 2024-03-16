package moe._47saikyo.models

import constant.GlobalConstant
import moe._47saikyo.constant.Constant
import moe._47saikyo.constant.HttpStatus

data class HttpResponse(val status: HttpStatus = HttpStatus.SUCCESS, val data: Any = GlobalConstant.NULL_PLACEHOLDER)