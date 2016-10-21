package com.proyecto.battlejump.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.proyecto.battlejump.BattleJump;

public class MenuState extends State{
    private Texture fondo;
    private Texture btnPlay;
    private Texture btnHighScore;
    public MenuState(GameStateManager gsm) {
        super(gsm);
        fondo = new Texture("Inicio.png");
        btnPlay = new Texture("Jugar.png");
        btnHighScore = new Texture("High_score.png");
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
        sb.draw(btnPlay, BattleJump.width / 2 - btnPlay.getWidth() / 2, BattleJump.height / 2 - btnPlay.getHeight() / 2 + BattleJump.height * 100 / 2560);
        sb.draw(btnHighScore, BattleJump.width / 2 - btnHighScore.getWidth() / 2, BattleJump.height / 2 - btnHighScore.getHeight() / 2);
        sb.end();
    }

    @Override
    public void dispose() {
        fondo.dispose();
        btnPlay.dispose();
    }
}