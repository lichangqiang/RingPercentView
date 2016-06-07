# RingPercentView
绘制圆环的控件，支持时间设置，角度设置，背景设置等
<br>
<img src="https://github.com/lichangqiang/RingPercentView/blob/master/ring.gif"/>
<br>
<h1>一、使用方法</h1>
<b>1)在Layout中使用如下声明</b></br>
<com.magic.circlepercentdemo.RingPercentView</br>
        android:id="@+id/arc_percent"</br>
        android:layout_width="wrap_content"</br>
        android:layout_height="wrap_content"</br>
        app:radius="100dp"</br>
        app:isRing="false"</br>
        /></br>
        其中radus表示半径,isRing表示是否绘制圆环</br>
        
<b>2)初始化参数</b></br>
ring.setBg(0, 360,Color.rgb(30, 96, 200));//设置圆环背景</br>
		ring.setFrontColor(Color.rgb(255, 255, 255));//设置圆环前景</br>
		ring.setPrimaryTextParam(primaryTextSize,Color.rgb(255, 255, 255), cpuPercent+"%");//设置主标题属性</br>
		ring.setSecondryTextParam(secondaryTextSize, Color.rgb(255, 255, 255), "CPU");//设置负标题属性</br>
		ring.setRingWidth(ringWidth);</br>
		</br>
<b>3)绘制Ring</b></br>
/**</br>
	 * @param startAngle开始角度</br>
	 * @param percent 百分比</br>
	 * @param radius 半径</br>
	 * @param totalDrawTime 动画总时间</br>
	 */</br>
	//绘制圆环
	public void drawCircleRing(int startAngle,int percent,int totalDrawTime)</br>
	//绘制扇形
        public void drawArcRing(int startAngle,  int  percent, int totalDrawTime) 
</br>欢迎大家访问我的博客提供宝贵建议</br>
<a href='http://blog.csdn.net/lcq376645763'>http://blog.csdn.net/lcq376645763</a></br>
