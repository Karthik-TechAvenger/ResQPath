package com.src.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.src.dao.AssetDAO;
import com.src.model.Asset;
import com.src.model.OfficeGrid;
import com.src.model.SharedOfficeGrid;
import com.src.util.DBUtil;

public class AssetDAOImpl implements AssetDAO {

	@Override
	public boolean saveAssets(Asset asset) {
		OfficeGrid grid = SharedOfficeGrid.getInstance();

		if (!grid.add(asset)) {
			System.out.println("Cannot place asset here: Reserved location");
			return false;
		}

		String deleteSql = "DELETE FROM assets WHERE x = ? AND y = ?";
		try (Connection con = DBUtil.getConnection(); PreparedStatement ps = con.prepareStatement(deleteSql)) {
			ps.setInt(1, asset.getX());
			ps.setInt(2, asset.getY());
			int deleted = ps.executeUpdate();
			if (deleted > 0) {
				System.out.println("Deleted old asset(s) at (" + asset.getX() + ", " + asset.getY() + ")");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		String insertSql = "INSERT INTO assets (type, x, y) VALUES (?, ?, ?)";
		try (Connection con = DBUtil.getConnection(); PreparedStatement ps = con.prepareStatement(insertSql)) {
			ps.setString(1, asset.getType().toString());
			ps.setInt(2, asset.getX());
			ps.setInt(3, asset.getY());

			int res = ps.executeUpdate();
			if (res > 0) {
				System.out.println("Asset " + asset.getType() + " inserted successfully at (" + asset.getX() + ", "
						+ asset.getY() + ")");
			}
			return res > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public void deleteAssets(Asset asset) {
		String sql = "DELETE FROM assets WHERE x = ? AND y = ?";
		try (Connection con = DBUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, asset.getX());
			ps.setInt(2, asset.getY());

			int res = ps.executeUpdate();
			if (res > 0) {
				System.out.println("Existing asset(s) at (" + asset.getX() + ", " + asset.getY() + ") deleted.");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
