package demo;

import org.protelis.lang.datatype.DeviceUID;

public interface MyDeviceUID extends DeviceUID, Comparable<MyDeviceUID> {

    int getUID();
}
