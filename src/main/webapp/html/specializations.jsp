<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Specializations | EnrollmentSystem</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
</head>
<body>
<c:if test="${not empty sessionScope.user}">
    <jsp:include page="/html/navbarloginned.jsp"/>
</c:if>

<c:if test="${not empty sessionScope.user}">
    <div class="container">
        <c:if test="${not empty specializations}">
            <table class="table">
                <thead>
                <tr>
                    <c:if test="${sessionScope.user.role.code == 'ADMIN'}">
                        <th scope="col">#</th>
                    </c:if>
                    <th scope="col">Code</th>
                    <th scope="col">Specialization Name</th>
                    <th scope="col">Description</th>
                    <th scope="col">Limit</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="specialization" items="${specializations}">
                    <tr>
                        <c:if test="${sessionScope.user.role.code == 'ADMIN'}">
                            <th scope="row"><c:out value="${specialization.specializationId}"/></th>
                        </c:if>
                        <td><c:out value="${specialization.code}"/></td>
                        <td><a href="specializations/<c:url value='${specialization.code}'/>"><c:out value="${specialization.name}"/></a></td>
                        <td><c:out value="${specialization.description}"/></td>
                        <td><c:out value="${specialization.limit}"/></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:if>

        <c:if test="${empty specializations}">
            <div class="bg-body-tertiary p-5 rounded">
                <h1>Enrollment system</h1>
                <p class="lead">There are no currently available specializations</p>
            </div>
        </c:if>

    </div>
</c:if>

<c:if test="${empty sessionScope.user}">
    <div class="d-flex align-items-center justify-content-center vh-100">
        <div class="text-center">
            <h1 class="display-1 fw-bold">404</h1>
            <p class="fs-3"> <span class="text-danger">Opps!</span> Page not found.</p>
            <p class="lead">You don't have permission to view this page.</p>
            <a href="/" class="btn btn-primary">Go Home</a>
        </div>
    </div>
</c:if>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
</body>
</html>
