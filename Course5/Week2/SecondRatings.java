
/**
 * Write a description of SecondRatings here.
 * 
 * @Orached 
 * @02/07/2017
 */

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }
    
    public SecondRatings(String moviefile, String ratingsfile) {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }
}