package com.src.model;

import com.src.anotations.Column;
import com.src.anotations.Table;

@Table(name = "walls")
public class Wall {
	@Column(name = "x", type = "INT")
    private int x;
	@Column(name = "y", type = "INT")
    private int y;

    public Wall(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() { return x; }
    public int getY() { return y; }

    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
}
