package com.labengine.examples.games;

import android.view.MotionEvent;
import android.view.View;

import com.labengine.base.scene.LEScene;
import com.labengine.base.sprite.LESimpleSprite;
import com.labengine.engine.LESettings;
import com.labengine.engine.camera.LECamera;
import com.labengine.physics.collision.LECollisionHandler;
import com.labengine.ui.activity.LEBaseGameActivity;

public class Pong extends LEBaseGameActivity {

	static float w, h, dx, dy;
	private LEScene scene;
	LESimpleSprite player, enemy, ball, wall, wall2;
	LECollisionHandler c, c2;

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
		scene = new LEScene();

		player = new LESimpleSprite("platform_pong.png");
		enemy = new LESimpleSprite("platform_pong.png");
		ball = new LESimpleSprite("ball_pong.png");
		wall = new LESimpleSprite("wall.png");
		wall2 = new LESimpleSprite("wall.png");
		c = new LECollisionHandler(wall.rectb, ball.rectb);
		c2 = new LECollisionHandler(wall.rectb, ball.rectb);
		dx = (float) Math.random() * 5;
		dy = (float) Math.random() * 5;
		return scene;
	}

	@Override
	public void onLoadScene() {
		scene.addItem(wall);
		scene.addItem(wall2);
		scene.addItem(player);
		scene.addItem(enemy);
		scene.addItem(ball);
		wall.resize(LESettings.CurrentScreenWidth, wall.getHeight());
		wall2.setY(h - wall2.getHeight());
		wall2.resize(LESettings.CurrentScreenWidth, wall2.getHeight());
		ball.setCenterXY(w / 2, h / 2);
		enemy.setX(w - enemy.getWidth());
		enemy.setCenterY(h / 2);
		player.setCenterY(h / 2);
		ball.setDx(dx);
		ball.setDy(dy);
		// new Collision();
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_MOVE) {

			if (player.isSelected(event.getX(), event.getY())) {
				player.setCenterY(event.getY());
				if (c.colRectDetection()) {
					ball.setDx(-dx);

				}
			}

			if (enemy.isSelected(event.getX(), event.getY())) {
				enemy.setCenterY(event.getY());
				if (c2.colRectDetection()) {
					ball.setDx(-dx);
					// ball.setDy(-dy);
				}
			}
		}
		return true;
	}

	class Collision implements Runnable {

		@Override
		public void run() {
			while (true) {
				if (c.colRectDetection()) {
					ball.setDx(-dx);
					ball.setDy(-dy);
				}
				if (c2.colRectDetection()) {
					ball.setDx(-dx);
					ball.setDy(-dy);
				}
			}
		}

	}

}
