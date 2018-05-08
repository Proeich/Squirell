package PROG2_SS2018.Aufgabe2.Entities.Environment;

import PROG2_SS2018.Aufgabe2.Utils.Vector2;

public class GoodBeast extends Entity {

    public GoodBeast() {
        super();
        this.playSymbol = 'G';
        this.lifes = 1;
        this.ID = 3;
        this.name = "GoodBeast";
        this.Energie = 200;
    }

    @Override
    public int onHit() {
        return 15;
    }

    @Override
    public void revive() {
        super.revive();
        this.playSymbol = 'G';
        this.lifes = 1;
        this.ID = 3;
        this.name = "GoodBeast";
        this.Energie = 200;
    }
}
