package PROG2_SS2018.Aufgabe2.Utils.Engine.Commands;

import PROG2_SS2018.Aufgabe2.Entities.Environment.BadBeast;
import PROG2_SS2018.Aufgabe2.Entities.Environment.Entity;
import PROG2_SS2018.Aufgabe2.Entities.Environment.GoodBeast;
import PROG2_SS2018.Aufgabe2.Entities.Player.MasterSquirell;
import PROG2_SS2018.Aufgabe2.Game;
import PROG2_SS2018.Aufgabe2.Utils.Engine.Engine;

public class move_Command extends MainCommand {

        public move_Command(Engine engine){
            super(engine);
        }

        @Override
        public void run(String[] args) {
            this.engine.getBoard().tryMove(this.engine.getMasterSquirell());
            this.engine.setEnd(true);
        }
    }
