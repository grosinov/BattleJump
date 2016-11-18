package com.proyecto.battlejump;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.proyecto.battlejump.States.GameStateManager;
import com.proyecto.battlejump.States.MenuState;

public class BattleJump extends ApplicationAdapter {
	public static int width;
	public static int height;

	private GameStateManager gsm;
	private SpriteBatch batch;

	@Override
	public void create () {
		batch = new SpriteBatch();
		gsm = new GameStateManager();
		Gdx.gl.glClearColor((float)74/255, (float)167/255, (float)221/255, 0f);
		width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();

		gsm.push(new MenuState(gsm, 0));
	}

	@Override
	public void render() {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);
	}
}
