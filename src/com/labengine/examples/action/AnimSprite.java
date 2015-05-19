package com.labengine.examples.action;

import android.view.MotionEvent;
import android.view.View;

import com.labengine.base.scene.LEScene;
import com.labengine.base.sprite.LEAnimSprite;
import com.labengine.base.sprite.LESimpleSprite;
import com.labengine.engine.LESettings;
import com.labengine.engine.camera.LECamera;
import com.labengine.ui.activity.LEBaseGameActivity;

public class AnimSprite extends LEBaseGameActivity {

	private LEScene scene;
	LESimpleSprite background, logo;
	LEAnimSprite anim;
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
		anim = new LEAnimSprite("run_anim.png", 4);
		int a[] = {7, 7, 7, 7 };
		anim.setFrameLen(a);
		return scene;
	}

	@Override
	public void onLoadScene() {
		scene.setSpriteBackground(background);
		logo.setX(w - logo.getWidth());
		scene.addItem(logo);
		anim.setAnimated(true);
		anim.setCenterXY(w / 2 + anim.getWidth() / 2, h / 2);
		scene.addItem(anim);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_MOVE) {
			anim.setXY(event.getX(), event.getY());
	}
	return true;
	}

}