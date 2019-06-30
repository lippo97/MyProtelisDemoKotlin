package demo;

import org.protelis.lang.datatype.DeviceUID;

public class MyDeviceUIDImpl implements MyDeviceUID {

    private final int uid;

    public MyDeviceUIDImpl(final int uid) {
        this.uid = uid;
    }

    @Override
    public int getUID() {
        return uid;
    }

    @Override
    public int compareTo(MyDeviceUID o) {
        if (this.getClass() == o.getClass()) {
            return this.getUID() - ((MyDeviceUIDImpl)o).getUID();
        } else {
            return 1;
        }
    }
}
