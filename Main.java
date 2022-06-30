package com.zenucode;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

class Person {
    String name;
    int[][] avail;
    int prefDay;
    int prefNoon;
    int prefNight;
    int workedHour;
    int change;

    int[] prefArray;
    int used;


    public Person(String name, int[][] allSchedule, int change, int prefDay, int prefNoon, int prefNight, int num) {
        this.name = name;
        int indexOne = num * 7;
        int[][] temp = new int[24][7];
        for(int i = 0; i < 24; i++) {
            for(int j = 0; j < 7; j++) {
                temp[i][j] = allSchedule[i][j + indexOne];
            }
        }
        this.avail = temp;
        this.change = change;
        this.prefDay = prefDay;
        this.prefNoon = prefNoon;
        this.prefNight = prefNight;
        this.workedHour = 0;

        this.prefArray = new int[3];
        this.used = 0;
        System.out.println("Hi " + name);
    }
}

public class Main {
    final static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws FileNotFoundException {
        //System.out.println("How many employees do you have?");
        int numSlaves = 8; //scanner.nextInt();

        //Initialize all parameters.
        Scanner scanInput = new Scanner(new File("/Users/Jaehyeok/Desktop/Employee Shift Assignment/src/com/zenucode/Test.csv"));
        scanInput.useDelimiter("\n");
        String[] tempValues = scanInput.nextLine().split(",+");
        String[] tempSlaves = new String[numSlaves];
        int[] prefDay = new int[numSlaves];
        int[] prefNoon = new int[numSlaves];
        int[] prefNight = new int[numSlaves];
        int[] change = new int[numSlaves];

        //Store to appropriate array
        int count = 0;
        for (int i = 0; i < tempValues.length; count++) {
            tempSlaves[count] = tempValues[i]; i++;
            prefDay[count] = Integer.parseInt(tempValues[i]); i++;
            prefNoon[count] = Integer.parseInt(tempValues[i]); i++;
            prefNight[count] = Integer.parseInt(tempValues[i]); i++;
            change[count] = Integer.parseInt(tempValues[i]); i++;
        }

        System.out.println("\nData from file has been loaded.\n");

        //Create whole schedule for team
        int[][] allSchedule = new int[24][(numSlaves - 1) * tempSlaves.length]; //[row][column]
        for (int i = 0; i < 24; i++) {
            String[] temp = scanInput.nextLine().split(",");
            for (int j = 0; j < (numSlaves - 1) * tempSlaves.length; j++) {
                allSchedule[i][j] = Integer.parseInt(temp[j]);
            }
        }

        //Make people
        Person[] theSlaves = new Person[numSlaves];
        for (int j = 0; j < numSlaves; j++) {
            theSlaves[j] = new Person(tempSlaves[j], allSchedule, change[j], prefDay[j], prefNoon[j], prefNight[j], j);
        }
        theSlaves[0].name = "Azriel"; //Included due to letter bug. Changed needed for other files.

        //Make the week
        String[][] theFDesk = new String[4][7];
        String[][] theMDesk = new String[4][7];
        String[][] theWeekMail = new String[2][7];

        initWeek(theSlaves, theFDesk, theMDesk, theWeekMail);

        System.out.println("Schedule Created\n");
        printFormatArray(theFDesk, 1);
        printFormatArray(theMDesk, 2);
        printFormatArray(theWeekMail, 3);


    }

    private static void printFormatArray(String[][] inputArr, int type) {
        int row = inputArr.length;
        int col = inputArr[0].length;

        String friedaParker = "Frieda Parker Residence Halls";
        String meredithSouth = "Meredith South Residence Halls";
        String mail = "Mailroom";

        if(type == 1) {
            System.out.println(friedaParker);
        } else if(type == 2) {
            System.out.println(meredithSouth);
        } else if(type == 3) {
            System.out.println(mail);
        }

        System.out.println("   Time      Monday      Tuesday     Wednesday   Thursday    Friday      Saturday    Sunday");

        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                int length = 10 - inputArr[i][j].length();
                StringBuilder temp = new StringBuilder();
                temp = new StringBuilder(inputArr[i][j]);
                for(int k = 0; k < length; k++) {
                    temp.insert(inputArr[i][j].length(), " ");
                }
                inputArr[i][j] = temp.toString();
            }
        }

        String[] splitVal = new String[inputArr.length];
        for(int i = 0; i < inputArr.length; i++) {
            splitVal[i] = Arrays.toString(inputArr[i]);
        }

        String[] timeDesk = {"12AM ~ 8AM: ", " 8AM ~ 1PM: ", " 1PM ~ 6PM: ", "6PM ~ 12AM: "};
        String[] timeMail = {"9AM ~ 12PM: ", "3PM ~  5PM: "};
        if(type == 1 || type == 2) {
            for(int i = 0; i < inputArr.length; i++) {
                System.out.println(timeDesk[i] + splitVal[i]);
            }
        } else if(type == 3) {
            for(int i = 0; i < inputArr.length; i++) {
                System.out.println(timeMail[i] + splitVal[i]);
            }
        }
        System.out.println("\n");
    }

    private static void initWeek(Person[] theSlaves, String[][] theFDesk, String[][] theMDesk, String[][] theWeekMail) {
        for(int i = 0; i < 7; i++) {
            theFDesk[0][i] = "Night"; theMDesk[0][i] = "Night";
        }

        String[] dayPref = new String[theSlaves.length]; int dayCount = 0;
        String[] noonPref = new String[theSlaves.length]; int noonCount = 0;
        String[] nightPref = new String[theSlaves.length]; int nightCount = 0;

        for(int i = 0; i < theSlaves.length; i++) {
            if(theSlaves[i].prefDay != 0) { dayPref[dayCount] = theSlaves[i].name; dayCount++; }
            if(theSlaves[i].prefNoon != 0) { noonPref[noonCount] = theSlaves[i].name; noonCount++; }
            if(theSlaves[i].prefNight != 0) { nightPref[nightCount] = theSlaves[i].name; nightCount++; }
        }

        dayPref = getShortArray(dayPref);
        noonPref = getShortArray(noonPref);
        nightPref = getShortArray(nightPref);

        int[] numAvail = new int[theSlaves.length];

        for(int j = 0; j < theSlaves.length; j++) {
            for (int i = 0; i < theSlaves.length; i++) {
                if(theSlaves[i].used == theSlaves.length + 1) {
                    numAvail[i] = theSlaves.length + 1;
                    continue;
                }
                numAvail[i] = checkAvail(theSlaves[i], dayPref, noonPref, nightPref);
            }

            int minAt = 0;
            for (int i = 0; i < numAvail.length; i++) {
                minAt = numAvail[i] < numAvail[minAt] ? i : minAt;
            }

            assignSchedule(theSlaves[minAt], theFDesk, theMDesk, theWeekMail);

            theSlaves[minAt].used = theSlaves.length + 1;
        }
        theFDesk[3][0] = theSlaves[6].name; theSlaves[6].workedHour += 6;
        theMDesk[3][6] = theSlaves[0].name; theSlaves[0].workedHour -= 6;
    }

    private static void assignSchedule(Person theSlave, String[][] theFDesk, String[][] theMDesk, String[][] theWeekMail) {
        int full = 0;

        if(theSlave.prefArray[0] == 1) {
            for(int i = 0; i < 7; i++) {
                if(theFDesk[1][i] == null && theSlave.workedHour < 32) {
                    theFDesk[1][i] = theSlave.name;
                    theSlave.workedHour += 5;
                } else if(theMDesk[1][i] == null && theFDesk[1][i] != theSlave.name && theSlave.workedHour < 32) {
                    theMDesk[1][i]  = theSlave.name;
                    theSlave.workedHour += 5;
                }

                if(theWeekMail[1][i] == null && theSlave.workedHour < 32) {
                    theWeekMail[1][i] = theSlave.name;
                    theSlave.workedHour += 2;
                }
            }
        }

        if(theSlave.prefArray[1] == 1) {
            for(int i = 0; i < 7; i++) {
                if(theFDesk[2][i] == null && theSlave.workedHour < 32) {
                    theFDesk[2][i] = theSlave.name;
                    theSlave.workedHour += 5;
                } else if(theMDesk[2][i] == null && theFDesk[2][i] != theSlave.name && theSlave.workedHour < 32) {
                    theMDesk[2][i]  = theSlave.name;
                    theSlave.workedHour += 5;
                }

                if(theWeekMail[0][i] == null && theSlave.workedHour < 32) {
                    theWeekMail[0][i] = theSlave.name;
                    theSlave.workedHour += 3;
                }
            }
        }

        if(theSlave.prefArray[2] == 1) {
            for(int i = 0; i < 7; i++) {
                if(theFDesk[3][i] == null && theSlave.workedHour < 32) {
                    theFDesk[3][i] = theSlave.name;
                    theSlave.workedHour += 6;
                } else if(theMDesk[3][i] == null && theFDesk[3][i] != theSlave.name && theSlave.workedHour < 32) {
                    theMDesk[3][i]  = theSlave.name;
                    theSlave.workedHour += 6;
                }
            }
        }
    }

    private static int checkAvail(Person theSlave, String[] dayPref, String[] noonPref, String[] nightPref) {
        int count = 0;
        if(Arrays.asList(dayPref).contains(theSlave.name) == true) { count++; theSlave.prefArray[0] = 1;}
        if(Arrays.asList(noonPref).contains(theSlave.name) == true) { count++; theSlave.prefArray[1] = 1;}
        if(Arrays.asList(nightPref).contains(theSlave.name) == true) { count++; theSlave.prefArray[2] = 1;}

        return count;
    }

    private static String[] getShortArray(String[] day) {
        int count = 0;
        for(int i = 0; i < day.length; i++) {
            if(day[i] != null) {
                count++;
            }
        }
        String[] shorten = Arrays.copyOf(day, count);
        return shorten;
    }

    public static void writeFile(String[][] theWeekDesk) {
        File myObj = new File("/Users/Jaehyeok/Desktop/Employee Shift Assignment/src/com/zenucode/Final.csv");
        try {
            FileWriter writer = new FileWriter("/Users/Jaehyeok/Desktop/Employee Shift Assignment/src/com/zenucode/Final.csv");
            writer.write(Arrays.deepToString(theWeekDesk).replace("], ", "]\n").replace("[[", "[").replace("]]", "]"));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
