package se.kth.knowhunt.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import se.kth.knowhunt.gameobjects.*;
import se.kth.knowhunt.screens.GameScreen;
import se.kth.knowhunt.maze.MazeGame;
import se.kth.knowhunt.mazehelpers.AssetLoader;

/**
 * Created by alex on 12/12/14.
 */
public class GameRenderer {

    private GameWorld world;
    private OrthographicCamera cam;
    private ShapeRenderer shapeRenderer;

    private SpriteBatch batcher;

    private Boy boy;

    public GameRenderer(GameWorld world) {
        this.world = world;

        this.cam = new OrthographicCamera();
        cam.setToOrtho(true, GameScreen.GAME_WIDTH, 204);

        batcher = new SpriteBatch();
        batcher.setProjectionMatrix(cam.combined);
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(cam.combined);

        initGameObjects();
    }

    private void initGameObjects() {
        boy = world.getBoy();
    }

    public void drawBoy() {
        batcher.draw(AssetLoader.boyhead, boy.getX(),
                boy.getY(), boy.getWidth() / 4.0f, boy.getHeight() / 4.0f,
                boy.getWidth() / 2.0f, boy.getHeight() / 2.0f, 1, 1,
                boy.getRotation(boy.getDir()));

    }

    public void drawBoyShadow() {
        for (Node n : boy.getBody()) {
            batcher.draw(AssetLoader.boyshadow, n.v.x, n.v.y,
                    boy.getWidth() / 4.0f, boy.getHeight() / 4.0f,
                    boy.getWidth() / 2.0f, boy.getHeight() / 2.0f, 1, 1, Boy.getRotation(n.dir));
        }
    }

    public void drawObstacles() {
        for (Autonomous a : world.getObstacles()) {
            batcher.draw(a.getTexture(), a.getX(), a.getY(),
                    a.getWidth() / 4.0f, a.getHeight() / 4.0f,a.getWidth()/4,a.getHeight()/4,1,1,1
                    );


        }


        for (Autonomous a : world.getObstaclesv()) {
            batcher.draw(a.getTexture(), a.getX(), a.getY(),
                    a.getWidth() / 4.0f, a.getHeight() / 4.0f, a.getWidth() / 4, a.getHeight() / 4, 1, 1, 1
            );
        }

            batcher.draw(world.getEnd().getTexture(), world.getEnd().getX(), world.getEnd().getY(),
                world.getEnd().getWidth() / 2.0f, world.getEnd().getHeight() / 2.0f);

    }

    public void render(float runTime) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        shapeRenderer.end();

        batcher.begin();
      batcher.disableBlending();
        batcher.draw(AssetLoader.bg, 0, 0, GameScreen.GAME_WIDTH, GameScreen.getHeight());
        batcher.enableBlending();

        /* draw boy Shadow */
        drawBoyShadow();
        /* draw boy */
        drawBoy();
        /* draw walls */
        drawObstacles();

        if (world.isReady()) {
            AssetLoader.shadow.draw(batcher, "Touch me", (GameScreen.GAME_WIDTH / 2) - 42, 76);
            AssetLoader.font.draw(batcher, "Touch me", (GameScreen.GAME_WIDTH / 2) - 42, 75);
            AssetLoader.fontSmall.draw(batcher,
                                       "V:" + MazeGame.version,
                                       GameScreen.GAME_WIDTH - 30,
                                       GameScreen.GAME_HEIGHT - 10);
        } else {
            if (world.isGameOver() && world.win) {

                AssetLoader.shadow.draw(batcher, "You won!", 25, 80);
                AssetLoader.font.draw(batcher, "You won!", 24, 79);

            }

            if (world.isGameOver() && !world.win) {
                AssetLoader.shadow.draw(batcher, "Game Over", 25, 56);
                AssetLoader.font.draw(batcher, "Game Over", 24, 55);
                AssetLoader.shadow.draw(batcher, "Try again ?", 23, 76);
                AssetLoader.font.draw(batcher, "Try again ?", 22, 75);
                }
        }
        batcher.end();
    }

    public OrthographicCamera getCam() { return cam; }
}
