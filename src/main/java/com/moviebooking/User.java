package com.moviebooking;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class User extends People {
    private String email;
    private String phone;

    public User(String name) {
        super(name);
    }
}
