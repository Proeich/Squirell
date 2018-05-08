package PROG2_SS2018.Aufgabe2.Utils.Engine.Commands;

import PROG2_SS2018.Aufgabe2.Utils.Engine.Engine;

public abstract class MainCommand {

    protected Engine engine;
    public MainCommand(Engine engine){
        this.engine = engine;
    }

    public void run(String[] args){}


}
