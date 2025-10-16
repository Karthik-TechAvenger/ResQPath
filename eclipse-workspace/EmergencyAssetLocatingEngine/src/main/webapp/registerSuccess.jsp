<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registration Successful</title>

<!-- Bootstrap -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600&display=swap"
	rel="stylesheet">
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css"
	rel="stylesheet">

<style>
body {
	font-family: 'Poppins', sans-serif;
	background: linear-gradient(135deg, #0f9d58, #1a73e8);
	height: 100vh;
	display: flex;
	align-items: center;
	justify-content: center;
	color: #fff;
	overflow: hidden;
}

.success-card {
	background: rgba(255, 255, 255, 0.12);
	backdrop-filter: blur(12px);
	padding: 45px 35px;
	border-radius: 18px;
	text-align: center;
	max-width: 420px;
	width: 100%;
	box-shadow: 0 10px 40px rgba(0, 0, 0, 0.25);
	animation: fadeInUp 0.8s ease;
}

@
keyframes fadeInUp {from { transform:translateY(40px);
	opacity: 0;
}

to {
	transform: translateY(0px);
	opacity: 1;
}

}
.success-icon {
	font-size: 65px;
	margin-bottom: 18px;
	color: #00e676;
	animation: pop 0.6s ease;
}

@
keyframes pop { 0% {
	transform: scale(0.3);
	opacity: 0;
}

100
%
{
transform
:
scale(
1
);
opacity
:
1;
}
}
h2 {
	font-weight: 600;
	font-size: 24px;
	margin-bottom: 10px;
}

p {
	font-size: 15px;
	opacity: 0.9;
}

.btn-custom {
	background: #fff;
	color: #1a73e8;
	border: none;
	padding: 10px 24px;
	border-radius: 8px;
	font-weight: 600;
	transition: all 0.3s ease-in-out;
	text-decoration: none;
}

.btn-custom:hover {
	background: #e9e9e9;
}
</style>

<!-- Auto redirect in 3 seconds -->
<meta http-equiv="refresh" content="3;URL=login.jsp">
</head>
<body>

	<div class="success-card">
		<div class="success-icon">
			<i class="fa-solid fa-circle-check"></i>
		</div>
		<h2>Registration Successful!</h2>
		<p>You will be redirected to login shortly.</p>
		<a href="login.jsp" class="btn-custom mt-3">Go to Login</a>
	</div>

</body>
</html>
