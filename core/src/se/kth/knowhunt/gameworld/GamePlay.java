package se.kth.knowhunt.gameworld;

import se.kth.knowhunt.gameobjects.Autonomous;
import se.kth.knowhunt.gameobjects.Boy;

import java.util.LinkedList;

/**
 * Created by Aiham on 03/13/17.
 */
public class GamePlay {

    private LinkedList<Autonomous> obstacles;
    private LinkedList<Autonomous> obstaclesv;

    private Boy boy;
    private int inc = 0, maxObstaclesCount = 0;

    public GamePlay(Boy boy) {
        obstacles = new LinkedList<Autonomous>();
        obstaclesv=new LinkedList<Autonomous>();
        this.boy = boy;
    }




    public LinkedList<Autonomous> getObstacles() { return obstacles; }
    public LinkedList<Autonomous> getObstaclesv() { return obstaclesv; }

}
