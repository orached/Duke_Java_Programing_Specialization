
/**
 * Write a description of MagnitudeFilter here.
 * 
 * @Orached
 * @24/12/2015
 */
public class MagnitudeFilter implements Filter{
    private double minMag;
    private double maxMag;
    
    public MagnitudeFilter(double min, double max){
        minMag = min;
        maxMag = max;
    }
    
    public boolean satisfies(QuakeEntry quake){
        if(quake.getMagnitude()>=minMag && quake.getMagnitude()<=maxMag){
            return true;
        }
        return false;
    }
    
    public String getName(){
        return "Magnitude";
    }
}
