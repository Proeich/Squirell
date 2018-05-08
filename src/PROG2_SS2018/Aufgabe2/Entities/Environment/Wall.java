package PROG2_SS2018.Aufgabe2.Entities.Environment;

import PROG2_SS2018.Aufgabe2.Utils.Vector2;

public class Wall extends Entity {

    public Wall(){
        super();
        this.playSymbol = 'W';
        this.lifes = -1;
        this.ID = 5;
        this.name = "Wall";
        this.Energie = -10;
    }

    public Wall(Vector2 pos){
        super();
        this.ID = 5;
        this.name = "Wall";
        this.Energie = -10;
        this.pos = pos;
    }

    @Override
    public void nextStep() {

    }

    @Override
    public int onHit() {
        return 0;
    }

    @Override
    public void revive() {
        super.revive();
        this.playSymbol = 'W';
        this.lifes = -1;
        this.ID = 5;
        this.name = "Wall";
        this.Energie = -10;
    }
}
