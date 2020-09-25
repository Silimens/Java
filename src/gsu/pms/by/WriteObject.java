package gsu.pms.by;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class WriteObject {
    public static void main(String[] args) {
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
        try {
            FileOutputStream fos = new FileOutputStream("wards.bin");
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(wards);

            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}