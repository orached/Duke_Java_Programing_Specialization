import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder sb = new StringBuilder();
        for(int i = whichSlice; i < message.length(); i+=totalSlices){
            sb.append(message.charAt(i));
        }
        return sb.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        CaesarCracker cc = new CaesarCracker(mostCommon);
        for(int i=0; i<klength; i++){
            String slicedMsg = sliceString(encrypted, i, klength);
            key[i] = cc.getKey(slicedMsg);
        }
        return key;
    }
    //TP2
    public HashSet<String> readDictionary(FileResource fr){
        HashSet<String> englishWords = new HashSet<String>();
        for(String line : fr.lines()){
            line = line.toLowerCase();
            englishWords.add(line);
        }
        return englishWords;
    }
    //TP2
    public int countWords(String message, HashSet<String> dictionary){
        int count = 0;
        for (String word : message.split("\\W")){
            if(dictionary.contains(word))
                count += 1;
        }
        return count;
    }
    //TP2
    public String breakForLanguage(String encrypted, HashSet<String> dictionary){
        int max = 0;
        String decryptedMsg ="";
        //We use 100 instead of encrypted.length()
        for(int i=1; i <= 100; i++){
            int[] keys = tryKeyLength(encrypted, i, 'e');
            VigenereCipher vc = new VigenereCipher(keys);
            String decrypted = vc.decrypt(encrypted);
            int curr = countWords(decrypted, dictionary);
            if(curr > max){
                max = curr;
                decryptedMsg = decrypted;
            }
        }
        return decryptedMsg;
    }
    //TP3
    public char mostCommonCharIn(HashSet<String> dictionary){
        HashMap<Character,Integer> charCount = new HashMap<Character,Integer>();
        //Maps the characters of all the words in dictionary with their frequences
        for(String word : dictionary){
            for(int i=0; i < word.length(); i++){
                char currChar = Character.toLowerCase(word.charAt(i));
                //currChar.toLowerCase();
                if(! charCount.containsKey(currChar)){
                    charCount.put(currChar,1);
                } else {
                    charCount.put(currChar,charCount.get(currChar)+1);
                }
            }
        }
        int maxCount = 0;
        char charMax = 'x';
        //Gets the char that occur the most frequently
        for(char currChar : charCount.keySet()){
            int currCount = charCount.get(currChar);
            if(currCount > maxCount){
                maxCount = currCount;
                charMax = currChar;
            }
        }
        return charMax;
    }
    //TP3
    public void breakForAllLanguages(String encrypted, HashMap<String,HashSet<String>> languages){
        for(String language : languages.keySet()){
            HashSet<String> dictionary = languages.get(language);
            char charMax = mostCommonCharIn(dictionary);
            String decrypted = breakForLanguageTP3(encrypted, dictionary);
            System.out.println("The language used here is : "+language);
            System.out.println(decrypted);
        }
    }
    //TP3
    public String breakForLanguageTP3(String encrypted, HashSet<String> dictionary){
        int max = 0;
        String decryptedMsg ="";
        char charMax = mostCommonCharIn(dictionary);
        //We use 100 instead of encrypted.length()
        for(int i=1; i <= 100; i++){
            int[] keys = tryKeyLength(encrypted, i, charMax);
            VigenereCipher vc = new VigenereCipher(keys);
            String decrypted = vc.decrypt(encrypted);
            int curr = countWords(decrypted, dictionary);
            if(curr > max){
                max = curr;
                decryptedMsg = decrypted;
            }
        }
        return decryptedMsg;
    }
    
    public void breakVigenere() {
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        //Change keylength manually here
        FileResource dictionary = new FileResource();
        //int[] keys = tryKeyLength(encrypted, 4, 'e');
        HashSet<String> englishWords = readDictionary(dictionary);
        //VigenereCipher vc = new VigenereCipher(keys);
        //String decrypted = vc.decrypt(encrypted);
        String decrypted = breakForLanguage(encrypted, englishWords);
        System.out.println(decrypted);
    }
    
    public void breakVigenereTP3(){
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        String[] languagesArray = {"Danish", "Dutch", "English", "French", "German", "Italian", "Portuguese", "Spanish"};
        HashMap<String,HashSet<String>> languages = new HashMap<String,HashSet<String>>();
        //DirectoryResource dr = new DirectoryResource();
        for(String language : languagesArray){
            FileResource dictionary = new FileResource("dictionaries/"+language);
            HashSet<String> words = readDictionary(dictionary);
            languages.put(language,words);
            System.out.println("Processing dictionary for "+language);
        }
        breakForAllLanguages(encrypted, languages);
    }
}
