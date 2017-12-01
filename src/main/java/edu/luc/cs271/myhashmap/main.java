package edu.luc.cs271.myhashmap;

import jdk.internal.util.xml.impl.Input;

import java.util.*;

public class main {
    public static void main(final String[] args) {
        DescendingByCount sort = new DescendingByCount();
        Scanner input = new Scanner(System.in);

        boolean valid = false;
        boolean in = false;
        boolean name = false;
        boolean what = false;
        int numberOfPatients = getNumberOfPatients();
        int whatDo = 0;
        int severity = 0;
        String patientName = null;

        HashMap<Integer, String> hmap = new HashMap<Integer, String>();
        PriorityQueue<Patient> queue = new PriorityQueue<Patient>(numberOfPatients, sort);
        ArrayList<String> list = new ArrayList<String>();

        if (numberOfPatients > 0) {
            for (int i = 0; i < numberOfPatients; i++) {
                name = false;
                in = false;
                //forces user to enter a name
                //add patient name
                while (!name) {
                    try {
                        System.out.println("Please enter the patient's first name:");
                        patientName = input.next().toLowerCase();
                        name = true;
                    } catch (InputMismatchException e) {
                        System.out.println("Enter a name using letters");
                        input.next();
                    }
                }
                //forces user to enter an integer
                while (!in) {
                    try {
                        //add patient severity
                        System.out.println("Please enter the severity of the patients injury.");
                        System.out.println("1 being lowest  and 10 being the highest severity:");
                        severity = input.nextInt();
                        if (severity > 0 && severity < 11) {
                            in = true;
                        } else {
                            System.out.println("Please enter a number between 1 and 10.");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Please enter an integer.");
                        input.next();
                    }

                }
                list.add(patientName);
                hmap.put(severity, patientName);
                queue.offer(new Patient(patientName, severity));

            }

            for (int i = 0; i < numberOfPatients; i++) {
                System.out.println(queue.poll());
            }

            printMenu();

            //Makes sure that the input is either 1 or 2.
            while (!valid) {
                try {
                    whatDo = input.nextInt();
                    if (whatDo > 0 && whatDo < 3) {
                        valid = true;
                    } else {
                        System.out.println("Please enter 1 or 2.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Input was not recognized, please enter 1 or 2.");
                    input.next();
                }
            }

            switch (whatDo) {
                case 1:
                    System.out.println("-----------------------------------------------------------------");
                    System.out.println("It is recommended to treat the patient with the highest severity");
                    int key = 0;
                    String treated = null;
                    while (!what) {
                        System.out.println("You have the chance to treat one person, please enter their name:");

                        treated = input.next().toLowerCase();

                        //Uses the array list to track down the patientNames to see if the input matches anyone of them
                        if (list.contains(treated)) {
                            for (Map.Entry entry : hmap.entrySet()) {
                                if (treated.equals(entry.getValue())) {
                                    key = (int) entry.getKey();
                                }
                            }

                            randomSurvival(key, treated);
                            what = true;
                        } else {
                            System.out.println("Patient was not recognized\nTry again.");
                        }
                    }
                    break;
                case 2:
                    System.out.println("------------------------");
                    System.out.println("Patients cleared from list...");
                    queue.clear();
                    hmap.clear();
                    break;

                default:
                    System.out.println("invalid response");
            }

        }
    }


    public static void printMenu() {
        System.out.println("--------------------------------------------------");
        System.out.println("What would you like to do? (please enter the corresponding number)");
        System.out.println("1. Treat patients");
        System.out.println("2. Clear list of patients");
    }

    public static int getNumberOfPatients() {
        Scanner input = new Scanner(System.in);
        boolean number = false;
        int n = 0;
        while (!number) {
            try {
                System.out.println("How many patients would you like to enter? (Patient Capacity is 15)");
                n = input.nextInt();
                if (n > 0 && n < 16) {
                    return n;
                }
            } catch (InputMismatchException e) {
                System.out.println("Input is not between 1 and 15.");
                input.next();
            }
        }
        return n;
    }

    public static void randomSurvival(int n, String s) {
        Random rand = new Random();
        int random = rand.nextInt(n);
        double chance = (1 / n) * 100;
        if (random >= (n / 2)) {
            System.out.println(s + " had a %" + chance + " chance of survival and has survived!");
        } else {
            System.out.println(s + " had a %" + chance + " chance of survival and died.");
        }
    } /*
        Have patient's with Level 1 injury severity have 100% chance of survival and have the percentage go down by 10% each time the
        level of injury severity goes up a level.
        Also, in the rare cases where a patient with a low level of injury severity dies, have a random survival percentage generated
        alongside the normal chance of survival and have the program choose between which survival percentage to go off of.
        */
}
