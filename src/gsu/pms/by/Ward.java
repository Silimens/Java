package gsu.pms.by;

import java.io.Serializable;

public class Ward implements Serializable {

    private int number_of_beds;
    private String name;
    private int number_of_freeBeds;

    public Ward(int number_of_beds, String name, int number_of_freeBeds) {
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