package com.proyecto.battlejump.sprites;

import com.badlogic.gdx.Gdx;
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

    private Texture personaje;

    public Speedy(float x, float y){
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);
        personaje = new Texture("personaje.png");
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
            return personaje;
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
        personaje = null;
        personaje = new Texture("burbuja.png");
        velocity.y = responsiveY(2500);
    }

    public void reboteTrampolin(){
        velocity.y = responsiveY(2000);
    }

    public void normal(){
        personaje = null;
        personaje = new Texture("personaje.png");
    }

    public void jump(){
        velocity.y = responsiveY(1200);
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
}
