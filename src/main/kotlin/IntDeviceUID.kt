package demo

import org.protelis.lang.datatype.DeviceUID

data class IntDeviceUID(val uid: Int) : DeviceUID, Comparable<IntDeviceUID> {
    override fun compareTo(other: IntDeviceUID) = Integer.compare(uid, other.uid)
    fun getUID() = uid
}