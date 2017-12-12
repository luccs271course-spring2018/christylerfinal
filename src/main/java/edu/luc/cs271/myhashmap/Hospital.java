package edu.luc.cs271.myhashmap;

import java.util.*;

public class Hospital {
    Scanner input = new Scanner(System.in);
    main m = new main();
    DescendingByCount sort = new DescendingByCount();
    String patientName = null;
    int numberOfPatients = getNumberOfPatients();
    int severity = 0;
    int n = 0;
    HashMap<Integer, String> hmap = new HashMap<>();
    ArrayList<String> list = new ArrayList<String>();
    PriorityQueue<Patient> queue = new PriorityQueue<Patient>(numberOfPatients, sort);

    public int getInjury() {
        boolean in = false;
        while (!in) {
            try {
                //add patient severity
                System.out.println("Please enter the severity of the patients injury.");
                System.out.println("1 being lowest and 10 being the highest severity:");
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
        return severity;
    }

    public String getName() {
        boolean nam = false;
        while (!nam) {
            try {
                System.out.println("Please enter the patient's first name (Please define any spaces with '_' :");
                patientName = input.next().toLowerCase();
                nam = true;
            } catch (InputMismatchException e) {
                System.out.println("Enter a name using letters");
                input.next();
            }
        }
        return patientName;
    }

    public int getNumberOfPatients() {
        boolean number = false;
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

    public int printMenu() {
        boolean valid = false;
        int whatDo = 0;
        System.out.println("------------------------------------------------------------------");
        System.out.println("What would you like to do? (please enter the corresponding number)");
        System.out.println("1. Treat patients");
        System.out.println("2. Clear list of patients");

        while (!valid) {
            try {
                whatDo = input.nextInt();
                if (whatDo > 0 && whatDo < 3) {
                    valid = true;
                } else {
                    System.out.println("Please enter 1 or 2:");
                }
            } catch (InputMismatchException e) {
                System.out.println("Input was not recognized, please enter 1 or 2.");
                input.next();
            }
        }
        return whatDo;
    }

    public void randomSurvival(int n, String s) {
        Random rand = new Random();
        int random = rand.nextInt(100) + 1;
        double chance = 100 - (n * 10);
        if (random <= chance) {
            System.out.println(s + " had a %" + chance + " chance of survival and has survived!");
        } else {
            System.out.println(s + " had a %" + chance + " chance of survival. But there was an implication and they died.");
        }
    }

    public void getPatients() {
        hmap.put(severity, patientName);
        list.add(patientName);
        queue.offer(new Patient(patientName, severity));
    }


    public void treatPatient() {
        boolean what = false;
        System.out.println("--------------------------------------------------------------------------------------------------------");
        System.out.println("It is recommended to treat the patient with the highest severity since their chances of dying are higher");
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
    }

    public void printQueue() {
        //Prints the patients with their severities from greatest to least
        for (int i = 0; i < numberOfPatients; i++) {
            System.out.println(queue.poll());
        }

    }

    public void clearList() {
        System.out.println("-----------------------------");
        queue.clear();
        hmap.clear();
        list.clear();
        System.out.println("Patients cleared from list...");
    }

}
