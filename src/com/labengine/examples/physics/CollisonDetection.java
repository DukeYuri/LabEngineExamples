package com.labengine.examples.physics;

import android.view.MotionEvent;
import android.view.View;

import com.labengine.audio.LESound;
import com.labengine.base.scene.LEScene;
import com.labengine.base.sprite.LESimpleSprite;
import com.labengine.engine.LESettings;
import com.labengine.engine.camera.LECamera;
import com.labengine.examples.R;
import com.labengine.physics.collision.LECollisionHandler;
import com.labengine.tools.debug.LEDebug;
import com.labengine.ui.activity.LEBaseGameActivity;

public class CollisonDetection extends LEBaseGameActivity {

	static float w, h;
	private LEScene scene;
	LESimpleSprite background, player, enemy, debug_btn;
	LESound s;
	LECollisionHandler c;

	@Override
	public LECamera onLoadEngine() {
		LESettings.AutoScale = true;
		LESettings.setDefaultWH(480, 800);
		LESettings.Init();
		LESettings.setSound(true);
		LESettings.setFullScreen(true);
		return new LECamera(LESettings.CurrentScreenWidth,
				LESettings.CurrentScreenHeight);
	}

	@Override
	public LEScene onLoadResource() {
		w = LESettings.CurrentScreenWidth;
		h = LESettings.CurrentScreenHeight;

		scene = new LEScene(0, 0, 1);
		background = new LESimpleSprite("space-back.jpg");
		player = new LESimpleSprite("spaceship.png");
		enemy = new LESimpleSprite("enemy.png");
		debug_btn = new LESimpleSprite("debug_btn2.png");
		c = new LECollisionHandler(player.rectb, enemy.rectb);
		s = new LESound(R.raw.bang, "bang.mp3", false);
		return scene;
	}

	@Override
	public void onLoadScene() {
		scene.setSpriteBackground(background);
		player.setCenterY(h / 2);
		scene.addItem(player);
		enemy.setCenterXY(w - enemy.getWidth(), h / 2);
		scene.addItem(enemy);
		debug_btn.setX(w - debug_btn.getWidth());
		scene.addItem(debug_btn);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_UP) {
			if (debug_btn.isSelected(event.getX(), event.getY())) {
				if (!LEDebug.debugMode) {
					LEDebug.setDebugMode(true);
				} else {
					LEDebug.setDebugMode(false);
					scene.update();
				}
			}
		}

		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			s.stop();
		}

		if (event.getAction() == MotionEvent.ACTION_MOVE) {
			if (player.isSelected(event.getX(), event.getY())) {
				player.setCenterXY(event.getX(), event.getY());
			}
			if (player.isCollision(enemy)) {
				s.forceStart();
			}

		}
		return true;
	}

}