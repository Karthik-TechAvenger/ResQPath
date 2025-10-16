<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
response.setHeader("Pragma", "no-cache"); // HTTP 1.0
response.setDateHeader("Expires", 0); // Proxies
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>Query History</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet" />
<style>
body {
	background-color: #f5f6f9;
	font-family: "Poppins", sans-serif;
}

.topbar {
	background-color: #ffffff;
	box-shadow: 0 2px 6px rgba(0, 0, 0, 0.08);
	padding: 12px 0;
}

.logo {
	width: 36px;
	height: 36px;
	background: #1976d2;
	border-radius: 8px;
}

.card {
	border-radius: 16px;
	border: none;
	padding: 24px;
	box-shadow: 0 8px 20px rgba(0, 0, 0, 0.1);
	transition: transform 0.2s ease-in-out, box-shadow 0.2s ease-in-out;
}

.card:hover {
	transform: translateY(-2px);
	box-shadow: 0 12px 25px rgba(0, 0, 0, 0.15);
}

.table th {
	background: #1976d2;
	color: white;
	font-weight: 500;
}

.table-striped>tbody>tr:nth-of-type(odd) {
	background-color: #f9fafc;
}

.highlight {
	font-weight: 600;
	color: #1976d2;
}

.highlight-asset {
	color: #e91e63;
	font-weight: 600;
}

.timestamp {
	color: #6c757d;
	font-size: 0.9rem;
}

.btn-link {
	text-decoration: none;
	font-weight: 500;
}

.btn-link:hover {
	text-decoration: underline;
}
</style>
</head>
<body>
	<nav class="topbar">
		<div
			class="container d-flex align-items-center justify-content-between">
			<div class="d-flex align-items-center">
				<div>
					<span class="h5 mb-0 fw-bold">ResQPath</span>
				</div>
				
			</div>
			<div>
				<a class="btn btn-sm btn-outline-secondary me-2"
					href="userDashboard.jsp">Dashboard</a> <a
					class="btn btn-sm btn-outline-danger" href="logout">Logout</a>
			</div>
		</div>
	</nav>

	<div class="container mt-4">
		<div class="card">
			<h4 class="card-title mb-4">Query History</h4>

			<c:choose>
				<c:when test="${empty history}">
					<div class="alert alert-info rounded-3">No history records
						found.</div>
				</c:when>
				<c:otherwise>
					<div class="table-responsive">
						<table class="table table-striped table-hover align-middle mb-0">
							<thead>
								<tr>
									<th>No</th>
									<th>Time</th>
									<th>User</th>
									<th>User Location (x,y)</th>
									<th>Nearest Asset</th>
									<th>Asset Location (x,y)</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="rec" items="${history}" varStatus="st">
									<tr>
										<td>${st.index + 1}</td>
										<td class="timestamp">${rec.timestamp}</td>
										<td class="highlight">${rec.username}</td>
										<td>(${rec.x}, ${rec.y})</td>
										<td class="highlight-asset">${rec.nearestAssetType}</td>
										<td>(${rec.assetX}, ${rec.assetY})</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</c:otherwise>
			</c:choose>

			<div class="mt-4">
				<a href="javascript:history.back()"
					class="btn btn-link text-primary">← Back to Dashboard</a>
			</div>
		</div>
	</div>
</body>
</html>
