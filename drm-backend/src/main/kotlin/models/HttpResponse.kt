package moe._47saikyo.models

import moe._47saikyo.constant.Constant
import moe._47saikyo.constant.HttpStatus

data class HttpResponse(val status: HttpStatus = HttpStatus.SUCCESS, val data: Any = Constant.Global.NULL_PLACEHOLDER)