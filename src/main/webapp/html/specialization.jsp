<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title><c:out value="${spec.name}"/> | EnrollmentSystem</title>
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
  <div class="row">
    <div class="col-lg-4">
      <div class="card mb-4">
        <div class="card-body text-center">
          <img src="https://img.freepik.com/premium-vector/collection-colored-thin-icon-learning-subject-book-graduated-hat-learning-education-concept-vector-illustration_168824-141.jpg" alt="subject image" class="rounded-circle img-fluid" style="width: 150px;">
          <h5 class="my-3"><c:out value="${spec.name}" /></h5>
        </div>
      </div>
    </div>
    <div class="col-lg-8">
      <div class="card mb-4">
        <div class="card-body">
          <div class="row">
            <div class="col-sm-3">
              <p class="mb-0">Specialization Code</p>
            </div>
            <div class="col-sm-9">
              <p class="text-muted mb-0"><c:out value="${spec.code}" /></p>
            </div>
          </div>
          <hr>
          <div class="row">
            <div class="col-sm-3">
              <p class="mb-0">Title</p>
            </div>
            <div class="col-sm-9">
              <p class="text-muted mb-0"><c:out value="${spec.name}" /></p>
            </div>
          </div>
          <hr>
          <div class="row">
            <div class="col-sm-3">
              <p class="mb-0">Description</p>
            </div>
            <div class="col-sm-9">
              <p class="text-muted mb-0"><c:out value="${spec.description}" /></p>
            </div>
          </div>
          <hr>
          <div class="row">
            <div class="col-sm-3">
              <p class="mb-0">Maximum amount of enrolled students</p>
            </div>
            <div class="col-sm-9">
              <p class="text-muted mb-0"><c:out value="${spec.limit}" /></p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
</body>
</html>