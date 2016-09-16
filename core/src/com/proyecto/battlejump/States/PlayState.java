package com.proyecto.battlejump.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.proyecto.battlejump.BattleJump;
import com.proyecto.battlejump.sprites.Burbuja;
import com.proyecto.battlejump.sprites.Estrella;
import com.proyecto.battlejump.sprites.Nube;
import com.proyecto.battlejump.sprites.Plataforms.BrokenPlatform;
import com.proyecto.battlejump.sprites.Plataforms.Plataform;
import com.proyecto.battlejump.sprites.Speedy;

import java.util.ArrayList;
import java.util.Random;

public class PlayState extends State {
    private Texture fondo;
    private Texture suelo;
    private Texture space;
    private Speedy personaje;

    private ArrayList<Plataform> platforms;
    private ArrayList<BrokenPlatform> plataformasrotas;
    private  ArrayList<Estrella> estrellas;
    Nube LeftNube;
    Nube RightNube;
    Burbuja burbuja;

    private int yPlatPos = 0;
    private int cantplat = 20;
    private int platRotaPos;
    boolean espacio;
    private Random rand;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        personaje = new Speedy(BattleJump.width / 2, 0);
        cam.setToOrtho(false, BattleJump.width, BattleJump.height);
        espacio = false;
        fondo = new Texture("Fondo_Tierra-Cielo_Cielo.png");
        suelo = new Texture("Fondo_Tierra-Cielo_Pasto.png");
        space = new Texture("Fondo_Espacio_Fondo.png");

        platforms = new ArrayList<Plataform>();
        plataformasrotas = new ArrayList<BrokenPlatform>();
        estrellas = new ArrayList<Estrella>();

        rand = new Random();
        platRotaPos = rand.nextInt(cantplat);
        for(int i = 1; i <= cantplat; i++){
            if(i == platRotaPos){
                plataformasrotas.add(new BrokenPlatform(yPlatPos));
            } else {
                platforms.add(new Plataform(yPlatPos));
            }

            yPlatPos += 150;
        }

        for(int i = 1; i <= 8; i++){
            estrellas.add(new Estrella());
        }

        LeftNube = new Nube();
        RightNube = new Nube();
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.isTouched()){
            if(Gdx.input.getX() < Gdx.graphics.getWidth() / 2){
                personaje.moveLeft();
            } else {
                personaje.moveRight();
            }
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        personaje.update(dt);

        if(personaje.getPosition().y > cam.position.y + 300){
            if(personaje.getVelocity().y > 0){
                cam.position.y = personaje.getPosition().y - 300;
            }
            LeftNube.getPosLeftNube().y -= 2;
            RightNube.getPosRightNube().y -= 2;
        }

        for(Plataform plat : platforms){
            if (cam.position.y - (cam.viewportHeight / 2) > plat.getPosplataforma().y){
                plat.reposition(yPlatPos);
                yPlatPos += 150;
            }

            if(plat.collides(personaje.getPlayerCollision()) && personaje.getVelocity().y <= 0 || personaje.getPosition().y <= 0){
                personaje.jump();
            }
        }

        for(BrokenPlatform brplat : plataformasrotas) {
            if (cam.position.y - (cam.viewportHeight / 2) > brplat.getPosplataforma().y) {
                brplat.reposition(yPlatPos);
                yPlatPos += 150;
            }

            if(brplat.collides(personaje.getPlayerCollision()) && personaje.getVelocity().y <= 0){
                personaje.jump();
                plataformasrotas.remove(brplat);
                plataformasrotas.add(new BrokenPlatform(yPlatPos));
                yPlatPos += 150;
            }
        }

        for(Estrella star: estrellas) {
            if (cam.position.y - (cam.viewportHeight / 2) > star.getEstposition().y) {
                star.reposition();
            }
        }

        if(cam.position.y - (cam.viewportHeight / 2) > LeftNube.getPosLeftNube().y + LeftNube.getNube().getHeight()){
            LeftNube.repositionLeft(Math.round(cam.position.y + (cam.viewportHeight / 2)));
        }

        if(cam.position.y - (cam.viewportHeight / 2) > RightNube.getPosRightNube().y + RightNube.getNube().getHeight()){
            RightNube.repositionRight(Math.round(cam.position.y + (cam.viewportHeight / 2)));
        }

        if (cam.position.y - (cam.viewportHeight / 2) > personaje.getPosition().y + 50){
            gsm.set(new PlayState(gsm));
        }

        if(personaje.getPosition().y > 10000){
            espacio = true;
        }

        cam.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        if(espacio){
            //sb.draw(space, 0, cam.position.y - (cam.viewportHeight / 2), BattleJump.width, BattleJump.height);
            for(Estrella star : estrellas){
                sb.draw(star.getEstrella(), star.getEstposition().x, star.getEstposition().y, star.getEstrella().getWidth(), star.getEstrella().getHeight());
            }
        } else{
            sb.draw(fondo, 0, cam.position.y - (cam.viewportHeight / 2), BattleJump.width, BattleJump.height);
            sb.draw(suelo, 0, 0, BattleJump.width, BattleJump.height);
            sb.draw(LeftNube.getNube(), LeftNube.getPosLeftNube().x, LeftNube.getPosLeftNube().y);
            sb.draw(RightNube.getNube(), RightNube.getPosRightNube().x, RightNube.getPosRightNube().y);
        }
        sb.draw(personaje.getPersonaje(), personaje.getPosition().x, personaje.getPosition().y);
        for(int i = 0; i < platforms.size(); i++){
            Plataform plat = platforms.get(i);
            sb.draw(plat.getPlataforma(), plat.getPosplataforma().x, plat.getPosplataforma().y);
        }

        for(int i = 0; i < plataformasrotas.size(); i++){
            if(plataformasrotas.get(i) != null){
                BrokenPlatform brplat = plataformasrotas.get(i);
                sb.draw(brplat.getPlatRota(), brplat.getPosplataforma().x, brplat.getPosplataforma().y);
            }
        }
        sb.end();
    }

    @Override
    public void dispose() {
        fondo.dispose();
        suelo.dispose();
        personaje.dispose();
        for(Plataform plat : platforms){
            plat.dispose();
        }
        LeftNube.dispose();
        RightNube.dispose();
    }
}
