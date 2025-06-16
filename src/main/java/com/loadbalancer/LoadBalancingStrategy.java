package com.loadbalancer;

import java.util.List;

public interface LoadBalancingStrategy {

    public Server getNextServer(List<Server> healthyServers);
}
