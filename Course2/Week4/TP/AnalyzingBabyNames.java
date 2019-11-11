
/**
 * Write a description of AnalyzingBabyNames here.
 * 
 * @Orached 
 * @20/11/2015
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class AnalyzingBabyNames {
    
    public void totalBirths (FileResource fr) {
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        int totalGirlsNames = 0;
        int totalBoysNames = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;
            if (rec.get(1).equals("M")) {
                totalBoys += numBorn;
                totalBoysNames ++;
           }
            else {
                totalGirls += numBorn;
                totalGirlsNames ++;
            }
        }
        System.out.println("total births = " + totalBirths);
        System.out.println("female girls = " + totalGirls);
        System.out.println("male boys = " + totalBoys);
        System.out.println("female names = " + totalGirlsNames);
        System.out.println("male boys = " + totalBoysNames);
    }
    
    //The tester
    public void testTotalBirths () {
        //FileResource fr = new FileResource();
        FileResource fr = new FileResource();
        totalBirths(fr);
    }    
    
    public long numbOfFemaleNames(CSVParser parser){
       long numbFemaleNames = 0;
       for (CSVRecord rec : parser) {
           if(rec.get(1).equals("F"))
               numbFemaleNames ++;
       }
       return numbFemaleNames;
    }
    //Tester
    void TestnumbOfFemaleNames(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser(false);
        System.out.println("Number of female names is: "+numbOfFemaleNames(parser));
    }
    
    //
    public long getRank(int year, String name, String gender){
        FileResource fr = new FileResource("us_babynames/us_babynames_by_year/yob"+year+".csv");
        CSVParser parser = fr.getCSVParser(false);
        long numbFemNames = 0;
        long rank = 0;
        for (CSVRecord rec : parser) {
            if(rec.get(1).equals("F"))
               numbFemNames ++;
            if(rec.get(0).equals(name)&&rec.get(1).equals(gender)){
                if(rec.get(1).equals("F")){
                   rank = parser.getCurrentLineNumber();
                } else {
                   rank = (parser.getCurrentLineNumber() - numbFemNames);
                   }
            }
        }
        if(rank == 0)
           return -1;
        return rank;
    }
    
    //Tester
    public void testGetRank(){
       FileResource fr = new FileResource();
       CSVParser parser = fr.getCSVParser(false);
       //Example to test
       long rank = getRank(2012, "Omar", "M");
       System.out.println(rank);
       }
    
    public String getName(int year, int rank, String gender){
        FileResource fr = new FileResource("us_babynames/us_babynames_by_year/yob"+year+".csv");
        CSVParser parser = fr.getCSVParser(false);
        long numbFemNames = 0;
        long rank1 = 0;
        StringBuilder nameOnRank = new StringBuilder();
        for (CSVRecord rec : parser) {
            if(rec.get(1).equals("F"))
                numbFemNames ++;
            if(gender.equals("M"))
                rank1 = rank + numbFemNames;
            else rank1 = rank;    
            if(parser.getCurrentLineNumber() == rank1 && rec.get(1).equals(gender)){
                nameOnRank.append(rec.get(0));
                String nameS = nameOnRank.toString();
            }
        }
        if(nameOnRank.toString().equals(""))
            return "NO NAME";
        else 
            return nameOnRank.toString();
    }
    
    public void whatIsNameInYear(String name, int year, int newYear, String gender){
        int rankYear = (int) getRank(year, name, gender);
        String newName = getName(newYear, rankYear, gender);
        System.out.println(name + " born in " +year+ " would be " +newName+ " if she was born in "+newYear);
    }
    //Return the year where the name's rank was the higher
    public int yearOfHighestRank(String name, String gender){
        DirectoryResource dr = new DirectoryResource();
        long rank = 999999999;
        int yearMax = -1;
        for (File f : dr.selectedFiles()) {
            int year = Integer.parseInt(f.getName().substring(3, 7));
            long curRank = getRank(year, name, gender);
            if((rank > curRank) && (curRank != -1)){
                rank = curRank;
                yearMax = year;
            }
        }
        return yearMax;
    }
    
    public double getAverageRank(String name, String gender){
        DirectoryResource dr = new DirectoryResource();
        int counter = 0;
        long rankTotal = 0;
        for (File f : dr.selectedFiles()) {
            int year = Integer.parseInt(f.getName().substring(3, 7));
            long curRank = getRank(year, name, gender);
            rankTotal += curRank;
            counter++;
        }
        if(rankTotal < 0)
            return -1;
        return (double)rankTotal/(double)counter;
    }
    
    public int getTotalBirthsRankedHigher(int year, String name, String gender){
        long rank = getRank(year, name, gender);
        while(rank > 0){
            -- rank;
            
        }
        return 1;
    }
}
