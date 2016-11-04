package com.proyecto.battlejump.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.proyecto.battlejump.BattleJump;

/**
 * Created by 41752347 on 14/10/2016.
 */
public class HighScoresState extends State implements Input.TextInputListener{
    private Texture fondo;
    private float campos = 0;
    private int puntaje = 0;

    private BitmapFont HighScoreText;
    private BitmapFont HighScore2Text;
    private BitmapFont HighScore3Text;
    private BitmapFont HighScore4Text;
    private BitmapFont HighScore5Text;
    GlyphLayout HighScoreLayout;
    GlyphLayout HighScore2Layout;
    GlyphLayout HighScore3Layout;
    GlyphLayout HighScore4Layout;
    GlyphLayout HighScore5Layout;
    float HighScoreWidth;
    float HighScore2Width;
    float HighScore3Width;
    float HighScore4Width;
    float HighScore5Width;
    Preferences prefs = Gdx.app.getPreferences("My Preferences");
    int HighScore = prefs.getInteger("highscore");
    int HighScore2 = prefs.getInteger("highscore2");
    int HighScore3 = prefs.getInteger("highscore3");
    int HighScore4 = prefs.getInteger("highscore4");
    int HighScore5 = prefs.getInteger("highscore5");

    public HighScoresState(GameStateManager gsm) {
        super(gsm);
        fondo = new Texture("FondoHighScore.png");
        HighScoreLayout = new GlyphLayout();
        HighScore2Layout = new GlyphLayout();
        HighScore3Layout = new GlyphLayout();
        HighScore4Layout = new GlyphLayout();
        HighScore5Layout = new GlyphLayout();
        HighScoreText = new BitmapFont();
        HighScore2Text = new BitmapFont();
        HighScore3Text = new BitmapFont();
        HighScore4Text = new BitmapFont();
        HighScore5Text = new BitmapFont();
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
        HighScoreText.getData().setScale(5, 5);
        HighScore2Text.getData().setScale(5, 5);
        HighScore3Text.getData().setScale(5, 5);
        HighScore4Text.getData().setScale(5, 5);
        HighScore5Text.getData().setScale(5, 5);

        HighScoreLayout.setText(HighScoreText, "" + HighScore);
        HighScore2Layout.setText(HighScore2Text, "" + HighScore2);
        HighScore3Layout.setText(HighScore3Text, "" + HighScore3);
        HighScore4Layout.setText(HighScore4Text, "" + HighScore4);
        HighScore5Layout.setText(HighScore5Text, "" + HighScore5);


        HighScoreWidth = HighScoreLayout.width;
        HighScore2Width = HighScore2Layout.width;
        HighScore3Width = HighScore3Layout.width;
        HighScore4Width = HighScore4Layout.width;
        HighScore5Width = HighScore5Layout.width;
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(fondo, 0, campos, BattleJump.width, BattleJump.height);
        HighScoreText.draw(sb, HighScoreLayout, BattleJump.width / 2 - HighScoreWidth / 2, BattleJump.height / 2 + responsiveY(400));
        HighScore2Text.draw(sb, HighScore2Layout, BattleJump.width / 2 - HighScore2Width / 2, BattleJump.height / 2 + responsiveY(200));
        HighScore3Text.draw(sb, HighScore3Layout, BattleJump.width / 2 - HighScore3Width / 2, BattleJump.height / 2);
        HighScore4Text.draw(sb, HighScore4Layout, BattleJump.width / 2 - HighScore4Width / 2, BattleJump.height / 2 - responsiveY(200));
        HighScore5Text.draw(sb, HighScore5Layout, BattleJump.width / 2 - HighScore5Width / 2, BattleJump.height / 2 - responsiveY(400));
        sb.end();
    }

    @Override
    public void dispose() {
        fondo.dispose();
        HighScoreText.dispose();
        HighScore2Text.dispose();
        HighScore3Text.dispose();
        HighScore4Text.dispose();
        HighScore5Text.dispose();
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
}
