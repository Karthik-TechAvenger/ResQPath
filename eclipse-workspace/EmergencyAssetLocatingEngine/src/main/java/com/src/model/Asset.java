package com.src.model;

import com.src.anotations.Column;
import com.src.anotations.Table;
import com.src.assetType.AssetType;

@Table(name = "assets" )
public class Asset {
	
	@Column(name = "type", type = "VARCHAR(50)")
	private AssetType type;
	
	@Column(name = "x", type = "INT")
	private int x;
	
	@Column(name = "y", type = "INT")
	private int y;
	
	
	public AssetType getType() {
		return type;
	}
	public void setType(AssetType type) {
		this.type = type;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	@Override
	public String toString() {
		return "Asset [type=" + type + ", x=" + x + ", y=" + y + "]";
	}
	public Asset(AssetType type, int x, int y) {
		super();
		this.type = type;
		this.x = x;
		this.y = y;
	}
	
	
	

}


