import java.util.*;
import edu.duke.*;

public class EarthQuakeClient
{

    public EarthQuakeClient() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData,
    double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry quake : quakeData){
            if(quake.getMagnitude()>magMin){
                answer.add(quake);
            }
        }

        return answer;
    }

    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData,
    double distMax,
    Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry quake : quakeData){
            //System.out.println(quake.getLocation().distanceTo(from));
            if(quake.getLocation().distanceTo(from) < distMax){
                answer.add(quake);
            }
        }
        return answer;
    }

    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }

    }

    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        for(QuakeEntry quake : filterByMagnitude(list, 5.0)){
            System.out.println(quake);
        }
    }

    public void closeToMe(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        // This location is Durham, NC
        //Location city = new Location(35.988, -78.907);
        
        // This location is Bridgeport, CA
        Location city =  new Location(38.17, -118.82);
        int i=1;
        for(QuakeEntry quake : filterByDistanceFrom(list, 1000000.0, city)){
            System.out.println("Quake "+i);
            System.out.println("The distance from the earthquake to the speciafied city is: "+quake.getLocation().distanceTo(city));
            System.out.println(quake.getInfo());
            i++;
        }        
    }

    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: "+list.size());
    }
    
    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData, double minDepth, double maxDepth){
        ArrayList<QuakeEntry> ret = new ArrayList<QuakeEntry>();
        for(QuakeEntry quake : quakeData){
            if(quake.getDepth()>minDepth &&  quake.getDepth()<maxDepth){
                ret.add(quake);
            }
        }
        return ret;
    }
    
    public void quakesOfDepth(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> quakeData = parser.read(source);
        System.out.println("read data for "+quakeData.size()+" quakes");
        int i=1;
        for(QuakeEntry quake : filterByDepth(quakeData, -4000.0, -2000.0)){
            System.out.println(quake);
            i++;
        }
        System.out.println("Found "+(i-1)+" quakes that match that criteria");
    }
    
    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData, String where, String phrase){
        ArrayList<QuakeEntry> ret = new ArrayList<QuakeEntry>();
        for(QuakeEntry quake : quakeData){
            if(where.equals("start")){
                if(quake.getInfo().substring(0, phrase.length()).equals(phrase)){
                    ret.add(quake);
                }
            } else if(where.equals("end")){
                if(quake.getInfo().substring(quake.getInfo().length()-phrase.length(), quake.getInfo().length()).equals(phrase)){
                    ret.add(quake);
                }
            } else if(where.equals("any")){
                for(int i=0; i<(quake.getInfo().length()-phrase.length()); i++){
                    if(quake.getInfo().substring(i, i+phrase.length()).equals(phrase)){
                        ret.add(quake);
                    }
                }
            }
        }
        return ret;
    }
    
    public void quakesByPhrase(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> quakeData = parser.read(source);
        System.out.println("read data for "+quakeData.size()+" quakes");
        int i=1;
        /*
        for(QuakeEntry quake : filterByPhrase(quakeData, "end", "Alaska")){
            System.out.println(quake);
            i++;
        }
        */
        for(QuakeEntry quake : filterByPhrase(quakeData, "any", "Can")){
            System.out.println(quake);
            i++;
        }
        
       /*
        for(QuakeEntry quake : filterByPhrase(quakeData, "start", "Quarry Blast")){
            System.out.println(quake);
            i++;
        }
        */
        System.out.println("Found "+(i-1)+" quakes that match that criteria");
    }
}
