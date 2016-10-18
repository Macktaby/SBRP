<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
</head>

<style>
body {
	font-family: "Lato", sans-serif;
}

ul.tab {
	list-style-type: none;
	margin: 0;
	padding: 0;
	overflow: hidden;
	border: 1px solid #ccc;
	background-color: #f1f1f1;
}

/* Float the list items side by side */
ul.tab li {
	float: left;
}

/* Style the links inside the list items */
ul.tab li a {
	display: inline-block;
	color: black;
	text-align: center;
	padding: 14px 16px;
	text-decoration: none;
	transition: 0.3s;
	font-size: 17px;
}

/* Change background color of links on hover */
ul.tab li a:hover {
	background-color: #ddd;
}

/* Create an active/current tablink class */
ul.tab li a:focus, .active {
	background-color: #ccc;
}

/* Style the tab content */
.tabcontent {
	display: none;
	padding: 6px 12px;
	-webkit-animation: fadeEffect 1s;
	animation: fadeEffect 1s;
}

@
-webkit-keyframes fadeEffect {
	from {opacity: 0;
}

to {
	opacity: 1;
}

}
@
keyframes fadeEffect {
	from {opacity: 0;
}

to {
	opacity: 1;
}
}
</style>

<body>

	<ul class="tab">
		<li><a href="/ImageInterior/view/home" class="tablinks">Home</a></li>
		<li><a href="/ImageInterior/view/catalogues" class="tablinks">Catalogues</a></li>
		<li><a href="/ImageInterior/view/products" class="tablinks">Products</a></li>
		<li><a href="/ImageInterior/view/showrooms" class="tablinks">Showrooms</a></li>
		<li><a href="/ImageInterior/view/designers" class="tablinks">Designers</a></li>
		<li><a href="/ImageInterior/view/houses" class="tablinks">Houses</a></li>
		<li><a href="/ImageInterior/view/envogue" class="tablinks">Envogue</a></li>
	</ul>

</body>
</html>