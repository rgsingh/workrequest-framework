<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<title>Welcome</title>
</head>

<body>
<h2>Welcome to Today</h2>
Today is <fmt:formatDate value="${today}" pattern="yyyy-MM-dd" />.

<h2>Get your coffee order in XML!!</h2>
<ul>
<li><a href="rest/coffee/Folgers">Folgers</a></li>
<li><a href="rest/coffee/Dunkin">Dunkin</a></li>
<li><a href="rest/coffee/7Eleven">7Eleven</a></li>
</ul>

</body>
</html>