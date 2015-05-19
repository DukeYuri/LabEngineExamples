package com.labengine.examples.primitives;

import android.view.MotionEvent;
import android.view.View;

import com.labengine.base.primitive.LECircle;
import com.labengine.base.scene.LEScene;
import com.labengine.base.sprite.LESimpleSprite;
import com.labengine.engine.LESettings;
import com.labengine.engine.camera.LECamera;
import com.labengine.ui.activity.LEBaseGameActivity;

public class DrawingCircle extends LEBaseGameActivity {

	private LEScene scene;
	LECircle[] circles;
	LESimpleSprite background, logo;
	int w, h;

	@Override
	public LECamera onLoadEngine() {
		LESettings.AutoScale = true;
		LESettings.setDefaultWH(480, 800);
		LESettings.Init();
		LESettings.setSound(false);
		LESettings.setFullScreen(true);
		return new LECamera(LESettings.CurrentScreenWidth, LESettings.CurrentScreenHeight);
	}

	@Override
	public LEScene onLoadResource() {
		w = LESettings.CurrentScreenWidth;
		h = LESettings.CurrentScreenHeight;
		scene = new LEScene(0, 0, 1);
		circles = new LECircle[50];
		logo = new LESimpleSprite("sum-logo.png");
		for (int i = 0; i < 50; i++) {
			circles[i] = new LECircle((float) Math.random() * w,
					(float) Math.random() * h, (float) Math.random() * 20);
		}
		background = new LESimpleSprite("menu-back.png");
		return scene;
	}

	@Override
	public void onLoadScene() {
		scene.setSpriteBackground(background);
		for (int i = 0; i < 50; i++) {
			scene.addItem(circles[i]);
		}
		logo.setX(w - logo.getWidth());
		scene.addItem(logo);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		return false;
	}

}
