package com.untgame.game.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import static com.untgame.game.helper.Constants.*;

public class GameHud {
    public Stage stage;
    private Viewport viewport;
    private Integer levelTimer;
    private Integer round;
    private float timeCount;
    private Integer score;

    Label countdownLabel;
    Label scoreLabel;
    Label timeLabel;
    Label levelLabel;
    Label gameLabel;
    Label levelRound;

    public GameHud(SpriteBatch sb) {
        levelTimer = 300;
        timeCount = 0;
        score = 0;

        viewport = new ExtendViewport(SCREEN_WIDTH, SCREEN_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        Table playerUI = new Table();
        //playerUI.top(); // position
        playerUI.bottom();
        playerUI.setFillParent(true); // size of stage

        countdownLabel = new Label(String.format("%03d", levelTimer), new Label.LabelStyle(new BitmapFont(), Color.LIGHT_GRAY));
        scoreLabel = new Label(String.format("%06d", score), new Label.LabelStyle(new BitmapFont(), Color.LIGHT_GRAY));
        timeLabel = new Label("TIME", new Label.LabelStyle(new BitmapFont(), Color.LIGHT_GRAY));
        levelLabel = new Label("LEVEL 1", new Label.LabelStyle(new BitmapFont(), Color.LIGHT_GRAY));
        gameLabel = new Label("UNTITLED GAME", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        playerUI.add(gameLabel).expandX().padBottom(10);
        playerUI.add(levelLabel).expandX().padBottom(10);
        playerUI.add(timeLabel).expandX().padBottom(10);
        playerUI.row();
        playerUI.add(scoreLabel).expandX();
        playerUI.add(countdownLabel).expandX();

        stage.addActor(playerUI);

        levelRound = new Label(String.format("ROUND: %03d", round), new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        Table generalUI = new Table();
        generalUI.top();
        generalUI.setFillParent(true);
        generalUI.add(levelRound).expandX().padLeft(Gdx.graphics.getWidth() - 100);

        stage.addActor(generalUI);

    }
}
