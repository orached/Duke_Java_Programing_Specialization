
/**
 * Write a description of TitleLastAndMagnitudeComparator here.
 * 
 * @Orached 
 * @25/12/2015
 */
import java.util.*;

public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry>{
    public int compare(QuakeEntry q1, QuakeEntry q2){
        if(q1.getInfo().substring(q1.getInfo().lastIndexOf(" ")+1).compareTo(q2.getInfo().substring(q2.getInfo().lastIndexOf(" ")+1))<0){
            return -1;
        }
        if(q1.getInfo().substring(q1.getInfo().lastIndexOf(" ")+1).compareTo(q2.getInfo().substring(q2.getInfo().lastIndexOf(" ")+1))>0){
            return 1;
        }
        return Double.compare(q1.getMagnitude(), q2.getMagnitude());
    }
}
