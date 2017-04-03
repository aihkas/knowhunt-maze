package se.kth.knowhunt.maze;

import com.badlogic.gdx.Game;
import se.kth.knowhunt.screens.GameScreen;
import se.kth.knowhunt.mazehelpers.AssetLoader;

public class MazeGame extends Game {

    public static final double version = 0.02;

    @Override
    public void create() {
        AssetLoader.load();
        setScreen(new GameScreen());
    }

    @Override
    public void dispose() {
        super.dispose();
        AssetLoader.dispose();
    }
}
