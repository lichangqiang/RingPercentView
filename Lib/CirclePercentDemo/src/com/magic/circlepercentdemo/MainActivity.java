package com.magic.circlepercentdemo;

import android.R.color;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

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
		ringView.setBg(0, 360,Color.rgb(30, 96, 200));
		ringView.setFrontColor(Color.rgb(255, 255, 255));
		ringView.setPrimaryTextParam(100,Color.rgb(255, 255, 255), "60%");
		ringView.setSecondryTextParam(60, Color.rgb(255, 255, 255), "硬盘");
		//ringView.drawRing(270, 180, 300, true, 000);
		
		arcView=(RingPercentView) findViewById(R.id.arc_percent);
		arcView.setBg(160, 220,Color.rgb(30, 96, 200));
		arcView.setFrontColor(Color.rgb(255, 255, 255));
		arcView.setPrimaryTextParam(100,Color.rgb(255, 255, 255), "60%");
		arcView.setSecondryTextParam(60, Color.rgb(255, 255, 255), "硬盘");

		//arcView.drawRing(160, 180, 300, true, 1000);
	}

	public void show(View view){
		//arcView.drawRing(160, 180, 300, true, 1000);
		arcView.drawArcRing(160, 300, 30, 500);
		ringView.drawCircleRing(270, 90, 300, 500);
	}
}
