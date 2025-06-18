package com.moviebooking;

public class MovieBookingService {
    private static MovieBookingService instance = null;

    private MovieBookingService() {

    }

    public synchronized static MovieBookingService getInstance() {
        if (instance == null) {
            instance = new MovieBookingService();
        }
        return instance;
    }
}
