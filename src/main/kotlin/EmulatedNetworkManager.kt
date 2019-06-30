package demo

import org.protelis.lang.datatype.DeviceUID
import org.protelis.vm.CodePath
import org.protelis.vm.NetworkManager

class EmulatedNetworkManager(val uid : DeviceUID) : NetworkManager {
    private var toBeSent: Map<CodePath, Any>? = null
    private var messages = HashMap<DeviceUID, Map<CodePath, Any>>()

    fun receiveMessage(src: DeviceUID, msg: Map<CodePath, Any>) = messages.put(src, msg)

    fun sendMessages(neighbors: Set<Device>) {
        toBeSent?.let { msg ->
            neighbors.forEach {
                it.netmgr.receiveMessage(uid, msg)
            }
        }
    }

    override fun getNeighborState() : Map<DeviceUID, Map<CodePath, Any>> =
        if (messages.isEmpty()) {
            emptyMap<DeviceUID, Map<CodePath, Any>>()
        } else {
            val t = messages
            messages = HashMap<DeviceUID, Map<CodePath, Any>>()
            t
        }

    override fun shareState(toSend: MutableMap<CodePath, Any>?) { toBeSent = toSend }

}