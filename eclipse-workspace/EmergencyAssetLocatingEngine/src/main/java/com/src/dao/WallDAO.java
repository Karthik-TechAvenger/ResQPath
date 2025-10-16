package com.src.dao;

import com.src.model.Wall;
import java.util.List;

public interface WallDAO {

    void saveWall(Wall wall);

    List<Wall> getAllWalls();

    void deleteWall(Wall wall);
}
