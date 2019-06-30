package demo

import org.jgrapht.graph.DefaultEdge
import org.jgrapht.graph.DefaultUndirectedGraph
import org.protelis.lang.ProtelisLoader

class HelloProtelis {

    val protelisModuleName = "hello"
    val N = 3
    val devices = ArrayList<Device>()

    fun hello() {
        val program = ProtelisLoader.parse(protelisModuleName)
        val d = Device(program, 0)
        d.deviceCapabilities.executionEnvironment.put("leader", true)
        d.runCycle()
        /*
        val network = initializeNetwork()
        // Set the leader
        setLeader(0)
        devices.forEach {
            it.network = network
        }
        syncRunNTimes(3)

         */
    }

    private fun initializeNetwork() : DefaultUndirectedGraph<Device, DefaultEdge> {
        // Initialize a graph
        val g = DefaultUndirectedGraph<Device, DefaultEdge>(DefaultEdge::class.java)
        // Initialize N nodes
        repeat(N) {
            val program = ProtelisLoader.parse(protelisModuleName)
            val d = Device(program, it)
            devices.add(d)
            g.addVertex(d)
        }
        // Link the nodes as a ring network

        repeat(N) {
            g.addEdge(
                    devices.get(it),
                    devices.get((it + 1) % N))
        }


        return g
    }

    private fun setLeader(id: Int) =
        devices.get(id).deviceCapabilities.executionEnvironment.put("leader", true)


    private fun syncRunNTimes(n: Int) = repeat(n) {
        devices.forEach({ it.runCycle() })
        //devices.forEach({ it.sendMessages() })
    }
}

fun main() {
    HelloProtelis().hello()
}

