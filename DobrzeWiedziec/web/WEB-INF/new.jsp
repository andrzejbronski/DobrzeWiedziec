<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>DobrzeWiedzieć - Dodaj nowe informacje</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="/resources/css/styles.css" type="text/css" rel="stylesheet">
    <link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
</head>

<body>
<jsp:include page="parts/navbar.jspf"/>

<div class="container">
    <div class="col-md-8 col-md-offset-2">
        <form class="form-signin" method="post" action="add">
            <h2 class="form-signin-heading">Dodaj nową informację</h2>
            <input name="inputName" type="text" class="form-control" placeholder="Tytuł dodawanej informacji?"
                   required autofocus/>
            <input name="inputUrl" type="url" class="form-control" placeholder="Adres URL"
                   required autofocus/>
            <textarea name="inputDescription" rows="5" name="inputUsername"
                      class="form-control" placeholder="Opis informacji" required autofocus></textarea>
            <input class="btn btn-lg btn-primary btn-block" type="submit"
                   value="Dodaj"/>
        </form>
    </div>
</div>

<jsp:include page="parts/footer.jspf"/>

<script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<script src="resources/js/bootstrap.js"></script>

</body>
</html>