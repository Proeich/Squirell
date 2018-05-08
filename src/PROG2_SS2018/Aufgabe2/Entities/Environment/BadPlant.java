package PROG2_SS2018.Aufgabe2.Entities.Environment;

import PROG2_SS2018.Aufgabe2.Utils.Vector2;

public class BadPlant extends Entity{

    public BadPlant(){
        super();
        this.lifes = 1;
        this.playSymbol = 'b';
        this.ID = 2;
        this.name = "BadPlant";
        this.Energie = -100;
    }


    @Override
    public int onHit() {
        return -15;
    }

    @Override
    public void revive() {
        super.revive();
        this.lifes = 1;
        this.playSymbol = 'b';
        this.ID = 2;
        this.name = "BadPlant";
        this.Energie = -100;
    }

    @Override
    public void move(Vector2 in) {
    }
}
