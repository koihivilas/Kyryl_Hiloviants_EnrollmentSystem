<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
</head>
<body>
<header class="p-3 text-bg-dark mb-4">
    <div class="container">
        <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
            <a href="/html/home.jsp" class="d-flex align-items-center mb-2 mb-lg-0 text-secondary text-decoration-none">EnrollmentSystem</a>
            <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                <li><a href="/html/home.jsp" class="nav-link px-2 text-white">Home</a></li>
                <li><a href="/html/subjects?command=subjects" class="nav-link px-2 text-white">Subjects</a></li>
                <li><a href="/html/specializations?command=specializations" class="nav-link px-2 text-white">Specializations</a></li>
            </ul>
            <form method="post" action="/html/logout?command=logout">
                <button type="submit" class="btn btn-outline-light me-2">Log out</button>
            </form>
        </div>
    </div>
</header>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
</body>
</html>

