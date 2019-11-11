
/**
 * Write a description of DistanceFilter here.
 * 
 * @Orached
 * @24/12/2015
 */
public class DistanceFilter implements Filter{
    private Location myLoc;
    private double maxDistance;
    
    public DistanceFilter(Location location, double max){
        myLoc = location;
        maxDistance = max;
    }
    
    public boolean satisfies(QuakeEntry quake){
        if(quake.getLocation().distanceTo(myLoc)<maxDistance){
            return true;
        }
        return false;
    }
    
    public String getName(){
        return "Distance";
    }
}
