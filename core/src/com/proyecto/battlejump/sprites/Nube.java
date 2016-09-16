package com.proyecto.battlejump.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.proyecto.battlejump.BattleJump;

import java.util.Random;

public class Nube {
    private Texture Nube;
    private Vector2 posLeftNube;
    private Vector2 posRightNube;
    private Random rand;

    public Nube(){
        Nube = new Texture("nubedefondo.png");
        rand = new Random();

        posLeftNube = new Vector2(0 - Nube.getWidth() / 2, rand.nextInt(5000));
        posRightNube = new Vector2(BattleJump.width - Nube.getWidth() / 2, rand.nextInt(5000));
    }

    public Texture getNube() {
        return Nube;
    }

    public Vector2 getPosLeftNube() {
        return posLeftNube;
    }

    public Vector2 getPosRightNube() {
        return posRightNube;
    }

    public void repositionLeft(int MinY){
        posLeftNube.set(0 - Nube.getWidth() / 2, rand.nextInt(MinY + 4000 - MinY) + MinY);
    }

    public void repositionRight(int MinY){
        posRightNube.set(BattleJump.width - Nube.getWidth() / 2, rand.nextInt(MinY + 4000 - MinY) + MinY);
    }

     public void dispose(){
         Nube.dispose();
     }
}