package demo;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.protelis.vm.NetworkManager;
import org.protelis.vm.ProtelisProgram;
import org.protelis.vm.ProtelisVM;

public class Device {

    private final ProtelisVM vm;
    private final DeviceCapabilities deviceCapabilities;
    private final EmulatedNetworkManager ntmgr;
    private Graph<Device, DefaultEdge> network;

    public Device(ProtelisProgram program, int uid) {
        ntmgr = new EmulatedNetworkManager(uid);
        deviceCapabilities = new DeviceCapabilities(uid, ntmgr);
        this.vm = new ProtelisVM(program, deviceCapabilities);
    }

    public EmulatedNetworkManager getNetworkManager() {
        return ntmgr;
    }

    public DeviceCapabilities getDeviceCapabilities() {
        return deviceCapabilities;
    }

    public void setNetwork(Graph<Device, DefaultEdge> network) {
        this.network = network;
    }

    public void runCycle() {
        this.vm.runCycle();
    }

    public void sendMessages() throws IllegalStateException {
        if (network == null) {
            throw new IllegalStateException("Uninitialized network.");
        }
        this.ntmgr.sendMessages(Graphs.neighborSetOf(network, this));
    }
}
