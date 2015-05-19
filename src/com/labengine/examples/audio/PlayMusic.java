package com.labengine.examples.audio;

import android.view.MotionEvent;
import android.view.View;

import com.labengine.audio.LESound;
import com.labengine.base.scene.LEScene;
import com.labengine.base.sprite.LESimpleSprite;
import com.labengine.engine.LESettings;
import com.labengine.engine.camera.LECamera;
import com.labengine.examples.R;
import com.labengine.ui.activity.LEBaseGameActivity;

public class PlayMusic extends LEBaseGameActivity {

	private LEScene scene;
	LESimpleSprite background, logo, play_btn;
	LESound music;
	int w, h;

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
		logo = new LESimpleSprite("sum-logo.png");
		background = new LESimpleSprite("menu-back.png");
		play_btn = new LESimpleSprite("play_btn.png");
		music = new LESound(R.raw.track1, "track1.mp3", true);
		return scene;
	}

	@Override
	public void onLoadScene() {
		scene.setSpriteBackground(background);
		logo.setX(w - logo.getWidth());
		scene.addItem(logo);
		play_btn.setCenterXY(w / 2, h / 2);
		scene.addItem(play_btn);
	}
	
	@Override
	public void onPause() {
		super.onPause();
		music.stop();
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN)
			if (play_btn.isSelected(event.getX(), event.getY())) {
				music.forceStart();
			} else music.stop();
		return true;
	}

}
