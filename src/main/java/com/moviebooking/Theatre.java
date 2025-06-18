package com.moviebooking;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Theatre {
    private String theatreName;
    private List<Screen> screenList;

    public Theatre(String theatreName) {
        this.theatreName = theatreName;
    }
}
