package PROG2_SS2018.Aufgabe2.Utils;

import PROG2_SS2018.Aufgabe2.Entities.Environment.*;
import PROG2_SS2018.Aufgabe2.Entities.Player.MasterSquirell;
import PROG2_SS2018.Aufgabe2.Entities.Player.MiniSquirrel;
import PROG2_SS2018.Aufgabe2.Entities.Player.Squirrel;

public interface EntityContext {

    public Vector2 getSize();
    public void tryMove(MiniSquirrel miniSquirrel);
    public void tryMove(GoodBeast goodBeast);
    public void tryMove(BadBeast BadBeast);
    public void tryMove(MasterSquirell masterSquirell);
    public Squirrel nearestPlayerEntity(Vector2 pos, int distance);

    public void kill(Entity entity);
    public void killAndReplace(Entity entity);
    public EntityType getEntityType(Vector2 pos);

}
