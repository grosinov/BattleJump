package com.proyecto.battlejump.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.proyecto.battlejump.BattleJump;

import java.util.ArrayList;

public class TiendaState extends State {
    private Texture fondo;
    private Texture FlechaDerecha;
    private Texture FlechaIzquierda;
    private Texture btnComprar;
    private Texture btnSeleccionar;
    private Texture btnVolver;
    private Texture personaje;
    private Texture personaje1;
    private Texture personaje2;
    private Texture personaje3;
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

    private boolean comprado = true;
    private boolean comprado1 = prefs.getBoolean("Comprado1");;
    private boolean comprado2 = prefs.getBoolean("Comprado2");;
    private boolean comprado3 = prefs.getBoolean("Comprado3");;

    float campos;

    public TiendaState(GameStateManager gsm, float campos){
        super(gsm);
        this.campos = campos;
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
        btnVolver = new Texture("btnVolver.png");
        personaje = new Texture("personaje.png");
        personaje1 = new Texture("Personaje1.png");
        personaje2 = new Texture("Personaje2.png");
        personaje3 = new Texture("Personaje3.png");
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            if(Gdx.input.getX() > BattleJump.width / 2 - responsiveX(btnSeleccionar.getWidth() / 2)  && Gdx.input.getX() < BattleJump.width / 2 - responsiveX(btnSeleccionar.getWidth() / 2)  + responsiveX(btnSeleccionar.getWidth()) && Gdx.input.getY() < BattleJump.height - BattleJump.height / 5 + responsiveY(btnSeleccionar.getHeight() / 2) && Gdx.input.getY() > BattleJump.height - BattleJump.height / 5 + responsiveY(btnSeleccionar.getHeight() / 2) - responsiveY(btnSeleccionar.getHeight())) {
                switch (actual) {
                    case 0:
                        if(comprado){
                            if (seleccionado != 0) {
                                seleccionado = 0;
                            }
                        }

                        break;
                    case 1:
                        if(comprado1){
                            if (seleccionado != 1) {
                                seleccionado = 1;
                            }
                        }

                        break;
                    case 2:
                        if(comprado2){
                            if (seleccionado != 2) {
                                seleccionado = 2;
                            }
                        }
                        break;
                    case 3:
                        if(comprado3){
                            if (seleccionado != 3) {
                                seleccionado = 3;
                            }
                        }
                        break;
                }
                prefs.putInteger("Seleccionado", seleccionado);
                prefs.flush();
            }

            if(Gdx.input.getX() > BattleJump.width / 2 - responsiveX(btnComprar.getWidth() / 2)  && Gdx.input.getX() < BattleJump.width / 2 - responsiveX(btnComprar.getWidth() / 2)  + responsiveX(btnComprar.getWidth()) && Gdx.input.getY() < BattleJump.height - BattleJump.height / 5 - responsiveY(btnSeleccionar.getHeight()) / 2 - responsiveY(50) && Gdx.input.getY() > BattleJump.height - BattleJump.height / 5 - responsiveY(btnSeleccionar.getHeight()) / 2 - responsiveY(50) - responsiveY(btnComprar.getHeight())) { //BattleJump.height / 5 - responsiveY(btnSeleccionar.getHeight()) / 2 - responsiveY(50)
                //btnComprar
                switch (actual) {
                    case 0:
                        if (!comprado) {
                            if(dinero >= 100){
                                dinero-=100;
                                comprado = true;
                            }
                        }
                        break;
                    case 1:
                        if (!comprado1) {
                            if(dinero >= 1000){
                                comprado1 = true;
                                dinero-=1000;
                                prefs.putBoolean("Comprado1", comprado1);
                            }
                        }
                        break;
                    case 2:
                        if (!comprado2) {
                            if(dinero >= 1000){
                                comprado2 = true;
                                dinero-=1000;
                                prefs.putBoolean("Comprado2", comprado2);
                            }
                        }
                        break;
                    case 3:
                        if (!comprado3) {
                            if(dinero >= 1500){
                                comprado3 = true;
                                dinero-=1500;
                                prefs.putBoolean("Comprado3", comprado3);
                            }
                        }
                        break;
                }
                prefs.putInteger("Dinero", dinero);
                prefs.flush();
            }

            if(Gdx.input.getX() > BattleJump.width / 2 - responsiveX(btnVolver.getWidth() / 2)  && Gdx.input.getX() < BattleJump.width / 2 - responsiveX(btnVolver.getWidth() / 2)  + responsiveX(btnVolver.getWidth()) && Gdx.input.getY() < BattleJump.height - BattleJump.height / 5 + responsiveY(btnSeleccionar.getHeight() / 2)  + responsiveY(btnVolver.getHeight()) + responsiveY(50) && Gdx.input.getY() > BattleJump.height - BattleJump.height / 5 + responsiveY(btnSeleccionar.getHeight() / 2)  + responsiveY(btnVolver.getHeight()) + responsiveY(50) - responsiveY(btnVolver.getHeight())) {
                //btnVolver
                gsm.set(new MenuState(gsm, campos));

            }

            if(Gdx.input.getX() > 0 && Gdx.input.getX() < FlechaIzquierda.getWidth() && Gdx.input.getY() > BattleJump.height / 2 - FlechaIzquierda.getHeight() / 2 && Gdx.input.getY() < BattleJump.height / 2 - FlechaIzquierda.getHeight() / 2 + FlechaIzquierda.getHeight()){
                if(actual == 0){
                    actual = 3;
                } else {
                    actual--;
                }
            }
            if(Gdx.input.getX() > BattleJump.width - FlechaDerecha.getWidth() && Gdx.input.getX() < BattleJump.width && Gdx.input.getY() > BattleJump.height / 2 - FlechaDerecha.getHeight() / 2 && Gdx.input.getY() < BattleJump.height / 2 - FlechaDerecha.getHeight() / 2 + FlechaDerecha.getHeight()){
                if(actual == 3){
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
        DineroText.getData().setScale(responsiveX(6), responsiveY(6));
        ValorText.getData().setScale(responsiveX(6), responsiveY(6));

        DineroLayout.setText(DineroText, "" + dinero);

        DineroWidth = DineroLayout.width;
        DineroHeight = DineroLayout.height;
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(fondo, 0, campos, BattleJump.width, BattleJump.height);
        sb.draw(Moneda, 0, campos + BattleJump.height - DineroHeight * 2, responsiveX(Moneda.getWidth()), responsiveY(Moneda.getHeight()));
        DineroText.draw(sb, DineroLayout, 0 + responsiveX(Moneda.getWidth()), campos + BattleJump.height - DineroHeight);
        sb.draw(FlechaIzquierda, 0, campos + BattleJump.height / 2 - responsiveY(FlechaIzquierda.getHeight()) / 2, responsiveX(FlechaIzquierda.getWidth()), responsiveY(FlechaIzquierda.getHeight()));
        sb.draw(FlechaDerecha, BattleJump.width - responsiveX(FlechaDerecha.getWidth()), campos + BattleJump.height / 2 - responsiveY(FlechaDerecha.getHeight()) / 2, responsiveX(FlechaDerecha.getWidth()), responsiveY(FlechaDerecha.getHeight()));
        switch(actual) {
            case 0:
                sb.draw(personaje, BattleJump.width / 2 - responsiveX(personaje.getWidth()) / 2, campos + BattleJump.height / 2 - responsiveY(personaje.getHeight()) / 2, responsiveX(personaje.getWidth()), responsiveY(personaje.getHeight()));
                if(seleccionado == 0){
                    ValorLayout.setText(ValorText, "Seleccionado!");
                } else if(comprado) {
                    ValorLayout.setText(ValorText, "Comprado!");
                } else {
                    ValorLayout.setText(ValorText, "" + 100);
                }
                break;
            case 1:
                sb.draw(personaje1, BattleJump.width / 2 - responsiveX(personaje1.getWidth()) / 2, campos + BattleJump.height / 2 - responsiveY(personaje1.getHeight()) / 2, responsiveX(personaje1.getWidth()), responsiveY(personaje1.getHeight()));
                if(seleccionado == 1){
                    ValorLayout.setText(ValorText, "Seleccionado!");
                } else if(comprado1) {
                    ValorLayout.setText(ValorText, "Comprado!");
                } else {
                    ValorLayout.setText(ValorText, "" + 1000);
                }
                break;
            case 2:
                sb.draw(personaje2, BattleJump.width / 2 - responsiveX(personaje2.getWidth()) / 2, campos + BattleJump.height / 2 - responsiveY(personaje2.getHeight()) / 2, responsiveX(personaje2.getWidth()), responsiveY(personaje2.getHeight()));
                if(seleccionado == 2){
                    ValorLayout.setText(ValorText, "Seleccionado!");
                } else if(comprado2) {
                    ValorLayout.setText(ValorText, "Comprado!");
                } else {
                    ValorLayout.setText(ValorText, "" + 1000);
                }
                break;
            case 3:
                sb.draw(personaje3, BattleJump.width / 2 - responsiveX(personaje3.getWidth()) / 2, campos + BattleJump.height / 2 - responsiveY(personaje3.getHeight()) / 2, responsiveX(personaje3.getWidth()), responsiveY(personaje3.getHeight()));
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
        sb.draw(Moneda, BattleJump.width / 2 - ValorWidth / 2 - responsiveX(Moneda.getWidth()), campos + BattleJump.height, responsiveX(Moneda.getWidth()), responsiveY(Moneda.getHeight()));
        ValorText.draw(sb, ValorLayout, BattleJump.width / 2 - ValorWidth / 2, campos + BattleJump.height / 2 - personaje.getHeight() / 2 - ValorHeight - responsiveY(50));
        sb.draw(btnComprar, BattleJump.width / 2 - responsiveX(btnComprar.getWidth()) / 2, campos + BattleJump.height / 5 + responsiveY(btnSeleccionar.getHeight()) / 2 + responsiveY(50), responsiveX(btnComprar.getWidth()), responsiveY(btnComprar.getHeight()));
        sb.draw(btnSeleccionar, BattleJump.width / 2 - responsiveX(btnSeleccionar.getWidth()) / 2, campos + BattleJump.height / 5 - responsiveY(btnSeleccionar.getHeight() / 2) , responsiveX(btnSeleccionar.getWidth()), responsiveY(btnSeleccionar.getHeight()));
        sb.draw(btnVolver, BattleJump.width / 2 - responsiveX(btnVolver.getWidth()) / 2, campos + BattleJump.height / 5 - responsiveY(btnSeleccionar.getHeight() / 2)  - responsiveY(btnVolver.getHeight()) - responsiveY(50), responsiveX(btnSeleccionar.getWidth()), responsiveY(btnSeleccionar.getHeight()));
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

    public int responsiveX (int tamaño){
        int tamañoY;
        tamañoY = (BattleJump.width * tamaño) / 1440;
        return tamañoY;
    }
}
