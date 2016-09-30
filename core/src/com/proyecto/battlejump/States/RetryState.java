package com.proyecto.battlejump.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.proyecto.battlejump.BattleJump;

public class RetryState extends State{
    private Texture fondo;
    private Texture fondo2;
    private BitmapFont perdio;
    private BitmapFont puntajetext;
    private float campos = 0;
    private int puntaje = 0;
    private int HighScore = 0;

    public RetryState(GameStateManager gsm, int puntaje, float campos) {
        super(gsm);
        fondo = new Texture("Fondo_Tierra-Cielo_Cielo.png");
        fondo2 = new Texture("Fondo_Tierra-Cielo_Pasto.png");
        //Arreglar camara
        perdio = new BitmapFont();
        puntajetext = new BitmapFont();
        this.puntaje = puntaje;
        this.campos = campos;


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
        sb.draw(fondo, 0, campos - BattleJump.height / 2, BattleJump.width, BattleJump.height);
        sb.draw(fondo2, 0, campos - BattleJump.height / 2, BattleJump.width, BattleJump.height);
        perdio.draw(sb, "Has perdido!!", BattleJump.width / 2, campos + 100);
        puntajetext.draw(sb, "Tu puntaje final es: " + String.valueOf(puntaje), BattleJump.width / 2, campos);
        sb.end();
    }

    @Override
    public void dispose() {
        fondo.dispose();
        fondo2.dispose();
        perdio.dispose();
        puntajetext.dispose();
    }
}
