package PROG2_SS2018.Aufgabe2;

import PROG2_SS2018.Aufgabe2.Entities.Environment.*;
import PROG2_SS2018.Aufgabe2.Entities.Player.*;
import PROG2_SS2018.Aufgabe2.Utils.*;
import PROG2_SS2018.Aufgabe2.Utils.Engine.Engine;

import java.util.Scanner;

public class Game{

    private EntitySet set;
    private Board board;
    private Engine engine;

    public Game(){}

    public Game(Entity[] in){
        set = new EntitySet();
        board = new Board(this.set);
        for(Entity en : in){
            en.setFirstPos(board);
            set.insertIn(en);
        }
    }

    public void run(){
        while(true){
            render();
            update();
            processInput();
        }
    }

    private void update(){
        board.superStep();
    }

    private void render(){
        System.out.println(board.show());
    }

    private boolean processInput(){
        return true;
    }
}
