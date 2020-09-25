package gsu.pms.by;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Arrays;

public class ReadObject {
    public static void main(String[] args) {
        try {
            FileInputStream fis = new FileInputStream("wards.bin");
            ObjectInputStream ois = new ObjectInputStream(fis);

            Ward[] wards = (Ward[]) ois.readObject();

            System.out.println(Arrays.toString(wards));

            ois.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}