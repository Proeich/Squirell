package PROG2_SS2018.Aufgabe2.Utils.Engine.Commands;

import PROG2_SS2018.Aufgabe2.Entities.Environment.Entity;
import PROG2_SS2018.Aufgabe2.Entities.Environment.EntityType;
import PROG2_SS2018.Aufgabe2.Entities.Player.MasterSquirell;
import PROG2_SS2018.Aufgabe2.Entities.Player.MiniSquirrel;
import PROG2_SS2018.Aufgabe2.Utils.Engine.Engine;
import PROG2_SS2018.Aufgabe2.Utils.EntitySet;
import PROG2_SS2018.Aufgabe2.Utils.Vector2;

import java.util.Random;

public class miniSquirellSpawn_Command extends MainCommand {

    public miniSquirellSpawn_Command(Engine engine){
        super(engine);
    }

    @Override
    public void run(String[] args) {
        int temp;

        try{
            temp = Integer.parseInt(args[0]);
        }catch (Exception e1){
            this.engine.writeln("Ungültiges Argument!");
            return;
        }

        MiniSquirrel buf = new MiniSquirrel(this.engine.getMasterSquirell(), Integer.parseInt(args[0]));
        Random rn = new Random();
        int x = rn.nextInt(2);
        int y = rn.nextInt(2);
        buf.setPos(new Vector2(this.engine.getMasterSquirell().getPos().getX(),this.engine.getMasterSquirell().getPos().getY()));

        EntitySet entitySet = this.engine.getBoard().getEntitySet();
        entitySet.getSet().add(buf);

        this.engine.getBoard().setEntitySet(entitySet);




        //this.engine.getBoard().spawnMiniSquirell(this.engine.getMasterSquirell(), temp);
        this.engine.setEnd(true);
    }
}
