<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="com.src.model.User"%>

<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
    response.setHeader("Pragma", "no-cache"); // HTTP 1.0
    response.setDateHeader("Expires", 0); // Proxies
%>


<%
String successMsg = (String) session.getAttribute("successMsg");
if (successMsg != null) {
%>
<div id="successToast"
	style="position: fixed; top: 20px; right: 650px; /* shifted a bit left from the right edge */ background: #4CAF50; color: white; padding: 14px 20px; font-size: 16px; border-radius: 8px; box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2); opacity: 1; transition: opacity 0.6s ease; z-index: 9999;">
	
	<%=successMsg%>
</div>

<script>
        setTimeout(function() {
            const toast = document.getElementById("successToast");
            toast.style.opacity = '0';
            setTimeout(() => toast.style.display = 'none', 600);
        }, 3000);
    </script>
<%
session.removeAttribute("successMsg");
}
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
<title>Admin Dashboard</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Bootstrap Icons -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css"
	rel="stylesheet">
<style>
body {
	background: #eef1f5;
}

.dashboard-card {
	transition: transform 0.2s;
}

.dashboard-card:hover {
	transform: translateY(-5px);
}

.card-title {
	font-weight: 600;
}

.card-text {
	font-size: 0.9rem;
	color: #555;
}

.card i {
	font-size: 2.5rem;
}

.navbar-brand {
	font-weight: bold;
}
</style>
</head>
<body>

	<!-- Top Navbar -->
	<nav class="navbar navbar-dark bg-dark shadow-sm">
		<div class="container-fluid">
			<span class="navbar-brand">🛠️ Admin Panel</span>
			<div>
				<a class="btn btn-sm btn-danger" href="logout">Logout</a>
			</div>
		</div>
	</nav>

	<!-- Dashboard Content -->
	<div class="container my-5">
		<h3 class="text-center mb-4">Welcome Admin</h3>
		<p class="text-center text-muted mb-5">What operations would you
			like to do?</p>

		<div class="row justify-content-center g-4">
			<!-- Add Asset Card -->
			<div class="col-md-3">
				<div class="card dashboard-card text-center shadow-sm h-100">
					<div class="card-body d-flex flex-column justify-content-between">
						<div>
							<i class="bi bi-plus-circle text-primary mb-3"></i>
							<h5 class="card-title">Add Asset</h5>
							<p class="card-text">Add new assets to the office grid.</p>
						</div>
						<a href="adminAction?action=addAsset" class="btn btn-primary mt-3">Go</a>
					</div>
				</div>
			</div>

			<!-- Remove Asset Card -->
			<div class="col-md-3">
				<div class="card dashboard-card text-center shadow-sm h-100">
					<div class="card-body d-flex flex-column justify-content-between">
						<div>
							<i class="bi bi-dash-circle text-warning mb-3"></i>
							<h5 class="card-title">Remove Asset</h5>
							<p class="card-text">Remove existing assets from the office
								grid.</p>
						</div>
						<a href="adminAction?action=removeAsset"
							class="btn btn-warning mt-3">Go</a>
					</div>
				</div>
			</div>

			<!-- View Assets Card -->
			<div class="col-md-3">
				<div class="card dashboard-card text-center shadow-sm h-100">
					<div class="card-body d-flex flex-column justify-content-between">
						<div>
							<i class="bi bi-list-ul text-dark mb-3"></i>
							<h5 class="card-title">View Assets</h5>
							<p class="card-text">See all assets currently in the office.</p>
						</div>
						<a href="adminAction?action=viewAssets"
							class="btn btn-outline-dark mt-3">Go</a>
					</div>
				</div>
			</div>

			<!-- View Grid Card -->
			<div class="col-md-3">
				<div class="card dashboard-card text-center shadow-sm h-100">
					<div class="card-body d-flex flex-column justify-content-between">
						<div>
							<i class="bi bi-grid-3x3-gap text-success mb-3"></i>
							<h5 class="card-title">View Grid</h5>
							<p class="card-text">Check the current office layout with all
								assets and walls.</p>
						</div>
						<a href="adminAction?action=viewGrid" class="btn btn-success mt-3">Go</a>
					</div>
				</div>
			</div>

			<!-- Add Wall Card -->
			<div class="col-md-3">
				<div class="card dashboard-card text-center shadow-sm h-100">
					<div class="card-body d-flex flex-column justify-content-between">
						<div>
							<i class="bi bi-square-fill text-secondary mb-3"></i>
							<h5 class="card-title">Add Wall</h5>
							<p class="card-text">Add walls to the office layout.</p>
						</div>
						<a href="adminAction?action=addWall"
							class="btn btn-secondary mt-3">Go</a>
					</div>
				</div>
			</div>

		</div>
	</div>

</body>
</html>
