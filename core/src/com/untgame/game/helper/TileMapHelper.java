package com.untgame.game.helper;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.untgame.game.screens.GameScreen;
import com.untgame.game.objects.player.Player;

import static com.untgame.game.helper.Constants.PPM;
import static com.untgame.game.helper.ContactType.PLAYER;

public class TileMapHelper {

    private TiledMap tiledMap;
    private GameScreen gameScreen;
    public TileMapHelper(GameScreen gameScreen){
        this.gameScreen=gameScreen;
    }

    public OrthogonalTiledMapRenderer setupMap(){
        tiledMap = new TmxMapLoader().load("level0.tmx");
        parseMapObject(tiledMap.getLayers().get("objects").getObjects());
        return new OrthogonalTiledMapRenderer(tiledMap);
    }

    private void parseMapObject(MapObjects mapObjects){
        for(MapObject mapObject: mapObjects){
            if (mapObject instanceof PolygonMapObject){
                createStaticBody((PolygonMapObject) mapObject);
            }

            if (mapObject instanceof RectangleMapObject) {
                Rectangle rectangle = ((RectangleMapObject) mapObject).getRectangle();
                String rectangleName = mapObject.getName();

                if (rectangleName.equals("player")) {
                    Body body = BodyHelperService.createBody(rectangle.getX(), rectangle.getY(),
                                                            rectangle.getWidth() / 2,
                                                            rectangle.getHeight() / 2,
                                                            false,
                                                            gameScreen.getLevel(),
                                                            PLAYER
                    );

                    gameScreen.setPlayer(new Player(rectangle.getWidth(), rectangle.getHeight(), body, new Texture("imp_idle_anim_f0.png")));
                }
            }
        }
    }

    private void createStaticBody(PolygonMapObject polygonMapObject){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        Body body = gameScreen.getLevel().createBody(bodyDef);
        Shape shape = createPolygonShape(polygonMapObject);
        body.createFixture(shape, 1000);
        shape.dispose();
    }

    private Shape createPolygonShape(PolygonMapObject polygonMapObject) {
        float[] vertices = polygonMapObject.getPolygon().getTransformedVertices();
        Vector2[] worldVertices = new Vector2[vertices.length/2];

        for (int i=0; i < vertices.length / 2; i++){
            Vector2 current = new Vector2(vertices[i*2]/PPM, vertices[i*2+1]/PPM);
            worldVertices[i] = current;
        }

        PolygonShape shape = new PolygonShape();
        shape.set(worldVertices);
        return shape;
    }

}
