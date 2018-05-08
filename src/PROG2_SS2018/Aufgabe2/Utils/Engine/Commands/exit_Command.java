package PROG2_SS2018.Aufgabe2.Utils.Engine.Commands;

import PROG2_SS2018.Aufgabe2.Utils.Engine.Engine;

public class exit_Command extends MainCommand {

    public exit_Command(Engine engine){
        super(engine);
    }

    @Override
    public void run(String[] args) {
        System.exit(42);
    }
}
