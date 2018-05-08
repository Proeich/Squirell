package PROG2_SS2018.Aufgabe2.Utils;
import PROG2_SS2018.Aufgabe2.Entities.Environment.Entity;
import PROG2_SS2018.Aufgabe2.Entities.Environment.EntityType;
import PROG2_SS2018.Aufgabe2.Entities.Player.MasterSquirell;
import PROG2_SS2018.Aufgabe2.Entities.Player.MiniSquirrel;

import java.util.ArrayList;
import java.util.List;

public class EntitySet {

    private List<Entity> set = new ArrayList<>();

    public void insertIn(Entity in){
        set.add(in);
    }

    public void insertIn(Entity in, Vector2 pos){
        Entity on = in;
        in.setPos(pos);
        set.add(on);
    }


    public void insertIn(Entity in, int index){
        set.add(index, in);
    }

    public void deleteIn(Entity in){
        set.remove(in);
    }

    public void setSize(Vector2 size){
    }

    public Entity getEntity(Vector2 pos){
        for(Entity e: set){
            if(e.getPos() == pos){
                return e;
            }
        }
        return null;
    }

    public Entity getEntity(Entity entity){
        for(Entity e: set){
            if(e == entity){
                return e;
            }
        }
        return null;
    }

    public void collideWithFriendly(EntitySet entitySet ,Entity movingEnt, Entity hittingEnt){
        entitySet.getEntity(movingEnt).updateEnergy(hittingEnt.onHit());
        entitySet.getEntity(hittingEnt).getHit();
    }

    public void collideWithHarmful(EntitySet entitySet, Entity hittedEnty, Entity movingEnty){
        entitySet.getEntity(movingEnty).updateEnergy(hittedEnty.getEnergie());
        entitySet.getEntity(hittedEnty).getHit();
    }

    public void collideWithWall(Entity entity){
        entity.setStunned(true);
    }

    public void collideAsMiniSquirell(EntitySet entitySet, MiniSquirrel miniSquirrel, Entity entity){
        switch(entity.getType()){
            case MasterSquirrel:
                if(((MasterSquirell)entity).isMineSquirrel(miniSquirrel)){
                    entity.updateEnergy(miniSquirrel.getSavedEnergy());
                    miniSquirrel.resetEnergy();
                    return;
                }
                return;
            case MiniSquirrel:
                return;
            case BadBeast:
                collideWithHarmful(entitySet,entity,miniSquirrel);
                return;
            case Wall:
                collideWithWall(miniSquirrel);
                return;
            case GoodBeast:
                collideWithFriendly(entitySet,miniSquirrel,entity);
                return;
            case BadPLant:
                collideWithHarmful(entitySet,entity,miniSquirrel);
                return;
            case GoodPLant:
                collideWithFriendly(entitySet,miniSquirrel,entity);
                return;
                default:
        }
    }

    public List<Entity> getSet() {
        return set;
    }

    @Override
    public String toString() {
        StringBuilder sb  = new StringBuilder();
        sb.append("Inhalt des Sets: " + '\n');
        for(Entity en : set){
            sb.append(en.toString()).append('\n');
        }
        return sb.toString();
    }
}
