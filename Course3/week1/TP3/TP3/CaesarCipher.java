
/**
 * Write a description of CaesarCipher here.
 * 
 * @Orached 
 * @21/11/2015
 */
import edu.duke.*;

public class CaesarCipher {
    //Fields
    private String alphabet;
    private String shiftedAlphabet;
    private int mainKey;
    //Constructor
    public CaesarCipher(int key){
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet = alphabet.substring(key)+ alphabet.substring(0,key);
        mainKey = key;
    }
    //Methods
    //Encrypt method
    public String encrypt(String input){
        StringBuilder sb = new StringBuilder(input);
        for(int i=0; i < sb.length(); i++){
            char c = sb.charAt(i);
            char provisoireC = Character.toUpperCase(c);
            int idx = alphabet.indexOf(provisoireC);
            if(idx != -1){
                c = shiftedAlphabet.charAt(idx);
                sb.setCharAt(i, c);
            }
        }
        return sb.toString();
    }
    //Decrypt method
    public String decrypt(String input){
        CaesarCipher cc = new CaesarCipher(26 - mainKey);
        String iniMsg = cc.encrypt(input);
        return iniMsg;
    }
}


