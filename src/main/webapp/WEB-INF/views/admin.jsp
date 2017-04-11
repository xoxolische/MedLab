<%@include file="header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<body>

	<!-- csrt for log out-->
	
	

	<c:if test="${pageContext.request.userPrincipal.name != null}">
		<h2>
			Welcome : ${pageContext.request.userPrincipal.name} | 
			
			
		</h2>
		
		<h1><c:out value="${sessionScope.role}"/></h1>
	</c:if>

</body>
</html>