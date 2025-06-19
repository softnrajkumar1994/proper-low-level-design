package com.moviebooking;

public class MovieBookingDemo {
    public static void main(String args[]) {
        Theatre theatre = new Theatre("PVR Cinemas");
        int numOfScreen = 5;
        for (int i = 0; i < numOfScreen; i++) {
            theatre.getScreenList().add(new Screen("Screen_" + i, theatre, 50));
        }

        Theatre theatre1 = new Theatre("AGS Cinemas");
        numOfScreen = 5;
        for (int i = 0; i < numOfScreen; i++) {
            theatre1.getScreenList().add(new Screen("Screen_" + i, theatre1, 50));
        }

        Movie movie = new Movie("Pirates of the carribean", Language.ENGLISH, 150);
        Movie movie1 = new Movie("Robot", Language.TAMIL, 180);


    }
}
/*
## Requirements

1. **Movie Management:**
   - Store movie information (title, duration, language)
   - Manage movie schedules and shows
   - Track movie availability

2. **Theater Management:**
   - Manage theater information
   - Handle multiple shows per theater
   - Track theater capacity

3. **Show Management:**
   - Schedule shows for movies
   - Manage show timings
   - Handle show availability

4. **Seat Management:**
   - Track seat availability
   - Handle seat selection
   - Manage different seat types

5. **Booking Management:**
   - Process ticket bookings
   - Handle booking cancellations
   - Manage booking status

 */