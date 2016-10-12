package com.proyecto.battlejump.sprites.Plataforms;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.proyecto.battlejump.BattleJump;
import com.proyecto.battlejump.States.PlayState;

import java.util.Random;

public class  Plataform {
    protected Texture plataforma;
    Vector2 posplataforma;
    Random rand;
    Rectangle platcollision;

    public Plataform (int y){
        plataforma = new Texture("Plataforma.png");
        rand = new Random();

        posplataforma = new Vector2(rand.nextInt(BattleJump.width - plataforma.getWidth()), y);

        platcollision = new Rectangle(posplataforma.x, posplataforma.y, responsiveX(plataforma), responsiveY(plataforma));
    }

    public Texture getPlataforma() {
        return plataforma;
    }

    public Vector2 getPosplataforma() {
        return posplataforma;
    }

    public void reposition(int y){
        posplataforma.set(rand.nextInt(BattleJump.width - plataforma.getWidth()), y);
        platcollision.setPosition(posplataforma.x, posplataforma.y);
    }

    public boolean collides(Rectangle player){
        return player.overlaps(platcollision);
    }

    public void dispose(){
        plataforma.dispose();
    }

    public int responsiveX (Texture textura){
        int tamañoX = 0;
        tamañoX = (BattleJump.width * textura.getWidth()) / 1440;
        return tamañoX;
    }

    public int responsiveY (Texture textura){
        int tamañoY = 0;
        tamañoY = (BattleJump.height * textura.getHeight()) / 2560;
        return tamañoY;
    }
}
