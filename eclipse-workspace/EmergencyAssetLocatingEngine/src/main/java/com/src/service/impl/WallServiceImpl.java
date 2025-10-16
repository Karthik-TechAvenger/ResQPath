package com.src.service.impl;

import com.src.dao.WallDAO;
import com.src.daoImpl.WallDAOImpl;
import com.src.model.Wall;
import com.src.service.WallService;

import java.util.List;

public class WallServiceImpl implements WallService {

	private final WallDAO wallDAO;

	// No-arg constructor, DAO instantiated internally
	public WallServiceImpl() {
		this.wallDAO = new WallDAOImpl(); // Can switch to DI later
	}

	@Override
	public void addWall(Wall wall) {
		wallDAO.saveWall(wall);
	}

	@Override
	public List<Wall> getWalls() {
		return wallDAO.getAllWalls();
	}

	@Override
	public void removeWall(Wall wall) {
		wallDAO.deleteWall(wall);
	}
}
