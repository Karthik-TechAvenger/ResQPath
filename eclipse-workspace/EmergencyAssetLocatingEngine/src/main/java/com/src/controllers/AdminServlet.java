package com.src.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.src.assetType.AssetType;
import com.src.model.Asset;
import com.src.model.OfficeGrid;
import com.src.model.SharedOfficeGrid;
import com.src.model.Wall;
import com.src.service.AssetService;
import com.src.service.WallService;
import com.src.service.impl.AssetServiceImpl;
import com.src.service.impl.WallServiceImpl;

@WebServlet("/adminAction")
public class AdminServlet extends HttpServlet {

	private AssetService assetDAO = new AssetServiceImpl();
	private WallService wallDAO = new WallServiceImpl();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");
		if (action == null) {
			response.sendRedirect("adminDashboard.jsp");
			return;
		}

		switch (action) {
		case "addAsset":
			request.getRequestDispatcher("addAsset.jsp").forward(request, response);
			break;
		case "removeAsset":
			request.getRequestDispatcher("removeAsset.jsp").forward(request, response);
			break;
		case "viewAssets":
			request.setAttribute("assets", assetDAO.getAllAssets());
			request.getRequestDispatcher("viewAssets.jsp").forward(request, response);
			break;
		case "viewGrid":
			request.setAttribute("grid", SharedOfficeGrid.getInstance().toHtmlTable());
			request.getRequestDispatcher("viewGrid.jsp").forward(request, response);
			break;
		case "addWall":
			request.getRequestDispatcher("addWall.jsp").forward(request, response);
			break;
		default:
			response.sendRedirect("adminDashboard.jsp");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String action = req.getParameter("action");
		OfficeGrid og = SharedOfficeGrid.getInstance();

		if ("addAsset".equals(action)) {
			AssetType type = AssetType.valueOf(req.getParameter("type"));
			int x = Integer.parseInt(req.getParameter("x"));
			int y = Integer.parseInt(req.getParameter("y"));

			Asset newAsset = new Asset(type, x, y);

			boolean added = assetDAO.addAsset(newAsset);
			if (!added) {
				req.setAttribute("error", "Cannot add the Asset, location is reserved.");
			} else {
				req.setAttribute("success", "Asset added successfully.");
				
			}

			req.getRequestDispatcher("addAsset.jsp").forward(req, resp);
		} else if ("removeAsset".equals(action)) {
			int x = Integer.parseInt(req.getParameter("x"));
			int y = Integer.parseInt(req.getParameter("y"));

			Asset asset = new Asset(null, x, y);
			assetDAO.removeAsset(asset);
			og.deleteAsset(asset);

			resp.sendRedirect("adminAction?action=viewAssets");
		} else if ("saveWall".equals(action)) {
			int x = Integer.parseInt(req.getParameter("x")); 
			int y = Integer.parseInt(req.getParameter("y"));

			Wall wall = new Wall(x, y);
			wallDAO.addWall(wall);
			og.addWall(x, y);

			resp.sendRedirect("adminAction?action=viewGrid");
		}
	}
}
