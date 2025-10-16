package com.src.model;

import java.util.Date;
import com.src.anotations.Column;
import com.src.anotations.Table;

@Table(name = "user_history")
public class UserHistory {

	@Column(name = "username", type = "VARCHAR(50)")
	private String username;

	@Column(name = "x", type = "INT")
	private int x;

	@Column(name = "y", type = "INT")
	private int y;

	@Column(name = "nearest_asset_type", type = "VARCHAR(50)")
	private String nearestAssetType;

	@Column(name = "asset_x", type = "INT")
	private int assetX;

	@Column(name = "asset_y", type = "INT")
	private int assetY;

	@Column(name = "timestamp", type = "TIMESTAMP")
	private Date timestamp;

	// ✅ Default constructor (important for frameworks or DAO reflection)
	public UserHistory() {
	}

	// ✅ Full constructor
	public UserHistory(String username, int x, int y, String nearestAssetType, int assetX, int assetY, Date timestamp) {
		this.username = username;
		this.x = x;
		this.y = y;
		this.nearestAssetType = nearestAssetType;
		this.assetX = assetX;
		this.assetY = assetY;
		this.timestamp = timestamp;
	}

	// ✅ Getters and Setters
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getNearestAssetType() {
		return nearestAssetType;
	}

	public void setNearestAssetType(String nearestAssetType) {
		this.nearestAssetType = nearestAssetType;
	}

	public int getAssetX() {
		return assetX;
	}

	public void setAssetX(int assetX) {
		this.assetX = assetX;
	}

	public int getAssetY() {
		return assetY;
	}

	public void setAssetY(int assetY) {
		this.assetY = assetY;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return timestamp + " | " + username + " | (" + x + "," + y + ") → " + nearestAssetType + " at (" + assetX + ","
				+ assetY + ")";
	}
}
