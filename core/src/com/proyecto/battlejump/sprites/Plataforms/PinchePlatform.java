package com.proyecto.battlejump.sprites.Plataforms;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.proyecto.battlejump.BattleJump;

import java.util.Random;

public class PinchePlatform extends Plataform{
    private Texture platPinches;

    public PinchePlatform (int y) {
        super(y);
        platPinches = new Texture("PlataformaPinche.png");
        rand = new Random();
    }

    public Texture getPlatPinches() {
        return platPinches;
    }

    @Override
    public void reposition(int y) {
        super.reposition(y);
    }

    @Override
    public boolean collides(Rectangle player) {
        if (player.overlaps(platcollision)){
            return true;
        } else{
            return false;
        }
    }

    public void dispose() {
        platPinches.dispose();
    }
}
