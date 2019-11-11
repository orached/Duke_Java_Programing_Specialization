
/**
 * Transform words from a file into another form
 * 
 * @Omar
 * @15/11/2015
 */
public class WordPlay {
    //Returns true if the parameter is a vowel
    boolean isVowel(char ch){
    if((ch == 'a')||(ch == 'e')||(ch == 'i')||(ch == 'o')||(ch == 'u')||(ch == 'A')||(ch == 'E')||(ch == 'I')||(ch == 'O')||(ch == 'U'))
    return true;
    return false;
    }
    //Replace the vowels in the string parameter with the char parameter
    String replaceVowels(String phrase, char ch){
        StringBuilder phraseS = new StringBuilder(phrase);
        for(int k=0; k < phrase.length(); k++){
            if(isVowel(phrase.charAt(k))){
                phraseS.setCharAt(k, ch);
            }
    }
    return phraseS.toString();
    }
    
    String emphasize(String phrase, char ch){
        StringBuilder phraseS = new StringBuilder(phrase);
        for(int k=0; k < phrase.length(); k++){
            if(phrase.charAt(k) == ch){
                if((k%2) == 0){
                    phraseS.setCharAt(k, '*');
                } else phraseS.setCharAt(k, '+');
            }
    }
        return phraseS.toString();
    }
}
