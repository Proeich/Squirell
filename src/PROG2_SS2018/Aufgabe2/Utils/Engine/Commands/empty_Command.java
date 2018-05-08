package PROG2_SS2018.Aufgabe2.Utils.Engine.Commands;

import PROG2_SS2018.Aufgabe2.Utils.Engine.Engine;

public class empty_Command extends MainCommand {

    public empty_Command(Engine engine){
        super(engine);
    }

    @Override
    public void run(String[] args) {
        engine.writeln("Nothing!");
    }
}
