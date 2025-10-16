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
<title>All Assets</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"/>
<style>
body {
    background: #f5f6f9;
    font-family: "Poppins", sans-serif;
}

.topbar {
    background: #ffffff;
    box-shadow: 0 2px 6px rgba(0,0,0,0.08);
    padding: 12px 0;
}

.logo {
    width: 36px;
    height: 36px;
    background: #1976d2;
    border-radius: 6px;
}

.card {
    border-radius: 12px;
    margin-top: 20px;
    box-shadow: 0 4px 12px rgba(0,0,0,0.05);
}

.table th {
    background: #1976d2;
    color: white;
    font-weight: 500;
}

.table-striped > tbody > tr:nth-of-type(odd) {
    background-color: #f9fafc;
}

.table-hover tbody tr:hover {
    background-color: #e3f2fd;
}

.btn-primary, .btn-secondary {
    border-radius: 6px;
    font-weight: 500;
}
</style>
</head>
<body>
<nav class="topbar">
    <div class="container d-flex align-items-center justify-content-between">
        <div class="d-flex align-items-center">
           
            <h5 class="mb-0 text-primary">ResQPath</h5>
        </div>
        <div>
            <a class="btn btn-sm btn-outline-secondary me-2" href="adminDashboard.jsp">Dashboard</a>
            <a class="btn btn-sm btn-outline-secondary me-2" href="adminAction?action=viewGrid">View Grid</a>
            <a class="btn btn-sm btn-outline-danger" href="logout">Logout</a>
        </div>
    </div>
</nav>

<div class="container">
    <div class="card p-4 shadow-sm">
        <h4 class="mb-3">All Assets</h4>

        <c:choose>
            <c:when test="${empty assets}">
                <div class="alert alert-info">No assets found.</div>
            </c:when>
            <c:otherwise>
                <div class="table-responsive">
                    <table class="table table-striped table-hover align-middle">
                        <thead>
                            <tr>
                                <th>No</th>
                                <th>Type</th>
                                <th>X</th>
                                <th>Y</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="a" items="${assets}" varStatus="st">
                                <tr>
                                    <td>${st.index + 1}</td>
                                    <td>${a.type}</td>
                                    <td>${a.x}</td>
                                    <td>${a.y}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:otherwise>
        </c:choose>

        <div class="mt-3 d-flex gap-2">
            <a class="btn btn-primary" href="adminAction?action=addAsset">Add Asset</a>
            <a class="btn btn-secondary" href="adminDashboard.jsp">Back</a>
        </div>
    </div>
</div>
</body>
</html>
