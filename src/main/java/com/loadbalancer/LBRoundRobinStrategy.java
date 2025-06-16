package com.loadbalancer;

import java.util.*;

public class LBRoundRobinStrategy implements LoadBalancingStrategy {
    private final Map<Server, Integer> numOfRequestsHandled;
    private final List<Server> servers;
    private final PriorityQueue<Server> serverPriorityQueue;

    public LBRoundRobinStrategy(List<Server> serverList) {
        this.numOfRequestsHandled = new HashMap<>();
        this.servers = serverList;

        this.serverPriorityQueue = new PriorityQueue<>((o1, o2) -> {
            if (o1.getStatus() != o2.getStatus()) {
                return o1.getStatus() == Server.Status.UP ? -1 : 1;
            }
            return Integer.compare(
                    numOfRequestsHandled.getOrDefault(o1, 0),
                    numOfRequestsHandled.getOrDefault(o2, 0)
            );
        });
    }

    @Override
    public Server getNextServer(List<Server> healthyServers) {
        serverPriorityQueue.clear();
        serverPriorityQueue.addAll(healthyServers);
        Server server = serverPriorityQueue.poll();
        if (server != null) {
            numOfRequestsHandled.put(server, numOfRequestsHandled.getOrDefault(server, 0) + 1);
        }
        return server;
    }


}
