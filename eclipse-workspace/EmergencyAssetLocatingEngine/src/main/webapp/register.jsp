<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register - Emergency Asset Locator</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600&display=swap"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
<style>
/* Background Image + Gradient + Blur */
body {
	font-family: 'Poppins', sans-serif;
	background: url('images/fireEx.jpg') no-repeat center center fixed;
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
	background: rgba(67, 160, 71, 0.5);
	backdrop-filter: blur(6px);
	z-index: -1;
}

.register-card {
	width: 420px;
	padding: 42px 38px;
	background: rgba(255, 255, 255, 0.18);
	backdrop-filter: blur(14px);
	border-radius: 22px;
	box-shadow: 0 12px 30px rgba(0, 0, 0, 0.25);
	text-align: center;
	color: #fff;
	animation: fadeIn 0.7s ease;
	position: relative;
	z-index: 1;
}

@
keyframes fadeIn {from { opacity:0;
	transform: translateY(25px);
}

to {
	opacity: 1;
	transform: translateY(0);
}

}
h3 {
	font-weight: 600;
	margin-bottom: 10px;
	color: #fff;
}

p {
	margin-bottom: 20px;
}

.form-label {
	color: #f1f1f1;
	font-weight: 500;
}

.form-control {
	background: rgba(255, 255, 255, 0.28);
	border: none;
	color: #fff;
	border-radius: 12px;
	padding: 11px 14px;
	transition: all 0.3s;
	font-size: 15px;
}

.form-control::placeholder {
	color: rgba(255, 255, 255, 0.75);
}

.form-control:focus {
	box-shadow: 0 0 0 3px rgba(165, 214, 167, 0.45);
}

.btn-success {
	background: #43a047;
	border: none;
	border-radius: 12px;
	padding: 11px 18px;
	font-weight: 600;
	font-size: 16px;
	transition: all 0.3s ease-in-out;
}

.btn-success:hover {
	background: #2e7d32;
	transform: translateY(-2px);
	box-shadow: 0 5px 15px rgba(0, 0, 0, 0.25);
}

a {
	color: #fff;
	text-decoration: underline;
	font-weight: 500;
}

a:hover {
	text-decoration: none;
}

.text-danger {
	font-weight: 500;
	margin-top: 8px;
}

.icon-title {
	font-size: 50px;
	margin-bottom: 8px;
}
</style>
</head>
<body>

	<div class="register-card">
		<div class="icon-title">
			<i class="fa-solid fa-user-plus"></i>
		</div>
		<h3>Create Account</h3>
		<p class="text-light mb-4">Register to start locating assets</p>

		<form method="post" action="register" class="text-start">

			<div class="mb-3">
				<label class="form-label">Email</label> <input type="email"
					name="email" class="form-control" placeholder="you@example.com"
					required>
			</div>

			<div class="mb-3">
				<label class="form-label">Username</label> <input type="text"
					name="uname" class="form-control" placeholder="Choose a username"
					required>
			</div>

			<div class="mb-3">
				<label class="form-label">Password</label> <input type="password"
					name="pwd" class="form-control" placeholder="Choose a password"
					required>
			</div>

			<button type="submit" class="btn btn-success w-100">Register</button>
		</form>

		<p class="mt-3 text-center">
			Already registered? <a href="login.jsp">Login here</a>
		</p>
		<p class="text-danger small text-center">${error}</p>
	</div>

</body>
</html>
