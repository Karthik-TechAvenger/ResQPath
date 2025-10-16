package com.src.reservedLocations;

import java.util.Map;
import com.src.model.Point;

public class ReservedLocations {
	public static final Map<String, Point> RESERVED = Map.of("Lobby", new Point(0, 0, null), "Meeting Room 1",
			new Point(0, 5, null), "Meeting Room 2", new Point(5, 0, null), "Games Room", new Point(5, 5, null),
			"Cafeteria", new Point(3, 3, null));
}
