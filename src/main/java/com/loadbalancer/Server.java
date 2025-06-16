package com.loadbalancer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;

@Getter
@Setter
@NoArgsConstructor
public class Server {
    private String id;
    private Status status;
    private HashMap<String, String> responseMap;

    public Server(String id) {
        this.id = id;
        this.status = Status.UP;
        this.responseMap = new HashMap<>();

        responseMap.put("What is your name", id + " responds as: Rajkumar");
        responseMap.put("What is your age", id + " responds as: 30");
        responseMap.put("attack", id + " down");
    }

    public void markServerDown() {
        this.status = Status.DOWN;
    }

    public void markServerUp() {
        this.status = Status.UP;
    }

    public HttpResponse handleRequest(HttpRequest httpRequest) {
        if ("attack".equalsIgnoreCase(httpRequest.getQuestion())) {
            return new HttpResponse(503, responseMap.getOrDefault(httpRequest.getQuestion(), "No answer"));
        }
        return new HttpResponse(200, responseMap.getOrDefault(httpRequest.getQuestion(), "No answer"));
    }

    enum Status {
        UP, DOWN
    }
}