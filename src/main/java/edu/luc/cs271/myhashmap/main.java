package edu.luc.cs271.myhashmap;

public class main {
  public static void main(final String[] args) {
    Hospital hp = new Hospital();
    int numberOfPatients = hp.numberOfPatients;
    boolean start = true;

    // Beginning of program
    while (start) {
      for (int i = 0; i < numberOfPatients; i++) {
        // add patient name
        hp.getName();
        // forces user to enter an integer
        hp.getInjury();
        // Uses patient names and injury to a arrayList, HashMap, and Priority Queue
        hp.getPatients();
      }
      // Prints the priority queue that was filled with all of the patients
      hp.printQueue();

      // Depending on the input, the patients on the list are either treated, or cleared from the
      // list
      boolean s = true;

      // While loop followed by a switch statement that prompts the user a set of choices
      while (s) {
        // Prints the menu of the different choices that the user can pick from
        int whatDo = hp.printMenu();
        switch (whatDo) {
          case 1:
            // treats a specific patient/survival rate based on level of severity
            hp.treatPatient();
            break;
          case 2:
            // Treats all of the patients at one time (fifo)
            hp.treatAll();
            break;
          case 3:
            // Prints the patients currently in the list
            hp.printList();
            break;
          case 4:
            // Adds patients to the list
            hp.addPatient();
            break;
          case 5:
            // Clears all data structures of any data they contain and exits program
            hp.clearList();
            break;
          case 6:
            // Exits program
            System.out.println("Exiting program...");
            s = false;
            break;
          default:
            System.out.println("invalid response");
        }
      }
      // Ends the program
      start = false;
    }
  }
}
