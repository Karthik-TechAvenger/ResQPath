package com.src.service;

import com.src.model.Wall;
import java.util.List;

public interface WallService {

    void addWall(Wall wall);

    List<Wall> getWalls();

    void removeWall(Wall wall);
}
