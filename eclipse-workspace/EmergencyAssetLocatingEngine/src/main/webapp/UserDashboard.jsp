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
User user = (User) session.getAttribute("user");
if (user == null || !"USER".equalsIgnoreCase(user.getRole())) {
	response.sendRedirect("login.jsp");
	return;
}
%>
<!DOCTYPE html>
<html>
<head>
<title>User Dashboard</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css"
	rel="stylesheet">
<style>
body {
	background: #f5f7fa;
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
	<nav class="navbar navbar-light bg-white shadow-sm">
		<div class="container-fluid">
			<div>
				<span class="h5 mb-0 fw-bold">ResQPath</span>
			</div>
			<div>
				<a class="btn btn-sm btn-outline-danger" href="logout">Logout</a>
			</div>
		</div>
	</nav>

	<!-- Dashboard Content -->
	<div class="container my-5">
		<h3 class="text-center mb-4">
			Welcome
			<%=user.getName()%>

		</h3>
		<p class="text-center text-muted mb-5">What would you like to do?</p>

		<div class="row justify-content-center g-4">

			<!-- Query Asset Card -->
			<div class="col-md-3">
				<div class="card dashboard-card text-center shadow-sm h-100">
					<div class="card-body d-flex flex-column justify-content-between">
						<div>
							<i class="bi bi-search text-primary mb-3"></i>
							<h5 class="card-title">Query Nearest Asset</h5>
							<p class="card-text">Find the closest asset in the office
								layout.</p>
						</div>
						<a href="userAction?action=queryAsset"
							class="btn btn-primary mt-3">Go</a>
					</div>
				</div>
			</div>

			<!-- My History Card -->
			<div class="col-md-3">
				<div class="card dashboard-card text-center shadow-sm h-100">
					<div class="card-body d-flex flex-column justify-content-between">
						<div>
							<i class="bi bi-journal-text text-info mb-3"></i>
							<h5 class="card-title">My History</h5>
							<p class="card-text">View your previous asset queries and
								actions.</p>
						</div>
						<a href="userAction?action=myHistory"
							class="btn btn-info mt-3 text-white">Go</a>
					</div>
				</div>
			</div>

			<!-- All Users History Card -->
			<div class="col-md-3">
				<div class="card dashboard-card text-center shadow-sm h-100">
					<div class="card-body d-flex flex-column justify-content-between">
						<div>
							<i class="bi bi-people text-dark mb-3"></i>
							<h5 class="card-title">All Users History</h5>
							<p class="card-text">See actions performed by all users.</p>
						</div>
						<a href="userAction?action=allHistory" class="btn btn-dark mt-3">Go</a>
					</div>
				</div>
			</div>

			<!-- Show Office Grid Card -->
			<div class="col-md-3">
				<div class="card dashboard-card text-center shadow-sm h-100">
					<div class="card-body d-flex flex-column justify-content-between">
						<div>
							<i class="bi bi-grid-3x3-gap text-success mb-3"></i>
							<h5 class="card-title">Show Office Grid</h5>
							<p class="card-text">View the current layout with all assets
								and walls.</p>
						</div>
						<a href="userAction?action=viewGrid" class="btn btn-success mt-3">Go</a>
					</div>
				</div>
			</div>

		</div>
	</div>

</body>
</html>
