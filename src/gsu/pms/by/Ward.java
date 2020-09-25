package gsu.pms.by;

import java.util.Comparator;

public class Ward implements Comparable<Ward>{

    private int number_of_beds;
    private String name;
    private int number_of_freeBeds;

    // default constructor
    public Ward() {
        this.number_of_beds = 2;
        this.name = "Vasy";
        this.number_of_freeBeds = 4;
    }

    // overloaded constructor
    public Ward(int number_of_beds){
        this.number_of_beds = number_of_beds;
        this.name = "Dima";
        this.number_of_freeBeds = 3;
    }

    public Ward(int number_of_beds, String name, int number_of_freeBeds) {
        this.number_of_beds = number_of_beds;
        this.name = name;
        this.number_of_freeBeds = number_of_freeBeds;
    }

    public int getNumber_of_beds() {
        return number_of_beds;
    }

    public void setNumber_of_beds(int number_of_beds) {
        this.number_of_beds = number_of_beds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber_of_freeBeds() {
        return number_of_freeBeds;
    }

    public void setNumber_of_freeBeds(int number_of_freeBeds) {
        this.number_of_freeBeds = number_of_freeBeds;
    }


    public int compareTo(Ward compareWards) {
        int compareBeds = ((Ward) compareWards).getNumber_of_beds();
        return this.number_of_beds - compareBeds;
    }

    public static Comparator<Ward> WardsNameComparator
            = new Comparator<Ward>() {

        public int compare(Ward o1, Ward o2) {

            String wardName1 = o1.getName();
            String wardName2 = o2.getName();

            return wardName1.compareTo(wardName2);
        }
    };

    @Override
    public String toString() {
        return "Number of beds: " + number_of_beds + ", Doctor's name: '" + name + ", Number of free beds: " + number_of_freeBeds;
    }
}