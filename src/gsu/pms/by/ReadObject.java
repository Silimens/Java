package gsu.pms.by;

import gsu.pms.by.exceptions.EmptyWardsException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Arrays;

public class ReadObject {
    public static void main(String[] args) {
        try {
            FileInputStream fis = new FileInputStream("wards.bin");
            ObjectInputStream ois = new ObjectInputStream(fis);

            Ward[] wards = (Ward[]) ois.readObject();

            if(wards.length == 0)
                throw new EmptyWardsException();

            System.out.println(Arrays.toString(wards));

            ois.close();
        } catch (EmptyWardsException e) {
            System.out.println("Array is empty!");
        } catch (StackOverflowError e) {
            System.out.println("You\'v run out of memory! :(");
        } catch (FileNotFoundException e) {
            System.out.println("File \"wards.bin\" has not been found!");
        } catch (IOException e) {
            System.out.println("Some trouble with input happened!");
        } catch (ClassNotFoundException e) {
            System.out.println("Class with name \"Ward\" has not been found!");
        }
    }
}