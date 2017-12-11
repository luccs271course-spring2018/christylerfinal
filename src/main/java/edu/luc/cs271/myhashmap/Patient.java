package edu.luc.cs271.myhashmap;

public class Patient {
    private int injury;
    private String name;

    public Patient(String name, int injury) {
        this.injury = injury;
        this.name = name;
    }

    public int getInjury() {

        return injury;
    }

    @Override
    public String toString() {
        return name + " has a severity of " + injury;
    }


}
