package com.badlogic.drop;

import java.io.IOException;
import java.util.HashMap;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.TextureDict;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.RotateToAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.doodleapp.animation.FAnimation;
import com.doodleapp.animation.FrameElement;
import com.doodleapp.animation.KFrame;
import com.doodleapp.animation.Parser;
import com.doodleapp.animation.xfl.Xfl;

public class Test implements ApplicationListener, InputProcessor {
	SpriteBatch batch;
	// private Stage stage;
	// TextureAtlas atlas;
	// Image play;
	// Image options;
	Texture actor;
	FAnimation animtaion;
	float[] spriteVertices;
	Sprite sprite;
	float time;
	TextureAtlas boxAtlas;
	FAnimation box;
	FAnimation hong;
	

	@Override
	public void create() {
		// TODO Auto-generated method stub
		batch = new SpriteBatch();

		FileHandle xml = Gdx.files.internal("tiekuai.xml");
		Parser xflparser = new Parser();
		Xfl xfl = null;
		try {
			xfl = xflparser.parse(xml);
			if (xfl == null) {
				System.out.println("xfl parse error!!!");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		time = 0;
		Texture suo = new Texture(Gdx.files.internal("suo.png"));
		Texture liantiao = new Texture(Gdx.files.internal("liantiao.png"));
		Texture suokou = new Texture(Gdx.files.internal("suokou.png"));
		Texture suokou2 = new Texture(Gdx.files.internal("suokou2.png"));
		Texture tiekuai = new Texture(Gdx.files.internal("tiekuai.png"));

		Texture tielian = new Texture(Gdx.files.internal("tielian.png"));
		HashMap<String, Texture> map = new HashMap<String, Texture>();
		map.put("suo", suo);
		map.put("liantiao", liantiao);
		map.put("suokou", suokou);
		map.put("suokou2", suokou2);
		map.put("tiekuai", tiekuai);
		map.put("tielian", tielian);

		animtaion = new FAnimation(xfl, map, 317.45f, 242.25f, FAnimation.LOOP);
		// KFrame kframe = animtaion.getKeyFrame(Gdx.graphics.getDeltaTime());
		KFrame kframe = animtaion.keyFrames[4];
		//kframe.Debug();

		actor = new Texture(Gdx.files.internal("actor1.gif"));
		sprite = new Sprite(actor);
		Color color = sprite.getColor();
		color.a *= 1.0;
		sprite.setColor(color);
		spriteVertices = sprite.getVertices();
		spriteVertices[0] = 0;
		spriteVertices[1] = 0;
		spriteVertices[3] = 0;
		spriteVertices[4] = 0;
		
		spriteVertices[5] = 0;
		spriteVertices[6] = actor.getHeight();
		spriteVertices[8] = 0;;
		spriteVertices[9] = 1.5f;
		
		spriteVertices[10] = actor.getWidth();
		spriteVertices[11] = actor.getHeight();
		spriteVertices[13] = 1.5f;
		spriteVertices[14] = 1.5f;
		
		spriteVertices[15] = actor.getWidth();
		spriteVertices[16] = 0;
		spriteVertices[18] = 1.5f;
		spriteVertices[19] = 0;

		boxAtlas = new TextureAtlas(Gdx.files.internal("box.atlas"));

		HashMap<String, Texture> boxmap = new HashMap<String, Texture>();
		boxmap.put("box", boxAtlas.findRegion("box").getTexture());
		boxmap.put("baiyang", boxAtlas.findRegion("baiyang").getTexture());
		boxmap.put("chunv", boxAtlas.findRegion("chunv").getTexture());
		boxmap.put("juxie", boxAtlas.findRegion("juxie").getTexture());
		boxmap.put("tianping", boxAtlas.findRegion("tianping").getTexture());
		boxmap.put("tianxie", boxAtlas.findRegion("tianxie").getTexture());
		boxmap.put("jinniu", boxAtlas.findRegion("jinniu").getTexture());
		boxmap.put("mojie", boxAtlas.findRegion("mojie").getTexture());
		boxmap.put("shuangzi", boxAtlas.findRegion("shuangzi").getTexture());
		boxmap.put("sheshou", boxAtlas.findRegion("sheshou").getTexture());
		boxmap.put("shuangnv", boxAtlas.findRegion("shuangnv").getTexture());
		boxmap.put("shuipin", boxAtlas.findRegion("shuipin").getTexture());
		boxmap.put("shizi", boxAtlas.findRegion("shizi").getTexture());
		
		try {
			xfl = xflparser.parse(Gdx.files.internal("box.xml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		box = new FAnimation(xfl, boxmap, 100, 100, FAnimation.LOOP);
		
		HashMap<String, Texture> hongmap = new HashMap<String, Texture>();
		hongmap.put("guang", new Texture(Gdx.files.internal("guang.png")));
		hongmap.put("guang2", new Texture(Gdx.files.internal("guang2.png")));
		hongmap.put("hong", new Texture(Gdx.files.internal("hong.png")));
		hongmap.put("xing", new Texture(Gdx.files.internal("xing.png")));
		try {
			xfl = xflparser.parse(Gdx.files.internal("hong.xml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		hong = new FAnimation(xfl, hongmap, 319.15f, 241.20f, FAnimation.LOOP);
	//	hong.keyFrames[3].Debug();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		// batch.setProjectionMatrix(cam.combined);
		Gdx.input.setInputProcessor(this);

	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		time += Gdx.graphics.getDeltaTime();
		KFrame kframe1 = animtaion.getKeyFrame(time);
		KFrame kframe2 = box.getKeyFrame(time);
		KFrame kframe3 = hong.getKeyFrame(time);
		batch.begin();
		kframe1.draw(batch, 100, 100, 85, 85);
//		batch.draw(actor, spriteVertices, 0, 20);
		// batch.draw(actor, 0, 0, 100, 100);
	//	kframe2.draw(batch, 0, 0, 85, 85);
	//	kframe3.draw(batch, 0, 0, 85, 85);
		batch.end();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub

		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}