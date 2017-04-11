<%@include file="header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<html>
		<body>	
		
<div id="myCarousel" class="carousel slide footer" data-ride="carousel">
  <!-- Indicators -->
  <ol class="carousel-indicators">
    <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
    <li data-target="#myCarousel" data-slide-to="1"></li>
    <li data-target="#myCarousel" data-slide-to="2"></li>
    <li data-target="#myCarousel" data-slide-to="3"></li>
  </ol>

  <!-- Wrapper for slides -->
  <div class="carousel-inner" role="listbox">
    <div class="item active">
      <img class="image-style" src="<c:url value="/resources/style/img/c1.jpg"/>">
      <div class="carousel-caption text-bright">
        <h3>Research is fast and reliable.</h3>
        <p>We provide fast and reliable research because we CARE about your HEALTH.</p>
      </div>
    </div>

    <div class="item">
      <img class="image-style" src="<c:url value="/resources/style/img/c3.jpg"/>">
      <div class="carousel-caption text-bright">
        <h3>The best staff.</h3>
        <p>The best specialists will take care of you.</p>
      </div>
    </div>

    <div class="item">
      <img class="image-style" src="<c:url value="/resources/style/img/c2.jpg"/>">
      <div class="carousel-caption text-bright">
        <h3>The newest equipment.</h3>
        <p>We use only the latest and tested equipment.</p>
      </div>
    </div>

    <div class="item">
      <img class="image-style" src="<c:url value="/resources/style/img/c4.jpg"/>">
      <div class="carousel-caption text-bright">
        <h3>Above all health.</h3>
        <p>Your health is in safety.</p>
      </div>
    </div>
  </div>

  <!-- Left and right controls -->
  <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
    <span class="sr-only">Previous</span>
  </a>
  <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
    <span class="sr-only">Next</span>
  </a>
</div>
	
		</body>
	</html>