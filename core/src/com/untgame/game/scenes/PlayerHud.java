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
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.untgame.game.screens.GameScreen;

import static com.untgame.game.helper.Constants.*;

public class PlayerHud {
    public Stage stage;
    private Viewport viewport;
    private Integer levelTimer;
    private float timeCount;
    private Integer score;

    Label countdownLabel;
    Label scoreLabel;
    Label timeLabel;
    Label levelLabel;
    Label gameLabel;

    public PlayerHud(SpriteBatch sb) {
        levelTimer = 300;
        timeCount = 0;
        score = 0;

        viewport = new ExtendViewport(SCREEN_WIDTH, SCREEN_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        Table table = new Table();
        //table.top(); // position
        table.bottom();
        table.setFillParent(true); // size of stage

        countdownLabel = new Label(String.format("%03d", levelTimer), new Label.LabelStyle(new BitmapFont(), Color.LIGHT_GRAY));
        scoreLabel = new Label(String.format("%06d", score), new Label.LabelStyle(new BitmapFont(), Color.LIGHT_GRAY));
        timeLabel = new Label("TIME", new Label.LabelStyle(new BitmapFont(), Color.LIGHT_GRAY));
        levelLabel = new Label("LEVEL 1", new Label.LabelStyle(new BitmapFont(), Color.LIGHT_GRAY));
        gameLabel = new Label("UNTITLED GAME", new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        table.add(gameLabel).expandX().padBottom(10);
        table.add(levelLabel).expandX().padBottom(10);
        table.add(timeLabel).expandX().padBottom(10);
        table.row();
        table.add(scoreLabel).expandX();
        table.add(countdownLabel).expandX();

        stage.addActor(table);

    }
}
