
/**
 * Write a description of PhraseFilter here.
 * 
 * @Orached
 * @24/12/2015
 */
public class PhraseFilter implements Filter{
    private String where;
    private String phrase;
    
    public PhraseFilter(String where, String phrase){
        this.where = where;
        this.phrase = phrase;
    }
    
    public boolean satisfies(QuakeEntry quake){
        if(where.equals("start")){
            if(quake.getInfo().substring(0, phrase.length()).equals(phrase)){
                return true;
            }
        } else if(where.equals("end")){
            if(quake.getInfo().substring(quake.getInfo().length()-phrase.length(), quake.getInfo().length()).equals(phrase)){
                return true;
            }
        } else if(where.equals("any")){
            for(int i=0; i<(quake.getInfo().length()-phrase.length()); i++){
                if(quake.getInfo().substring(i, i+phrase.length()).equals(phrase)){
                    return true;
                }
            }
        }
        return false;
    }
    
    public String getName(){
        return "Phrase";
    }
}
