import jugs.*;
import persons.*;
import utility.*;
import java.util.*;
import static reader.Reader.*;

/*
    Autor:Michal Zajdel
    CMD commands:
    cd Lab02
    javac -sourcepath src/ -d build/prodution/Lab02 src/pwr/mzajdel/lab02/main/main.java
    java -cp build/prodution/Lab02 pwr/mzajdel/lab02/main.main
    compiled with java18
    jar cfvm Lab02_pop.jar src/main/resources/META-INF/MANIFEST.MF -C build/prodution/Lab02 .
    Executing jar file:
    java -jar Lab02_pop.jar
 */
public class main {
    public static void main(String[] args) {
        ArrayList<Person> PersonList;
        ArrayList<Jugs> JugsList;
        PersonList = readPerson();
        JugsList = readJugs();

        //assuming there are no skipped flavours: example being if there are only jugs with flavour 1 and 3 but
        // there exists a preference for flavour 2. flavour 2 still exists but has no volume
        int amount_of_flavours = 0;
        for (Person person : PersonList) {
            amount_of_flavours = Integer.max(amount_of_flavours, Collections.max(person.getPreference()));
        }

        ArrayList<Integer> NumberOfJugsPerFlavour = new ArrayList<>();
        for (int i = 0; i<amount_of_flavours; i++) NumberOfJugsPerFlavour.add(0);
        for (Jugs jug : JugsList) {
            if (jug.getVolume()>0) NumberOfJugsPerFlavour.set(jug.getFlavor()-1, NumberOfJugsPerFlavour.get(jug.getFlavor()-1) + 1);
        }

        System.out.println("List of Jugs:");
        for (Jugs jugs : JugsList){
            System.out.print(jugs.toString());
        }
        System.out.println();
        System.out.println("List of people:");
        for (Person person : PersonList){
            System.out.print(person.toString());
        }

        ArrayList<Jugs> sortedJugsList = UtilityFunctions.sortListofJugsbyVolume(JugsList);

        for(Person person : PersonList){
            person.flavoursAndWeight(amount_of_flavours);
        }

        for (Jugs jug : sortedJugsList) {

            HashMap<Integer, Integer> personId_flavourweight = new HashMap<>();

            for (Person person : PersonList) {

                for (int i = 0; i < person.getPreference().size(); i++) {

                    if (person.getDrankFlavour(jug.getFlavor()) == false){
                        if (jug.getFlavor() == person.getPreference().get(i)) {
                         if (i == 0) {
                                personId_flavourweight.put(person.getID(), person.getWeight().get(jug.getFlavor()-1) + 1 + amount_of_flavours);
                            } else  {
                                personId_flavourweight.put(person.getID(), person.getWeight().get(jug.getFlavor()-1));
                            }
                        }
                    }

                }

            }

            LinkedHashMap<Integer, Integer> sortedMap = UtilityFunctions.sortHashMap(personId_flavourweight);
            String KeySet = String.valueOf(sortedMap.keySet());
            String[] KeySetUsable = KeySet.split(", ");
            KeySetUsable[0] = KeySetUsable[0].replace("[", "");
            KeySetUsable[KeySetUsable.length - 1] = KeySetUsable[KeySetUsable.length - 1].replace("]", "");
            //this is for ID's of people in sorted map
            int ratio = sortedMap.size() / NumberOfJugsPerFlavour.get( jug.getFlavor()-1 );
            for (int i = 0; i < ratio; i++){
                int portion = jug.getVolume()/(ratio-i);
                int ID = Integer.parseInt(KeySetUsable[i]) - 1;
                PersonList.get(ID).addPortion(jug.getID(), portion);
                PersonList.get(ID).addSatisfaction(portion, PersonList.get(ID).getWeight().get(jug.getFlavor()-1));
                if (PersonList.get(ID).getPreference().get(0) == jug.getFlavor()) {
                    PersonList.get(ID).lowerDissatisfaction(portion);
                }
                PersonList.get(ID).drankflavour(jug.getFlavor());
                jug.changeVolume(portion);
            }

            NumberOfJugsPerFlavour.set(jug.getFlavor()-1, NumberOfJugsPerFlavour.get(jug.getFlavor()-1) - 1);
        }


        System.out.println("\nPortions: ");
        int dissatisfaction_final=0, satisfaction_final=0;
        for(Person person : PersonList){
            System.out.print(person.toStringFinish());
            satisfaction_final += person.getSatisfaction();
            dissatisfaction_final += person.getDissatisfaction();
        }
        System.out.println("Satisfaction: "+ satisfaction_final);
        System.out.println("Dissatisfaction: "+ dissatisfaction_final);

    }
}


