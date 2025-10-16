<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login - Emergency Asset Locator</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600&display=swap"
	rel="stylesheet">
<style>
/* Background Image + Gradient + Blur */
body {
	font-family: 'Poppins', sans-serif;
	background: url('images/fire-exit-2.jpg') no-repeat center center fixed;
	background-size: cover;
	height: 100vh;
	display: flex;
	align-items: center;
	justify-content: center;
	margin: 0;
	position: relative;
}

/* Gradient & Blur overlay */
body::before {
	content: '';
	position: absolute;
	top: 0;
	left: 0;
	width: 100vw;
	height: 100vh;
	background: rgba(26, 115, 232, 0.5);
	backdrop-filter: blur(6px);
	z-index: -1;
}

.login-card {
	width: 420px;
	padding: 40px;
	background: rgba(255, 255, 255, 0.15);
	border-radius: 22px;
	backdrop-filter: blur(12px);
	box-shadow: 0 12px 35px rgba(0, 0, 0, 0.3);
	color: #fff;
	text-align: center;
	transition: transform 0.3s ease-in-out, box-shadow 0.3s ease-in-out;
	position: relative;
	z-index: 1;
}

.login-card:hover {
	transform: scale(1.02);
	box-shadow: 0 16px 45px rgba(0, 0, 0, 0.35);
}

h3 {
	font-weight: 600;
	margin-bottom: 12px;
	color: #fff;
}

.text-light {
	margin-bottom: 25px;
	font-size: 0.95rem;
}

.form-label {
	color: #fff;
	font-weight: 500;
}

.form-control {
	background: rgba(255, 255, 255, 0.25);
	border: none;
	color: #fff;
	border-radius: 12px;
	padding: 10px;
	transition: all 0.3s;
}

.form-control:focus {
	box-shadow: 0 0 0 3px rgba(144, 202, 249, 0.6);
	background: rgba(255, 255, 255, 0.35);
}

.btn-primary {
	background: #1a73e8;
	border: none;
	border-radius: 12px;
	padding: 10px;
	font-weight: 500;
	transition: all 0.3s;
}

.btn-primary:hover {
	background: #155ab6;
	transform: translateY(-2px);
	box-shadow: 0 8px 18px rgba(0, 0, 0, 0.25);
}

a {
	color: #fff;
	font-weight: 500;
	text-decoration: underline;
	transition: color 0.2s;
}

a:hover {
	text-decoration: none;
	color: #dcdcdc;
}

.text-danger, .text-success {
	font-weight: 500;
	font-size: 0.9rem;
	margin-top: 10px;
	display: block;
}
</style>
</head>
<body>

	<div class="login-card">
		<h3>Login</h3>
		<p class="text-light">Access your account</p>

		<form action="login" method="post" class="text-start">
			<div class="mb-3">
				<label class="form-label">Username</label> <input type="text"
					name="uname" class="form-control" placeholder="Enter username"
					required>
			</div>

			<div class="mb-3">
				<label class="form-label">Password</label> <input type="password"
					name="pwd" class="form-control" placeholder="Enter password"
					required>
			</div>

			<button type="submit" class="btn btn-primary w-100">Login</button>
		</form>

		<p class="text-danger text-center">${error}</p>

		<p class="mt-3 text-center">
			New user? <a href="register.jsp">Register here</a>
		</p>
	</div>

</body>
</html>
