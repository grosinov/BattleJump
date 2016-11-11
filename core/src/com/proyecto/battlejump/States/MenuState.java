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
    float campos;
    public MenuState(GameStateManager gsm, float campos) {
        super(gsm);
        this.campos = campos;
        fondo = new Texture("Inicio.png");
        btnPlay = new Texture("btnJugar.png");
        btnHighScore = new Texture("btnHighScore.png");
        btnTienda = new Texture("btnTienda.png");
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            if(Gdx.input.getX() > BattleJump.width / 2 - btnPlay.getWidth() / 2 && Gdx.input.getX() < BattleJump.width / 2 - btnPlay.getWidth() / 2 + btnPlay.getWidth() && Gdx.input.getY() < BattleJump.height - BattleJump.height / 2 + responsiveY(btnHighScore.getHeight() / 2) && Gdx.input.getY() > BattleJump.height - BattleJump.height / 2 + responsiveY(btnHighScore.getHeight() / 2) - responsiveY(btnHighScore.getHeight())){
                gsm.set(new HighScoresState(gsm, campos));
            }

            if(Gdx.input.getX() > BattleJump.width / 2 - btnPlay.getWidth() / 2 && Gdx.input.getX() < BattleJump.width / 2 - btnPlay.getWidth() / 2 + btnPlay.getWidth() && Gdx.input.getY() < BattleJump.height - BattleJump.height / 2 - responsiveY(btnPlay.getHeight()) && Gdx.input.getY() > BattleJump.height - BattleJump.height / 2 - responsiveY(btnPlay.getHeight() * 2) - responsiveY(btnHighScore.getHeight())){
                gsm.set(new PlayState(gsm));
            }

            if(Gdx.input.getX() > BattleJump.width / 2 - btnPlay.getWidth() / 2 && Gdx.input.getX() < BattleJump.width / 2 - btnPlay.getWidth() / 2 + btnPlay.getWidth() && Gdx.input.getY() < BattleJump.height - BattleJump.height / 2 + responsiveY(btnTienda.getHeight() / 2) + responsiveY((3 * btnPlay.getHeight()) / 2) && Gdx.input.getY() > BattleJump.height - BattleJump.height / 2 + responsiveY(btnTienda.getHeight() / 2) + responsiveY((3 * btnPlay.getHeight()) / 2) - responsiveY(btnHighScore.getHeight())){
                gsm.set(new TiendaState(gsm, campos));
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
        sb.draw(fondo, 0, campos, BattleJump.width, BattleJump.height);
        sb.draw(btnPlay, BattleJump.width / 2 - responsiveX(btnPlay.getWidth() / 2), campos + BattleJump.height / 2 - responsiveY(btnPlay.getHeight() / 2) + responsiveY((3 * btnPlay.getHeight()) / 2), responsiveX(btnPlay.getWidth()), responsiveY(btnPlay.getHeight()));
        sb.draw(btnHighScore, BattleJump.width / 2 - responsiveX(btnHighScore.getWidth() / 2), campos + BattleJump.height / 2 - responsiveY(btnHighScore.getHeight() / 2), responsiveX(btnHighScore.getWidth()), responsiveY(btnHighScore.getHeight()));
        sb.draw(btnTienda, BattleJump.width / 2 - responsiveX(btnTienda.getWidth() / 2), campos + BattleJump.height / 2 - responsiveY(btnPlay.getHeight() / 2) - responsiveY((3 * btnPlay.getHeight()) / 2), responsiveX(btnTienda.getWidth()), responsiveY(btnTienda.getHeight()));
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

    public int responsiveX (int tamaño){
        int tamañoY;
        tamañoY = (BattleJump.width * tamaño) / 1440;
        return tamañoY;
    }
}