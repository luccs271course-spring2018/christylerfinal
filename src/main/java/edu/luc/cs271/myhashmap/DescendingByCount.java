package edu.luc.cs271.myhashmap;

import java.util.Comparator;

public class DescendingByCount implements Comparator<Patient> {
  @Override
  public int compare(Patient patient1, Patient patient2) {
    //compares patients to sort them from highest severity to lowest
    return patient2.getInjury() - patient1.getInjury();
  }
}

