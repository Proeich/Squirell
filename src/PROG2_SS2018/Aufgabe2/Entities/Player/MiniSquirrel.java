package PROG2_SS2018.Aufgabe2.Entities.Player;

public class MiniSquirrel extends Squirrel {

    private MasterSquirell master;
    private int savedEnergy;

    public MiniSquirrel(MasterSquirell master, int Energie) {
        this.savedEnergy = 0;
        this.name = "MiniSquirrel";
        this.playSymbol = 'm';
        this.ID = 10;
        this.master = master;
        this.Energie = Energie;
    }


    public MasterSquirell getMaster() {
        return master;
    }

    public void setMaster(MasterSquirell master) {
        this.master = master;
    }

    public void setSavedEnergy(int savedEnergy) {
        this.savedEnergy = savedEnergy;
    }

    public int getSavedEnergy() {
        return savedEnergy;
    }

    public void resetEnergy(){
        this.savedEnergy = 0;
    }
}
