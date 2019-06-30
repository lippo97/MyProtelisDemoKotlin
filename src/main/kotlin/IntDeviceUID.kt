package demo

import org.protelis.lang.datatype.DeviceUID

class IntDeviceUID(private val uid: Int) : DeviceUID, Comparable<IntDeviceUID> {
    override fun compareTo(other: IntDeviceUID) = uid - other.uid
    fun getUID() = uid
}