package javademo;

import org.protelis.lang.datatype.DeviceUID;
import org.protelis.vm.NetworkManager;
import org.protelis.vm.impl.AbstractExecutionContext;
import org.protelis.vm.impl.SimpleExecutionEnvironment;

public class DeviceCapabilities extends AbstractExecutionContext {

    private MyDeviceUID uid;

    public DeviceCapabilities(final int uid, final NetworkManager ntmgr) {
        super(new SimpleExecutionEnvironment(), ntmgr);
        this.uid = new MyDeviceUIDImpl(uid);
    }

    public void announce(String msg) {
        System.out.println(msg);
    }

    public void announce(int a) {
        System.out.println(a);
    }

    @Override
    protected AbstractExecutionContext instance() {
        return new DeviceCapabilities(this.uid.getUID(), getNetworkManager());
    }

    @Override
    public DeviceUID getDeviceUID() {
        return uid;
    }

    @Override
    public Number getCurrentTime() {
        return System.currentTimeMillis();
    }

    @Override
    public double nextRandomDouble() {
        return Math.random();
    }
}
