package se.kth.knowhunt.gameobjects;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import se.kth.knowhunt.screens.GameScreen;
import se.kth.knowhunt.shared.Directions;



public class Node {
    public Vector2 v;
    public Directions dir;
    private Circle boundingCircle;
    private float circleRadius = 3.5f;

    public Node(Vector2 v, Directions d) {
        this.v = v;
        this.dir = d;
        boundingCircle = new Circle();
        boundingCircle.set(v.x, v.y, circleRadius);
    }

    /* x, y getter and setters */
    public float getX() { return v.x; }
    public float getY() { return v.y; }
    public void setX(float x) {
        v.x = x;
        if (v.x > GameScreen.GAME_WIDTH) { v.x = 0; }
        if (v.x < 0) { v.x = GameScreen.GAME_WIDTH; }
        boundingCircle.set(v.x, v.y, circleRadius);
    }
    public void setY(float y) {
        v.y = y;
        if (v.y > GameScreen.GAME_HEIGHT) { v.y = 0; }
        if (v.y < 0) { v.y = GameScreen.GAME_HEIGHT; }
        boundingCircle.set(v.x, v.y, circleRadius);
    }

    /* Direction getter and setter */
    public Directions getDir() { return dir; }
    public void setDir(Directions d) { dir = d; }

    public Circle getBoundingCircle() { return boundingCircle; }
}

