
/**
 * Write a description of DepthFilter here.
 * 
 * @Orached
 * @24/12/2015
 */
public class DepthFilter implements Filter{
    private double minDepth;
    private double maxDepth;
    
    public DepthFilter(double min, double max){
        minDepth = min;
        maxDepth = max;
    }
    
    public boolean satisfies(QuakeEntry quake){
        if(quake.getDepth()>=minDepth && quake.getDepth()<=maxDepth){
            return true;
        }
        return false;
    }
    
    public String getName(){
        return "Depth";
    }
}
