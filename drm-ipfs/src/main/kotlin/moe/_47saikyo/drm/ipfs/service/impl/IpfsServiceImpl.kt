package moe._47saikyo.drm.ipfs.service.impl

import moe._47saikyo.drm.ipfs.service.IpfsService
import io.ipfs.api.NamedStreamable.ByteArrayWrapper
import io.ipfs.multihash.Multihash

class IpfsServiceImpl : IpfsService {
    override fun upload(content: ByteArray): String {
        val ipfs = IpfsService.getInstance()
        val file = ByteArrayWrapper(content)

        val node = ipfs.add(file)[0]

        return node.hash.toString()
    }

    override fun download(hash: String): ByteArray {
        val ipfs = IpfsService.getInstance()
        val content = ipfs.cat(Multihash.decode(hash))

        return content
    }
}