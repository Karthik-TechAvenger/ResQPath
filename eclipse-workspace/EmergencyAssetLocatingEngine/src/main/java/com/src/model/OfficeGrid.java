package com.src.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import com.src.assetType.AssetType;
import com.src.daoImpl.HistoryDAOImpl;
import com.src.reservedLocations.ReservedLocations;

/**
 * OfficeGrid: grid storage, BFS pathfinding, and HTML rendering (with tooltips
 * & colors).
 */
public class OfficeGrid {

	private int rows;
	private int cols;
	private List<Asset> assets = new ArrayList<>();
	private char[][] grid;

	public OfficeGrid(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
		grid = new char[rows][cols];
		for (int i = 0; i < rows; i++)
			Arrays.fill(grid[i], '.');
	}

	/* -------------------- basic operations -------------------- */

	public boolean add(Asset asset) {
		if (!isValidPosition(asset.getX(), asset.getY()))
			return false;

		// Check reserved locations
		for (Point p : ReservedLocations.RESERVED.values()) {
			if (asset.getX() == p.x && asset.getY() == p.y) {
				System.out.println("Cannot place asset here: Reserved location");
				return false;
			}
		}

		// Check if asset already exists at this coordinate
		assets.removeIf(a -> a.getX() == asset.getX() && a.getY() == asset.getY());

		assets.add(asset);
		grid[asset.getX()][asset.getY()] = symbolFor(asset.getType());
		return true;
	}

	public void deleteAsset(Asset asset) {
		if (!isValidPosition(asset.getX(), asset.getY()))
			return;
		assets.removeIf(a -> a.getX() == asset.getX() && a.getY() == asset.getY());
		grid[asset.getX()][asset.getY()] = '.';
	}

	public List<Asset> getAssets() {
		return Collections.unmodifiableList(assets);
	}

	public void addWall(int x, int y) {
		if (!isValidPosition(x, y))
			return;
		grid[x][y] = '#';
	}

	private boolean isValidPosition(int x, int y) {
		return x >= 0 && y >= 0 && x < rows && y < cols;
	}

	public Asset getAssetAt(int x, int y) {
		for (Asset a : assets)
			if (a.getX() == x && a.getY() == y)
				return a;
		return null;
	}

	/* -------------------- BFS nearest asset -------------------- */

	public List<Point> findNearestAsset(User user, AssetType targetType) {
		boolean[][] visited = new boolean[rows][cols];
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(user.getX(), user.getY(), null));
		visited[user.getX()][user.getY()] = true;

		int[] dx = { 1, -1, 0, 0 };
		int[] dy = { 0, 0, 1, -1 };

		while (!q.isEmpty()) {
			Point curr = q.poll();

			Asset at = getAssetAt(curr.x, curr.y);
			if (at != null && at.getType() == targetType) {
				// Found — save history & return path
				List<Point> path = reconstructPath(curr);
				try {
					new HistoryDAOImpl().saveUserQuery(user.getName(), user.getX(), user.getY(), at);
				} catch (Exception ignore) {
					/* don't break UI if history fails */ }
				return path;
			}

			for (int k = 0; k < 4; k++) {
				int nx = curr.x + dx[k];
				int ny = curr.y + dy[k];
				if (isValidPosition(nx, ny) && !visited[nx][ny] && grid[nx][ny] != '#') {
					visited[nx][ny] = true;
					q.add(new Point(nx, ny, curr));
				}
			}
		}
		return null;
	}

	public List<Point> reconstructPath(Point end) {
		List<Point> path = new ArrayList<>();
		Point p = end;
		while (p != null) {
			path.add(p);
			p = p.p;
		}
		Collections.reverse(path);
		return path;
	}

	/* -------------------- helpers: symbols & names -------------------- */

	// underlying stored symbols in grid[][]
	// F = Extinguisher, M = First Aid (medical), E = Exit, # = wall, . = empty
	private char symbolFor(AssetType t) {
		if (t == null)
			return 'A';
		switch (t) {
		case FIRE_EXTINGUISHER:
			return 'F';
		case FIRST_AID_KIT:
			return 'M';
		case EMERGENCY_EXIT:
			return 'E';
		default:
			return 'A';
		}
	}

	private String shortNameForSymbol(char ch) {
		switch (ch) {
		case 'F':
			return "Extinguisher";
		case 'M':
			return "First Aid";
		case 'E':
			return "Exit";
		case '#':
			return "Wall";
		case '*':
			return "Path";
		case 'U':
			return "You";
		case '.':
		default:
			return "Empty";
		}
	}

	/* -------------------- text grid (legacy) -------------------- */

	public String printGridToString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				sb.append(grid[i][j]).append(' ');
			}
			sb.append("<br>");
		}
		return sb.toString();
	}

	/* -------------------- HTML rendering (table) -------------------- */

	/**
	 * Render current grid as an HTML table. Cells have: - class "grid-cell <type>"
	 * - data-x / data-y attributes - title attribute for tooltip (short names)
	 */
	public String toHtmlTable() {
		StringBuilder sb = new StringBuilder(1024);
		sb.append("<table class=\"office-grid\" role=\"grid\" style=\"border-collapse:collapse;\">");
		for (int i = 0; i < rows; i++) {
			sb.append("<tr>");
			for (int j = 0; j < cols; j++) {
				char ch = grid[i][j];
				// If an asset exists at this location, prefer asset symbol (keeps sync)
				Asset a = getAssetAt(i, j);
				if (a != null)
					ch = symbolFor(a.getType());

				String css = cssClassFor(ch);
				String icon = iconFor(ch);
				String title = shortNameForSymbol(ch);
				// If asset object present, refine title with coords
				if (a != null)
					title = shortNameForSymbol(symbolFor(a.getType())) + " at (" + i + "," + j + ")";

				sb.append("<td class=\"grid-cell ").append(css).append("\" data-x=\"").append(i).append("\" data-y=\"")
						.append(j).append("\" title=\"").append(escapeHtml(title)).append("\">").append(icon)
						.append("</td>");
			}
			sb.append("</tr>");
		}
		sb.append("</table>");
		return sb.toString();
	}

	/**
	 * Render a temporary overlay of grid with path + user highlighted. Does not
	 * mutate underlying grid[][]
	 */
	public String renderGridWithPathHTML(User user, List<Point> path) {
		// create a display-symbol matrix (clone base grid)
		char[][] display = new char[rows][cols];
		for (int i = 0; i < rows; i++)
			System.arraycopy(grid[i], 0, display[i], 0, cols);

		// place assets from list (ensure symbol consistency)
		for (Asset a : assets) {
			if (isValidPosition(a.getX(), a.getY()))
				display[a.getX()][a.getY()] = symbolFor(a.getType());
		}

		// map to store path index for each path cell (so JS can animate in correct
		// order)
		Map<String, Integer> pathIndex = new HashMap<>();

		// overlay path (but do NOT overwrite user or target asset)
		if (path != null) {
			for (int idx = 0; idx < path.size(); idx++) {
				Point p = path.get(idx);
				if (p == null)
					continue;

				// skip user
				if (p.x == user.getX() && p.y == user.getY())
					continue;

				// skip target asset (last in path) — keep asset emoji there
				if (idx == path.size() - 1)
					continue;

				display[p.x][p.y] = '*';
				pathIndex.put(p.x + "," + p.y, idx); // record order
			}
		}

		// place user
		if (isValidPosition(user.getX(), user.getY()))
			display[user.getX()][user.getY()] = 'U';

		// build table
		StringBuilder sb = new StringBuilder(1024);
		sb.append("<table class=\"office-grid\" role=\"grid\" style=\"border-collapse:collapse;\">");
		for (int i = 0; i < rows; i++) {
			sb.append("<tr>");
			for (int j = 0; j < cols; j++) {
				char ch = display[i][j];
				String css = cssClassFor(ch);
				String icon = iconFor(ch);
				String title = shortNameForSymbol(ch);

				// show coordinates for items
				if (ch == 'F' || ch == 'M' || ch == 'E') {
					title = shortNameForSymbol(ch) + " at (" + i + "," + j + ")";
				} else if (ch == 'U') {
					title = "You (" + i + "," + j + ")";
				} else if (ch == '*') {
					title = "Path";
				} else if (ch == '#') {
					title = "Wall";
				}

				sb.append("<td class=\"grid-cell ").append(css).append("\" data-x=\"").append(i).append("\" data-y=\"")
						.append(j).append("\"");

				// attach data-path-index if this is a path cell (so JS can sort/animate)
				if (ch == '*') {
					Integer idx = pathIndex.get(i + "," + j);
					if (idx != null) {
						sb.append(" data-path-index=\"").append(idx).append("\"");
					}
				}

				sb.append(" title=\"").append(escapeHtml(title)).append("\">").append(icon).append("</td>");
			}
			sb.append("</tr>");
		}
		sb.append("</table>");
		return sb.toString();
	}

	/* -------------------- utility mapping -------------------- */

	private String cssClassFor(char ch) {
		switch (ch) {
		case 'F':
			return "asset-fire";
		case 'M':
			return "asset-firstaid";
		case 'E':
			return "asset-exit";
		case '#':
			return "wall";
		case 'U':
			return "user";
		case '*':
			return "path";
		case 'A':
			return "asset-generic";
		case '.':
		default:
			return "empty";
		}
	}

	private String iconFor(char ch) {
		switch (ch) {
		case 'F':
			return "🧯"; // Fire Extinguisher
		case 'M':
			return "🏥"; // First Aid Kit as Hospital emoji
		case 'E':
			return "🚪"; // Emergency Exit
		case '#':
			return "⬛"; // Black Wall
		case 'U':
			return "🧍"; // User
		case '*':
			return "▢";

		case 'A':
			return "★"; // Generic asset
		case '.':
		default:
			return "▢"; // Empty
		}
	}

	// Small HTML escape helper for titles (very small, not full-fledged)
	private String escapeHtml(String s) {
		if (s == null)
			return "";
		return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;");
	}
	
	
}
