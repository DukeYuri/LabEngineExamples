package com.labengine.examples.touch;

import android.view.MotionEvent;
import android.view.View;

import com.labengine.base.primitive.LECircle;
import com.labengine.base.primitive.LERect;
import com.labengine.base.scene.LEScene;
import com.labengine.base.sprite.LESimpleSprite;
import com.labengine.engine.LESettings;
import com.labengine.engine.camera.LECamera;
import com.labengine.ui.activity.LEBaseGameActivity;

public class TouchCreate extends LEBaseGameActivity {

	private LEScene scene;
	LERect rects;
	LECircle c;
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
		logo = new LESimpleSprite("sum-logo.png");
		background = new LESimpleSprite("menu-back.png");
		return scene;
	}

	@Override
	public void onLoadScene() {
		scene.setSpriteBackground(background);
		logo.setX(w - logo.getWidth());
		scene.addItem(logo);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_MOVE) {
			c = new LECircle(event.getX(), event.getY(), 5);
			scene.addItem(c);
		}
		return true;
	}

}
