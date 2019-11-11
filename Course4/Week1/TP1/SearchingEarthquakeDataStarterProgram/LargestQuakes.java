
/**
 * Write a description of LargestQuakes here.
 * 
 * @Orached
 * @24/12/2015
 */
import java.util.*;

public class LargestQuakes {
    public void findLargestQuakes(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        int i = 1;
        for(QuakeEntry quake : list){
            System.out.println(quake);
            i++;
        }
        System.out.println("There are "+(i-1)+" quakes in this file");
        System.out.println("The quake with the largest magnitude is "+indexOfLargest(list));
        System.out.println("And the 50 top largest are ");
        for(QuakeEntry quake : getLargest(list, 50)){
            System.out.println(quake);
        }
    }
    
    public int indexOfLargest(ArrayList<QuakeEntry> data){
        double max = 0;
        int maxIndex = 0;
        for(int i=0; i<data.size(); i++){
            double magnitude = data.get(i).getMagnitude();
            if(magnitude>max){
                max = magnitude;
                maxIndex = i;
            }
        }
        return maxIndex;
    }
    
    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int howMany){
        ArrayList<QuakeEntry> ret = new ArrayList<QuakeEntry>();
        ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(quakeData);
        for(int i=0; i<howMany; i++){
            QuakeEntry q = copy.get(indexOfLargest(copy));
            ret.add(q);
            copy.remove(q);
        }
        return ret;
    }
}
