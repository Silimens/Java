package gsu.pms.by;

import gsu.pms.by.exceptions.EmptyNameException;
import gsu.pms.by.exceptions.NegativeNumberException;

import java.io.Serializable;

public class Ward implements Serializable {

    private int number_of_beds;
    private String name;
    private int number_of_freeBeds;

    public Ward(int number_of_beds, String name, int number_of_freeBeds) {
        if(number_of_beds < 0)
            throw new NegativeNumberException("Number of beds can\'t be less than 0, " +
                    "but " + number_of_beds + " was found!");
        if(number_of_freeBeds < 0)
            throw new NegativeNumberException("Number of free beds can\'t be less than 0, " +
                    "but " + number_of_freeBeds + " was found!");
        if(name.equals("") || name == null)
            throw new EmptyNameException("Name can\'t be empty!");

        this.number_of_beds = number_of_beds;
        this.name = name;
        this.number_of_freeBeds = number_of_freeBeds;
    }

    public int getNumber_of_beds() {
        return number_of_beds;
    }

    public String getName() {
        return name;
    }

    public int getNumber_of_freeBeds() {
        return number_of_freeBeds;
    }

    public String toString(){
        return "Number of beds: "+ number_of_beds + ", Name of doctor: " + name + ", Number of free beds:  " + number_of_freeBeds + "\n";
    }
}