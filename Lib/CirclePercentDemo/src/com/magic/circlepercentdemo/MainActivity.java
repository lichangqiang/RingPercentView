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
		ringView.setPrimaryTextParam(100,Color.rgb(255, 255, 255), "%");
		ringView.setSecondryTextParam(60, Color.rgb(255, 255, 255), "CPU");
		//ringView.drawRing(270, 180, 300, true, 000);
		
		arcView=(RingPercentView) findViewById(R.id.arc_percent);
		arcView.setBg(160, 220,Color.rgb(30, 96, 200));
		arcView.setFrontColor(Color.rgb(255, 255, 255));
		arcView.setPrimaryTextParam(100,Color.rgb(255, 255, 255), "%");
		arcView.setSecondryTextParam(60, Color.rgb(255, 255, 255), "硬盘");
		
	}

	public void show(View view){
		arcView.drawArcRing(160,  60, 500);
		ringView.drawCircleRing(270, 90,500);
	}
}
