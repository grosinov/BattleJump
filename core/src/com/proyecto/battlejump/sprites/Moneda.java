package com.proyecto.battlejump.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.proyecto.battlejump.BattleJump;

import java.util.Random;

public class Moneda {
    private Texture moneda;
    private Vector2 posMoneda;
    private Rectangle monColision;

    Random rand;

    public Moneda() {
        moneda = new Texture("Moneda.png");
        posMoneda = new Vector2(rand.nextInt(BattleJump.width - moneda.getWidth()) , rand.nextInt(BattleJump.height));
    }
    public Texture getMoneda() {
        return moneda;
    }

    public Vector2 getPosMoneda() {
        return posMoneda;
    }

    public boolean collides(Rectangle player){
        return player.overlaps(monColision);
    }

    public void reposition(int topCam){
        posMoneda.set(rand.nextInt(BattleJump.width - moneda.getWidth()) , rand.nextInt(topCam + BattleJump.height - topCam) + topCam);
        monColision.setPosition(posMoneda.x, posMoneda.y);
    }

    public void dispose(){
        moneda.dispose();
    }
}
