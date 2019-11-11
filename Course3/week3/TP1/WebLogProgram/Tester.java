
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        // complete method
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        la.printAll();
    }
    
    public void testUniqueIP(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        System.out.println("The number of unique IPs is "+la.countUniqueIPs());
    }
    
    public void testHigherThanNum(){
        LogAnalyzer la = new LogAnalyzer();
        //la.readFile("short-test_log");
        la.readFile("weblog1_log");
        la.printAllHigherThanNum(400);
    }
    
    public void testUniqueIPVisitsOnDay(){
        LogAnalyzer la = new LogAnalyzer();
        //la.readFile("weblog-short_log");  
        la.readFile("weblog2_log");
        ArrayList<String> uniqueIPs = la.uniqueIPVisitsOnDay("Sep 27");
        int size = uniqueIPs.size();
        System.out.println("Number of unique IPs is: "+size);
        for(int i=0; i<size; i++){
            System.out.println(uniqueIPs.get(i));
        }
    }
    
    public void testCountUniqueIPsInRange(){
        LogAnalyzer la = new LogAnalyzer();
        //la.readFile("short-test_log");
        la.readFile("weblog2_log");
        //System.out.println(la.countUniqueIPsInRange(200,299));
        System.out.println(la.countUniqueIPsInRange(200,299));
    }
    
    public void testMostNumberVisitsByIP(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String,Integer> map = la.countVisitsPerIP();
        System.out.println(la.mostNumberVisitsByIP(map));
    }
    
    public void testIPsMostVisits(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String,Integer> map = la.countVisitsPerIP();
        System.out.println(la.iPsMostVisits(map));
    }
    
    public void testIPsForDays(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog3-short_log");
        HashMap<String,ArrayList<String>> map = la.iPsForDays();
        System.out.println(map);
    }
    
    public void testdayWithMostIPVisits(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String,ArrayList<String>> map = la.iPsForDays();
        System.out.println(la.dayWithMostIPVisits(map));
    }
    
    public void testiPsWithMostVisitsOnDay(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String,ArrayList<String>> map = la.iPsForDays();
        ArrayList<String> iPsWithMostVisitsThatDay = la.iPsWithMostVisitsOnDay(map, "Sep 29");
        System.out.println(iPsWithMostVisitsThatDay);
    }
}
