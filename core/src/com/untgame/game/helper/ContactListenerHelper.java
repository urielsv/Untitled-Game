package com.untgame.game.helper;

import com.badlogic.gdx.physics.box2d.*;
import com.untgame.game.objects.player.Player;
import com.untgame.game.objects.proyectiles.Proyectile;
import com.untgame.game.screens.GameScreen;

import static com.untgame.game.helper.ContactType.BULLET;
import static com.untgame.game.helper.ContactType.WALL;

public class ContactListenerHelper implements ContactListener {

    private Proyectile proyectile;
    @Override
    public void beginContact(Contact contact) {
        Fixture fA = contact.getFixtureA();
        Fixture fB = contact.getFixtureB();

        if (fA == null || fB == null) return;
        if (fA.getUserData() == null || fB.getUserData() == null) return;

        if (fA.getUserData().equals(WALL) && fB.getUserData().equals(BULLET)) {
            //proyectile.remove = true;
            //Proyectile proyectile = (Proyectile) fB.getBody();
            System.out.println(fB.getUserData() + " hit " + fA.getUserData());
        }
    }

    @Override
    public void endContact(Contact contact) {
        Fixture fA = contact.getFixtureA();
        Fixture fB = contact.getFixtureB();

        if (fA == null || fB == null) return;
        if (fA.getUserData() == null || fB.getUserData() == null) return;

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }

    private boolean checkContact(Fixture a, Fixture b) {
        // check si son objetos de BodyHelperService.
        return (a.getUserData() instanceof Proyectile && b.getUserData() instanceof TileMapHelper && b.getUserData() == WALL);
    }
}
