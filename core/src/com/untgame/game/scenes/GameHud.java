package com.untgame.game.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.untgame.game.UntitledGame;

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

        LabelStyle font = new LabelStyle(new BitmapFont(Gdx.files.internal("whiteFont.fnt"), false), Color.WHITE);
        countdownLabel = new Label(String.format("%03d", levelTimer), font);
        scoreLabel = new Label(String.format("%06d", score), font);
        timeLabel = new Label("TIME", font);
        levelLabel = new Label("LEVEL 1", font);
        gameLabel = new Label("UNTITLED GAME", font);
        playerUI.add(gameLabel).expandX().padBottom(10);
        playerUI.add(levelLabel).expandX().padBottom(10);
        playerUI.add(timeLabel).expandX().padBottom(10);
        playerUI.row();
        playerUI.add(scoreLabel).expandX();
        playerUI.add(countdownLabel).expandX();

        stage.addActor(playerUI);

        levelRound = new Label(String.format("ROUND: %03d", round), font);

        Table generalUI = new Table();
        generalUI.top();
        generalUI.setFillParent(true);
        generalUI.add(levelRound).expandX().padLeft(Gdx.graphics.getWidth() - 200);

        stage.addActor(generalUI);

    }
}
