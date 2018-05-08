package PROG2_SS2018.Aufgabe2.Utils.Engine.Commands;

import PROG2_SS2018.Aufgabe2.Utils.Engine.Engine;

public class help_Command extends MainCommand {

        public help_Command(Engine engine){
            super(engine);
        }

        @Override
        public void run(String[] args) {
            //Get CommandList
            StringBuilder sb = new StringBuilder();
            for(String s : this.engine.getHashes()){
                sb.append(s).append(" ").append(this.engine.getHashe(s).getArgument()).append('\n');
            }
            this.engine.writeln(sb.toString());
        }
    }
