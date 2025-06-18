package com.moviebooking;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class People {
    private String name;

    public People(String name) {
        this.name = name;
    }
}
