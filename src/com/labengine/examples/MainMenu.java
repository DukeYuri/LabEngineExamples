package com.labengine.examples;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.SimpleExpandableListAdapter;

import com.labengine.examples.action.AnimSprite;
import com.labengine.examples.action.MovingBall;
import com.labengine.examples.audio.PlayMusic;
import com.labengine.examples.audio.PlaySound;
import com.labengine.examples.games.Menu;
import com.labengine.examples.physics.CollisonDetection;
import com.labengine.examples.primitives.DrawingCircle;
import com.labengine.examples.primitives.DrawingLines;
import com.labengine.examples.primitives.DrawingRect;
import com.labengine.examples.primitives.DrawingSprites;
import com.labengine.examples.primitives.DrawingText;
import com.labengine.examples.touch.MoveShoot;
import com.labengine.examples.touch.TouchCreate;

public class MainMenu extends Activity {

	String[] groups = new String[] { "Primitives", "Action & Animation",
			"Touch", "Audio", "Physics", "Games" };

	String[] primitives = new String[] { "		Drawing Lines",
			"		Drawing Rectangles", "		Drawing Circles", "		Drawing Sprites", "		Drawing Text" };
	String[] action = new String[] { "		Animation Sprite", "		Moving Ball" };
	String[] audio = new String[] { "		Play Sound", "		Play Music" };
	String[] touch = new String[] { "		Move & Shoot", "		Touch & Create" };
	String[] physics = new String[] { "		Collision Detection" };
	String[] games = new String[] { "		Game Menu" };
	
	ArrayList<Map<String, String>> groupData;

	ArrayList<Map<String, String>> childDataItem;

	ArrayList<ArrayList<Map<String, String>>> childData;

	Map<String, String> m;

	ExpandableListView expListView;

	Intent i;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		i = new Intent(MainMenu.this, DrawingLines.class);
		groupData = new ArrayList<Map<String, String>>();
		for (String group : groups) {
			m = new HashMap<String, String>();
			m.put("groupName", group);
			groupData.add(m);
		}

		String groupFrom[] = new String[] { "groupName" };
		int groupTo[] = new int[] { android.R.id.text1 };

		childData = new ArrayList<ArrayList<Map<String, String>>>();

		childDataItem = new ArrayList<Map<String, String>>();
		for (String month : primitives) {
			m = new HashMap<String, String>();
			m.put("monthName", month);
			childDataItem.add(m);
		}
		childData.add(childDataItem);

		childDataItem = new ArrayList<Map<String, String>>();
		for (String month : action) {
			m = new HashMap<String, String>();
			m.put("monthName", month);
			childDataItem.add(m);
		}
		childData.add(childDataItem);

		childDataItem = new ArrayList<Map<String, String>>();
		for (String month : touch) {
			m = new HashMap<String, String>();
			m.put("monthName", month);
			childDataItem.add(m);
		}
		childData.add(childDataItem);

		childDataItem = new ArrayList<Map<String, String>>();
		for (String month : audio) {
			m = new HashMap<String, String>();
			m.put("monthName", month);
			childDataItem.add(m);
		}
		childData.add(childDataItem);
		
		childDataItem = new ArrayList<Map<String, String>>();
		for (String month : physics) {
			m = new HashMap<String, String>();
			m.put("monthName", month);
			childDataItem.add(m);
		}
		childData.add(childDataItem);
		
		childDataItem = new ArrayList<Map<String, String>>();
		for (String month : games) {
			m = new HashMap<String, String>();
			m.put("monthName", month);
			childDataItem.add(m);
		}
		childData.add(childDataItem);

		String childFrom[] = new String[] { "monthName" };
		int childTo[] = new int[] { android.R.id.text1 };

		SimpleExpandableListAdapter adapter = new SimpleExpandableListAdapter(
				this, groupData,
				android.R.layout.simple_expandable_list_item_1, groupFrom,
				groupTo, childData, android.R.layout.simple_list_item_1,
				childFrom, childTo);

		expListView = (ExpandableListView) findViewById(R.id.expListView);
		expListView.setAdapter(adapter);

		expListView.setOnChildClickListener(new OnChildClickListener() {

			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				if (groupPosition == 0 && childPosition == 0) {
					startActivity(new Intent(MainMenu.this, DrawingLines.class));
				};

				if (groupPosition == 0 && childPosition == 1) {
					startActivity(new Intent(MainMenu.this, DrawingRect.class));
				};

				if (groupPosition == 0 && childPosition == 2) {
					startActivity(new Intent(MainMenu.this, DrawingCircle.class));
				};

				if (groupPosition == 0 && childPosition == 3) {
					startActivity(new Intent(MainMenu.this,
							DrawingSprites.class));
				};
				
				if (groupPosition == 0 && childPosition == 4) {
					startActivity(new Intent(MainMenu.this,
							DrawingText.class));
				};

				if (groupPosition == 1 && childPosition == 0) {
					startActivity(new Intent(MainMenu.this, AnimSprite.class));
				};
				
				if (groupPosition == 1 && childPosition == 1) {
					startActivity(new Intent(MainMenu.this, MovingBall.class));
				};

				if (groupPosition == 2 && childPosition == 0) {
					startActivity(new Intent(MainMenu.this, MoveShoot.class));
				};

				if (groupPosition == 2 && childPosition == 1) {
					startActivity(new Intent(MainMenu.this, TouchCreate.class));
				};

				if (groupPosition == 3 && childPosition == 0) {
					startActivity(new Intent(MainMenu.this, PlaySound.class));
				};
				
				if (groupPosition == 3 && childPosition == 1) {
					startActivity(new Intent(MainMenu.this, PlayMusic.class));
				};
				
				if (groupPosition == 4 && childPosition == 0) {
					startActivity(new Intent(MainMenu.this, CollisonDetection.class));
				};
				
				if (groupPosition == 4 && childPosition == 0) {
					startActivity(new Intent(MainMenu.this, CollisonDetection.class));
				};
				
				if (groupPosition == 5 && childPosition == 0) {
					startActivity(new Intent(MainMenu.this, Menu.class));
				};
				
				return false;
			}

		});
	}
}
