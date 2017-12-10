package edu.luc.cs271.myhashmap;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Hospital {
    Scanner input = new Scanner(System.in);
    boolean nam = false;
    boolean in = false;
    String patientName = null;
    int severity;
    int n = 0;
    HashMap<Integer, String> hmap = new HashMap<>();
    public int getInjury(){
        in = false;
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

    public String getName(){
        nam = false;
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

    public int getNumberOfPatients(){
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



}
