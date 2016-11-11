package com.proyecto.battlejump.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.proyecto.battlejump.BattleJump;

public class RetryState extends State implements Input.TextInputListener{
    private Texture fondo;
    private Texture fondo2;
    private String perdio = "Has perdido!";
    private float campos = 0;
    public int puntaje = 0;

    private BitmapFont perdiotext;
    private BitmapFont puntajetext;
    private BitmapFont highscoretext;
    GlyphLayout perdioLayout;
    GlyphLayout puntajeLayout;
    GlyphLayout highscoreLayout;
    float perdioWidth;
    float puntajeWidth;
    float highscoreWidth;
    Preferences prefs = Gdx.app.getPreferences("My Preferences");
    int HighScore = prefs.getInteger("highscore");
    int HighScore2 = prefs.getInteger("highscore2");
    int HighScore3 = prefs.getInteger("highscore3");
    int HighScore4 = prefs.getInteger("highscore4");
    int HighScore5 = prefs.getInteger("highscore5");

    public RetryState(GameStateManager gsm, int puntaje, float campos) {
        super(gsm);
        fondo = new Texture("Fondo_Tierra-Cielo_Cielo.png");
        fondo2 = new Texture("Fondo_Tierra-Cielo_Pasto.png");
        perdioLayout = new GlyphLayout();
        puntajeLayout = new GlyphLayout();
        highscoreLayout = new GlyphLayout();
        perdiotext = new BitmapFont();
        puntajetext = new BitmapFont();
        highscoretext = new BitmapFont();

        this.puntaje = puntaje;
        this.campos = campos;

        if (puntaje > HighScore) {
            HighScore5 = HighScore4;
            HighScore4 = HighScore3;
            HighScore3 = HighScore2;
            HighScore2 = HighScore;
            HighScore = puntaje;
            prefs.putInteger("highscore", HighScore);
            prefs.flush();
        } else {
            if (puntaje > HighScore2) {
                HighScore5 = HighScore4;
                HighScore4 = HighScore3;
                HighScore3 = HighScore2;
                HighScore2 = puntaje;
            }
            if (puntaje > HighScore3) {
                HighScore5 = HighScore4;
                HighScore4 = HighScore3;
                HighScore3 = puntaje;
            }
            if (puntaje > HighScore4) {
                HighScore5 = HighScore4;
                HighScore4 = puntaje;
            }
            if (puntaje > HighScore5) {
                HighScore5 = puntaje;
            }
            prefs.putInteger("highscore2", HighScore2);
            prefs.putInteger("highscore3", HighScore3);
            prefs.putInteger("highscore4", HighScore4);
            prefs.putInteger("highscore5", HighScore5);
            prefs.flush();
        }
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            gsm.set(new MenuState(gsm));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        perdiotext.getData().setScale(responsiveX(5), responsiveY(5));
        puntajetext.getData().setScale(responsiveX(5), responsiveY(5));
        highscoretext.getData().setScale(responsiveX(5), responsiveY(5));

        perdioLayout.setText(perdiotext, perdio);
        puntajeLayout.setText(puntajetext, "Tu puntaje final es: " + String.valueOf(puntaje));
        highscoreLayout.setText(highscoretext, "Mayor puntaje: " + String.valueOf(HighScore));

        perdioWidth = perdioLayout.width;
        puntajeWidth = puntajeLayout.width;
        highscoreWidth = highscoreLayout.width;
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(fondo, 0, campos - BattleJump.height / 2, BattleJump.width, BattleJump.height);
        sb.draw(fondo2, 0, campos - BattleJump.height / 2, BattleJump.width, BattleJump.height);
        perdiotext.draw(sb, perdioLayout, BattleJump.width / 2 - perdioWidth / 2, campos + 100);
        puntajetext.draw(sb, puntajeLayout, BattleJump.width / 2 - puntajeWidth / 2, campos);
        highscoretext.draw(sb, highscoreLayout, BattleJump.width / 2 - highscoreWidth / 2, campos - 100);
        sb.end();
    }

    @Override
    public void dispose() {
        fondo.dispose();
        fondo2.dispose();
        perdiotext.dispose();
        puntajetext.dispose();
    }

    @Override
    public void input(String text) {

    }

    @Override
    public void canceled() {

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