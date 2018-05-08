package PROG2_SS2018.Aufgabe2.Entities.Environment;

public class BadBeast extends Entity {

    public BadBeast() {
        super();
        this.playSymbol = 'B';
        this.lifes = 6;
        this.ID = 1;
        this.Energie = -150;
        this.name = "BadBeast";
    }

    @Override
    public int onHit() {
        this.lifes -=1;
        return getEnergie();
    }

    @Override
    public void revive() {
        super.revive();
        this.playSymbol = 'B';
        this.lifes = 6;
        this.ID = 1;
        this.Energie = -150;
        this.name = "BadBeast";
    }
}
