package com.proyecto.battlejump.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.proyecto.battlejump.BattleJump;

public class RetryState extends State{
    private Texture fondo;
    private Texture fondo2;
    private int HighScore = 0;

    public RetryState(GameStateManager gsm, int puntaje) {
        super(gsm);
        fondo = new Texture("Fondo_Tierra-Cielo_Cielo.png");
        fondo2 = new Texture("Fondo_Tierra-Cielo_Pasto.png");

        if(puntaje > HighScore){
            HighScore = puntaje;
        }
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            gsm.set(new PlayState(gsm));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(fondo, 0,0, BattleJump.width, BattleJump.height);
        sb.draw(fondo2, 0,0, BattleJump.width, BattleJump.height);
        sb.end();
    }

    @Override
    public void dispose() {
        fondo.dispose();
        fondo2.dispose();
    }
}
