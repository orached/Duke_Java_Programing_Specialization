
/**
 * Write a description of MatchAllFilter here.
 * 
 * @Orached
 * @24/12/2015
 */
import java.util.*;

public class MatchAllFilter implements Filter{
    private ArrayList<Filter> filters;
    
    public MatchAllFilter(){
        filters = new ArrayList<Filter>();
    }
    
    public void addFilter(Filter f){
        filters.add(f);
    }
    
    public boolean satisfies(QuakeEntry quake){
        for(Filter f : filters){
            if(! f.satisfies(quake)){
                return false;
            }
        }
        return true;
    }
    
    public String getName(){
        ArrayList<String> filtersNames = new ArrayList<String>();
        for(Filter f : filters){
            filtersNames.add(f.getName());
        }
        return "Filters used are: "+filtersNames.toString();
    }
}
