<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
  <title>DobrzeWiedzieć</title>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link href="/resources/css/styles.css" type="text/css" rel="stylesheet">
  <link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
</head>

<body>
<nav class = "navbar navbar-inverse navbar-fixed-top">
  <div class="container">
    <a href="#" class="navbar-brand">DobrzeWiedzieć</a>
    <button class="navbar-toggle" data-toggle="collapse" data-target=".navHeaderCollapse">
      <span class="glyphicon glyphicon-list"></span>
    </button>
    <div class="collapse navbar-collapse navHeaderCollapse">
      <ul class="nav navbar-nav navbar-right">
        <li class="active"><a href="#">Główna</a></li>
        <li><a href="${pageContext.request.contextPath}/add">Dodaj</a></li>
        <c:choose>
        <c:when test="${not empty sessionScope.user}">
          <li><a href="${pageContext.request.contextPath}/logout">Wyloguj się</a></li>
        </c:when>
        <c:otherwise>
          <li><a href="${pageContext.request.contextPath}/login">Zaloguj się</a></li>
        </c:otherwise>
        </c:choose>
      </ul>
    </div>
  </div>
</nav>

<c:if test="${not empty requestScope.informations}">
  <c:forEach var="information" items="${requestScope.informations}">
    <div class="container">
      <div class="row bs-callout bs-callout-primary">
        <div class="col col-md-1 col-sm-2">
          <a href="${pageContext.request.contextPath}/vote?information_id=${information.id}&vote=VOTE_UP" class="btn btn-block btn-primary btn-success">
            <span class="glyphicon glyphicon-arrow-up"></span>  </a>
          <div class="well well-sm centered"><c:out value="${information.upVote - information.downVote}" /></div>
          <a href="${pageContext.request.contextPath}/vote?information_id=${information.id}&vote=VOTE_DOWN" class="btn btn-block btn-primary btn-warning">
            <span class="glyphicon glyphicon-arrow-down"></span>  </a>
        </div>
        <div class="col col-md-11 col-sm-10">
          <h3 class="centered"><a href="<c:out value="${information.url}" />"><c:out value="${information.name}" /></a></h3>
          <h6><small>Dodane przez: <c:out value="${information.user.username}" />,
            Dnia: <fmt:formatDate value="${information.timestamp}" pattern="dd/MM/YYYY"/></small></h6>
          <p><c:out value="${information.description}" /></p>
          <a href="<c:out value="${information.url}" />" class="btn btn-default btn-xs">Przejdź do strony</a>
        </div>
      </div>
    </div>
  </c:forEach>
</c:if>

<footer class="footer">
  <div class="container">
    <p class="navbar-text">DobrzeWiedzieć - <a href="http://andrzejbronski.pl">Andrzej</a></p>
  </div>
</footer>

<script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<script src="resources/js/bootstrap.js"></script>
</body>
</html>