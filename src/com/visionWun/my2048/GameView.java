package com.visionWun.my2048;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridLayout;

public class GameView extends GridLayout {

	public GameView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

		initGameView();
	}

	public GameView(Context context, AttributeSet attrs) {
		super(context, attrs);

		initGameView();
	}

	public GameView(Context context) {
		super(context);

		initGameView();
	}

	private void initGameView(){
		setColumnCount(4);
		setBackgroundColor(0xffbbada0);
		
		setOnTouchListener(new View.OnTouchListener() {
			private float startX,startY,offsetX,offsetY;
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch(event.getAction()){
				case MotionEvent.ACTION_DOWN:
					startX = event.getX();
					startY = event.getY();
					break;
				case MotionEvent.ACTION_UP:
					offsetX = event.getX()-startX;
					offsetY = event.getY()-startY;
					
					if (Math.abs(offsetX)>Math.abs(offsetY)) {
						if (offsetX>5){
							swipeRight();
						}else if(offsetX<-5) {
							swipeLeft();
						}
					}else{
						if (offsetY>5){
							swipeDown();
						}else if(offsetY<-5) {
							swipeUp();
						}
					}
					
					break;
				}
				return true;
			}
		});
	}
	
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		
		int cardWidth = (Math.min(w, h)-10)/4;
		
		addCards(cardWidth, cardWidth);
		
		startGame();
	}
	
	private void addCards(int cardWidth, int cardHeight) {
		Card c;
		
		for(int y = 0; y < 4; y ++) {
			for(int x = 0; x < 4; x ++) {
				c = new Card(getContext());
				c.setNum(0);
				addView(c, cardWidth, cardHeight);
				
				cardsMap[x][y] = c;
			}
		}
	}
	
	private void addRandomNum() {
		
		emptyPoints.clear();
		
		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 4; x++) {
				if (cardsMap[x][y].getNum()<=0) {
					emptyPoints.add(new Point(x, y));
				}
			}
		}
		
		Point p = emptyPoints.remove((int)(Math.random()*emptyPoints.size()));
		cardsMap[p.x][p.y].setNum(Math.random()>0.1?2:4);
	}
	
	private void startGame() {
		
		for (int y = 0; y < 4; y++) {
			for(int x = 0; x < 4; x++) {
				cardsMap[x][y].setNum(0);
			}
		}
		
		addRandomNum();
		addRandomNum();
	}
	
	private void swipeLeft(){
		
	}
	
	private void swipeRight(){
		
	}
	
	private void swipeUp(){
		
	}
	
	private void swipeDown(){
		
	}
	
	private Card[][] cardsMap = new Card[4][4];
	private List<Point> emptyPoints = new ArrayList<>();
}
