package PROG2_SS2018.Aufgabe2;

import PROG2_SS2018.Aufgabe2.Entities.Environment.BadBeast;
import PROG2_SS2018.Aufgabe2.Entities.Environment.Entity;
import PROG2_SS2018.Aufgabe2.Entities.Environment.GoodBeast;
import PROG2_SS2018.Aufgabe2.Entities.Environment.GoodPlant;
import PROG2_SS2018.Aufgabe2.Entities.Player.MasterSquirell;
import PROG2_SS2018.Aufgabe2.Utils.Engine.Engine;

public class Launcher {

    public static void main(String[] args) {
        Game game = new Game( new Entity[]{new BadBeast(), new GoodPlant(),new GoodBeast(), new MasterSquirell(0)});
        game.run();
    }


}
