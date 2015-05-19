package com.labengine.examples.games;

import android.view.MotionEvent;
import android.view.View;

import com.labengine.audio.LESound;
import com.labengine.base.primitive.LEPoint;
import com.labengine.base.primitive.LERect;
import com.labengine.base.scene.LEScene;
import com.labengine.base.sprite.LESimpleSprite;
import com.labengine.engine.LESettings;
import com.labengine.engine.camera.LECamera;
import com.labengine.engine.handler.LETimerCounter;
import com.labengine.examples.R;
import com.labengine.physics.collision.LECollisionHandler;
import com.labengine.physics.collision.LERectBody;
import com.labengine.tools.LEObjectPool;
import com.labengine.tools.debug.LEDebug;
import com.labengine.ui.activity.LEBaseGameActivity;

public class SpaceBattle extends LEBaseGameActivity {

	static float w, h;
	int k = 0;
	private LEScene scene;
	LESimpleSprite background, player, bullet, enemy, bonus, health, shield;
	LESound boom, bang;
	LEObjectPool<LESimpleSprite> bulletPool, enemyPool;
	Enemy e;
	LERectBody pb;
	LERect rect;
	LECollisionHandler c;
	LEPoint p1, p2;

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

		boom = new LESound(R.raw.boom, "boom.wav", false);
		bang = new LESound(R.raw.bang, "bang.mp3", false);
		scene = new LEScene();
		bulletPool = new LEObjectPool<LESimpleSprite>() {
			@Override
			public LESimpleSprite onCreateItemIfEmpty() {
				return new LESimpleSprite("bullet.png");
			}
		};
		enemyPool = new LEObjectPool<LESimpleSprite>() {
			@Override
			public LESimpleSprite onCreateItemIfEmpty() {
				return new LESimpleSprite("enemy.png");
			}
		};
		background = new LESimpleSprite("space-back.jpg");
		player = new LESimpleSprite("spaceship.png");
		for (int i = 0; i < 10; i++) {
			enemyPool.addItemToPool(new LESimpleSprite("enemy.png"));
			bulletPool.addItemToPool(new LESimpleSprite("bullet.png"));
		}
		// c = new LECollision(player.pb, enemy.pb);
		return scene;
	}

	@Override
	public void onLoadScene() {
		scene.setSpriteBackground(background);
		player.setCenterXY(w/2, h/2);
		scene.addItem(player);
		new Enemy(enemy, scene, 1000);
	}

	@Override
	public void onPause() {
		System.exit(0);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			LESimpleSprite ss = bulletPool.getItemFromPool(k);
			ss.setCenterXY(player.getX() + player.getWidth() / 2, player.getY()
					+ player.getHeight() / 2);
			ss.setDy(0);
			ss.setDy(-15);
			scene.addItem(ss);
			//boom.forceStart();
			k++;
			if (k == 9) {
				k = 0;
			}
			if (!isInWindow(ss.getX(), ss.getY())) {
				bulletPool.addItemToPool(ss);
				LEDebug.makeDebugToast("!isInWindow");
			}
		}
		if (event.getAction() == MotionEvent.ACTION_MOVE) {
				player.setCenterXY(event.getX(), event.getY());
		}
		return true;
	}

	public boolean isInWindow(float x, float y) {
		return x >= 0 && x < w && y >= 0 && y < h;
	}

	class Enemy implements Runnable {

		Thread thread;
		LESimpleSprite enemy;
		LEScene scene;
		LETimerCounter count;
		int d, k = 0;

		Enemy(LESimpleSprite enemy, LEScene scene, int d) {
			thread = new Thread(this);
			this.enemy = enemy;
			this.scene = scene;
			this.d = d;
			thread.start();
		}

		public void createEnemy(int k) {
			LESimpleSprite ss = enemyPool.getItemFromPool(k);
			ss.setCenterXY((float) Math.random() * w, 0);
			ss.setDy(5);
			scene.addItem(ss);
			//collison(ss);
		}

		public void collison(LESimpleSprite enemy) {
			c = new LECollisionHandler(player.rectb, enemy.rectb);
			if (c.colRectDetection()) {
				// enemy.show(false);
			}
		}

		@Override
		public void run() {
			try {
				while (true) {
					createEnemy(k);
					Thread.sleep(d);
					k++;
					if (k == 9) {
						k = 0;
					}
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
