
/**
 * Write a description of TitleAndDepthComparator here.
 * 
 * @Orached
 * @25/12/2015
 */
import java.util.*;

public class TitleAndDepthComparator implements Comparator<QuakeEntry>{
    public int compare(QuakeEntry q1, QuakeEntry q2){
        if(q1.getInfo().compareTo(q2.getInfo())<0){
            return -1;
        }
        if(q1.getInfo().compareTo(q2.getInfo())>0){
            return 1;
        }
        return Double.compare(q1.getDepth(), q2.getDepth());
    }
}
