package com.proyecto.battlejump.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.proyecto.battlejump.BattleJump;

public class MenuState extends State{
    private Texture fondo;
    private Texture btnPlay;
    private Texture btnHighScore;
    private Texture btnTienda;
    public MenuState(GameStateManager gsm) {
        super(gsm);
        fondo = new Texture("Inicio.png");
        btnPlay = new Texture("btnJugar.png");
        btnHighScore = new Texture("btnHighScore.png");
        btnTienda = new Texture("btnTienda.png");
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            if(Gdx.input.getX() > BattleJump.width / 2 - btnPlay.getWidth() / 2 && Gdx.input.getX() < BattleJump.width / 2 - btnPlay.getWidth() / 2 + btnPlay.getWidth() && Gdx.input.getY() > BattleJump.height / 2 - btnPlay.getHeight() / 2 + BattleJump.height * 100 / 2560 && Gdx.input.getY() < BattleJump.height / 2 - btnPlay.getHeight() / 2 + BattleJump.height * 100 / 2560 + btnPlay.getHeight()){
                gsm.set(new TiendaState(gsm));
            }
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
        sb.draw(btnPlay, BattleJump.width / 2 - btnPlay.getWidth() / 2, BattleJump.height / 2 - btnPlay.getHeight() / 2 + BattleJump.height * 500 / 2560, btnPlay.getWidth(), btnPlay.getHeight());
        sb.draw(btnHighScore, BattleJump.width / 2 - btnHighScore.getWidth() / 2, BattleJump.height / 2 - btnHighScore.getHeight() / 2, btnHighScore.getWidth(), btnHighScore.getHeight());
        sb.draw(btnTienda, BattleJump.width / 2 - btnHighScore.getWidth() / 2, BattleJump.height / 2 - btnHighScore.getHeight() / 2 - btnTienda.getHeight() - responsiveY(100), btnTienda.getWidth(), btnTienda.getHeight());
        sb.end();
    }

    @Override
    public void dispose() {
        fondo.dispose();
        btnPlay.dispose();
    }

    public int responsiveY (int tamaño){
        int tamañoY;
        tamañoY = (BattleJump.height * tamaño) / 2560;
        return tamañoY;
    }
}