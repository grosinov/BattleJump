package com.proyecto.battlejump.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.proyecto.battlejump.BattleJump;
import com.proyecto.battlejump.sprites.ObjetoTienda;

import java.util.ArrayList;

public class TiendaState extends State {
    private Texture fondo;
    private Texture FlechaDerecha;
    private Texture FlechaIzquierda;
    private Texture btnComprar;
    private Texture btnSeleccionar;
    private Texture personaje;
    private Texture personaje1;
    private Texture personaje2;
    private Texture personaje3;
    private boolean comprado;
    private boolean comprado1;
    private boolean comprado2;
    private boolean comprado3;
    private boolean comprado4;
    private boolean comprado5;
    private int id = 0;
    private int id1 = 1;
    private int id2 = 2;
    private int id3 = 3;
    private int id4 = 4;
    private int id5 = 5;
    private int actual = 0;
    private Texture Moneda;

    private BitmapFont DineroText;
    private BitmapFont ValorText;
    GlyphLayout DineroLayout;
    GlyphLayout ValorLayout;
    float DineroWidth;
    float DineroHeight;
    float ValorWidth;
    float ValorHeight;

    Preferences prefs = Gdx.app.getPreferences("My Preferences");
    int dinero = prefs.getInteger("Dinero");
    int seleccionado = prefs.getInteger("Seleccionado");

    public TiendaState(GameStateManager gsm){
        super(gsm);
        DineroText = new BitmapFont();
        ValorText = new BitmapFont();
        DineroLayout = new GlyphLayout();
        ValorLayout = new GlyphLayout();

        fondo = new Texture("tienda_fondo.png");
        FlechaDerecha = new Texture("FlechaDerecha.png");
        FlechaIzquierda = new Texture("FlechaIzquierda.png");
        Moneda = new Texture("Moneda.png");
        btnComprar = new Texture("btnComprar.png");
        btnSeleccionar = new Texture("btnSeleccionar.png");
        personaje = new Texture("personaje.png");
        personaje1 = new Texture("Personaje1.png");
        personaje2 = new Texture("Personaje2.png");
        personaje3 = new Texture("Personaje3.png");
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.isTouched()){
            if(Gdx.input.getX() > 0 && Gdx.input.getX() < FlechaIzquierda.getWidth() && Gdx.input.getY() > BattleJump.height / 2 - FlechaIzquierda.getHeight() / 2 && Gdx.input.getY() < BattleJump.height / 2 - FlechaIzquierda.getHeight() / 2 + FlechaIzquierda.getHeight()){
                if(actual == 0){
                    actual = 5;
                } else {
                    actual--;
                }
            }
            if(Gdx.input.getX() > BattleJump.width - FlechaDerecha.getWidth() && Gdx.input.getX() < BattleJump.width && Gdx.input.getY() > BattleJump.height / 2 - FlechaDerecha.getHeight() / 2 && Gdx.input.getY() < BattleJump.height / 2 - FlechaDerecha.getHeight() / 2 + FlechaDerecha.getHeight()){
                if(actual == 5){
                    actual = 0;
                } else {
                    actual++;
                }
            }
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        DineroText.getData().setScale(5, 5);
        ValorText.getData().setScale(5, 5);

        DineroLayout.setText(DineroText, "" + dinero);

        DineroWidth = DineroLayout.width;
        DineroHeight = DineroLayout.height;
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(fondo, 0, 0, BattleJump.width, BattleJump.height);
        sb.draw(Moneda, 0, BattleJump.height, Moneda.getWidth(), Moneda.getHeight());
        DineroText.draw(sb, DineroLayout, 0 + Moneda.getWidth(), BattleJump.height - DineroHeight);
        sb.draw(FlechaIzquierda, 0, BattleJump.height / 2 - FlechaIzquierda.getHeight() / 2, FlechaIzquierda.getWidth(), FlechaIzquierda.getHeight());
        sb.draw(FlechaDerecha, BattleJump.width - FlechaDerecha.getWidth(), BattleJump.height / 2 - FlechaDerecha.getHeight() / 2, FlechaDerecha.getWidth(), FlechaDerecha.getHeight());
        switch(actual) {
            case 0:
                sb.draw(personaje, BattleJump.width / 2 - personaje.getWidth() / 2, BattleJump.height / 2 - personaje.getHeight() / 2, personaje.getWidth(), personaje.getHeight());
                if(seleccionado == 0){
                    ValorLayout.setText(ValorText, "Seleccionado!");
                } else if(comprado) {
                    ValorLayout.setText(ValorText, "Comprado!");
                } else {
                    ValorLayout.setText(ValorText, "" + 100);
                }
                break;
            case 1:
                sb.draw(personaje1, BattleJump.width / 2 - personaje1.getWidth() / 2, BattleJump.height / 2 - personaje1.getHeight() / 2, personaje1.getWidth(), personaje1.getHeight());
                if(seleccionado == 1){
                    ValorLayout.setText(ValorText, "Seleccionado!");
                } else if(comprado1) {
                    ValorLayout.setText(ValorText, "Comprado!");
                } else {
                    ValorLayout.setText(ValorText, "" + 1000);
                }
                break;
            case 2:
                sb.draw(personaje2, BattleJump.width / 2 - personaje2.getWidth() / 2, BattleJump.height / 2 - personaje2.getHeight() / 2, personaje2.getWidth(), personaje2.getHeight());
                if(seleccionado == 2){
                    ValorLayout.setText(ValorText, "Seleccionado!");
                } else if(comprado2) {
                    ValorLayout.setText(ValorText, "Comprado!");
                } else {
                    ValorLayout.setText(ValorText, "" + 1000);
                }
                break;
            case 3:
                sb.draw(personaje3, BattleJump.width / 2 - personaje3.getWidth() / 2, BattleJump.height / 2 - personaje3.getHeight() / 2, personaje3.getWidth(), personaje3.getHeight());
                if(seleccionado == 3){
                    ValorLayout.setText(ValorText, "Seleccionado!");
                } else if(comprado3) {
                    ValorLayout.setText(ValorText, "Comprado!");
                } else {
                    ValorLayout.setText(ValorText, "" + 1500);
                }
                break;
        }
        ValorWidth = ValorLayout.width;
        ValorHeight = ValorLayout.height;
        sb.draw(Moneda, BattleJump.width / 2 - ValorWidth / 2 - Moneda.getWidth(), BattleJump.height, Moneda.getWidth(), Moneda.getHeight());
        ValorText.draw(sb, ValorLayout, BattleJump.width / 2 - ValorWidth / 2, BattleJump.height / 2 - responsiveY(500));
        sb.draw(btnComprar, BattleJump.width / 2 - ValorWidth / 2, BattleJump.height / 2 - ValorHeight - responsiveY(100), btnComprar.getWidth(), btnComprar.getHeight());
        sb.draw(btnSeleccionar, BattleJump.width / 2 - ValorWidth / 2, BattleJump.height / 2 - ValorHeight - btnSeleccionar.getHeight() - responsiveY(100), btnSeleccionar.getWidth(), btnSeleccionar.getHeight());
        sb.end();
    }

    @Override
    public void dispose() {
        fondo.dispose();
        FlechaDerecha.dispose();
        FlechaIzquierda.dispose();
        Moneda.dispose();
        btnComprar.dispose();
        btnSeleccionar.dispose();
        personaje.dispose();
        personaje1.dispose();
        personaje2.dispose();
        personaje3.dispose();
    }

    public int responsiveY (int tamaño){
        int tamañoY;
        tamañoY = (BattleJump.height * tamaño) / 2560;
        return tamañoY;
    }
}
