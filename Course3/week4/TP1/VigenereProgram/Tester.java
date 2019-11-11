
/**
 * Write a description of Tester here.
 * 
 * @Orached 
 * @30/11/2015
 */
import edu.duke.*;

public class Tester {
    public void testCaesarCipher(){
        CaesarCipher cc = new CaesarCipher(5);
        FileResource fr = new FileResource();
        String inputMsg = fr.asString();
        String encrypted = cc.encrypt(inputMsg);
        System.out.println(encrypted);
        System.out.println(cc.decrypt(encrypted));
    }
    
    public void testCaesarCracker(){
        CaesarCracker cc = new CaesarCracker();
        //'a' is the most common letter in portuguese
        //CaesarCracker cc = new CaesarCracker('a');
        FileResource fr = new FileResource();
        String inputMsg = fr.asString();
        String decrypted = cc.decrypt(inputMsg);
        System.out.println(decrypted);
    }
    
    public void testVigenereCipher(){
        VigenereCipher vc = new VigenereCipher(new int[] {17, 14, 12, 4});
        FileResource fr = new FileResource();
        String inputMsg = fr.asString();
        String encrypted = vc.encrypt(inputMsg);
        System.out.println(encrypted);
        System.out.println(vc.decrypt(encrypted));
    }
    
    public void testVigenereBreaker(){
        VigenereBreaker vb = new VigenereBreaker();
        FileResource fr = new FileResource();
        String inputMsg = fr.asString();
        int[] keys = vb.tryKeyLength(inputMsg, 4, 'e');
        for(int i=0; i < keys.length; i++){
            System.out.println(keys[i]);
        }
    }
}
