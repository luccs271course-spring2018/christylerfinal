package edu.luc.cs271.myhashmap;

public class main {
    public static void main(final String[] args) {

        Hospital hp = new Hospital();
        int numberOfPatients = hp.numberOfPatients;

        if (numberOfPatients > 0) {
            for (int i = 0; i < numberOfPatients; i++) {
                //add patient name
                hp.getName();
                //forces user to enter an integer
                hp.getInjury();

                //Adds patient names and injury to a an arrayList, HashMap, and Priority Queues
                hp.getPatients();
            }
            //Prints the priority queue that was filled with all of the patients
            hp.printQueue();
            //Makes sure that the input is either 1 or 2.
            int whatDo = hp.printMenu();

            //Depending on the input, the patients on the list are either treated, or cleared from the list
            switch (whatDo) {
                case 1:
                    //treats patient and survival rate based on level of severity
                    hp.treatPatient();
                    break;
                case 2:
                    //Clears the patient from all of the relevant data structures
                    hp.clearList();
                    break;
                default:
                    System.out.println("invalid response");
            }

        }
    }

}