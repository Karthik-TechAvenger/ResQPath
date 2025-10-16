<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.src.model.User"%>

<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
response.setHeader("Pragma", "no-cache"); // HTTP 1.0
response.setDateHeader("Expires", 0); // Proxies
%>

<%
User admin = (User) session.getAttribute("user");
if (admin == null || !"ADMIN".equalsIgnoreCase(admin.getRole())) {
	response.sendRedirect("login.jsp");
	return;
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Remove Asset</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet" />
<style>
body {
	background: #fbfbfb;
}

.topbar {
	background: #ffffff;
	box-shadow: 0 1px 4px rgba(0, 0, 0, 0.06);
}

.logo {
	width: 36px;
	height: 36px;
	display: inline-block;
	background: #1976d2;
	border-radius: 6px;
	margin-right: 10px;
}

.card {
	margin: 28px auto;
	max-width: 640px;
	padding: 20px;
	border-radius: 8px;
}
</style>
</head>
<body>
	<nav class="topbar py-2">
		<div class="container d-flex align-items-center">
			<div>
				<span class="h5 mb-0 fw-bold">ResQPath</span>
			</div>
			<div class="ms-auto">
				<a class="btn btn-sm btn-outline-secondary"
					href="adminDashboard.jsp">Dashboard</a> <a
					class="btn btn-sm btn-outline-secondary"
					href="adminAction?action=viewGrid">View Grid</a> <a
					class="btn btn-sm btn-outline-danger" href="logout">Logout</a>
			</div>
		</div>
	</nav>

	<div class="container">
		<div class="card bg-white shadow-sm mt-4">
			<h4>Remove Asset</h4>
			<form method="post" action="adminAction?action=removeAsset">
				<div class="row g-2">
					<div class="col">
						<label class="form-label">X (0-5)</label> <input name="x"
							type="number" min="0" max="5" class="form-control" required />
					</div>
					<div class="col">
						<label class="form-label">Y (0-5)</label> <input name="y"
							type="number" min="0" max="5" class="form-control" required />
					</div>
				</div>
				<div class="mt-3">
					<button class="btn btn-danger" type="submit">Remove</button>
					<a class="btn btn-link" href="adminAction?action=viewAssets">View
						Assets</a>
				</div>
			</form>
			<div class="mt-3 text-muted">Tip: Use View Grid → click a cell
				to get coordinates.</div>
		</div>
	</div>
</body>
</html>
