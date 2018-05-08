package PROG2_SS2018.Aufgabe2.Entities.Environment;

import PROG2_SS2018.Aufgabe2.Entities.Player.MasterSquirell;

public enum EntityType {
    BadBeast, GoodBeast, GoodPLant, BadPLant, Wall, MiniSquirrel, MasterSquirrel,;

    public static EntityType getType(int ID){
        switch(ID){
            case 1:
                return BadBeast;
            case 2:
                return BadPLant;
            case 3:
                return GoodBeast;
            case 4:
                return GoodPLant;
            case 5:
                return Wall;
            case 0:
                return MasterSquirrel;
            case 10:
                return MiniSquirrel;
            default:
                return Wall;
        }
    }


}
