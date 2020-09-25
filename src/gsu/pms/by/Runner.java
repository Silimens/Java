package gsu.pms.by;
import gsu.pms.by.Ward;
import java.util.Arrays;

public class Runner {
    public static void main(String[] args) {

        Ward[] wards = new Ward[9];

        wards[0] = new Ward(3, "Vika", 5);
        wards[2] = new Ward(2, "Pavel", 3);
        wards[1] = new Ward(4, "Andrey", 2);
        wards[3] = new Ward(5, "Maxim", 0);
        wards[4] = new Ward();
        wards[5] = new Ward(6, "Vity",0);
        wards[6] = new Ward(5, "Anton", 0);
        wards[7] = new Ward(4, "Kristina", 0);
        wards[8] = new Ward(1, "Masha", 0);

        // Output
        for (int i = 0; i < wards.length; i++) {
            System.out.println(wards[i]);
        }

        // Output of the sorted array by Beds
        Arrays.sort(wards, Ward::compareTo);
        System.out.println();

        for (int i = 0; i < wards.length; i++) {
            System.out.println(wards[i]);
        }

        // Output of the sorted array by Names
        Arrays.sort(wards, Ward.WardsNameComparator);
        System.out.println();

        for (int i = 0; i < wards.length; i++) {
            System.out.println(wards[i]);
        }

        // Total number of beds
        int totalNumberOfBeds = 0;
        for (int i=0; i < wards.length; i++){
            totalNumberOfBeds += wards[i].getNumber_of_beds();
        }
        System.out.println("Total number of beds");
        System.out.println(totalNumberOfBeds);

        // Total number of free beds
        int totalNumberOfFreeBeds = 0;
        for (int i=0; i < wards.length; i++){
            totalNumberOfFreeBeds += wards[i].getNumber_of_freeBeds();
        }
        System.out.println("Total number of free beds");
        System.out.println(totalNumberOfFreeBeds);
    }
}