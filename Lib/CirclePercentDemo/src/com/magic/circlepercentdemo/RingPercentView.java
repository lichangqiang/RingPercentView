package com.magic.circlepercentdemo;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.Path.Direction;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class RingPercentView extends View {
	private int startAngle;// 开始角度
	private int sweepAngle;// 扫过角度
	private int radius = 0;// 圆环半径
	private int ringFrontColor = Color.RED;// 圆环的前景色
	private int ringBackColor = Color.BLUE;// 圆环的背景色
	private int strokeWidth = 30;// 圆环的宽度
	private int drawInterTime;// 绘制的速度
	private int currentSweepAngle;// 当前扫过
	private int bgStartAngle;// 背景开始角度
	private int bgSweepAngle;// 背景扫过角度
	private boolean isDrawCircleBg = true;// 是否还整个圆环背景
	private boolean isCircleFillView = false;// 当半径为0时是否设置为控件的宽度.若是则为控件宽度，若不是则不绘制圆环
	private int textSizePrimaryText=40;//主要文字的大小
	private int textColorPrimaryText=Color.BLUE;//主要文字的颜色
	private int textSizeSecondryText=30;//次要文字的大小
	private int textColorSecondText=Color.RED;//次要文字的大小
	private String primarText="20%";//主要文字内容
	private String secondaryText="30%";//次要文字内容
	private boolean isDynamic=true;//是否动态的更新百分比
	private int percent;//百分比
	Paint ringPaint;// 圆环的画笔
	Paint ringBgPaint;// 圆环背景的画笔
	Paint primaryTextPaint;//主要文字的画笔
	Paint secondaryTextPaint;//次要文字的画笔
	Timer timer;

	public RingPercentView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	private void init() {
		ringPaint = new Paint();
		ringPaint.setColor(ringFrontColor);
		ringPaint.setStyle(Style.STROKE);
		ringPaint.setStrokeWidth(strokeWidth);
		ringPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
		ringPaint.setStrokeCap(Paint.Cap.ROUND);

		ringBgPaint = new Paint(0);
		ringBgPaint.setColor(ringBackColor);
		ringBgPaint.setStyle(Style.STROKE);
		ringBgPaint.setStrokeWidth(strokeWidth);
		ringBgPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
		ringBgPaint.setStrokeCap(Paint.Cap.ROUND);
		
		primaryTextPaint=new Paint();
		primaryTextPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
		primaryTextPaint.setTextSize(textSizePrimaryText);
		primaryTextPaint.setColor(textColorPrimaryText);
		
		secondaryTextPaint=new Paint();
		secondaryTextPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
		secondaryTextPaint.setTextSize(textSizeSecondryText);
		secondaryTextPaint.setTextSize(textColorSecondText);
	}

	public void setFrontColor(int color) {
		ringFrontColor = color;
		ringPaint.setColor(ringFrontColor);
	}

	public void setBgColor(int color) {
		ringBackColor = color;
		ringPaint.setColor(ringBackColor);
		ringBgPaint.setStrokeWidth(strokeWidth);
		ringPaint.setStrokeWidth(strokeWidth);
	}
	
	public void setIsDynamicAnimation(boolean isDynamic){
		this.isDynamic=isDynamic;
	}
	
	public void setPrimaryTextParam(int textSize,int textColor,String text){
		textSizePrimaryText=textSize;
		textColorPrimaryText=textColor;
		primaryTextPaint.setColor(textColorPrimaryText);
		primaryTextPaint.setTextSize(textSizePrimaryText);
		primarText=text;
	}
	
	public void setSecondryTextParam(int textSize,int textColor,String text){
		textSizeSecondryText=textSize;
		textColorSecondText=textColor;
		secondaryText=text;
		secondaryTextPaint.setColor(textColorSecondText);
		secondaryTextPaint.setTextSize(textSizeSecondryText);
	}

	public void setRingWidth(int width) {
		strokeWidth = width;
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		int widthSize = MeasureSpec.getSize(widthMeasureSpec);
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		int heightSize = MeasureSpec.getSize(heightMeasureSpec);

		if (widthMode == MeasureSpec.AT_MOST) {
			widthSize = radius * 2 > widthSize ? widthSize : radius * 2;
		}

		if (heightMode == MeasureSpec.AT_MOST) {
			heightSize = radius * 2 > heightSize ? heightSize : radius * 2;
		}

		// radius=getRealRadius(radius);
		setMeasuredDimension(MeasureSpec.makeMeasureSpec(widthSize, widthMode),
				MeasureSpec.makeMeasureSpec(heightSize, heightMode));
	}

	@Override
	protected void onDraw(Canvas canvas) {
		if (radius == 0) {
			if (!isCircleFillView)
				return;
		}

		canvas.translate(getMeasuredWidth() / 2, getMeasuredHeight() / 2);
		drawRing(canvas);
		drawPrimaryText(canvas);
		drawSecondaryText(canvas);
	}

	private void drawRing(Canvas canvas) {
		int radius = getRealRadius(this.radius);
		if (isDrawCircleBg) {
			Path bgpath = new Path();
			RectF ovalBG = new RectF(-radius, -radius, radius, radius);
			bgpath.addArc(ovalBG, bgStartAngle, bgSweepAngle);
			canvas.drawPath(bgpath, ringBgPaint);
		}

		Path frontPath = new Path();
		ringPaint.setColor(ringFrontColor);
		RectF ovalFront = new RectF(-radius, -radius, radius, radius);
		frontPath.addArc(ovalFront, startAngle, currentSweepAngle);
		canvas.drawPath(frontPath, ringPaint);
	}

	public void setBg(int bgStartAngle, int bgSweepAngle,int ringBackColor) {
		this.bgStartAngle = bgStartAngle;
		this.bgSweepAngle = bgSweepAngle;
		this.ringBackColor=ringBackColor;
	}

	public void drawRing(int startAngle, int sweepAngle, int radius, boolean isDrawCircle, int totalDrawTime) {
		this.startAngle = startAngle;
		this.sweepAngle = sweepAngle;

		this.radius = radius;
		this.isDrawCircleBg = isDrawCircle;
		this.drawInterTime = totalDrawTime / sweepAngle;
		startDrawRing(drawInterTime);
	}

	
	public void drawArcRing(int startAngle, int radius, int  percent, int totalDrawTime) {
		this.startAngle = startAngle;
		this.sweepAngle = (int) (bgSweepAngle*percent*0.01);
		this.percent=percent;
		this.radius = radius;
		this.drawInterTime = totalDrawTime / percent;
		startDrawRing(drawInterTime);
	}
	
	public void drawCircleRing(int startAngle,int percent,int radius,int totalDrawTime){
		this.startAngle = startAngle;
		this.sweepAngle = (int) (percent*3.6);
		this.percent=percent;
		this.radius = radius;
		this.isDrawCircleBg = true;
		this.drawInterTime = totalDrawTime / percent;
		startDrawRing(drawInterTime);
	}
	
	public int getRealRadius(int radius) {
		// 若圆环直径大于视图宽度则直径为视图宽度
		int circleTempSize = (getMeasuredHeight() > getMeasuredWidth() ? getMeasuredWidth() : getMeasuredHeight()) / 2;
		radius = radius > circleTempSize ? circleTempSize : radius;
		return radius - strokeWidth;
	}

	private void startDrawRing(int peroidTime) {
		currentSweepAngle = 0;
		if (timer != null) {
			timer.cancel();
		}
		timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				currentSweepAngle++;
				
				if(isDynamic){
					primarText=(int)((currentSweepAngle*100/sweepAngle)*percent*0.01)+"%";
				}
				
				if (currentSweepAngle > sweepAngle) {
					timer.cancel();
				} else {
					postInvalidate();
				}
			}
		}, 0, peroidTime);

	}

	private void drawPrimaryText(Canvas canvas){
		float textWidth=primaryTextPaint.measureText(primarText);
		FontMetrics fm=primaryTextPaint.getFontMetrics();
		float textHeight=primaryTextPaint.getFontMetrics().bottom-primaryTextPaint.getFontMetrics().top;
		canvas.drawText(primarText, -textWidth/2, fm.descent, primaryTextPaint);
	}
	
	private void drawSecondaryText(Canvas canvas){
		float textWidth=secondaryTextPaint.measureText(secondaryText);
		FontMetrics fmSecondary=secondaryTextPaint.getFontMetrics();
		FontMetrics fmPrimary=primaryTextPaint.getFontMetrics();
		//float textHeight=secondaryTextPaint.getFontMetrics().bottom-primaryTextPaint.getFontMetrics().top;
		canvas.drawText(secondaryText, -textWidth/2, fmPrimary.descent+fmSecondary.descent-fmSecondary.ascent, secondaryTextPaint);
	}
}
