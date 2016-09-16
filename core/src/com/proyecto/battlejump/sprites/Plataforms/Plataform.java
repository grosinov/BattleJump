package com.proyecto.battlejump.sprites.Plataforms;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.proyecto.battlejump.BattleJump;

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

        platcollision = new Rectangle(posplataforma.x, posplataforma.y, plataforma.getWidth(), plataforma.getHeight());
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
}
