import java.util.*;
import edu.duke.*;

public class EarthQuakeClient2
{
    public EarthQuakeClient2() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) { 
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) { 
            if (f.satisfies(qe)) { 
                answer.add(qe); 
            } 
        } 
        return answer;
    } 

    public void quakesWithFilter() { 
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");

        EarthQuakeClient2 eqc = new EarthQuakeClient2(); 
        //Filter f = new MinMagFilter(4.0);
        Filter f1 = new MagnitudeFilter(3.5, 4.5);
        Filter f2 = new DepthFilter(-55000.0, -20000.0);
        /*ArrayList<QuakeEntry> m7  = eqc.filter(list, f); 
        for (QuakeEntry qe: m7) { 
            System.out.println(qe);
        }
        */
       
        //Tokyo location
        //Location location = new Location(35.42, 139.43);
        //Denver location
        Location location = new Location(39.7392, -104.9903);
        Filter f3 = new DistanceFilter(location, 1000000.0);
        Filter f4 = new PhraseFilter("end", "a");
        for(QuakeEntry qe : list){
            
           if(f1.satisfies(qe) && f2.satisfies(qe)){
               System.out.println(qe);
           }
           
          /*
          if(f3.satisfies(qe) && f4.satisfies(qe)){
              System.out.println(qe);
            }
            */
        }
    }

    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../data/nov20quakedata.atom";
        String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: "+list.size());
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
    
    public void testMatchAllFilter(){
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");
        for(QuakeEntry quake : list){
            System.out.println(quake);
        }
        EarthQuakeClient2 eqc = new EarthQuakeClient2();
        
        MatchAllFilter maf = new MatchAllFilter();
        Filter f1 = new MagnitudeFilter(1.0, 4.0);
        maf.addFilter(f1);
        Filter f2 = new DepthFilter(-180000.0, -30000.0);
        maf.addFilter(f2);
        Filter f3 = new PhraseFilter("any", "o");
        maf.addFilter(f3);
        System.out.println("Quakes answering the criteria are:");
        for(QuakeEntry quake : filter(list, maf)){
            System.out.println(quake);
        }
        System.out.println(maf.getName());
    }
    
    public void testMatchAllFilter2(){
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");
        for(QuakeEntry quake : list){
            System.out.println(quake);
        }
        EarthQuakeClient2 eqc = new EarthQuakeClient2();
        
        MatchAllFilter maf = new MatchAllFilter();
        Filter f1 = new MagnitudeFilter(0.0, 5.0);
        maf.addFilter(f1);
        //Tulsa Oklahoma
        //Location location = new Location(36.1314, -95.9372);
        //Billund Denmark
        Location location = new Location(55.7308, 9.1153);
        Filter f2 = new DistanceFilter(location, 3000000.0);
        maf.addFilter(f2);
        Filter f3 = new PhraseFilter("any", "e");
        maf.addFilter(f3);
        System.out.println("Quakes answering the criteria are:");
        for(QuakeEntry quake : filter(list, maf)){
            System.out.println(quake);
        }
        System.out.println(maf.getName());
    }
}
