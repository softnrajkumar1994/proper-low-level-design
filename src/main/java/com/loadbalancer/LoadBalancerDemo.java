package com.loadbalancer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LoadBalancerDemo {
    public static void main(String args[]) throws InterruptedException {
        Random random = new Random();
        List<Server> serverList = new ArrayList<>();
        int numOfServers = 3;

        for (int i = 0; i < numOfServers; i++) {
            serverList.add(new Server(String.valueOf(i)));
        }

        LoadBalancingStrategy strategy = new LBLeastRequestStrategy(serverList);
        LoadBalancer loadBalancer = new LoadBalancer("lb1", strategy, serverList);

        List<String> questions = List.of(
                "What is your name",
                "What is your age",
                "attack"
        );

        for (int i = 0; i < 15; i++) {
            int questionIndex = random.nextInt(questions.size());
            String question = questions.get(questionIndex);

            System.out.println("Request: " + question);
            HttpResponse response = loadBalancer.handleRequest(new HttpRequest(question));
            System.out.println("Response: " + response.getStatusCode() + " - " + response.getResponse());
            System.out.println("--------");

            // Simulate server recovery every 5 cycles
            if (i % 5 == 4) {
                for (Server s : serverList) {
                    if (s.getStatus() == Server.Status.DOWN) {
                        s.markServerUp();
                        System.out.println("Recovered Server: " + s.getId());
                    }
                }
                System.out.println("--------");
            }

            Thread.sleep(500);
        }

    }
}
