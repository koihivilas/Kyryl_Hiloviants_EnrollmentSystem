<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Home | EnrollmentSystem</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
</head>
<body>
<c:if test="${empty sessionScope.user}">
    <jsp:include page="navbarnotloginned.jsp"/>
</c:if>
<c:if test="${not empty sessionScope.user}">
    <jsp:include page="navbarloginned.jsp"/>
</c:if>

<c:if test="${empty sessionScope.user}">
    <main class="container">
        <div class="bg-body-tertiary p-5 rounded">
            <h1>Enrollment system</h1>
            <p class="lead">This is the home page. In order to get access to functionality you should sign up or login.</p>
            <div class="col-md-3">
                <a class="btn btn-outline-primary" href="/login" role="button">Login</a>
                <a class="btn btn-primary" href="/signup" role="button">Sign up</a>
            </div>

        </div>
    </main>
</c:if>

<c:if test="${not empty sessionScope.user}">
    <main class="container">
        <div class="bg-body-tertiary p-5 rounded">
            <h1 class="mb-5">Welcome, <c:out value="${sessionScope.user.username}"/>!</h1>
            <p class="lead">Webpage is under construction. Sorry for inconvenience</p>
        </div>
    </main>
</c:if>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
</body>
</html>
