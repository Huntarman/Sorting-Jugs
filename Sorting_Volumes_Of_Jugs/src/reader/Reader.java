package reader;

import jugs.Jugs;
import persons.Person;

import java.io.*;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class Reader {
    public static ArrayList<Jugs> JugsList = new ArrayList<Jugs>();
    public ArrayList<Person> PersonList = new ArrayList<>();

    //JUGS
    public static ArrayList<Jugs> readJugs(){
        try (BufferedReader reader = new BufferedReader(new FileReader("Jugs.txt"))) {
            String line = reader.readLine();
            while (line!=null) {
                String[] strarr = line.split("; ");
                int[] casttoint = new int[3];
                int i = 0;
                for (String str : strarr) {
                    casttoint[i] = parseInt(str);
                    i++;
                }
                JugsList.add(new Jugs(casttoint[0], casttoint[1], casttoint[2]));
                line = reader.readLine();
            }
        }catch (IOException e) {
            e.printStackTrace();
        }

        return JugsList;
    }


    //PERSONS
    public static ArrayList<Person> readPerson() {
        ArrayList<Person> PersonList = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader("Persons.txt"));
            String line = reader.readLine();
            while (line != null) {
                String[] strarr = line.split(";");
                int Id = Integer.parseInt(strarr[0]);
                String[] strarr2 = strarr[1].split(",");
                ArrayList<Integer> prefferedflavour = new ArrayList<Integer>();
                int i = 0;
                for (String str : strarr2) {
                    prefferedflavour.add(parseInt(strarr2[i]));
                    i++;
                }
                PersonList.add(new Person(Id, prefferedflavour));
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return PersonList;
    }
}
