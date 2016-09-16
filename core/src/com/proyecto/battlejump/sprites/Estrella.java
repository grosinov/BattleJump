package com.proyecto.battlejump.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g3d.particles.influencers.ColorInfluencer;
import com.badlogic.gdx.math.Vector2;
import com.proyecto.battlejump.BattleJump;

import java.util.Random;

/**
 * Created by 41752347 on 16/9/2016.
 */
public class Estrella {
    private Texture estrella;
    private Vector2 estposition;
    private Random rand;

    public Estrella() {
        estrella = new Texture("Estrella.png");
        rand = new Random();

        estposition = new Vector2(rand.nextInt(BattleJump.width - estrella.getWidth()), rand.nextInt(BattleJump.height - estrella.getHeight()));
    }

    public Texture getEstrella() {
        return estrella;
    }

    public Vector2 getEstposition() {
        return estposition;
    }

    public void reposition(){
        estposition.set(rand.nextInt(BattleJump.width - estrella.getWidth()), rand.nextInt(BattleJump.height - estrella.getHeight()));
    }

    public void dispose(){
        estrella.dispose();
    }
}
