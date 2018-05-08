package PROG2_SS2018.Aufgabe2.Entities.Environment;

public class GoodPlant extends Entity {

    public GoodPlant(){
        super();
        this.lifes = 1;
        this.playSymbol = 'g';
        this.ID = 4;
        this.name = "GoodPlant";
        this.Energie = 100;
    }

    @Override
    public int onHit() {
        return 15;
    }

    @Override
    public void revive() {
        super.revive();
        this.lifes = 1;
        this.playSymbol = 'g';
        this.ID = 4;
        this.name = "GoodPlant";
        this.Energie = 100;
    }
}
