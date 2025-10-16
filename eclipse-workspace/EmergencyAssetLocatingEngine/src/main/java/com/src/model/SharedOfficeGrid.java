package com.src.model;



import java.util.List;

import com.src.service.AssetService;
import com.src.service.WallService;
import com.src.service.impl.AssetServiceImpl;
import com.src.service.impl.WallServiceImpl;

public class SharedOfficeGrid {

    private static OfficeGrid og;
    private static AssetService assetDAO = new AssetServiceImpl();
    private static WallService wallDAO = new WallServiceImpl();

    public static synchronized OfficeGrid getInstance() {
        if (og == null) {
            og = new OfficeGrid(6, 6);
            loadFromDB();
        }
        return og;
    }

    public static synchronized void reload() {
        og = new OfficeGrid(6, 6);
        loadFromDB();
    }

    private static void loadFromDB() {
        List<Asset> assets = assetDAO.getAllAssets();
        List<Wall> walls = wallDAO.getWalls();
        for (Asset a : assets) og.add(a);
        for (Wall w : walls) og.addWall(w.getX(), w.getY());
    }
}
