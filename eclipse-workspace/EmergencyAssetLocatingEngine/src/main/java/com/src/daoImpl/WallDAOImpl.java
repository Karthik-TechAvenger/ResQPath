package com.src.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.src.dao.WallDAO;
import com.src.model.Wall;
import com.src.util.DBUtil;

public class WallDAOImpl implements WallDAO {

	// Save a new wall
	@Override
	public void saveWall(Wall wall) {
		String sql = "INSERT INTO walls (x, y) VALUES (?, ?)";
		try (Connection con = DBUtil.getConnection()) {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, wall.getX());
			ps.setInt(2, wall.getY());
			int res = ps.executeUpdate();
			if (res > 0) {
				System.out.println("Wall inserted successfully at (" + wall.getX() + ", " + wall.getY() + ")");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Get all walls
	@Override
	public List<Wall> getAllWalls() {
		List<Wall> walls = new ArrayList<>();
		String sql = "SELECT * FROM walls";

		try (Connection con = DBUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				Wall wall = new Wall(rs.getInt("x"), rs.getInt("y"));
				walls.add(wall);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return walls;
	}

	// Delete a wall at specific coordinates
	@Override
	public void deleteWall(Wall wall) {
		String sql = "DELETE FROM walls WHERE x = ? AND y = ?";
		try (Connection con = DBUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, wall.getX());
			ps.setInt(2, wall.getY());

			int res = ps.executeUpdate();
			if (res > 0) {
				System.out.println("Wall at (" + wall.getX() + ", " + wall.getY() + ") deleted successfully");
			} else {
				System.out.println("No wall found at coordinates (" + wall.getX() + ", " + wall.getY() + ")");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
