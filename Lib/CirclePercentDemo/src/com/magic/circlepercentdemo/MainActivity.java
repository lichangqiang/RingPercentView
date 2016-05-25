package com.magic.circlepercentdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends Activity {
	RingPercentView ringView;
	RingPercentView arcView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		
	}
	
	private void initView(){
		ringView=(RingPercentView) findViewById(R.id.circle_percent);
		ringView.setBg(0, 360);
		//ringView.drawRing(270, 180, 300, true, 000);
		
		arcView=(RingPercentView) findViewById(R.id.arc_percent);
		arcView.setBg(160, 220);
		//arcView.drawRing(160, 180, 300, true, 1000);
	}

	public void show(View view){
		arcView.drawRing(160, 180, 300, true, 1000);
		ringView.drawRing(270, 180, 300, true, 1000);
	}
}
