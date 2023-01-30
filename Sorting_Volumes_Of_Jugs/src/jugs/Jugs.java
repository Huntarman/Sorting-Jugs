package jugs;

public class Jugs {
    private int ID;
    private int flavor;
    public int volume;
    public Jugs(int ID_file, int flavor_file, int volume_file){
        this.ID = ID_file;
        this.flavor = flavor_file;
        this.volume = volume_file;
    }
    public void changeVolume (int portion){this.volume -= portion;}
    public int getID(){return this.ID;}
    public int getFlavor(){return  this.flavor;}
    public int getVolume(){return  this.volume;}
    public String toString(){
        String string = "ID:" + this.ID + "|"+"Flavour: " + this.flavor + "|" + "Volume: " + this.volume + "\n";
        return string;
    }
}
