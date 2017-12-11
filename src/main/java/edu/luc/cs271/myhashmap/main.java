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

                hp.getPatients();
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
                    //Prompts user to "treat" one patient from the list. Whatever patient was picked has a formula that determines their
                    //rate of survival based on the level of their severity
                    hp.treatPatient();
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
