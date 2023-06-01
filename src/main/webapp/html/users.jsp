<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Users | EnrollmentSystem</title>
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

<div class="container">
    <div class="row mt-5 justify-content-center">
        <div>
            <h1>Users</h1>
        </div>
        <div>
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>Username</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Email</th>
                    <th>Phone</th>
                    <th>Role</th>
                    <th></th>
                    <th>Status</th>
                    <th></th>
                    <th>Specialization</th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${users}" var="u">
                    <tr>
                        <td><a href="../u/${u.username}">${u.username}</a></td>
                        <td>${u.firstName}</td>
                        <td>${u.lastName}</td>
                        <td>${u.email}</td>
                        <td>${u.phone}</td>
                        <td>${u.role.code}</td>
                        <td>
                            <form action="/users/change_role/${u.userId}" method="post">
                                <input type="hidden" name="page" value="${page}">
                                <select name="roleCode">
                                    <option value="STUDENT">Student</option>
                                    <option value="ADMIN">Admin</option>
                                </select>
                                <button type="submit" class="btn btn-primary">Change role</button>
                            </form>
                        </td>
                        <td>${u.userStatus.code}</td>
                        <td>
                            <form action="/users/change_status/${u.userId}" method="post">
                                <input type="hidden" name="page" value="${page}">
                                <select name="statusCode">
                                    <option value="REG">Registered</option>
                                    <option value="WAIT">Waiting</option>
                                    <option value="RATED">Rated</option>
                                    <option value="ENR">Enrolled</option>
                                    <option value="REJ">Rejected</option>
                                    <option value="ADMIN">For admin</option>
                                </select>
                                <button type="submit" class="btn btn-primary">Change status</button>
                            </form>
                        </td>
                        <td>${u.specialization.name}</td>
                        <c:if test="${u.role.code != 'ADMIN'}">
                            <td>
                                <form action="/users/delete/${u.userId}" method="post">
                                    <input type="hidden" name="page" value="${page}">
                                    <button type="submit" class="btn btn-danger"
                                            onclick="return confirm('Are you sure?')">Delete</button>
                                </form>
                            </td>
                        </c:if>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <div class="d-flex justify-content-center">
                <nav>
                    <ul class="pagination">
                        <c:choose>
                            <c:when test="${page > 1}">
                                <li class="page-item">
                                    <a class="page-link" href="${page - 1}" tabindex="-1">Prev</a>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li class="page-item disabled">
                                    <a class="page-link" href="#" tabindex="-1">Prev</a>
                                </li>
                            </c:otherwise>
                        </c:choose>

                        <c:if test="${startPage > 1}">
                            <li class="page-item disabled">
                                <a class="page-link" href="#">...</a>
                            </li>
                        </c:if>

                        <c:forEach begin="${startPage}" end="${endPage}" var="i">
                            <c:choose>
                                <c:when test="${i == page}">
                                    <li class="page-item active"><a class="page-link" href="${i}">${i}</a></li>
                                </c:when>
                                <c:otherwise>
                                    <li class="page-item"><a class="page-link" href="${i}">${i}</a></li>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>

                        <c:if test="${endPage < totalPages}">
                            <li class="page-item disabled">
                                <a class="page-link" href="#">...</a>
                            </li>
                        </c:if>

                        <c:choose>
                            <c:when test="${page < totalPages}">
                                <li class="page-item">
                                    <a class="page-link" href="${page + 1}">Next</a>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li class="page-item disabled">
                                    <a class="page-link" href="#">Next</a>
                                </li>
                            </c:otherwise>
                        </c:choose>
                    </ul>
                </nav>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
</body>
</html>