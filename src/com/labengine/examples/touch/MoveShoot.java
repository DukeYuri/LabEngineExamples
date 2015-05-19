package com.labengine.examples.touch;

import android.view.MotionEvent;
import android.view.View;

import com.labengine.base.scene.LEScene;
import com.labengine.base.sprite.LESimpleSprite;
import com.labengine.engine.LESettings;
import com.labengine.engine.camera.LECamera;
import com.labengine.ui.activity.LEBaseGameActivity;

public class MoveShoot extends LEBaseGameActivity {

	static float w, h;
	private LEScene scene;
	LESimpleSprite background, player;

	@Override
	public LECamera onLoadEngine() {
		LESettings.AutoScale = true;
		LESettings.setDefaultWH(480, 800);
		LESettings.Init();
		LESettings.setSound(true);
		LESettings.setFullScreen(true);
		return new LECamera(LESettings.CurrentScreenWidth, LESettings.CurrentScreenHeight);
	}

	@Override
	public LEScene onLoadResource() {
		w = LESettings.CurrentScreenWidth;
		h = LESettings.CurrentScreenHeight;

		scene = new LEScene(0, 0, 1);
		background = new LESimpleSprite("space-back.jpg");
		player = new LESimpleSprite("spaceship.png");
		return scene;
	}

	@Override
	public void onLoadScene() {
		scene.setSpriteBackground(background);
		player.setCenterXY(w/2, h/2);
		scene.addItem(player);
	}

	@Override
	public void onPause() {
		System.exit(0);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			LESimpleSprite ss = new LESimpleSprite("bullet.png");
			ss.setCenterXY(player.getX() + player.getWidth() / 2, player.getY()
					+ player.getHeight() / 2);
			ss.setDy(0);
			ss.setDy(-15);
			scene.addItem(ss);
			
		}
		
		if (event.getAction() == MotionEvent.ACTION_MOVE) {
				player.setCenterXY(event.getX(), event.getY());
		}
		return true;
	}

}
