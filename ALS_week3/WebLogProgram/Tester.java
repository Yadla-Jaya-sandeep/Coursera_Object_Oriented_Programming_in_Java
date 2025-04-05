
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public static final String PATH=System.getProperty("user.dir")+"\\ALS_week3\\WebLogProgram\\";
    public void testLogEntry() {
    	System.out.println("testLogEntry ------------------ ");
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        System.out.println("testLogAnalyzer ------------------ ");
        // creates a LogAnalyzer object
        LogAnalyzer la = new LogAnalyzer();
        // calls readFile on the data file short-test_log
        la.readFile(PATH+"short-test_log");
        // calls printAll to print all the web logs
        la.printAll();
    }

    public void testCountUniqueIPs () {
        System.out.println("testCountUniqueIPs ------------------ ");
        // create a LogAnalyzer
        LogAnalyzer la = new LogAnalyzer();
        // read from the file short-test_log
        la.readFile(PATH+"weblog2_log");
        // test the method countUniqueIPs
        int uniqueIps = la.countUniqueIPs();
        System.out.println("The number of unique IP in this file is/are " + uniqueIps);
    }

    public void testPrintAllHigherThanNum () {
        System.out.println("testPrintAllHigherThanNum ------------------ ");
        // create a LogAnalyzer
        LogAnalyzer la = new LogAnalyzer();
        // read from the file short-test_log
        la.readFile(PATH+"weblog1_log");
        // test the method testPrintAllHigherThanNum
        la.printAllHigherThanNum(400);
    }

    public void testUniqueIPVisitsOnDay () {
        System.out.println("testUniqueIPVisitsOnDay ------------------ ");
        // create a LogAnalyzer
        LogAnalyzer la = new LogAnalyzer();
        // read from the file short-test_log
        la.readFile(PATH+"weblog2_log");
        // test the method testUniqueIPVisitsOnDay
        System.out.println(la.uniqueIPVisitsOnDay("Sep 27").size());
    }

    public void testCountUniqueIPsInRange () {
        System.out.println("testCountUniqueIPsInRange ------------------ ");
        // create a LogAnalyzer
        LogAnalyzer la = new LogAnalyzer();
        // read from the file short-test_log
        la.readFile(PATH+"weblog2_log");

        System.out.println(la.countUniqueIPsInRange(200,299));
    }

    public void testCountVisitsPerIP () {
        System.out.println("testCountVisitsPerIP ------------------ ");
        // create a LogAnalyzer
        LogAnalyzer la = new LogAnalyzer();
        // read from the file short-test_log
        la.readFile(PATH+"weblog1_log");

        System.out.println(la.countVisitsPerIP());
    }

    public void testMostNumberVisitsByIP () {
        System.out.println("testMostNumberVisitsByIP ------------------ ");
        // create a LogAnalyzer
        LogAnalyzer la = new LogAnalyzer();
        // read from the file short-test_log
        la.readFile(PATH+"weblog2_log");
        HashMap<String, Integer> visitIpCount = la.countVisitsPerIP();

        System.out.println(la.mostNumberVisitsByIP(visitIpCount));
    }

    public void testIPsMostVisits () {
        System.out.println("testIPsMostVisits ------------------ ");
        // create a LogAnalyzer
        LogAnalyzer la = new LogAnalyzer();
        // read from the file short-test_log
        la.readFile(PATH+"weblog2_log");
        HashMap<String, Integer> visitIpCount = la.countVisitsPerIP();

        System.out.println(la.iPsMostVisits(visitIpCount));
    }

    public void testIPsForDays () {
        System.out.println("testIPsForDays ------------------ ");
        // create a LogAnalyzer
        LogAnalyzer la = new LogAnalyzer();
        // read from the file short-test_log
        la.readFile(PATH+"weblog3-short_log");

        System.out.println(la.iPsForDays());
    }

    public void testDayWithMostIPVisits () {
        System.out.println("testDayWithMostIPVisits ------------------ ");
        // create a LogAnalyzer
        LogAnalyzer la = new LogAnalyzer();
        // read from the file short-test_log
        la.readFile(PATH+"weblog2_log");
        HashMap<String, ArrayList<String>> dayIps = la.iPsForDays();

        System.out.println(la.dayWithMostIPVisits(dayIps));
    }

    public void testIPsWithMostVisitsOnDay () {
        System.out.println("testIPsWithMostVisitsOnDay ------------------ ");
        // create a LogAnalyzer
        LogAnalyzer la = new LogAnalyzer();
        // read from the file short-test_log
        la.readFile(PATH+"weblog2_log");
        HashMap<String, ArrayList<String>> dayIps = la.iPsForDays();

        System.out.println(la.iPsWithMostVisitsOnDay(dayIps, "Sep 30"));
    }
    public static void main(String[] args) {
        Tester tester = new Tester();
        System.out.println(Tester.PATH);
        tester.testIPsWithMostVisitsOnDay();
    }

}
