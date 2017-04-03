package se.kth.knowhunt.gameworld;

import se.kth.knowhunt.gameobjects.*;
import se.kth.knowhunt.mazehelpers.AssetLoader;

import java.util.LinkedList;
import java.util.Random;


public class GameWorld {

    private Boy boy;
    private Goal end;
    public enum GameState { READY, RUNNING, GAMEOVER, HIGHSCORE }
    public boolean win;
    private GameState currentState;
    private GamePlay gp;

    private int midPointY;
    private Wall wall;
    private Wall wall2;
    private Wall wall3;
    private Wall2 wall4;
    private Wall2 wall5;



    public void generateMap (int blocksCount) {
        Wall wall = new Wall();
        Wall2 wall2;

        int y = 10;
        int x = 10;
        for (int i = 0; i < 7; i++) {
            int rand = (int) (Math.random() * 150);
            int rand2 = (int) (Math.random() * 150);
            for (int j = 0; j < 5; j++) {
                wall = new Wall(x+(x*j),y );
                gp.getObstacles().add(wall);
            }
            x+=60;
            wall2=new Wall2(y,wall.getX());
            gp.getObstaclesv().add(wall2);
            y+=60;
            wall2=new Wall2(y,wall.getX()+40);
            gp.getObstaclesv().add(wall2);

        }



        }




    public GameWorld(int midPointY) {
        this.midPointY = midPointY;
        currentState = GameState.READY;

        boy = new Boy(33, midPointY - 5, 15, 15, midPointY * 2);

        gp = new GamePlay(boy);
        end= new Goal();
        end.setNode(100,100);

        wall=new Wall();
        wall.setX(20);
        wall.setY(50);

        wall2= new Wall();
        wall2.setX(60);
        wall2.setY(80);

        wall3= new Wall();
        wall3.setX(50);
        wall3.setY(20);

        wall4= new Wall2();
        wall3.setX(60);
        wall3.setY(80);

        wall5= new Wall2();
        wall3.setX(50);
        wall3.setY(20);

generateMap(3);
   //    gp.getObstacles().add(wall);
     //  gp.getObstacles().add(wall2);
      // gp.getObstacles().add(wall3);
       //gp.getObstacles().add(wall4);
       //gp.getObstacles().add(wall5);


    }

    public void update(float delta) {
        switch (currentState) {
            case READY:
                break;
            case RUNNING:
                updateRunning(delta);
                break;
            default:
                break;
        }

    }


    public void updateRunning(float delta) {
        boy.update(delta);
        for(Autonomous a : gp.getObstacles()) {
            if (boy.collides(a)) { a.playSound();gameOver();return;}
        }

        for(Autonomous a : gp.getObstaclesv()) {
            if (boy.collides(a)) { a.playSound();gameOver();return;}
        }

        if (boy.collides(end)) {
            win=true; end.playSound();gameOver(); return;
        }
    }

    private void gameOver() {
        boy.die();
        AssetLoader.dead.play();
        currentState = GameState.GAMEOVER;
    }

    public Goal getEnd () {return end;}

    public void restart() {
        currentState = GameState.READY;
        boy.onRestart(midPointY - 5);
        currentState = GameState.READY;
    }

    public void start() { currentState = GameState.RUNNING; }


    public Boy getBoy() { return boy; }
    public LinkedList<Autonomous> getObstacles() { return gp.getObstacles(); }
    public LinkedList<Autonomous> getObstaclesv() { return gp.getObstaclesv(); }


    public boolean isReady() { return currentState == GameState.READY; }
    public boolean isGameOver() { return currentState == GameState.GAMEOVER; }
    public boolean isHighScore() { return currentState == GameState.HIGHSCORE; }
}
