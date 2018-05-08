package PROG2_SS2018.Aufgabe2.Utils;

import PROG2_SS2018.Aufgabe2.Entities.Environment.*;
import PROG2_SS2018.Aufgabe2.Entities.Player.MasterSquirell;
import PROG2_SS2018.Aufgabe2.Entities.Player.MiniSquirrel;
import PROG2_SS2018.Aufgabe2.Entities.Player.Squirrel;
import PROG2_SS2018.Aufgabe2.Utils.Engine.Engine;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Random;

public class Board implements BoardView,EntityContext {


    private BoardConfig boardConfig;
    private EntitySet entitySet;
    private FlattenedBoard flattenedBoard;
    private int timer;
    private Engine engine;

    public Board(EntitySet entitySet) {
        this.engine = new Engine(this);
        timer = 4;
        this.entitySet = entitySet;
        boardConfig = new BoardConfig();
        flattenedBoard = new FlattenedBoard();
        setWalls();
    }

    public String show() {
        StringBuilder sb = new StringBuilder();
        sb.append("Round: ").append(timer).append('\n');
        for (Entity e : entitySet.getSet()) {

            if (e.getType() != EntityType.Wall) {
                sb.append("Name: ")
                        .append(e.getName())
                        .append("Energie: ")
                        .append(e.getEnergie())
                        .append("Lifes: ")
                        .append(e.getLifes())
                        .append("Stunned?: ")
                        .append(e.getStunned())
                        .append('\n');
            }
        }
        sb.append(flattenedBoard.flatten(this));

        return sb.toString();
    }

    public void superStep() {
        this.engine = new Engine(this);
        try {
            for (Entity entity : entitySet.getSet()) {
                testDead(entity);
            }
        }catch (Exception e1){e1.printStackTrace();}

        for (Entity e : entitySet.getSet()) {
            if (e.getStunned()) {
                e.setStunned(false);
            } else {
                switch (EntityType.getType(e.getID())) {
                    case GoodBeast:
                        if (timer % 4 == 0) {
                            tryMove((GoodBeast) e);
                        }
                        break;
                    case BadBeast:
                        if (timer % 4 == 0) {
                            tryMove((BadBeast) e);
                        }
                        break;
                    case MiniSquirrel:
                        if (timer % 4 == 0) {
                            tryMove((MiniSquirrel) e);
                        }
                        break;
                    case MasterSquirrel:
                        engine.setMasterSquirell((MasterSquirell) e);
                        engine.run();

                        break;
                }
            }
        }
    }

    private void testDead(Entity entity){
            switch(entity.getType()){
                case Wall:
                    return;
                case BadBeast:
                    if(entity.getLifes() <= 0){
                        killAndReplace(entity);
                    }
                    return;
                case GoodBeast:
                    if(entity.getLifes() <= 0){
                        killAndReplace(entity);
                    }
                    return;
                case GoodPLant:
                    if(entity.getLifes() <= 0){
                        killAndReplace(entity);
                    }
                    return;
                case BadPLant:
                    if(entity.getLifes() <= 0){
                        killAndReplace(entity);
                    }
                    return;
                case MasterSquirrel:
                    if(entity.getEnergie() <= 0){
                        System.exit(42);
                    }
                    return;
                case MiniSquirrel:
                    if(entity.getEnergie() <= 0){
                        kill(entity);
                    }
                    return;
            }
        }

    private Entity[][] getBoard() {
        return flattenedBoard.getBoard(entitySet, boardConfig.getSize());
    }

    public EntitySet getEntitySet() {
        return entitySet;
    }

    public void setEntitySet(EntitySet entitySet) {
        this.entitySet = entitySet;
    }

    private void setWalls() {

        for (int i = 0; i < boardConfig.getSize().getX(); i++) {
            entitySet.insertIn(new Wall(new Vector2(i, 0)));
        }

        for (int i = 0; i < boardConfig.getSize().getY(); i++) {
            entitySet.insertIn(new Wall(new Vector2(0, i)));
        }

        for (int i = boardConfig.getSize().getY() - 1; i > 0; i--) {
            entitySet.insertIn(new Wall(new Vector2(boardConfig.getSize().getX() - 1, i)));
        }

        for (int i = boardConfig.getSize().getX() - 1; i > 0; i--) {
            entitySet.insertIn(new Wall(new Vector2(i, boardConfig.getSize().getY() - 1)));
        }

    }

    public BoardConfig getBoardConfig() {
        return boardConfig;
    }

    @Override
    public Vector2 getSize() {
        return boardConfig.getSize();
    }

    @Override
    public void tryMove(MiniSquirrel miniSquirrel) {
        for (Entity e : entitySet.getSet()) {
            if (e == miniSquirrel) {
                Vector2 mmoveVector;
                while(true) {
                    Random rn = new Random();

                    mmoveVector = posNeg(new Vector2(rn.nextInt(2), rn.nextInt(2)));
                    if(testOutrange(miniSquirrel,mmoveVector)){
                        break;
                    }
                }
                if (!testCollide(e, mmoveVector)) {
                    e.move(mmoveVector);
                }
            }
        }
    }

    @Override
    public void tryMove(GoodBeast goodBeast) {
        for (Entity e : entitySet.getSet()) {
            if (e == goodBeast) {
                Vector2 mmoveVector;
                while(true) {
                    Random rn = new Random();
                    mmoveVector = posNeg(new Vector2(rn.nextInt(2), rn.nextInt(2)));
                    if(testOutrange(goodBeast,mmoveVector)){
                        break;
                    }
                }
                if (!testCollide(e, mmoveVector)) {
                    e.move(mmoveVector);
                }
            }
        }
    }

    @Override
    public void tryMove(BadBeast badBeast) {
        for (Entity e : entitySet.getSet()) {
            Vector2 mmoveVector;
            if (e == badBeast) {
                try {
                    Vector2 movement = nearestPlayerEntity(badBeast.getPos(), 5).getPos();
                    movement = getMovement(badBeast.getPos(),movement);
                    mmoveVector = calcSquiPos(badBeast,movement);
                    if (mmoveVector != null) {
                        if (!testCollide(e, mmoveVector)) {
                            e.move(mmoveVector);
                        }
                        return;
                    }
                } catch (Exception ignored) {
                    while(true) {
                        Random rn = new Random();
                        mmoveVector = posNeg(new Vector2(rn.nextInt(2), rn.nextInt(2)));
                        if(testOutrange(badBeast,mmoveVector)){
                            break;
                        }
                    }
                    Random rn = new Random();
                    mmoveVector = posNeg(new Vector2(rn.nextInt(2), rn.nextInt(2)));
                    if (!testCollide(e, mmoveVector)) {
                        e.move(mmoveVector);
                    }
                }
            }
        }
    }

    private Vector2 getMovement(Vector2 badBeastV,Vector2 squirrelV){
        Vector2 v2 = new Vector2(0,0);
        if (badBeastV.getX() > squirrelV.getX()){
            v2.setX(-1);
        }
        if(badBeastV.getY() > squirrelV.getY()){
            v2.setY(-1);
        }
        if (badBeastV.getX() < squirrelV.getX()){
            v2.setX(1);
        }
        if(badBeastV.getY() < squirrelV.getY()){
            v2.setY(1);
        }
        return v2;

    }

    private Vector2 posNeg(Vector2 in){
        Random rn = new Random();

        if(rn.nextInt(2) == 0){
            return new Vector2(in.getX()*-1,in.getY()*-1);
        }
        return new Vector2(in.getX()*1,in.getY()*1);
    }

    private Vector2 calcSquiPos(BadBeast badBeast, Vector2 movement){

        Vector2 mmoveVector = new Vector2(0,0);
        if(movement.getY() > badBeast.getPos().getY() && movement.getX() > badBeast.getPos().getX()){
            mmoveVector = new Vector2(1,1);
        }
        if(movement.getY() < badBeast.getPos().getY() && movement.getX() < badBeast.getPos().getX()){
            mmoveVector = new Vector2(-1,-1);
        }
        if(movement.getY() < badBeast.getPos().getY() && movement.getX() > badBeast.getPos().getX()){
            mmoveVector = new Vector2(-1,1);
        }
        if(movement.getY() > badBeast.getPos().getY() && movement.getX() < badBeast.getPos().getX()){
            mmoveVector = new Vector2(1,-1);
        }
        return mmoveVector;

    }

    @Override
    public void tryMove(MasterSquirell masterSquirell) {
        Vector2 mmoveVector;
        while(true) {
            mmoveVector = ((MasterSquirell) masterSquirell).askMovement();
            if(testOutrange(masterSquirell,mmoveVector)){
                break;
            }
        }

        if (!testCollide(masterSquirell, mmoveVector)) {
            masterSquirell.move(mmoveVector);
        }
    }

    @Override
    public Squirrel nearestPlayerEntity(Vector2 pos, int distance) {
        Entity[][] field = this.flattenedBoard.getBoard(entitySet, this.getSize());
        try {
            for (int i = 0; i < distance; i++) {
                for (int k = 0; k >= distance; k++) {
                    if (field[pos.getY() + i][pos.getX() + k].getType() == EntityType.MasterSquirrel | field[pos.getY() + i][pos.getX() + k].getType() == EntityType.MiniSquirrel) {
                        return (Squirrel) field[pos.getY() + i][pos.getX() + k];
                    }
                }
            }
            for (int i = 0; i < distance; i++) {
                for (int k = 0; k >= distance; k++) {
                    if (field[pos.getY() - i][pos.getX() - k].getType() == EntityType.MasterSquirrel | field[pos.getY() + i][pos.getX() + k].getType() == EntityType.MiniSquirrel) {
                        return (Squirrel) field[(pos.getY() - i)][(pos.getX() - k)];
                    }
                }
            }
        } catch (Exception ignored) {
            return null;
        }

        return null;
    }

    @Override
    public void kill(Entity entity) {
        entitySet.deleteIn(entity);
    }

    @Override
    public void killAndReplace(Entity entity) {
        entitySet.getEntity(entity).revive();
    }

    @Override
    public EntityType getEntityType(Vector2 pos) {
        return EntityType.getType(entitySet.getEntity(pos).getID());
    }

    public boolean testCollide(Entity entity, Vector2 moveVector) {
        Entity[][] buf = getBoard();
        if(entity.getPos().getY() + moveVector.getY() >= boardConfig.getSize().getY() | entity.getPos().getX() + moveVector.getX() >= boardConfig.getSize().getY()){
            return false;
        }
        if(moveVector.getY() == 0 && moveVector.getX() == 0){
            return false;
        }

        if (buf[entity.getPos().getY() + moveVector.getY()][entity.getPos().getX() + moveVector.getX()] != null) {
            switch (buf[entity.getPos().getY() + moveVector.getY()][entity.getPos().getX() + moveVector.getX()].getType()) {
                case GoodPLant:
                    if (entity.getType() == EntityType.MiniSquirrel) {
                        entitySet.collideAsMiniSquirell(entitySet, (MiniSquirrel) entity, buf[entity.getPos().getY() + moveVector.getY()][entity.getPos().getX() + moveVector.getX()]);
                        return true;
                    }
                    entitySet.collideWithFriendly(entitySet, entity, buf[entity.getPos().getY() + moveVector.getY()][entity.getPos().getX() + moveVector.getX()]);
                    return true;
                case BadPLant:
                    if (entity.getType() == EntityType.MiniSquirrel) {
                        entitySet.collideAsMiniSquirell(entitySet, (MiniSquirrel) entity, buf[entity.getPos().getY() + moveVector.getY()][entity.getPos().getX() + moveVector.getX()]);
                        return true;
                    }
                    entitySet.collideWithHarmful(entitySet, buf[entity.getPos().getY() + moveVector.getY()][entity.getPos().getX() + moveVector.getX()], entity);
                    return true;
                case GoodBeast:
                    if (entity.getType() == EntityType.MiniSquirrel) {
                        entitySet.collideAsMiniSquirell(entitySet, (MiniSquirrel) entity, buf[entity.getPos().getY() + moveVector.getY()][entity.getPos().getX() + moveVector.getX()]);
                        return true;
                    }
                    entitySet.collideWithFriendly(entitySet, entity, buf[entity.getPos().getY() + moveVector.getY()][entity.getPos().getX() + moveVector.getX()]);
                    return true;
                case BadBeast:
                    if (entity.getType() == EntityType.MiniSquirrel) {
                        entitySet.collideAsMiniSquirell(entitySet, (MiniSquirrel) entity, buf[entity.getPos().getY() + moveVector.getY()][entity.getPos().getX() + moveVector.getX()]);
                        return true;
                    }
                    entitySet.collideWithHarmful(entitySet, buf[entity.getPos().getY() + moveVector.getY()][entity.getPos().getX() + moveVector.getX()], entity);
                    return true;
                case MiniSquirrel:
                    if (entity.getType() == EntityType.MiniSquirrel) {
                        return true;
                    }
                case MasterSquirrel:
                case Wall:
                    entitySet.collideWithWall(entity);
                    return true;
                default:
                    return false;
            }
        }
        return false;
    }

    public void spawnMiniSquirell(MasterSquirell masterSquirell, int energie){
        MiniSquirrel buf = new MiniSquirrel(masterSquirell, energie);
        Random rn = new Random();
        int x = rn.nextInt(2);
        int y = rn.nextInt(2);
        buf.setPos(new Vector2(masterSquirell.getPos().getX(),masterSquirell.getPos().getY()));
        while(!testCollide(buf, new Vector2(x,y))){
            x = rn.nextInt(2);
            y = rn.nextInt(2);
        }
        this.entitySet.insertIn(buf, new Vector2(x,y));
    }

    private boolean testOutrange(Entity entity, Vector2 move) {
        if (entity.getPos().getY() + move.getY() >= boardConfig.getSize().getY()) {
            return false;
        }
        if (entity.getPos().getX() + move.getX() >= boardConfig.getSize().getX()) {
            return false;
        }
        return true;
    }
}

class BoardConfig {

    //to be written to File
    private Vector2 size;
    private int wallCount;

    public BoardConfig(){
        try {
            readFromFile();
        } catch (Exception e) {
            size = new Vector2(10,10);
            wallCount = 20;
        }
    }

    public void writeToFile(){
    }

    private void readFromFile() throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("Test"));
        StringBuilder sb = new StringBuilder();
        String k = br.readLine();
        size = new Vector2(Integer.parseInt(k.split(", ")[0]),Integer.parseInt(k.split(", ")[1]));
        k = br.readLine();
        wallCount = Integer.parseInt(k);
        br.close();
    }

    public int getWallCount() {
        return wallCount;
    }

    public void setWallCount(int wallCount) {
        this.wallCount = wallCount;
    }

    public Vector2 getSize() {
        return size;
    }

    public void setSize(Vector2 size) {
        this.size = size;
    }

}

class FlattenedBoard {

    public FlattenedBoard(){

    }

    public String flatten(Board board){
        StringBuilder sb = new StringBuilder();
        String[][] buffer = new String[board.getBoardConfig().getSize().getX()][board.getBoardConfig().getSize().getY()];
        int index1 = 0;
        int index2 = 0;

        for(Entity entity : board.getEntitySet().getSet()){
            buffer[entity.getPos().getX()][entity.getPos().getY()] = String.valueOf(entity.getPlaySymbol());
        }
        for(String[] s : buffer){
            index2=0;
            for(String ss : s){
                if(ss == null){
                    buffer[index1][index2] = " ";
                }
                index2++;
            }
            index1++;
        }

        index1 = 0;
        index2 = 0;
        for(String s[] : buffer){
            for (String ss : s){
                sb.append(ss).append(" ");
                index2++;
            }
            sb.append('\n');
            index1 ++;
        }
        return sb.toString();
    }

    public Entity[][] getBoard(EntitySet entitySet,Vector2 size){
        Entity[][] Buffer = new Entity[size.getY()][size.getX()];

        for(Entity e : entitySet.getSet()){
            Buffer[e.getPos().getY()][e.getPos().getX()] = e;
        }
        return Buffer;
    }



}