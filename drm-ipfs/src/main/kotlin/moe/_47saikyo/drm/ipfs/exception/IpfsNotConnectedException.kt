package moe._47saikyo.drm.ipfs.exception

import moe._47saikyo.drm.core.exception.DrmCheckedException

class IpfsNotConnectedException(override val message:String?): DrmCheckedException(message)