package com.proyecto.battlejump.sprites.Plataforms;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.proyecto.battlejump.BattleJump;

import java.util.Random;

public class BrokenPlatform extends Plataform{
    private Texture platRota;

    public BrokenPlatform(int y) {
        super(y);
        platRota = new Texture("Plataforma_Rota.png");
        rand = new Random();
    }

    public Texture getPlatRota() {
        return platRota;
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
        platRota.dispose();
    }
}