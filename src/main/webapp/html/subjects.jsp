<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Subjects | EnrollmentSystem</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
</head>
<body>
<c:if test="${not empty sessionScope.user}">
  <jsp:include page="/html/navbarloginned.jsp"/>
</c:if>

<c:if test="${not empty sessionScope.user}">
  <c:if test="${sessionScope.user.role.code == 'STUDENT'}">
    <div class="container">
      <c:if test="${not empty subjects}">
        <c:forEach items="${subjects}" var="subject" varStatus="count">
          <c:if test="${count.index % 4 == 0}">
            <div class="row justify-content-center">
          </c:if>
          <div class="col-md-3 col-sm-6 mb-4">
            <div class="card h-100">
              <div class="card-body">
                <h5 class="card-title"><a href="subjects/<c:url value='${subject.subjectId}'/>"><c:out value="${subject.name}"/></a></h5>
                <p class="card-text"><c:out value="${subject.description}"/></p>
              </div>
              <div class="card-footer bg-white">
                <form action="/subjects/change_score/${subject.subjectId}" method="post">
                  <input type="hidden" name="score" value="-1">
                  <c:choose>
                    <c:when test="${subject.available == true}">
                      <button type="submit" class="btn btn-primary">Start test</button>
                    </c:when>
                    <c:otherwise>
                      <button type="submit" class="btn btn-primary" disabled>Start test</button>
                    </c:otherwise>
                  </c:choose>
                </form>
              </div>
            </div>
          </div>
          <c:if test="${count.index % 4 == 3}">
            </div>
          </c:if>
        </c:forEach>
      </c:if>

      <c:if test="${empty subjects}">
        <div class="bg-body-tertiary p-5 rounded">
          <h1>Enrollment system</h1>
          <p class="lead">There are no currently available subject tests</p>
        </div>
      </c:if>

    </div>
  </c:if>

  <c:if test="${sessionScope.user.role.code == 'ADMIN'}">
    <div class="container">
      <c:if test="${not empty subjects}">
        <table class="table">
          <thead>
          <tr>
            <th scope="col">#</th>
            <th scope="col">Subject Name</th>
            <th scope="col">Description</th>
            <th scope="col">Closed</th>
          </tr>
          </thead>
          <tbody>
          <c:forEach var="subject" items="${subjects}">
            <tr>
              <th scope="row"><c:out value="${subject.subjectId}"/></th>
              <td><a href="subjects/<c:url value='${subject.subjectId}'/>"><c:out value="${subject.name}"/></a></td>
              <td><c:out value="${subject.description}"/></td>
              <td><c:out value="${subject.closed}"/></td>
            </tr>
          </c:forEach>
          </tbody>
        </table>
      </c:if>

      <c:if test="${empty subjects}">
        <div class="bg-body-tertiary p-5 rounded">
          <p class="lead">There are no currently available subject tests</p>
        </div>
      </c:if>

    </div>
  </c:if>

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
