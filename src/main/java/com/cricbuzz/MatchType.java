package com.cricbuzz;

import lombok.Getter;

@Getter
public enum MatchType {
    ODI(50), T20(20);
    int overs;

    MatchType(int overs) {
        this.overs = overs;
    }
}
