package se.kth.knowhunt.gameobjects;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import se.kth.knowhunt.mazehelpers.AssetLoader;


public class Wall2 extends Autonomous
{
    private float lvl = 0.3f;

    public static final int width = 15;
    public static final int height = 200;

    public Wall2() {
        super(width, height);
        setFactor(2000);
        setDivider(543);
    }

    public Wall2 (float x,float y){
        super(width, height);
        setX(x);
        setY(y);
    }


    public void update(float delta) {
        changeDir();
        move(delta + lvl);
    }


    public TextureRegion getTexture() { return AssetLoader.wall2; }

    public void playSound() { AssetLoader.coin.play(); }

}
