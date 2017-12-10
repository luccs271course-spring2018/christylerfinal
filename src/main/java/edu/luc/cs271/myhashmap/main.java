package edu.luc.cs271.myhashmap;

import java.util.*;

public class main {
    public static void main(final String[] args) {
        DescendingByCount sort = new DescendingByCount();
        Scanner input = new Scanner(System.in);

        boolean what = false;
        String patientName = null;
        int severity = 0;

        Hospital hp = new Hospital();
        int numberOfPatients = hp.getNumberOfPatients();
        int whatDo = 0;

        HashMap<Integer, String> hmap = new HashMap<Integer, String>();

        PriorityQueue<Patient> queue = new PriorityQueue<Patient>(numberOfPatients, sort);
        ArrayList<String> list = new ArrayList<String>();

        if (numberOfPatients > 0) {
            for (int i = 0; i < numberOfPatients; i++) {
                //add patient name
                patientName = hp.getName();
                //forces user to enter an integer
                severity = hp.getInjury();
                //Adds patient names and injury to a an arrayList, HashMap, and Priority Queues
                list.add(patientName);
                hmap.put(severity, patientName);
                queue.offer(new Patient(patientName, severity));
            }

            //Prints the patients with their severities from greatest to least
            for (int i = 0; i < numberOfPatients; i++) {
                System.out.println(queue.poll());
            }

            //Makes sure that the input is either 1 or 2.
            whatDo = hp.printMenu();

            //Depending on the input, the patients on the list are either treated, or cleared from the list
            switch (whatDo) {
                case 1:
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

                            hp.randomSurvival(key, treated);
                            what = true;
                        } else {
                            System.out.println("Patient was not recognized\nTry again.");
                        }
                    }
                    break;
                case 2:
                    queue.clear();
                    hmap.clear();
                    list.clear();
                    System.out.println("-----------------------------");
                    System.out.println("Patients cleared from list...");
                    break;

                default:
                    System.out.println("invalid response");
            }

        }
    }

}
