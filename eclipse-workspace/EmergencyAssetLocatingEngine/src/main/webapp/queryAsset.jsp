<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.src.model.User"%>

<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
response.setHeader("Pragma", "no-cache"); // HTTP 1.0
response.setDateHeader("Expires", 0); // Proxies
%>

<%
User user = (User) session.getAttribute("user");
if (user == null || !"USER".equalsIgnoreCase(user.getRole())) {
	response.sendRedirect("login.jsp");
	return;
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Find Nearest Asset</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet" />
<style>
body {
	background: #fbfbfb;
}

.topbar {
	background: #fff;
	box-shadow: 0 1px 4px rgba(0, 0, 0, 0.06);
}

.logo {
	width: 36px;
	height: 36px;
	background: #1976d2;
	border-radius: 6px;
}

.card {
	margin: 28px auto;
	max-width: 640px;
	padding: 18px;
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
				<a class="btn btn-sm btn-outline-secondary" href="UserDashboard.jsp">Dashboard</a>
				<a class="btn btn-sm btn-outline-danger" href="logout">Logout</a>
			</div>
		</div>
	</nav>

	<div class="container">
		<div class="card bg-white shadow-sm mt-4">
			<h4>Find Nearest Emergency Asset</h4>

			<!-- ✅ Form mapped to your servlet -->
			<form method="post" action="userAction?action=queryAsset">

				<div class="mb-3">
					<label class="form-label">Your Location</label> <select
						name="location" class="form-select" required>
						<option value="Lobby">Lobby</option>
						<option value="Meeting Room 1">Meeting Room 1</option>
						<option value="Meeting Room 2">Meeting Room 2</option>
						<option value="Games Room">Games Room</option>
						<option value="Cafeteria">Cafeteria</option>
					</select>
				</div>

				<div class="mb-3">
					<label class="form-label">Asset Type</label> <select name="type"
						class="form-select" required>
						<option value="FIRE_EXTINGUISHER">Fire Extinguisher</option>
						<option value="FIRST_AID_KIT">First Aid Kit</option>
						<option value="EMERGENCY_EXIT">Emergency Exit</option>
					</select>
				</div>

				<div>
					<button class="btn btn-primary" type="submit">Find Nearest</button>
					<a class="btn btn-link" href="UserDashboard.jsp">Back</a>
				</div>
			</form>

			<!-- ✅ Show message if no asset found -->
			<c:if test="${not empty message}">
				<div class="alert alert-warning mt-3">${message}</div>
			</c:if>

			<div class="mt-3 text-muted small">Note: Choose your current
				office location — the system will compute the shortest path.</div>
		</div>
	</div>
</body>
</html>
