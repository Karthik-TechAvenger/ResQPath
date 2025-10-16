<%@ page contentType="text/html;charset=UTF-8" language="java"%>
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
<title>Add Wall</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet" />
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css"
	rel="stylesheet">
<style>
body {
	background: #f5f7fa;
	min-height: 100vh;
	display: flex;
	flex-direction: column;
}

.topbar {
	background: #ffffff;
	box-shadow: 0 2px 6px rgba(0, 0, 0, 0.1);
}

.card {
	margin: 50px auto;
	max-width: 500px;
	padding: 30px;
	border-radius: 12px;
	box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
	transition: transform 0.2s;
}

.card:hover {
	transform: translateY(-5px);
}

label {
	font-weight: 600;
}

h4 {
	margin-bottom: 25px;
	font-weight: 600;
	text-align: center;
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
		<div class="card bg-white shadow-sm mt-5">
			<h4>⬛ Add New Wall</h4>
			<form method="post" action="adminAction?action=saveWall">
				<div class="row g-3">
					<div class="col">
						<label class="form-label">X (0-5)</label> <input name="x"
							type="number" min="0" max="5" class="form-control" required />
					</div>
					<div class="col">
						<label class="form-label">Y (0-5)</label> <input name="y"
							type="number" min="0" max="5" class="form-control" required />
					</div>
				</div>

				<div class="mt-4 d-flex justify-content-between">
					<button class="btn btn-dark w-50" type="submit">
						<i class="bi bi-plus-lg"></i> Add Wall
					</button>
					<a class="btn btn-outline-secondary w-45 text-center"
						href="adminAction?action=viewGrid"><i class="bi bi-arrow-left"></i>
						Back to Grid</a>
				</div>
			</form>
		</div>
	</div>
</body>
</html>
