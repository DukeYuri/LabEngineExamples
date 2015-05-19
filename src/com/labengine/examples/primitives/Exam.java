package com.labengine.examples.primitives;

import android.view.MotionEvent;
import android.view.View;

import com.labengine.base.scene.LEScene;
import com.labengine.engine.LESettings;
import com.labengine.engine.camera.LECamera;
import com.labengine.ui.activity.LEBaseGameActivity;

public class Exam extends LEBaseGameActivity {

	private LEScene scene;

	@Override
	public LECamera onLoadEngine() {
		LESettings.setDefaultWH(480, 800);
		LESettings.Init();
		LESettings.setSound(false);
		LESettings.setFullScreen(true);
		LESettings.AutoScale = true;
		return new LECamera(LESettings.CurrentScreenWidth, LESettings.CurrentScreenHeight);
	}

	@Override
	public LEScene onLoadResource() {
		scene = new LEScene(0, 0, 1);
		return scene;
	}

	@Override
	public void onLoadScene() {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		return false;
	}

}
