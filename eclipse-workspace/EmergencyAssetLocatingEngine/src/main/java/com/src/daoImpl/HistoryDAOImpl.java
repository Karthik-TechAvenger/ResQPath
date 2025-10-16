package com.src.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.src.dao.HistoryDAO;
import com.src.model.Asset;
import com.src.model.UserHistory;
import com.src.util.DBUtil;

public class HistoryDAOImpl implements HistoryDAO {

	@Override
	public void saveUserQuery(String username, int x, int y, Asset newAsset) {
		String sql = "INSERT INTO user_history (username, x, y, nearest_asset_type, asset_x, asset_y, timestamp) VALUES (?,?,?,?,?,?,CURRENT_TIMESTAMP)";
		try (Connection con = DBUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, username);
			ps.setInt(2, x);
			ps.setInt(3, y);
			ps.setString(4, newAsset.getType().toString());
			ps.setInt(5, newAsset.getX());
			ps.setInt(6, newAsset.getY());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<UserHistory> getUserHistory(String username) {
		List<UserHistory> historyList = new ArrayList<>();
		String sql = "SELECT * FROM user_history WHERE username = ? ORDER BY timestamp DESC";

		try (Connection con = DBUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				UserHistory record = new UserHistory();
				record.setUsername(rs.getString("username"));
				record.setX(rs.getInt("x"));
				record.setY(rs.getInt("y"));
				record.setNearestAssetType(rs.getString("nearest_asset_type"));
				record.setAssetX(rs.getInt("asset_x"));
				record.setAssetY(rs.getInt("asset_y"));
				record.setTimestamp(rs.getTimestamp("timestamp"));
				historyList.add(record);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return historyList;
	}

	@Override
	public List<UserHistory> getAllHistory() {
		List<UserHistory> historyList = new ArrayList<>();
		String sql = "SELECT * FROM user_history ORDER BY timestamp DESC";

		try (Connection con = DBUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				UserHistory record = new UserHistory();
				record.setUsername(rs.getString("username"));
				record.setX(rs.getInt("x"));
				record.setY(rs.getInt("y"));
				record.setNearestAssetType(rs.getString("nearest_asset_type"));
				record.setAssetX(rs.getInt("asset_x"));
				record.setAssetY(rs.getInt("asset_y"));
				record.setTimestamp(rs.getTimestamp("timestamp"));
				historyList.add(record);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return historyList;
	}

}
