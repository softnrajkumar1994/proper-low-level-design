package com.loadbalancer;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class LoadBalancer {
    private String id;
    private List<Server> serverList;
    private LoadBalancingStrategy strategy;


    public LoadBalancer(String id, LoadBalancingStrategy strategy, List<Server> servers) {
        this.id = id;
        this.strategy = strategy;
        this.serverList = servers;
    }

    private List<Server> getAllHealthyServers() {
        return serverList.stream()
                .filter(x -> x.getStatus() == Server.Status.UP)
                .collect(Collectors.toList());
    }

    public HttpResponse handleRequest(HttpRequest httpRequest) {
        Server server = strategy.getNextServer(getAllHealthyServers());
        if (server == null || server.getStatus() == Server.Status.DOWN) {
            return new HttpResponse(503, "Service not available");
        }
        HttpResponse httpResponse = server.handleRequest(httpRequest);
        if (httpResponse.getStatusCode() == 503) {
            server.markServerDown();
        }
        return httpResponse;
    }
}
