package com.zenucode;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.Random;

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

    int stamina;
    int life;



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

        this.stamina = 70;
        this.life = 3;
        System.out.println("Hi " + name);
    }
}

class CA {
    int situation;

    public int randChance(int situation) {
        Random random = new Random();
        int upperbound = 100;
        int intRandom = random.nextInt(upperbound);
        if(situation == 1) {
            return 1;
        } else if(situation == 2) {
            if(intRandom < 50) {
                return 1;
            } else {
                return 0 ;
            }
        } else if(situation == 3) {
            if(intRandom < 25) {
                return 1;
            } else {
                return 0;
            }
        } else if(situation == 4) {
            if(intRandom < 5) {
                return 1;
            } else {
                return 0;
            }
        }
        return 0;
    }
}

class SCC {
    int situation;

    public int randChance(int situation) {
        Random random = new Random();
        int upperbound = 100;
        int intRandom = random.nextInt(upperbound);
        if(situation == 1 || situation == 2) {
            return 1;
        } else if(situation == 3) {
            if(intRandom < 60) {
                return 1;
            } else {
                return 0;
            }
        } else if(situation == 4) {
            if(intRandom < 25) {
                return 1;
            } else {
                return 0;
            }
        }
        return 0;
    }
}

class Alex {
    int situation;

    public int randChance(int situation) {
        Random random = new Random();
        int upperbound = 100;
        int intRandom = random.nextInt(upperbound);
        if(situation == 1 || situation == 2) {
            return 1;
        } else if(situation == 3) {
            if(intRandom < 70) {
                return 1;
            } else {
                return 0;
            }
        } else if(situation == 4) {
            if(intRandom < 50) {
                return 1;
            } else {
                return 0;
            }
        }
        return 0;
    }
}

class Authority {
    int situation;

    public int randChance(int situation) {
        Random random = new Random();
        int upperbound = 100;
        int intRandom = random.nextInt(upperbound);
        if(situation == 1 || situation == 2 || situation == 3) {
            return 1;
        } else if(situation == 4) {
            if(intRandom < 95) {
                return 1;
            } else {
                return 0;
            }
        }
        return 0;
    }
}

public class Main {
    final static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
        //System.out.println("How many employees do you have?");
        int numSlaves = 8; //scanner.nextInt();

        //Initialize all parameters.
        System.out.println("Welcome to the Simulation of a Purdue Summer CA Employee's Week!");
        System.out.println("Checking Data");
        for(int i = 0; i < 1; i++) {
            TimeUnit.SECONDS.sleep(1);
            System.out.print(". ");
        }
        System.out.println("\nLoading Data");
        for(int i = 0; i < 1; i++) {
            TimeUnit.SECONDS.sleep(1);
            System.out.print(". ");
        }

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

        int userInt = 10;
        while(userInt < 1 || userInt > 3) {
            System.out.println("\nChoose Option");
            System.out.println("1. Check/Edit Schedule");
            System.out.println("2. Play Game");
            System.out.println("3. Exit Simulation");
            try {
                userInt = scanner.nextInt();
            } catch(InputMismatchException e) {
                System.out.println("Please enter a valid number");
                scanner.nextLine();
                userInt = 10;
                continue;
            }
            if(userInt < 1 || userInt > 3) {
                System.out.println("Please enter a valid number");
            }
        }
        if(userInt == 3) {
            System.out.println("\nBye!");
            return;
        }

        //Make the week
        String[][] theFDesk = new String[4][7];
        String[][] theMDesk = new String[4][7];
        String[][] theWeekMail = new String[2][7];

        initWeek(theSlaves, theFDesk, theMDesk, theWeekMail);

        System.out.println("\nSchedule Created\n");

        if(userInt == 1) {
            printFormatArray(theFDesk, 1);
            printFormatArray(theMDesk, 2);
            printFormatArray(theWeekMail, 3);
            userInt = 10;
            while(userInt < 1 || userInt > 2) {
                System.out.println("Would you like to edit the schedule?\n");
                System.out.println("1. Yes");
                System.out.println("2. No (Proceed to Game)\n");
                try {
                    userInt = scanner.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Please enter a valid number");
                    scanner.nextLine();
                    userInt = 10;
                    continue;
                }
                if(userInt < 1 || userInt > 2) {
                    System.out.println("Please enter a valid number");
                }
                if(userInt == 1) { changeSchedule(theFDesk, theMDesk, theWeekMail); }
            }
        }

        System.out.println("\nGame Time!\n");

        System.out.println("Which hall would you like to clear first?");
        System.out.println("1. Frieda\n2. Meredith\n3. Mailroom");
        int choice = scanner.nextInt();
        int freida = 0; int meredith = 0; int mailroom = 0; //Turns 1 if cleared
        int cleared = 0;
        int finishGame = 0;
        while(freida != 1 && meredith != 1 && mailroom != 1) {
            if(choice == 1) { freida = playGame(theSlaves, theFDesk, theMDesk, theWeekMail, 1); };
            if(choice == 2) { meredith = playGame(theSlaves, theFDesk, theMDesk, theWeekMail, 2); };
            //if(choice == 3) { mailroom = playMail(theSlaves, theFDesk, theMDesk, theWeekMail); };

            if(freida == 2 || meredith == 2 || mailroom == 2) {
                //GameOVER HERE
            }

            while(cleared != 1 || finishGame != 1) {
                if(freida == 1 && meredith == 1 && mailroom == 1) {
                    finishGame = 1;
                    continue;
                }
                System.out.println("Which hall would you like to clear next?");
                System.out.println("1. Frieda\n2. Meredith\n3. Mailroom");
                choice = scanner.nextInt();
                if(choice == 1 && freida == 1) {
                    System.out.println("You have already cleared this hall!\nChoose another hall.");
                    cleared = 0;
                } else if(choice == 2 && meredith == 1) {
                    System.out.println("You have already cleared this hall!\nChoose another hall.");
                    cleared = 0;
                } else if(choice == 3 && mailroom == 1) {
                    System.out.println("You have already cleared this hall!\nChoose another hall.");
                    cleared = 0;
                } else {
                    cleared = 1;
                }
            }
        }
    }

    private static int playGame(Person[] theSlaves, String[][] theFDesk, String[][] theMDesk, String[][] theMailRoom, int hallNum) throws InterruptedException {
        int finish = 0;
        int day = 0;
        int hour = 8;
        int minute = 0;
        int position = 0;
        String shiftPerson = new String();
        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        String[][] assignedHall = new String[4][7];

        if(hallNum == 1) {
            assignedHall = theFDesk;
        } else if(hallNum == 2) {
            assignedHall = theMDesk;
        }

        while(finish != 1 || finish != 2) { //2 is all members gone, 1 is clear
            if(checkEnding(assignedHall) == 1) { return 2; } //Function that counts #of failed emp

            if(hour >= 8 && hour < 13) { position = 1; }
            else if(hour >= 13 && hour < 18 ) { position = 2; }
            else if(hour >= 18 && hour < 24 ) { position = 3; }

            int trigger = 0;
            if((hour >= 8 || hour < 24)) {
                if(!shiftPerson.replaceAll("\\s+","").equalsIgnoreCase(assignedHall[position][day].replaceAll("\\s+",""))) {
                    System.out.printf("\nNew person on Shift!\nName: %s\n", assignedHall[position][day]);
                    shiftPerson = assignedHall[position][day];
                    trigger = 1;
                }
                String adjustedTime = new String();
                int adjustedHour = 0;
                if(hour >= 8) {
                    if(hour >= 24) { hour = (hour % 24) + 8; day++;}

                    if(day == 7) {
                        int check = checkEnding(assignedHall);
                        if(check == 0) { return 1; }
                        return 2;
                    }

                    if(hour == 12) { adjustedTime = hour + "PM"; }
                    else if (hour > 12) { adjustedHour = hour % 12; adjustedTime = adjustedHour + "PM"; }
                    else { adjustedTime = hour + "AM"; }
                }
                if(trigger == 1) {
                    System.out.printf("Current Time - Day: %s, Hour: %s, Minute: %d\n\n", days[day], adjustedTime, minute);
                    int userInt = 10;
                    while(userInt != 3) {
                        System.out.println("Manager Status Checkup");
                        System.out.println("1. Check Employee Status");
                        System.out.println("2. Change Shifts");
                        System.out.println("3. Done");
                        userInt = scanner.nextInt();
                        if (userInt == 1) {
                            slavesStatusPrint(theSlaves);
                        } else if (userInt == 2) {
                            printFormatArray(theFDesk, 1);
                            printFormatArray(theMDesk, 2);
                            printFormatArray(theMailRoom, 3);
                            userInt = 10;
                            while (userInt < 1 || userInt > 2) {
                                System.out.println("Would you like to edit the schedule?\n");
                                System.out.println("1. Yes");
                                System.out.println("2. No (Proceed to Game)\n");
                                try {
                                    userInt = scanner.nextInt();
                                } catch (InputMismatchException e) {
                                    System.out.println("Please enter a valid number");
                                    scanner.nextLine();
                                    userInt = 10;
                                    continue;
                                }
                                if (userInt < 1 || userInt > 2) {
                                    System.out.println("Please enter a valid number");
                                }
                                if (userInt == 1) {
                                    changeSchedule(theFDesk, theMDesk, theMailRoom);
                                }
                            }
                        }
                    }
                }
            }

            minute += 30;
            if(minute == 60) {
                hour += 1;
                minute = 0;
            }

            checkSituation(day, hour, minute, theSlaves, shiftPerson);
            finish = checkPerson(day, hour, minute, theSlaves, shiftPerson, theFDesk, theMDesk, theMailRoom);
            if(finish == 1) { System.out.println("You failed as a CA! Good luck!"); break; }
        }
        return 0;
    }

    private static int checkEnding(String[][] assignedHall) {
        int count = 0;
        for(int i = 0; i < assignedHall.length; i++) {
            for(int j = 0; j < assignedHall[0].length; j++) {
                if(assignedHall[i][j].equals("Lost Employee")) {
                    count++;
                }
            }
        }
        if(count == 21) { return 1; }
        return 0;
    }

    private static void slavesStatusPrint(Person[] theSlaves) {
        System.out.println("\n");
        for(int i = 0; i < theSlaves.length; i++) {
            if(theSlaves[i].name.equals("Lost Employee")) {
                continue;
            }
            System.out.printf("Name: %s, Life: %d, Stamina: %d\n", theSlaves[i].name, theSlaves[i].life, theSlaves[i].stamina);
        }
        System.out.println("\n");
    }

    private static int checkPerson(int day, int hour, int minute, Person[] theSlaves, String shiftPerson, String[][] FDesk, String[][] MDesk, String[][] mailRoom) {
        int index = 0;
        for(int i = 0; i < theSlaves.length; i++) {
            if(shiftPerson.replaceAll("\\s+","").equalsIgnoreCase(theSlaves[i].name.replaceAll("\\s+",""))) { index = i; }
        }
        theSlaves[index].stamina -= 5;


        int userInt = 10;
        if(theSlaves[index].stamina < 30) {
            Random random = new Random();
            String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
            int timeAdd = random.nextInt(25);
            String adjustedTime = new String();
            int adjustedHour = 0;

            if(hour >= 8) {
                if(hour > 24) { hour %= 24; day++;}
                if(hour == 12) { adjustedTime = hour + "PM"; }
                else if (hour > 12) { adjustedHour = hour % 12; adjustedTime = adjustedHour + "PM"; }
                else { adjustedTime = hour + "AM"; }
            }
            minute += timeAdd;

            String[] slackOff = {"You are hungry and the dining court is open. Should you leave?",
                    "Maybe you magically had a sleeping pill in your food. You.. are... dozing..... o..f...f....",
                    "The phone is ringing, but you don't feel like answering it.",
                    "You accidentally spilled your drink on the floor. Should you clean it up?",
                    "You forgot your conference shirt at home!",
                    "Someone asks you to play air hockey! Should you play?"};
            int randomInt = random.nextInt(slackOff.length);
            System.out.printf("\nCurrent Time - Day: %s, Hour: %s, Minute: %d\n", days[day], adjustedTime, minute);
            System.out.println(slackOff[randomInt]);
            System.out.println("1. Eh... Who cares...\n2. Be a professional do the right thing!");
            userInt = scanner.nextInt();
            if(userInt == 1) {
                float randomFloat = random.nextFloat(100);
                if(randomFloat < 40) {
                    System.out.printf("%s got caught by the staff!\n", theSlaves[index].name);
                    randomInt = random.nextInt(15);
                    theSlaves[index].stamina -= randomInt;
                    System.out.printf("%s loses %d stamina.\n", theSlaves[index].name, randomInt);
                    randomInt = random.nextInt(2);
                    if(randomInt == 0) {
                        theSlaves[index].life -= 1;
                        theSlaves[index].stamina = 70;
                        System.out.printf("%s lost a life!\n", theSlaves[index].name);
                    }
                } else {
                    System.out.println("Whew... You didn't get caught!");
                    theSlaves[index].stamina += 30;
                    System.out.println("Gained 30 stamina!\n");
                }
            } else if(userInt == 2) {
                System.out.println("You did the right thing but it tires you a bit");
                System.out.println("You lost -3 stamina");
                theSlaves[index].stamina -= 3;
            }
        }

        if(theSlaves[index].stamina < 0) {
            System.out.printf("%s doesn't have enough stamina. %s loses a life\n", theSlaves[index].name, theSlaves[index].name);
            theSlaves[index].life -= 1;
            theSlaves[index].stamina = 70;
        }
        if(theSlaves[index].life <= 0) {
            System.out.printf("%s has lost all lives.\nHis remaining schedule will be emptied.", theSlaves[index].name);
            for(int i = 0; i < FDesk.length; i++) {
                for(int j = 0; j < FDesk[0].length; j++) {
                    if(!FDesk[i][j].replaceAll("\\s+","").equalsIgnoreCase(theSlaves[index].name.replaceAll("\\s+",""))) {
                        FDesk[i][j] = "Empty";
                    }
                }
            }
            for(int i = 0; i < MDesk.length; i++) {
                for(int j = 0; j < MDesk[0].length - 1; j++) {
                    if(!MDesk[i][j].replaceAll("\\s+","").equalsIgnoreCase(theSlaves[index].name.replaceAll("\\s+",""))) {
                        MDesk[i][j] = "Empty";
                    }
                }
            }
            for(int i = 0; i < mailRoom.length; i++) {
                for(int j = 0; j < mailRoom[0].length; j++) {
                    if(!mailRoom[i][j].replaceAll("\\s+","").equalsIgnoreCase(theSlaves[index].name.replaceAll("\\s+",""))) {
                        mailRoom[i][j] = "Empty";
                    }
                }
            }
            theSlaves[index].name = "Lost Employee";
        }

        //Need to include warning about empty schedule;

        return 0;
    }

    private static void checkSituation(int day, int hour, int minute, Person[] theSlaves, String shiftPerson) {
        Random random = new Random();
        int userInt = 0;
        int solved = 0;
        int situationType = 0;
        float floatRandom = random.nextFloat(100);

        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        int timeAdd = random.nextInt(25);

        CA student = new CA();
        SCC staff = new SCC();
        Alex alex = new Alex();
        Authority board = new Authority();

        if (floatRandom >= 60 && floatRandom < 85) { situationType = 1; }
        else if (floatRandom >= 85 && floatRandom < 85) { situationType = 2; }
        else if (floatRandom >= 95 && floatRandom < 98) { situationType = 3; }
        else if (floatRandom >= 98 && floatRandom < 99) { situationType = 4; }

        String adjustedTime = new String();
        if (hour >= 8) {
            if (hour == 12) { adjustedTime = hour + "PM"; }
            else if (hour > 12) { hour %= 12; adjustedTime = hour + "PM"; }
            else { adjustedTime = hour + "AM"; }
        }
        minute += timeAdd;

        switch (situationType) {
            case 1:
                String[] situationOne = {"You have recieved a call from a parent regarding student move ins",
                        "Conference member came to the front desk for linen exchange",
                        "A student is asking for some help with directions on campus",
                        "A phone call came in asking to talk with the hall manager",
                        "The vending machine is empty! People are constantly asking the front desk to fill it up!"};
                int intRandom = random.nextInt(situationOne.length);
                System.out.printf("\nCurrent Time - Day: %s, Hour: %s, Minute: %d\n", days[day], adjustedTime, minute);
                System.out.println(situationOne[intRandom]);
                break;
            case 2:
                String[] situationTwo = {"There are too many people checking in for conferences!",
                        "There is a StarRez issue with a summer student check in!",
                        "Someone checking in is not registered in StarRez",
                        "You have something urgent that happened and need to leave the desk"};
                intRandom = random.nextInt(situationTwo.length);
                System.out.printf("\nCurrent Time - Day: %s, Hour: %s, Minute: %d\n", days[day], adjustedTime, minute);
                System.out.println(situationTwo[intRandom]);
                break;
            case 3:
                String[] situationThree = {"One of the rooms is leaking water from the backroom!",
                        "There is a wild animal that entered the residence hall!",
                        "We have found alcohol in one of the residence rooms!",
                        "One of the elevators has broken down.",
                        "The lights of a residence building went down!",
                        "A stranger is forcefully trying to enter the residence hall"};
                intRandom = random.nextInt(situationThree.length);
                System.out.printf("\nCurrent Time - Day: %s, Hour: %s, Minute: %d\n", days[day], adjustedTime, minute);
                System.out.println(situationThree[intRandom]);
                break;
            case 4:
                String[] situationFour = {"The President of Purdue has walked into the building!",
                        "A car crashed into the residence hall.",
                        "There is someone with a weapon trying to enter the residence hall",
                        "A conference member has disappeared!"};
                intRandom = random.nextInt(situationFour.length);
                System.out.printf("\nCurrent Time - Day: %s, Hour: %s, Minute: %d\n", days[day], adjustedTime, minute);
                System.out.println(situationFour[intRandom]);
                break;
        }

        if (situationType >= 1 && situationType <= 4) {
            printSolutions();
            userInt = scanner.nextInt(); //This gives us the solution type
            while (userInt < 1 || userInt > 4) {
                try {
                    userInt = scanner.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Please enter a valid number");
                    scanner.nextLine();
                    userInt = 10;
                    continue;
                }
                if (userInt < 1 || userInt > 4) { System.out.println("Please enter a valid number"); }
            }


            if (userInt == 1) { solved = student.randChance(situationType); }
            else if (userInt == 2) { solved = staff.randChance(situationType); }
            else if (userInt == 3) { solved = alex.randChance(situationType); }
            else if (userInt == 4) { solved = board.randChance(situationType); }

            if (solved == 1 && userInt == situationType) {
                int gainNum = userInt * 5;
                System.out.println("\nThe problem has been solved! Good job!");
                slaveUpdate(theSlaves, shiftPerson, gainNum);
                System.out.printf("Gained %d stamina!\n\n", gainNum);
            } else if (solved == 0 && userInt != situationType) {
                int gainNum = -1 * userInt * 5;
                System.out.println("\nYou didn't choose the best solution and made the situation worse.");
                slaveUpdate(theSlaves, shiftPerson, gainNum);
                int index = 0;
                for(int i = 0; i < theSlaves.length; i++) {
                    if(theSlaves[i].name.equals(shiftPerson)) { index = i; }
                }
                theSlaves[index].life -= 1;
                System.out.printf("You lose a life and %d stamina.\n\n", gainNum);
            } else if (solved == 1 && userInt != situationType) {
                if(userInt == 3 && situationType == 2 || userInt == 2 && situationType == 3) {
                    int gainNum = userInt * 5;
                    System.out.println("\nThe problem has been solved! Good job!");
                    slaveUpdate(theSlaves, shiftPerson, gainNum);
                    System.out.printf("Gained %d stamina!\n\n", gainNum);
                } else {
                    int gainNum = userInt * -3;
                    System.out.println("\nThe problem was solved but another option would have been better.");
                    slaveUpdate(theSlaves, shiftPerson, gainNum);
                    System.out.printf("Lost %d stamina.\n\n", gainNum);
                }
            } else if (solved == 0) {
                int gainNum = -1 * userInt * 3;
                System.out.println("\nYou made the situation worse.");
                slaveUpdate(theSlaves, shiftPerson, gainNum);
                System.out.printf("You lost %d stamina.\n\n", gainNum);
            }
        }
    }

    private static void slaveUpdate(Person[] theSlaves, String shiftPerson, int gainNum) {
        int index = 0;
        int userInt = 10;
        for(int i = 0; i < theSlaves.length; i++) {
            if(shiftPerson.replaceAll("\\s+","").equalsIgnoreCase(theSlaves[i].name.replaceAll("\\s+",""))) { index = i; }
        }

        theSlaves[index].stamina += gainNum;
        if(theSlaves[index].stamina >= 100) {
            System.out.printf("%s's stamina is over 100!\n Would you like to trade 50 stamina for a life?\n", shiftPerson);
            System.out.println("Employee Stamina can not be over 100. Any extra stamina will be lost.");
            System.out.println("1. Yes\n 2.No");
            while (userInt < 1 || userInt > 2) {
                try {
                    userInt = scanner.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Please enter a valid number");
                    scanner.nextLine(); userInt = 10; continue;
                }
                if (userInt < 1 || userInt > 2) { System.out.println("Please enter a valid number"); }
            }
            if(userInt == 1) {
                theSlaves[index].stamina = 50;
                theSlaves[index].life += 1;
            } else {
                theSlaves[index].stamina = 100;
            }
        }
    }

    private static void printSolutions() {
        System.out.println("1. Solve it yourself");
        System.out.println("2. Call the SCC");
        System.out.println("3. Call Alex");
        System.out.println("4. Need Authority Staff");
    }

    private static void changeSchedule(String[][] theFDesk, String[][] theMDesk, String[][] theWeekMail) throws InterruptedException {
        int userInt = 10;
        while(userInt < 1 || userInt > 3) {
            System.out.println("\nEditing Schedule");
            System.out.println("Choose Option:");
            System.out.println("1. See Current Employee Schedule");
            System.out.println("2. Change Employee Schedule");
            System.out.println("3. Continue to Game");
            try {
                userInt = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number");
                scanner.nextLine();
                userInt = 10;
                continue;
            }
            if(userInt < 1 || userInt > 3) {
                System.out.println("Please enter a valid number");
            }
            if(userInt == 1) {
                printFormatArray(theFDesk, 1);
                printFormatArray(theMDesk, 2);
                printFormatArray(theWeekMail, 3);
                userInt = 10;
            } else if(userInt == 2) {
                swapShift(theFDesk, theMDesk, theWeekMail);
                userInt = 10;
            }
        }
    }

    private static void swapShift(String[][] theFDesk, String[][] theMDesk, String[][] theWeekMail) throws InterruptedException {
        int day = 0;
        int time = 0;
        int area = 0;
        String[] days = { "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday" };
        String[] times = { "8AM ~ 1PM", "1PM ~ 6PM", "6PM ~ 12AM" };
        String[] mailTimes = { "9AM ~ 12PM", "3PM ~ 5PM" };
        String[] zone = {"Frieda", "Meredith", "Mail"};

        int userInt = 10;
        while(userInt < 1 || userInt > 4) {
            System.out.println("\nChoose Area:");
            System.out.println("1. Frieda\n2. Meredith\n3. Mail Room\n4. Back to Menu");
            try {
                userInt = scanner.nextInt();
                area = userInt - 1;
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number");
                scanner.nextLine();
                userInt = 10;
                continue;
            }
            if (userInt < 1 || userInt > 4) {
                System.out.println("Please enter a valid number");
            }
            if (userInt == 4) {
                return;
            }
        }

        userInt = 10;
        while(userInt < 1 || userInt > 8) {
            System.out.println("\nChoose Day:");
            System.out.println("1. Monday\n2. Tuesday\n3. Wednesday\n4. Thursday");
            System.out.println("5. Friday\n6. Saturday\n7. Sunday\n8. Back to Menu");
            try {
                userInt = scanner.nextInt();
                day = userInt - 1;
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number");
                scanner.nextLine();
                userInt = 10;
                continue;
            }
            if(userInt < 1 || userInt > 8) {
                System.out.println("Please enter a valid number");
            }
            if(userInt == 8) { return; }
        }

        userInt = 10;
        if(area == 2) {
            while (userInt < 1 || userInt > 3) {
                System.out.println("\nChoose Time:");
                System.out.println("1. 9AM ~ 12PM\n2. 3PM ~ 5PM\n3. Back to Menu");
                try {
                    userInt = scanner.nextInt();
                    time = userInt - 1;
                } catch (InputMismatchException e) {
                    System.out.println("Please enter a valid number");
                    scanner.nextLine();
                    userInt = 10;
                    continue;
                }
                if (userInt < 1 || userInt > 3) {
                    System.out.println("Please enter a valid number");
                }
                if (userInt == 3) { return; }
            }
        } else {
            while (userInt < 1 || userInt > 5) {
                System.out.println("Choose Time:");
                System.out.println("1. 8AM ~ 1PM\n2. 1PM ~ 6PM\n3. 6PM ~ 12AM\n4. Night Shift\n5. Back to Menu");
                try {
                    userInt = scanner.nextInt();
                    time = userInt - 1;
                } catch (InputMismatchException e) {
                    System.out.println("Please enter a valid number");
                    scanner.nextLine();
                    userInt = 10;
                    continue;
                }
                if (userInt < 1 || userInt > 5) {
                    System.out.println("Please enter a valid number");
                }
                if (userInt == 4) {
                    System.out.println("Checking Permission to Change Night Shifts");
                    for (int i = 0; i < 3; i++) {
                        TimeUnit.SECONDS.sleep(1);
                        System.out.print(". ");
                    }
                    System.out.println("\nAccess Denied\n");
                    userInt = 10;
                }
                if (userInt == 5) {
                    return;
                }
            }
        }

        System.out.printf("Chosen Day: %s , Time: %s\n", days[day], times[time]);
        if(area == 0) { System.out.printf("Currently Assigned Personnel: %s", theFDesk[time + 1][day]); }
        else if(area == 1) { System.out.printf("Currently Assigned Personnel: %s", theMDesk[time + 1][day]); }
        else if(area == 2) { System.out.printf("Currently Assigned Personnel: %s", theWeekMail[time][day]);}
        scanner.nextLine();

        System.out.println("\nEnter Name of New Assignment:");
        String newName = scanner.nextLine();

        if(area == 0) { theFDesk[time + 1][day] = newName; }
        else if(area == 1) { theMDesk[time + 1][day] = newName; }
        else if(area == 2) { theWeekMail[time][day] = newName; }

        System.out.printf("\nShift Assigned to %s\n", newName);
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
                StringBuilder temp = new StringBuilder(inputArr[i][j]);
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
