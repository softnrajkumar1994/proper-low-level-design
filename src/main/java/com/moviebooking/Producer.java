package com.moviebooking;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Producer extends People {
    public Producer(String name) {
        super(name);
    }
}
