package com.proyecto.battlejump.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.proyecto.battlejump.BattleJump;

public class Moneda {
    private Texture moneda;
    private Vector2 posMoneda;
    private Rectangle monColision;

    public Moneda(float x, float y) {
        moneda = new Texture("Moneda.png");
        posMoneda = new Vector2(x, y + responsiveY(moneda.getHeight()));
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

    public void reposition(float x, float y){
        posMoneda.set(x, y + responsiveY(moneda.getHeight()));
        monColision.setPosition(posMoneda.x, posMoneda.y);
    }

    public void dispose(){
        moneda.dispose();
    }

    public int responsiveX (int tamaño){
        int tamañoX;
        tamañoX = (BattleJump.width * tamaño) / 1440;
        return tamañoX;
    }

    public int responsiveY (int tamaño){
        int tamañoY;
        tamañoY = (BattleJump.height * tamaño) / 2560;
        return tamañoY;
    }
}
