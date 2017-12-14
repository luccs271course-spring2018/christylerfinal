package edu.luc.cs271.christylerfinalproject;

import java.util.*;

public class Hospital {

  Scanner input = new Scanner(System.in);
  // Used to sort the priority queue
  DescendingByCount sort = new DescendingByCount();

  String patientName = null;
  int numberOfPatients = getNumberOfPatients();
  int severity = 0;
  int n = 0;

  // Data Structures
  HashMap<Integer, String> hmap = new HashMap<>(); // takes in input from patient names and severity
  ArrayList<String> slist = new ArrayList<>(); // Represents the list for patient names
  ArrayList<Integer> ilist = new ArrayList<>(); // Represents the list for injury severities
  PriorityQueue<Patient> queue =
      new PriorityQueue<Patient>(numberOfPatients, sort); // sorts the inputs from greatest to least
  Stack stack = new Stack();

  public int getInjury() {
    boolean in = false;
    // Prompt the user to input an integer, will run until a number 1-10 is entered.
    while (!in) {
      try {
        // add patient severity
        System.out.println("Please enter the severity of the patients injury.");
        System.out.println("1 being lowest and 10 being the highest severity:");
        severity = input.nextInt();
        // Scale of 10 used to limit misrepresentations (some people may be thinking on a different
        // scale, like out of 100)
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
    // Returns a valid severity

    return severity;
  }

  public String getName() {
    boolean nam = false;
    // Prompts user to enter a name for a patient (can be letters or numbers to avoid
    // discrimination)
    while (!nam) {
      try {
        System.out.println(
            "Please enter the patient's first name (Please define any spaces with '_' :");
        // All names are set to lowercase for simplicity.
        patientName = input.next().toLowerCase();
        nam = true;
      } catch (InputMismatchException e) {
        System.out.println("Enter a name using letters");
        input.next();
      }
    }
    // Returns a valid name
    return patientName;
  }

  // Prompts user to pick the initial amount of patients they want to enter into the program.
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

  // Prompts user to have a choice from 6 options to later perform an actions
  public int printMenu() {
    boolean valid = false;
    int whatDo = 0;
    // Later used in a switch statement to perform a specific action
    System.out.println("------------------------------------------------------------------");
    System.out.println("What would you like to do? (please enter the corresponding number)");
    System.out.println("1. Treat specific patients");
    System.out.println("2. Last patient entered is important, treat them first");
    System.out.println("3. Print list of patients");
    System.out.println("4. Add a patient");
    System.out.println("5. Clear list of patients");
    System.out.println("6. Exit");

    // Makes sure that the input to the switch statement(located in the main class) is valid and in
    // between the set of given options.
    while (!valid) {
      try {
        whatDo = input.nextInt();
        if (whatDo > 0 && whatDo < 7) {
          valid = true;
        } else {
          System.out.println("Please enter a number 1-6");
        }
      } catch (InputMismatchException e) {
        System.out.println("Input was not recognized, please enter a number 1-6");
        input.next();
      }
    }
    return whatDo;
  }

  public void randomSurvival(int n, String s) {
    // Creates a randomizer and picks an integer between 1 and the variable "chance"
    Random rand = new Random();
    int random = rand.nextInt(100) + 1;
    double chance = 100 - (n * 10);
    // If the random number is less than the chance percentage then...
    if (random <= chance) {
      System.out.println(s + " had a " + chance + "% chance of survival and has survived!");
    } else {
      System.out.println(
          s + " had a " + chance + "% chance of survival. There was a complication and they died.");
    }
  }

  // Adds the inputs to their alotted data structure that is pulled into use throughout the entire
  // program.
  public void getPatients() {
    hmap.put(severity, patientName);
    slist.add(patientName);
    ilist.add(severity);
    queue.offer(new Patient(patientName, severity));
    stack.add(patientName);
  }

  // Allows user to treat a specific patient
  public void treatPatient() {
    boolean what = false;
    int key = 0;
    String treated;
    // If there is at least 1 patient on the list
    if (slist.size() > 0) {
      System.out.println(
          "--------------------------------------------------------------------------------------------------------");
      System.out.println(
          "It is recommended to treat the patient with the highest severity since their chances of dying are higher");

      while (!what) {
        System.out.println("Enter a patient name: ");

        treated = input.next().toLowerCase();

        // Uses the array list to track down the patientNames to see if the input matches anyone of
        // them
        // If so, the patient continues and is treated
        if (slist.contains(treated)) {
          for (Map.Entry entry : hmap.entrySet()) {
            if (treated.equals(entry.getValue())) {
              key = (int) entry.getKey();
            }
          }

          randomSurvival(key, treated);
          stack.push(treated);
          stack.pop();
          hmap.remove(key, treated);
          slist.remove(treated);
          ilist.remove(ilist.indexOf(key));
          what = true;
        } else {
          System.out.println("Patient was not recognized\nTry again.");
        }
      }
    } else {
      System.out.println("There are no more patients to treat.");
    }
  }

  public void printQueue() {
    // Prints the patients with their severities from greatest to least
    for (int i = 0; i < numberOfPatients; i++) {
      System.out.println(queue.poll());
    }
  }

  public void clearList() {
    // Clears all data structures
    System.out.println("-----------------------------");
    queue.clear();
    hmap.clear();
    slist.clear();
    ilist.clear();
    stack.clear();
    System.out.println("Patients cleared from list...");
  }

  // Prints the current patients inputted into the program
  public void printList() {
    Iterator itr = slist.iterator();
    Iterator its = ilist.iterator();
    // As long as there is a patient at all on the list.
    if (slist.size() > 0) {
      while (itr.hasNext()) {
        Object element = itr.next();
        Object elements = its.next();
        System.out.println(element + ", " + elements);
      }
    } else {
      System.out.println("There are no patients on the list");
    }
  }

  // Allows user to add a patient to the list.
  public void addPatient() {
    getName();
    getInjury();
    hmap.put(severity, patientName);
    slist.add(patientName);
    ilist.add(severity);
    stack.add(patientName);
  }

  // Allows user to treat all of the patients at one time with their respective odds of survival
  public void treatLast() {
    int key = 0;
    if (slist.size() > 0) {
      // Checks the top of the stack and uses that to treat the most recent patient
      String treated = (String) stack.peek();
      for (Map.Entry entry : hmap.entrySet()) {
        if (treated.equals(entry.getValue())) {
          key = (int) entry.getKey();
        }
      }
      // Removes the specific patient from the list after treatment
      randomSurvival(key, treated);
      hmap.remove(key, treated);
      slist.remove(treated);
      ilist.remove(new Integer(key));
      stack.pop();
    } else {
      System.out.println("There are no patients to treat.");
    }
  }
}
