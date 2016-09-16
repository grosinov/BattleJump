package com.proyecto.battlejump.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.proyecto.battlejump.BattleJump;

public class MenuState extends State{
    private Texture fondo;
    private Texture fondo2;
    private Texture btnPlay;
    public MenuState(GameStateManager gsm) {
        super(gsm);
        fondo = new Texture("Fondo_Tierra-Cielo_Cielo.png");
        fondo2 = new Texture("Fondo_Tierra-Cielo_Pasto.png");
        btnPlay = new Texture("btnPlay.png");
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
        sb.draw(btnPlay, BattleJump.width / 2 - btnPlay.getWidth() / 2, BattleJump.height / 2 - btnPlay.getHeight() / 2);
        sb.end();
    }

    @Override
    public void dispose() {
        fondo.dispose();
        fondo2.dispose();
        btnPlay.dispose();
    }
}