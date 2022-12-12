package com.untgame.game.helper;

public class ShootCooldownHelper {
    private float coolDown;

    public ShootCooldownHelper(float coolDown){
        this.coolDown   = coolDown;
    }

    public float getCoolDown() {
        return coolDown;
    }

    public void setCoolDown(float coolDown) {
        this.coolDown = coolDown;
    }
}
