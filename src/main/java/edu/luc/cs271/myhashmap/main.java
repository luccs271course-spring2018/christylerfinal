package edu.luc.cs271.myhashmap;

public class main {
    public static void main(final String[] args) {
        Hospital hp = new Hospital();
        int numberOfPatients = hp.numberOfPatients;
        boolean start = true;
        while (start) {
            for (int i = 0; i < numberOfPatients; i++) {
                //add patient name
                hp.getName();
                //forces user to enter an integer
                hp.getInjury();
                //Uses patient names and injury to a arrayList, HashMap, and Priority Queue
                hp.getPatients();
            }
            //Prints the priority queue that was filled with all of the patients
            hp.printQueue();

            //Depending on the input, the patients on the list are either treated, or cleared from the list
            boolean s = true;
            while (s) {
                int whatDo = hp.printMenu();
                switch (whatDo) {
                    case 1:
                        //treats patient and survival rate based on level of severity
                        hp.treatPatient();
                        start = false;
                        break;
                    case 2:
                        //Prints the patients currently in the hashmap
                        hp.printList();
                        break;
                    case 3:
                        //Adds patients to the list
                        hp.addPatient();
                        break;
                    case 4:
                        //Clears all data structures of any data they contain and exits program
                        hp.clearList();
                        s = false;
                        break;
                    default:
                        System.out.println("invalid response");
                }
            }

            start = false;
        }


    }

}