
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @Orached 
 * @29/11/2015
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         // complete constructor
         records = new ArrayList<LogEntry>();
     }
        
     public void readFile(String filename) {
         // complete method
         FileResource f = new FileResource(filename);
         for(String line : f.lines()){
             LogEntry le = WebLogParser.parseEntry(line);
             records.add(le);
            }
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     public int countUniqueIPs(){
         ArrayList<String> uniqueIPs = new ArrayList<String>();
         for (LogEntry le : records){
             String ipAddr = le.getIpAddress();
             if(! uniqueIPs.contains(ipAddr))
                uniqueIPs.add(ipAddr);
            }
         return uniqueIPs.size();
        }
     
     public void printAllHigherThanNum(int num){
          //ArrayList<LogEntry> logs = new ArrayList<LogEntry>();
          System.out.println("Logs that have a status greater than "+num+" are");
          for (LogEntry le : records){
             int status = le.getStatusCode();
             if(status > num){
                 //logs.add();
                 System.out.println(le);
                }
            }
        }
        
     public ArrayList<String> uniqueIPVisitsOnDay(String someday){
         ArrayList<String> uniqueIPs = new ArrayList<String>();
         for (LogEntry le : records){
             String date = le.getAccessTime().toString();
             String sDate = date.substring(4,10);
             if(sDate.equals(someday)){
                 String ipAddr = le.getIpAddress();
                 if(! uniqueIPs.contains(ipAddr))
                    uniqueIPs.add(ipAddr);
             }
         }
         return uniqueIPs;
     }
     
     public int countUniqueIPsInRange(int low, int high){
         ArrayList<String> uniqueIPs = new ArrayList<String>();
         for (LogEntry le : records){
             int status = le.getStatusCode();
             if(status >= low && status <= high){
                 String ipAddr = le.getIpAddress();
                 if(! uniqueIPs.contains(ipAddr))
                    uniqueIPs.add(ipAddr);
             }
         }
         return uniqueIPs.size();
     }
     
     public HashMap<String,Integer> countVisitsPerIP(){
         HashMap<String,Integer> map = new HashMap<String,Integer>();
         for (LogEntry le : records){
              String ipAddr = le.getIpAddress();
              if(! map.containsKey(ipAddr)){
                map.put(ipAddr, 1);
              } else {
                  map.put(ipAddr, map.get(ipAddr)+1);
                }
            }
         return map;
     }
     
     public int mostNumberVisitsByIP(HashMap<String,Integer> map){
         int max = 0;
         for (String ip : map.keySet()){
             int curr = map.get(ip);
             if(curr > max)
                max = curr;
         }
         return max;
     }
     //returns an ArrayList of Strings of IP adresses that all have the maximum number of visits to this website
     public ArrayList<String> iPsMostVisits(HashMap<String,Integer> map){
         int max = mostNumberVisitsByIP(map);
         ArrayList<String> maxIPs = new ArrayList<String>();
         for (String ip : map.keySet()){
             if(map.get(ip)==max)
                maxIPs.add(ip);
         }
         return maxIPs;
     }
     
     //For exercise
     public ArrayList<String> iPVisitsOnDay(String someday){
         ArrayList<String> uniqueIPs = new ArrayList<String>();
         for (LogEntry le : records){
             String date = le.getAccessTime().toString();
             String sDate = date.substring(4,10);
             if(sDate.equals(someday)){
                 String ipAddr = le.getIpAddress();
                 uniqueIPs.add(ipAddr);
             }
         }
         return uniqueIPs;
     }
     
     public HashMap<String,ArrayList<String>> iPsForDays(){
         HashMap<String,ArrayList<String>> ipPerDay = new HashMap<String,ArrayList<String>>();
         for (LogEntry le : records){
             String date = le.getAccessTime().toString();
             String sDate = date.substring(4,10);
             ArrayList<String> ipThisDay = iPVisitsOnDay(sDate);
             if(! ipPerDay.containsKey(sDate)){
                ipPerDay.put(sDate,ipThisDay);
                }
         }
         return ipPerDay;
     }
     
     public String dayWithMostIPVisits(HashMap<String,ArrayList<String>> ipPerDay){
         int max = 0;
         //StringBuilder result = new StringBuilder();
         String result = "";
         for (String date : ipPerDay.keySet()){
             int curr = ipPerDay.get(date).size();
             if (curr > max){
                 result = date;
                 max = curr;
             }
         }
         //return result.toString();
         return result;
     }
     
     public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String,ArrayList<String>> ipPerDay, String day){
         ArrayList<String> iPs = ipPerDay.get(day);
         HashMap<String,Integer> myMap = new HashMap<String,Integer>();
         for(String ip : iPs){
              if(! myMap.containsKey(ip)){
                  myMap.put(ip, 1);
              } else {
                  myMap.put(ip, myMap.get(ip)+1);
                }
            }
         ArrayList<String> mostIPs = iPsMostVisits(myMap);
         return mostIPs;
     }
}
