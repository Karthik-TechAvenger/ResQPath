package com.src.model;

public class Point {
	int x,y;
	Point p;
	public Point(int x, int y, Point p) {
		super();
		this.x = x;
		this.y = y;
		this.p = p;
	}
	@Override
	public String toString() {
		return "Point [x=" + x + ", y=" + y + ", p=" + p + "]";
	}
	

}
