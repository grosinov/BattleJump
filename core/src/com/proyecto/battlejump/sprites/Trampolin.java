package com.proyecto.battlejump.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.proyecto.battlejump.BattleJump;

public class Trampolin {
    private Texture trampolin;
    private Vector2 tramPos;
    private Rectangle tramColision;

    public Trampolin(float x, float y) {
        trampolin = new Texture("Trampolin.png");
        tramPos = new Vector2(x, y + responsiveX(trampolin.getHeight()));
        tramColision = new Rectangle(tramPos.x, tramPos.y, responsiveX(trampolin.getWidth()), responsiveY(trampolin.getHeight()));
    }

    public Texture getTrampolin() {
        return trampolin;
    }

    public Vector2 getTramPos() {
        return tramPos;
    }

    public boolean collides(Rectangle player){
        return player.overlaps(tramColision);
    }

    public void reposition(float x, float y){
        tramPos.set(x, y + responsiveX(trampolin.getHeight()));
        tramColision.setPosition(tramPos.x, tramPos.y);
    }

    public void dispose(){
        trampolin.dispose();
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
