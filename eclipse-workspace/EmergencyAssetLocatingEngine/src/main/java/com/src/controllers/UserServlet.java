package com.src.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.src.assetType.AssetType;
import com.src.model.Asset;
import com.src.model.OfficeGrid;
import com.src.model.Point;
import com.src.model.SharedOfficeGrid;
import com.src.model.User;
import com.src.service.AssetService;
import com.src.service.impl.AssetServiceImpl;
import com.src.service.impl.HistoryServiceImpl;

@WebServlet("/userAction")
public class UserServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user == null) {
			response.sendRedirect("login.jsp");
			return;
		}

		String action = request.getParameter("action");
		OfficeGrid og = SharedOfficeGrid.getInstance();

		switch (action) {
		case "queryAsset":
			request.getRequestDispatcher("queryAsset.jsp").forward(request, response);
			break;
		case "viewGrid":
			request.setAttribute("grid", og.toHtmlTable());
			request.getRequestDispatcher("viewGrid.jsp").forward(request, response);
			break;
		case "myHistory":
			request.setAttribute("history", new HistoryServiceImpl().getUserHistory(user.getName()));
			request.getRequestDispatcher("viewHistory.jsp").forward(request, response);
			break;
		case "allHistory":
			request.setAttribute("history", new HistoryServiceImpl().getAllHistory());
			request.getRequestDispatcher("viewHistory.jsp").forward(request, response);
			break;
		default:
			response.sendRedirect("UserDashboard.jsp");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String action = req.getParameter("action");
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");
		if (user == null) {
			resp.sendRedirect("login.jsp");
			return;
		}

		OfficeGrid og = SharedOfficeGrid.getInstance();

		if ("queryAsset".equals(action)) {
			String typeParam = req.getParameter("type").trim().toUpperCase();
			AssetType type = AssetType.valueOf(typeParam);

			String location = req.getParameter("location");
			int ux = 0, uy = 0;

			switch (location) {
			case "Lobby":
				ux = 0;
				uy = 0;
				break;
			case "Meeting Room 1":
				ux = 0;
				uy = 5;
				break;
			case "Meeting Room 2":
				ux = 5;
				uy = 0;
				break;
			case "Games Room":
				ux = 5;
				uy = 5;
				break;
			case "Cafeteria":
				ux = 3;
				uy = 3;
				break;
			default:
				ux = 0;
				uy = 0;
			}

			// ✅ Refresh Grid from DB before BFS
			AssetService assetDAO = new AssetServiceImpl();

			// Clear any previous in-memory data
			og = SharedOfficeGrid.getInstance();
			// If no clear method exists, create one or rebuild SharedOfficeGrid instance
//			og.resetGrid(); // Add this method to empty grid[][] and assets list

			// Load assets to grid
			List<Asset> assetsDb = assetDAO.getAllAssets();
			for (Asset a : assetsDb) {
				og.add(a);
			}

			User tempUser = new User(user.getName(), user.getRole(), user.getPassword(), ux, uy);
			List<Point> path = og.findNearestAsset(tempUser, type);

			if (path == null) {
				req.setAttribute("message", "No " + type + " found!");
				req.getRequestDispatcher("queryAsset.jsp").forward(req, resp);
				return;
			}

			req.setAttribute("grid", og.renderGridWithPathHTML(tempUser, path));
			req.getRequestDispatcher("viewGrid.jsp").forward(req, resp);
		}
	}
}
