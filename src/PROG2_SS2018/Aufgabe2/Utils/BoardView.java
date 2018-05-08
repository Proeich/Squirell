package PROG2_SS2018.Aufgabe2.Utils;

import PROG2_SS2018.Aufgabe2.Entities.Environment.EntityType;

public interface BoardView {

    public EntityType getEntityType(Vector2 pos);
    public Vector2 getSize();

}
