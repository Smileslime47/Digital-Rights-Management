package moe._47saikyo.drm.ipfs.service

import moe._47saikyo.drm.ipfs.service.IpfsService
import moe._47saikyo.drm.ipfs.service.impl.IpfsServiceImpl
import kotlin.test.BeforeTest
import kotlin.test.Test

class IpfsServiceTest {
    @BeforeTest
    fun init() {
        IpfsService.init("/ip4/127.0.0.1/tcp/5001")
    }

    @Test
    fun testUpload() {
        val ipfsService = IpfsServiceImpl()
        println(ipfsService.upload("test nginx".toByteArray()))
    }

    @Test
    fun testDownload() {
        val ipfsService = IpfsServiceImpl()
        println(String(ipfsService.download("QmQtuFaZhnvyCB7fWcrLZamWFQMhxXVmrWwwH23iy8nNky")))

    }
}
