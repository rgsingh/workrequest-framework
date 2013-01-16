<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<title>Welcome</title>
</head>

<body>
	<h2>Welcome to Today</h2>
	Today is
	<fmt:formatDate value="${today}" pattern="yyyy-MM-dd" />.

	<h2>Inventory Management</h2>
	<ul>
		<li><a href="inventory/part/list">Show All Parts</a></li>
	</ul>

</body>
</html>