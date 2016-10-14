package com.proyecto.battlejump.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.proyecto.battlejump.BattleJump;

import java.util.Random;

public class Burbuja{
    private Texture burbuja;
    private Vector2 posburbuja;
    private Rectangle burcollision;

    Random rand;

    public Burbuja() {
        rand = new Random();

        burbuja = new Texture("burbuja.png");
        posburbuja = new Vector2(rand.nextInt(BattleJump.width- burbuja.getWidth()) , rand.nextInt(BattleJump.height));

        burcollision = new Rectangle(posburbuja.x, posburbuja.y, burbuja.getWidth(), burbuja.getHeight());
    }

    public Texture getBurbuja() {
        return burbuja;
    }

    public Vector2 getPosBurbuja() {
        return posburbuja;
    }

    public void reposition(int topCam){
        posburbuja.set(rand.nextInt(BattleJump.width- burbuja.getWidth()) , rand.nextInt(topCam + responsiveY(10000) - topCam) + topCam);
        burcollision.setPosition(posburbuja.x, posburbuja.y);
    }

    public boolean collides(Rectangle player){
        return player.overlaps(burcollision);
    }

    public void dispose(){
        burbuja.dispose();
    }

    public int responsiveY (int tamaño){
        int tamañoY;
        tamañoY = (BattleJump.height * tamaño) / 2560;
        return tamañoY;
    }
}
