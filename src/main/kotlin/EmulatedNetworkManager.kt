package demo

import org.protelis.lang.datatype.DeviceUID
import org.protelis.vm.CodePath
import org.protelis.vm.NetworkManager

class EmulatedNetworkManager(val uid : DeviceUID) : NetworkManager {
    private var toBeSent: Map<CodePath, Any> = emptyMap()
    private var messages: Map<DeviceUID, Map<CodePath, Any>> = emptyMap()

    fun receiveMessage(src: DeviceUID, msg: Map<CodePath, Any>) {
        messages += Pair(src, msg)
    }

    fun sendMessages(neighbors: Set<Device>) {
        if (toBeSent.isNotEmpty()) {
            neighbors.forEach { it.netmgr.receiveMessage(uid, toBeSent) }
        }
    }

    override fun getNeighborState() : Map<DeviceUID, Map<CodePath, Any>> =
        messages.apply { messages = emptyMap() }


    override fun shareState(toSend: Map<CodePath, Any>) { toBeSent = toSend }

}