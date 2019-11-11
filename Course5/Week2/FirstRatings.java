
/**
 * This class process the movie AND the ratings data to answer questions about them
 * 
 * @Orached
 * @01/07/2017
 */
import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class FirstRatings {
    public ArrayList<Movie> loadMovies(String filename){
        FileResource fr = new FileResource(filename); 
        CSVParser parser = fr.getCSVParser();
        ArrayList<Movie> listMovie = new ArrayList<Movie>();
        for(CSVRecord record : parser){
            String id = record.get("id");
            String title = record.get("title");
            String year = record.get("year");
            String genres = record.get("genre");
            String director = record.get("director");
            String country = record.get("country");
            String poster= record.get("poster");
            int minutes = Integer.parseInt(record.get("minutes").trim());
            Movie movie = new Movie(id, title, year, genres, director, country, poster, minutes);
            listMovie.add(movie);
        }
        return listMovie;
    }
    
    public ArrayList<Rater> loadRaters(String filename){
        FileResource fr = new FileResource(filename); 
        CSVParser parser = fr.getCSVParser();
        ArrayList<Rater> listRater = new ArrayList<Rater>();
        for(CSVRecord record : parser){
            String id = record.get("rater_id");
            String item = record.get("movie_id");
            double value = Double.parseDouble(record.get("rating").trim());
            //String time = record.get("time");
            Rater rater = listRaterContainsId(listRater, id);
            if(rater!=null){
                rater.addRating(item, value);
            } else {
                Rater raterN = new Rater(id);
                listRater.add(raterN);
                raterN.addRating(item, value);
            }
            
        }
        return listRater;
    }
    
    public Rater listRaterContainsId (ArrayList<Rater> listRater, String id){
        for (Rater rater : listRater){
                String idRater = rater.getID();
                if(idRater.equals(id)){
                    return rater;
                }
            }
        return null;
    }
    
    public void testLoadMovies() {
        ArrayList<Movie> listMovie = new ArrayList<Movie>();
        listMovie = loadMovies("data/ratedmovies_short.csv");
        //listMovie = loadMovies("data/ratedmoviesfull.csv");
        System.out.println(listMovie.size());
        //System.out.println(listMovie);
        int i = 0;
        for (Movie movie : listMovie){
            if (movie.getGenres().contains("Comedy")) {
                i++;
            }
        }
        System.out.println("Number of movies that are Comedy is "+i);

        int  k = 0;
        for (Movie movie : listMovie){
            if (movie.getMinutes() > 150) {
                k++;
            }
        }
        System.out.println("Number of movies that last more than 150 minutes are "+k);
        
        //Seems not work well
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        for (Movie movie : listMovie){
            map.put(movie.getDirector(),numberOfMoviesForDir(movie.getDirector()));
        }
        int j=0;
        for (String director : map.keySet())
        {
            if (map.get(director)>j){
                j=map.get(director);
            }
        }
        System.out.println("Directors with their number of movies "+map);
        System.out.println("Maximum number of movies for any director is "+j);
        
    }
    
    //Seems not work well
    public int numberOfMoviesForDir(String director){
        ArrayList<Movie> listMovie = new ArrayList<Movie>();
        listMovie = loadMovies("data/ratedmovies_short.csv");
        //HashMap<String, int> map = new HashMap<String, int>();
        int k = 0;
        for (Movie movie : listMovie){
            if(movie.getDirector().contains(director)){
                k++;
            }
        }
        //map.put(director, k);
        return k;
    }
    
    public void testLoadRaters(){
        ArrayList<Rater> listRater = new ArrayList<Rater>();
        //listRater = loadRaters("data/ratings_short.csv");
        listRater = loadRaters("data/ratings.csv");
        System.out.println(listRater.size());
        /*
        for(Rater rater : listRater){
            System.out.println("The rater of ID " +rater.getID()+ " has rated the following items : " +rater.getItemsRated());
        }
        */
        System.out.println("Number of ratings for rater 193 is: " +numOfRatingsForRater(listRater, "193"));
        
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        for (Rater rater : listRater){
            map.put(rater.getID(),numOfRatingsForRater(listRater, rater.getID()));
        }
        int j=0;
        for (String raterId : map.keySet())
        {
            if (map.get(raterId)>j){
                j=map.get(raterId);
            }
        }
        System.out.println("Raters with their number of ratings "+map);
        System.out.println("Maximum number of ratings for any rater is "+j);
        String Idddd = new String();
        for (String raterId : map.keySet())
        {
            if (map.get(raterId)==j){
                Idddd = raterId;
            }
        }
        System.out.println("The rater with the maxim number of movies rated is "+Idddd);

        System.out.println("Number of ratings for the movie 1798709 is: " +numOfRatingsForMovie(listRater, "1798709"));
        
        System.out.println("Number of different movies rated by all the raters is: " +differentMoviesRated(listRater));
    }
    
    public int numOfRatingsForRater(ArrayList<Rater> listRater, String raterID){
        int i = 0;
        for(Rater rater : listRater){
            if(rater.getID().equals(raterID)){
                i = rater.getItemsRated().size();
            }
        }
        return i;
    }
    
    public int numOfRatingsForMovie(ArrayList<Rater> listRater, String item){
        int i = 0;
        ArrayList<String> listOfItemsRated = new ArrayList<String>();
        for(Rater rater : listRater){
            listOfItemsRated = rater.getItemsRated();
            if(listOfItemsRated.contains(item)){
                i++;
            }
        }
        return i;
    }
    
    public int differentMoviesRated(ArrayList<Rater> listRater){
        int i = 0;
        ArrayList<String> listOfItemsRatedToReturn = new ArrayList<String>();
        listOfItemsRatedToReturn = listRater.get(0).getItemsRated();
        
        for(int k=1; k<listRater.size(); k++){
            ArrayList<String> listOfItemsRatedK = listRater.get(k).getItemsRated();
            for(int j=0; j<listOfItemsRatedK.size(); j++){
                if(! listOfItemsRatedToReturn.contains(listOfItemsRatedK.get(j))){
                    listOfItemsRatedToReturn.add(listOfItemsRatedK.get(j));
                }
            }
        }
        
        return listOfItemsRatedToReturn.size();
    }
}
