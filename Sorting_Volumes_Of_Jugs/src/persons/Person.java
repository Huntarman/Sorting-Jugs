package persons;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Person {
    private int ID;
    private ArrayList<Integer> preference = new ArrayList<>();
    private ArrayList<Integer> weightPerFlavour = new ArrayList<>();
    public int dissatisfaction = 400;
    public int satisfaction = 0;
    private ArrayList<Boolean> drankFlavour = new ArrayList<>();
    public HashMap<Integer,Integer> Portions= new HashMap<>();
    public Person(int ID_file, ArrayList<Integer> preferences){

        this.ID = ID_file;
        this.preference = preferences;
    }
    public void flavoursAndWeight (int amount_of_flavours){
        for (int i=0 ; i<amount_of_flavours; i++){
            this.weightPerFlavour.add(0);
            this.drankFlavour.add(false);
        }
        for (int i = 0 ; i<this.preference.size(); i++){
            this.weightPerFlavour.set(this.preference.get(i)-1 , preference.size()-i);
        }
    }
    public String toString(){
        String string = "ID:" + this.ID + "|" + "Preferences: " + this.preference + "\n";
        return string;
    }
    public void drankflavour (int NumFlavour){
        this.drankFlavour.set(NumFlavour - 1, true);
    }
    public void addPortion(int Jug_Id, int Volume){
        this.Portions.put(Jug_Id, Volume);
    }
    public void addSatisfaction (int volume, int weight){
        this.satisfaction += volume * weight;
    }
    public void lowerDissatisfaction (int volume){
        this.dissatisfaction = Integer.max( 0 , this.dissatisfaction-volume);
    }
    public int getID(){return this.ID;}
    public ArrayList<Integer> getPreference(){return this.preference;}
    public ArrayList<Integer> getWeight(){return this.weightPerFlavour;}
    public int getDissatisfaction(){return this.dissatisfaction;}
    public int getSatisfaction(){return this.satisfaction;}
    public Boolean getDrankFlavour(int which) {return this.drankFlavour.get(which-1);}
     public String toStringFinish(){
        String string = "ID:" + this.ID + "|Portions: " + this.Portions + "\n";
         return string;
     }
}
