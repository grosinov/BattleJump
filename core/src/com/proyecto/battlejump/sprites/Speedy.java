package com.proyecto.battlejump.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Timer;
import com.proyecto.battlejump.BattleJump;
import com.proyecto.battlejump.States.PlayState;

public class Speedy {
    private static final int gravity = -20;
    private Vector3 position;
    protected Vector3 velocity;
    private Rectangle playerCollision;
    private Rectangle colisionPoder;

    private Texture personajeElejido;
    private Texture personaje;
    private Texture personaje1;
    private Texture personaje2;
    private Texture personaje3;
    private Texture burbuja;

    Preferences prefs = Gdx.app.getPreferences("My Preferences");
    int seleccionado = prefs.getInteger("Seleccionado");

    public Speedy(float x, float y) {
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);
        personajeElejido = new Texture("personaje.png");
        personaje = new Texture("personaje.png");
        personaje1 = new Texture("Personaje1.png");
        personaje2 = new Texture("Personaje2.png");
        personaje3 = new Texture("Personaje3.png");
        burbuja = new Texture("burbuja.png");
        elejirPersonaje();

        playerCollision = new Rectangle(x, y, responsiveX(personaje.getWidth()), (responsiveY(personaje.getHeight()) * 2) / personaje.getHeight());
        colisionPoder = new Rectangle(x, y, responsiveX(personaje.getWidth()), responsiveY(personaje.getHeight()));
    }

    public void update(float dt){
        velocity.add(0, gravity, 0);
        velocity.scl(dt);
        position.add(0, velocity.y, 0);
        velocity.scl(1/dt);
        playerCollision.setPosition(position.x, position.y);
        colisionPoder.setPosition(position.x, position.y);
    }

    public Vector3 getPosition() {
        return position;
    }

    public Texture getPersonaje() {
            return personajeElejido;
        }

    public Vector3 getVelocity() { return velocity; }

    public void moveLeft(){
        position.x -= responsiveX(9);
        if(position.x < 0 - personaje.getWidth()){
            position.x = BattleJump.width;
        }
    }

    public void moveRight(){
        position.x += responsiveX(9);
        if(position.x > BattleJump.width){
            position.x = 0 - personaje.getWidth();
        }
    }

    public void burbuja(){
        personajeElejido = burbuja;
        if(BattleJump.height > 1280){
            velocity.y = responsiveY(2500);
        } else {
            velocity.y = responsiveY(3300);
        }
    }

    public void reboteTrampolin(){
        if(BattleJump.height > 1280){
            velocity.y = responsiveY(2000);
        } else {
            velocity.y = responsiveY(2800);
        }

    }

    public void normal(){
        elejirPersonaje();
    }

    public void jump(){
        if(BattleJump.height > 1280){
            velocity.y = 1200;
        } else {
            velocity.y = responsiveY(2000);
        }
    }

    public Rectangle getPlayerCollision(){
        return playerCollision;
    }

    public Rectangle getPlayerCollisionPoder(){
        return colisionPoder;
    }

    public void dispose(){
        personaje.dispose();
    }

    public int responsiveX (int tamaño){
        int tamañoX;
        tamañoX = (BattleJump.width * tamaño) / 1440;
        return tamañoX;
    }

    public int responsiveY (int tamaño){
        int tamañoY;
        tamañoY = (BattleJump.height * tamaño) / 2560;
        return tamañoY;
    }

    private void elejirPersonaje(){
        switch (seleccionado){
            case 0:
                personajeElejido = personaje;
                break;
            case 1:
                personajeElejido = personaje1;
                break;
            case 2:
                personajeElejido = personaje2;
                break;
            case 3:
                personajeElejido = personaje3;
                break;
        }
    }
}
