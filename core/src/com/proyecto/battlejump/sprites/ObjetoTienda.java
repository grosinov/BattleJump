package com.proyecto.battlejump.sprites;

import com.badlogic.gdx.graphics.Texture;

public class ObjetoTienda {
    private Texture personaje;
    private int Valor;
    private boolean comprado;
    private int id;

    public ObjetoTienda(Texture personaje, int valor, boolean comprado, int id) {
        this.personaje = personaje;
        Valor = valor;
        this.comprado = comprado;
        this.id = id;
    }
}
