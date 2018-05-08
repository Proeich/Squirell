package PROG2_SS2018.Aufgabe2.Utils.Engine;

import PROG2_SS2018.Aufgabe2.Utils.Engine.Commands.MainCommand;

public class CompiledValue {

    private MainCommand mainCommand;
    private String argument;

    public CompiledValue(MainCommand mainCommand, String argument){
        this.mainCommand = mainCommand;
        this.argument = argument;
    }

    public void run(String[] args){
        this.mainCommand.run(args);
    }

    public String getArgument() {
        return argument;
    }

    public MainCommand getMainCommand() {
        return mainCommand;
    }
}
