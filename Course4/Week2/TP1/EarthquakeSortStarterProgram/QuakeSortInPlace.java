
/**
 * Write a description of class QuakeSortInPlace here.
 * 
 * @Orached
 * @25/12/2015
 */

import java.util.*;
import edu.duke.*;

public class QuakeSortInPlace
{
    public QuakeSortInPlace() {
        // TODO Auto-generated constructor stub
    }
   
    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }
    
    public int getLargestDepth(ArrayList<QuakeEntry> quakeData, int from){
        int maxIdx = from;
        for (int i=from+1; i< quakeData.size(); i++) {
            if (quakeData.get(i).getDepth() > quakeData.get(maxIdx).getDepth()) {
                maxIdx = i;
            }
        }
        return maxIdx;
    }
    
    public void sortByMagnitude(ArrayList<QuakeEntry> in) {
       
       for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
        }
        
    }
    
    public void sortByLargestDepth(ArrayList<QuakeEntry> in) {
       //for (int i=0; i< in.size(); i++) {
       for (int i=0; i< 50; i++) {
            int maxIdx = getLargestDepth(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmax = in.get(maxIdx);
            in.set(i,qmax);
            in.set(maxIdx,qi);
        }
    }

    public void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "data/nov20quakedatasmall.atom";
        //String source = "data/nov20quakedata.atom";
        //String source = "data/earthquakeDataSampleSix1.atom";
        //String source = "data/earthquakeDataSampleSix2.atom";
        //String source = "data/earthQuakeDataDec6sample1.atom";
        //String source = "data/earthQuakeDataDec6sample2.atom";
        String source = "data/earthQuakeDataWeekDec6sample1.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);  
       
        System.out.println("read data for "+list.size()+" quakes");    
        //sortByMagnitude(list);
        //sortByLargestDepth(list);
        //sortByMagnitudeWithBubbleSort(list);
        sortByMagnitudeWithBubbleSortWithCheck(list);
        //sortByMagnitudeWithCheck(list);

        for (QuakeEntry qe: list) { 
            System.out.println(qe);
        }
        
    }
    
    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
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
    
    //Implementing the bubble sort
    public void onePassBubbleSort(ArrayList<QuakeEntry> quakeData, int numSorted){
        //Here we sort based on magnitude
        for (int i=0; i< numSorted; i++) {
            QuakeEntry qi = quakeData.get(i);
            QuakeEntry qi1 = quakeData.get(i+1);
            if(qi.getMagnitude()>qi1.getMagnitude()){
                quakeData.set(i,qi1);
                quakeData.set(i+1,qi);
            }
        }
    }
    
    public void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> in){
        for (QuakeEntry qe: in) { 
            System.out.println(qe);
        }
        int k = 1;
        //for (int i=in.size()-1; i> 0; i--) {
        for(int i=0; i<in.size(); i++){
            //onePassBubbleSort(in, i);
            onePassBubbleSort(in, in.size()-i-1);
            System.out.println("Printing Quakes after pass "+k);
            for (QuakeEntry qe: in) { 
            System.out.println(qe);
            }
            k++;
        }
    }
    
    public boolean checkInSortedOrder(ArrayList<QuakeEntry> quakes){
        for(int i=0; i<quakes.size()-1; i++){
            if(quakes.get(i).getMagnitude()>quakes.get(i+1).getMagnitude()){
                return false;
            }
        }
        return true;
    }
    
    public void sortByMagnitudeWithBubbleSortWithCheck(ArrayList<QuakeEntry> in){
        int k = 1;
        for (int i=in.size()-1; i> 0; i--) {
            onePassBubbleSort(in, i);
            if(checkInSortedOrder(in)){
                break;
            }
            k++;
        }
        System.out.println("# passes used with bubbleSortwithCheck:: "+k);
    }
    
    public void sortByMagnitudeWithCheck(ArrayList<QuakeEntry> in){
        int k = 1;
        for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
            if(checkInSortedOrder(in)){
                break;
            }
            k++;
        }
        System.out.println("# passes used with selectionSortwithCheck: "+k);
    }
}
