package demo

import org.protelis.vm.NetworkManager
import org.protelis.vm.impl.AbstractExecutionContext
import org.protelis.vm.impl.SimpleExecutionEnvironment

class DeviceCapabilities(private val uid: Int, private val netmgr: NetworkManager) :
        AbstractExecutionContext(SimpleExecutionEnvironment(), netmgr),
        Speaker {
    private val myUID = IntDeviceUID(uid)

    override fun nextRandomDouble() = Math.random()

    override fun getDeviceUID() = myUID

    override fun getCurrentTime() =  System.currentTimeMillis()

    override fun instance() = DeviceCapabilities(uid, netmgr)

}