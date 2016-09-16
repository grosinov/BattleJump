package com.proyecto.battlejump.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.proyecto.battlejump.BattleJump;

public class Speedy {
    private static final int gravity = -15;
    private Vector3 position;
    private Vector3 velocity;
    private Rectangle playerCollision;

    private Texture personaje;

    public Speedy(int x, int y){
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);
        personaje = new Texture("personaje.png");
        playerCollision = new Rectangle(x, y, personaje.getWidth(), 2);
    }

    public void update(float dt){
        velocity.add(0, gravity, 0);
        velocity.scl(dt);
        position.add(0, velocity.y, 0);
        velocity.scl(1/dt);
        playerCollision.setPosition(position.x, position.y);
    }

    public Vector3 getPosition() {
        return position;
    }

        public Texture getPersonaje() {
            return personaje;
        }

    public Vector3 getVelocity() { return velocity; }

    public void moveLeft(){
        position.x -= 10;
        if(position.x < 0 - personaje.getWidth()){
            position.x = BattleJump.width;
        }
    }

    public void moveRight(){
        position.x += 10;
        if(position.x > BattleJump.width){
            position.x = 0;
        }
    }

    public void jump(){
        velocity.y = 1000;
    }

    public Rectangle getPlayerCollision(){
        return playerCollision;
    }

    public void dispose(){
        personaje.dispose();
    }
}
