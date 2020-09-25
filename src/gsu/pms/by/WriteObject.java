package gsu.pms.by;

import gsu.pms.by.exceptions.EmptyNameException;
import gsu.pms.by.exceptions.NegativeNumberException;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class WriteObject {
    public static void main(String[] args) {
        try {
            Ward[] wards = {new Ward(3, "Vika", 5),
                    new Ward(2, "Pavel", 3),
                    new Ward(4, "Andrey", 2),
                    new Ward(5, "Maxim", 0),
                    new Ward(4, "Alexey",2),
                    new Ward(6, "Vity",0),
                    new Ward(5, "Anton", 0),
                    new Ward(4, "Kristina", 0),
                    new Ward(1, "Masha", 0),
                    new Ward(3, "Nikita",1)};

            FileOutputStream fos = new FileOutputStream("wards.bin");
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(wards);

            oos.close();
        } catch (StackOverflowError e) {
            System.out.println("You\'v run out of memory! :(");
        } catch (EmptyNameException e) {
            System.out.println(e.getMessage());
        } catch (NegativeNumberException e) {
            System.out.println(e.getMessage());
        } catch (FileNotFoundException e) {
            System.out.println("File \"wards.bin\" has not been found!");
        } catch (IOException e) {
            System.out.println("Some trouble with input happened!");
        }

    }
}