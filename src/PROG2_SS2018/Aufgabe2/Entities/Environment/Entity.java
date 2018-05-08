package PROG2_SS2018.Aufgabe2.Entities.Environment;

import PROG2_SS2018.Aufgabe2.Utils.Board;
import PROG2_SS2018.Aufgabe2.Utils.Vector2;

import java.util.Random;

public abstract class Entity {

    protected String name;
    protected int ID;
    protected int Energie;
    protected Vector2 pos;
    protected int lifes;
    protected char playSymbol;
    protected boolean stunned;

    public Entity(){
        this.stunned = false;
        Random rn = new Random();
        int k = rn.nextInt(5)+1;
        int p = rn.nextInt(5)+1;
        pos = new Vector2(k,p);
    }

    public void updateEnergy(int inp){
        Energie+=inp;
    }

    public void setFirstPos(Board board){
        Random rn = new Random();
        int n = rn.ints(2,board.getSize().getY()).findFirst().getAsInt() - 1;
        int nn = rn.ints(2,board.getSize().getX()).findFirst().getAsInt() - 1;
        pos = new Vector2(n,nn);
    }

    public void nextStep(){
        Random rn = new Random();
        switch(rn.nextInt(9)){
            case 0:
                move(new Vector2(0,1));
                return;
            case 1:
                move(new Vector2(0,-1));
                return;
            case 2:
                move(new Vector2(1,0));
                return;
            case 3:
                move(new Vector2(-1,0));
                return;
            case 4:
                move(new Vector2(1,1));
                return;
            case 5:
                move(new Vector2(1,-1));
                return;
            case 6:
                move(new Vector2(-1,1));
                break;
            case 7:
                move(new Vector2(-1,-1));
                return;
            case 8:
                move(new Vector2(0,0));
                return;

        }
    }

    public void move(Vector2 in){

        this.pos.setX(this.pos.getX() + in.getX());
        this.pos.setY(this.pos.getY() + in.getY());

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("ID: " + ID + '\n');
        sb.append("Name: " + name + '\n');
        sb.append("Energie: " + Energie + '\n');
        sb.append("X: " + pos.getX() + "; Y: " + pos.getY() + '\n');
        return sb.toString();
    }

    public void getHit(){
        this.lifes -=1;
    }

    public String getName() {
        return name;
    }

    public Vector2 getPos() {
        return pos;
    }

    public int getID() {
        return ID;
    }

    public int onHit(){
        return 0;
    }

    public int getEnergie() {
        return Energie;
    }

    public int getLifes() {
        return lifes;
    }

    public EntityType getType(){
        return EntityType.getType(this.getID());
    }

    public char getPlaySymbol() {
        return playSymbol;
    }

    public void setStunned(boolean stunned) {
        this.stunned = stunned;
    }

    public boolean getStunned(){
        return stunned;
    }

    public void revive(){
        this.stunned = false;
        Random rn = new Random();
        int k = rn.nextInt(5)+1;
        int p = rn.nextInt(5)+1;
        pos = new Vector2(k,p);
    }

    public void setPos(Vector2 pos) {
        this.pos = pos;
    }
}
