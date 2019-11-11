
/**
 * Write a description of Tester here.
 * 
 * @Orached
 * @26/12/2015
 */
import edu.duke.*;

public class Tester {
    public void testGetFollows(){
        String st = "this is a test yes this is a test.";
        MarkovOne markov = new MarkovOne();
        markov.setTraining(st);
        System.out.println(markov.getFollows("t"));
        System.out.println(markov.getFollows("e"));
        System.out.println(markov.getFollows("es"));
        System.out.println(markov.getFollows("."));
        System.out.println(markov.getFollows("t."));
    }
    
    public void testGetFollowsWithFile(){
        FileResource fr = new FileResource();
		String st = fr.asString();
		st = st.replace('\n', ' ');
        MarkovOne markov = new MarkovOne();
        markov.setTraining(st);
        //System.out.println(markov.getFollows("o").size());
        System.out.println(markov.getFollows("he").size());
    }
}
