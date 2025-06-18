package com.moviebooking;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Movie {
    private String movieName;
    private Language language;
    private Director director;
    private Producer producer;
    private Integer duration;
    private List<Actor> actorList;
}