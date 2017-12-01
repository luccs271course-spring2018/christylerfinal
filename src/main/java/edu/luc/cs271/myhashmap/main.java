package edu.luc.cs271.myhashmap;

import edu.luc.cs271.myhashmap.DescendingByCount;
import edu.luc.cs271.myhashmap.Patient;

import java.util.*;

public class main {
    public static void main(final String[] args) {
        DescendingByCount sort = new DescendingByCount();
        Scanner input = new Scanner(System.in);

        boolean valid = false;
        int numberOfPatients = getNumberOfPatients();
        int whatDo = 0;

        HashMap<Integer, String> hmap = new HashMap<Integer, String>();
        PriorityQueue<Patient> queue = new PriorityQueue<Patient>(numberOfPatients, sort);

        if (numberOfPatients > 0) {
            for (int i = 0; i < numberOfPatients; i++) {

                //add patient name
                System.out.println("Please enter the patient's first name:");
                String patientName = input.next().toLowerCase();


                //add patient severity
                System.out.println("Please enter the severity of the patients injury.");
                System.out.println("1 being lowest  and 10 being the highest severity:");
                int severity = input.nextInt();

                hmap.put(severity, patientName);
                queue.offer(new Patient(patientName, severity));

            }

            for (int i = 0; i < numberOfPatients; i++) {
                System.out.println(queue.poll());
            }

            printMenu();


            while(!valid) {
                try {
                    whatDo = input.nextInt();
                    if(whatDo > 0 && whatDo < 3) {
                        valid = true;
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
                    System.out.println("Please treat one person.....? (Enter their name)");

                    int key = 0;
                    String treated = input.next().toLowerCase();
                    for (Map.Entry entry : hmap.entrySet()) {
                        if (treated.equals(entry.getValue())) {
                            key = (int) entry.getKey();
                        }
                    }

                    randomSurvival(key, treated);


                    break;
                case 2:
                    System.out.println("------------------------");
                    System.out.println("Patients cleared from list...");
                    queue.clear();
                    break;

                default:
                    System.out.println("invalid response");
            }

        }


        //account for if the highest severity is the same


    }


    public static void printMenu() {
        System.out.println("--------------------------------------------------");
        System.out.println("What would you like to do? (please enter the corresponding number)");
        System.out.println("1. Treat patients");
        System.out.println("2. Clear list of patients");
    }

    public static int getNumberOfPatients() {
        Scanner input = new Scanner(System.in);
        int n = 0;
        do {
            System.out.println("How many patients would you like to enter? (Patient Capacity is 15)");
            n = input.nextInt();

            if (n < 0) {
                System.out.println("The number of patients cannot be less than zero.");
            } else if (n > 15) {
                System.out.println("The number of patients must be under capacity.");
            } else if (n == 0) {
                System.out.println("You have no patients to treat. Goodbye!");
                System.exit(0);
            }
        } while (n < 0 || n > 15);
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
    }
}
