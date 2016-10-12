package com.proyecto.battlejump.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.proyecto.battlejump.BattleJump;
import com.proyecto.battlejump.sprites.Burbuja;
import com.proyecto.battlejump.sprites.Estrella;
import com.proyecto.battlejump.sprites.Nube;
import com.proyecto.battlejump.sprites.Plataforms.BrokenPlatform;
import com.proyecto.battlejump.sprites.Plataforms.PinchePlatform;
import com.proyecto.battlejump.sprites.Plataforms.Plataform;
import com.proyecto.battlejump.sprites.Speedy;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class PlayState extends State {
    private Texture fondo;
    private Texture suelo;
    private Texture atardecer;
    private Texture space;
    private Texture sol;
    private Texture luna;
    private Speedy personaje;

    private ArrayList<Plataform> platforms;
    private ArrayList<BrokenPlatform> plataformasrotas;
    private ArrayList<PinchePlatform> plataformaspinche;
    private  ArrayList<Estrella> estrellas;
    Nube LeftNube;
    Nube RightNube;
    Burbuja burbuja;
    int momento = 1;

    private int yPlatPos = 0;
    private int platSpace = responsiveY(200);
    private int cantplat = 15;
    private int platRotaPos;
    private int platpinchepos;
    private int comienzoAtardecer = responsiveY(9993);
    private int comienzoNoche;
    float dia = 0;
    boolean espacio;
    boolean existeatardecer;
    boolean existeEstrellas;
    boolean existeLuna;
    boolean existeSol;
    int dificultad;
    float posicionluna;
    float posicionSol;
    private Random rand;

    private int puntaje;
    private BitmapFont puntajetext;
    GlyphLayout puntajeLayout;
    float puntajeHeight;

    public PlayState(GameStateManager gsm) {
        super(gsm);

        puntajeLayout = new GlyphLayout();
        puntajetext = new BitmapFont();

        personaje = new Speedy(BattleJump.width / 2, 0);
        cam.setToOrtho(false, BattleJump.width, BattleJump.height);
        espacio = false;
        existeEstrellas = false;
        existeLuna = false;
        existeatardecer = false;
        existeSol = false;
        dificultad = 0;
        fondo = new Texture("Fondo_Tierra-Cielo_Cielo.png");
        suelo = new Texture("Fondo_Tierra-Cielo_Pasto.png");
        atardecer = new Texture("Atardecer.png");
        space = new Texture("Fondo_Espacio_Fondo.png");
        sol = new Texture("Luna.png");
        luna = new Texture("Luna.png");
        comienzoNoche  = comienzoAtardecer + atardecer.getHeight();

        platforms = new ArrayList<Plataform>();
        plataformasrotas = new ArrayList<BrokenPlatform>();
        plataformaspinche = new ArrayList<PinchePlatform>();
        estrellas = new ArrayList<Estrella>();

        rand = new Random();
        platRotaPos = rand.nextInt(cantplat);
        platpinchepos = rand.nextInt(cantplat);
        if(platRotaPos == platpinchepos){
            while(platRotaPos == platpinchepos){
                platpinchepos = rand.nextInt(cantplat);
            }
        }

        for(int i = 1; i <= cantplat; i++){
            if(i == platRotaPos){
                plataformasrotas.add(new BrokenPlatform(yPlatPos));
            } else if (i == platpinchepos){
                plataformaspinche.add(new PinchePlatform(yPlatPos));
            } else {
                platforms.add(new Plataform(yPlatPos));
            }

            yPlatPos += platSpace;
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

        puntajetext.getData().setScale(5, 5);
        puntajeLayout.setText(puntajetext, String.valueOf(puntaje));
        puntajeHeight = puntajeLayout.height;

        if(!existeSol){
            posicionSol = cam.position.y - (cam.viewportHeight / 2) - sol.getHeight() - 50;
            existeSol = true;
        }

        if(momento == 2 && dificultad == 0){
            platSpace += responsiveY(50);
            plataformasrotas.add(new BrokenPlatform(yPlatPos));
            yPlatPos += platSpace;
            dificultad = 1;
        }

        if(personaje.getPosition().y == comienzoNoche && dificultad == 1){
            plataformaspinche.add(new PinchePlatform(yPlatPos));
            yPlatPos += platSpace;
            dificultad = 2;
        }

        if(personaje.getPosition().y > cam.position.y + responsiveY(300) && personaje.getVelocity().y > 0){
            float camposanterior = cam.position.y;
            cam.position.y = personaje.getPosition().y - responsiveY(300);
            float campos = cam.position.y;
            if(existeatardecer){
                posicionluna += campos - camposanterior - responsiveY(1);
            }
            puntaje = Math.round(personaje.getPosition().y / 10);
            posicionSol += campos - camposanterior - responsiveY(1);
            if(espacio) {
                for(Estrella star : estrellas) {
                    star.getEstposition().y += campos - camposanterior;
                }
            }
            LeftNube.getPosLeftNube().y -= responsiveY(2);
            RightNube.getPosRightNube().y -= responsiveY(2);
        }

        for(Plataform plat : platforms){
            if (cam.position.y - (cam.viewportHeight / 2) > plat.getPosplataforma().y){
                plat.reposition(yPlatPos);
                yPlatPos += platSpace;
            }

            if(plat.collides(personaje.getPlayerCollision()) && personaje.getVelocity().y <= 0 || personaje.getPosition().y <= 0){
                personaje.jump();
            }
        }

        for(int i = 0; i < plataformasrotas.size(); i++) {
            Plataform brplat = plataformasrotas.get(i);
            if (brplat != null){
                if (cam.position.y - (cam.viewportHeight / 2) > brplat.getPosplataforma().y) {
                    brplat.reposition(yPlatPos);
                    yPlatPos += platSpace;
                }

                if(brplat.collides(personaje.getPlayerCollision()) && personaje.getVelocity().y <= 0){
                    personaje.jump();
                    plataformasrotas.remove(brplat);
                    plataformasrotas.add(new BrokenPlatform(yPlatPos));
                    yPlatPos += platSpace;
                }
            }
        }

        for(PinchePlatform pncplat : plataformaspinche) {
            if (cam.position.y - (cam.viewportHeight / 2) > pncplat.getPosplataforma().y) {
                pncplat.reposition(yPlatPos);
                yPlatPos += platSpace;
            }

            if(pncplat.collides(personaje.getPlayerCollision()) && personaje.getVelocity().y <= 0){
                gsm.set(new RetryState(gsm, puntaje, cam.position.y));
            }
        }

        if(cam.position.y - (cam.viewportHeight / 2) > LeftNube.getPosLeftNube().y + LeftNube.getNube().getHeight()){
            LeftNube.repositionLeft(Math.round(cam.position.y + (cam.viewportHeight / 2)));
        }

        if(cam.position.y - (cam.viewportHeight / 2) > RightNube.getPosRightNube().y + RightNube.getNube().getHeight()){
            RightNube.repositionRight(Math.round(cam.position.y + (cam.viewportHeight / 2)));
        }

        if (cam.position.y - (cam.viewportHeight / 2) > personaje.getPosition().y + responsiveY(50)){
            gsm.set(new RetryState(gsm, puntaje, cam.position.y));
        }

        if(cam.position.y + (cam.viewportHeight / 2) >= comienzoAtardecer) {
            momento = 2;
            espacio = true;
            existeatardecer = true;
        }
        if(cam.position.y - (cam.viewportHeight / 2) >= comienzoNoche){
            momento = 3;
        }

        if(espacio && !existeEstrellas){
            for(int i = 1; i <= 30; i++) {
                estrellas.add(new Estrella(Math.round(cam.position.y - (cam.viewportHeight / 2)), Math.round(cam.position.y + (cam.viewportHeight / 2))));
                existeEstrellas = true;
            }
        }

        if(espacio && !existeLuna){
            posicionluna = cam.position.y + (cam.viewportHeight / 2);
            existeLuna = true;
        }

        cam.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(sol, BattleJump.width - sol.getWidth() - responsiveX(100), posicionSol);
        switch (momento){
            case 1:
                sb.draw(fondo, 0, cam.position.y - (cam.viewportHeight / 2), BattleJump.width, BattleJump.height);
                sb.draw(suelo, 0, 0, BattleJump.width, BattleJump.height);
                sb.draw(LeftNube.getNube(), LeftNube.getPosLeftNube().x, LeftNube.getPosLeftNube().y, responsiveX(LeftNube.getNube().getWidth()), responsiveY(LeftNube.getNube().getHeight()));
                sb.draw(RightNube.getNube(), RightNube.getPosRightNube().x, RightNube.getPosRightNube().y, responsiveX(RightNube.getNube().getWidth()), responsiveY(RightNube.getNube().getHeight()));
                dia = cam.position.y - (cam.viewportHeight / 2);
                break;
            case 2:
                sb.draw(fondo, 0, dia, BattleJump.width, BattleJump.height);
                sb.draw(atardecer, 0, comienzoAtardecer, BattleJump.width, atardecer.getHeight());
                sb.draw(space, 0, comienzoNoche, BattleJump.width, BattleJump.height);
                sb.draw(luna, BattleJump.width - luna.getWidth() - responsiveX(100), posicionluna, responsiveX(luna.getWidth()), responsiveY(luna.getHeight()));
                break;
            case 3:
                sb.draw(space, 0, cam.position.y - (cam.viewportHeight / 2), BattleJump.width, BattleJump.height);
                for(Estrella star : estrellas){
                    sb.draw(star.getEstrella(), star.getEstposition().x, star.getEstposition().y,responsiveX(star.getEstrella().getWidth()), responsiveY(star.getEstrella().getHeight()));
                }
                sb.draw(luna, BattleJump.width - luna.getWidth() - luna.getWidth() / 2, posicionluna, responsiveX(luna.getWidth()), responsiveY(luna.getHeight()));
                break;
        }

        sb.draw(personaje.getPersonaje(), personaje.getPosition().x, personaje.getPosition().y, personaje.responsiveX(personaje.getPersonaje().getWidth()), personaje.responsiveY(personaje.getPersonaje().getHeight()));

        for(int i = 0; i < platforms.size(); i++){
            Plataform plat = platforms.get(i);
            sb.draw(plat.getPlataforma(), plat.getPosplataforma().x, plat.getPosplataforma().y, plat.responsiveX(plat.getPlataforma()), plat.responsiveY(plat.getPlataforma()));
        }

        for(int i = 0; i < plataformasrotas.size(); i++){
            if(plataformasrotas.get(i) != null){
                BrokenPlatform brplat = plataformasrotas.get(i);
                sb.draw(brplat.getPlatRota(), brplat.getPosplataforma().x, brplat.getPosplataforma().y, brplat.responsiveX(brplat.getPlatRota()), brplat.responsiveY(brplat.getPlatRota()));
            }
        }

        for(int i = 0; i < plataformaspinche.size(); i++){
            if(plataformaspinche.get(i) != null){
                PinchePlatform pncplat = plataformaspinche.get(i);
                sb.draw(pncplat.getPlatPinches(), pncplat.getPosplataforma().x, pncplat.getPosplataforma().y, pncplat.responsiveX(pncplat.getPlatPinches()), pncplat.responsiveY(pncplat.getPlatPinches()));
            }
        }

        puntajetext.draw(sb, puntajeLayout, 0, (cam.position.y + (cam.viewportHeight / 2)) - puntajeHeight);

        sb.end();
    }

    @Override
    public void dispose() {
        fondo.dispose();
        atardecer.dispose();
        suelo.dispose();
        personaje.dispose();
        for(Plataform plat : platforms){
            plat.dispose();
        }
        for(BrokenPlatform brplat : plataformasrotas){
            brplat.dispose();
        }
        for(PinchePlatform pncplat: plataformaspinche){
            pncplat.dispose();
        }
        LeftNube.dispose();
        RightNube.dispose();
        for(Estrella star : estrellas){
            star.dispose();
        }
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