package demo;

import org.protelis.lang.datatype.DeviceUID;
import org.protelis.vm.CodePath;
import org.protelis.vm.NetworkManager;

import java.util.*;

public class EmulatedNetworkManager implements NetworkManager {

    private Map<CodePath, Object> toBeSent;
    private Map<DeviceUID, Map<CodePath, Object>> messages;
    private final DeviceUID uid;

    public EmulatedNetworkManager(int uid) {
        this.uid = new MyDeviceUIDImpl(uid);
        messages = new HashMap<>();
    }

    private void receiveMessage(DeviceUID src, Map<CodePath, Object> msg) {
        messages.put(src, msg);
    }

    public void sendMessages(Set<Device> neighbors) {
        neighbors.forEach(d -> {
            d.getNetworkManager().receiveMessage(uid, toBeSent);
        });

    }

    @Override
    public Map<DeviceUID, Map<CodePath, Object>> getNeighborState() {
        Map<DeviceUID, Map<CodePath, Object>> t = messages;
        messages = new HashMap<>();
        return t;
    }

    @Override
    public void shareState(Map<CodePath, Object> toSend) {
        toBeSent = toSend;
    }
}
