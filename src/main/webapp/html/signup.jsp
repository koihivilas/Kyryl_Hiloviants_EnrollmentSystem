<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>SignUp | EnrollmentSystem</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
</head>
<body>
<c:if test="${empty sessionScope.user}">
    <jsp:include page="/html/navbarnotloginned.jsp"/>
</c:if>
<c:if test="${not empty sessionScope.user}">
    <jsp:include page="/html/navbarloginned.jsp"/>
</c:if>

<c:if test="${empty sessionScope.user}">
    <div class="container my-5">
        <div class="row justify-content-center">
            <div class="col-md-4">
                <div class="card-header pb-4 text-center">
                    <h3>Please sign up</h3>
                </div>
                <div class="card-body">
                    <form class="needs-validation" action="/html/signup?command=signup" method="POST" novalidate>
                        <div class="has-validation">
                            <div class="form-floating mb-2">
                                <input type="text" class="form-control" id="username" name="username" placeholder="exampleuser" required>
                                <label for="username">Username</label>
                                <div class="valid-feedback mb-4">
                                    Looks good!
                                </div>
                                <div class="invalid-feedback mb-4">
                                    Please enter your username.
                                </div>
                            </div>
                        </div>

                        <div class="form-floating mb-2">
                            <div class="has-validation">
                                <div class="form-floating mb-2">
                                    <input type="text" class="form-control" id="firstName" name="firstName" placeholder="Bob" required>
                                    <label for="firstName">First name</label>
                                    <div class="valid-feedback mb-4">
                                        Looks good!
                                    </div>
                                    <div class="invalid-feedback mb-4">
                                        Please enter your first name.
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="form-floating mb-2">
                            <div class="has-validation">
                                <div class="form-floating mb-2">
                                    <input type="text" class="form-control" id="lastName" name="lastName" placeholder="Bobenko" required>
                                    <label for="lastName">Last name</label>
                                    <div class="valid-feedback mb-4">
                                        Looks good!
                                    </div>
                                    <div class="invalid-feedback mb-4">
                                        Please enter your last name.
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="form-floating mb-2">
                            <div class="has-validation">
                                <div class="form-floating mb-2">
                                    <input type="email" class="form-control" id="email" name="email" placeholder="name@example.com" required>
                                    <label for="email">Email address</label>
                                    <div class="valid-feedback mb-4">
                                        Looks good!
                                    </div>
                                    <div class="invalid-feedback mb-4">
                                        Please enter your email.
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="form-floating mb-2">
                            <div class="has-validation">
                                <div class="form-floating mb-2">
                                    <input type="tel" pattern="380[0-9]{9}" class="form-control" id="phone" name="phone" placeholder="380500500505" required>
                                    <label for="phone">Phone</label>
                                    <div class="valid-feedback mb-4">
                                        Looks good!
                                    </div>
                                    <div class="invalid-feedback mb-4">
                                        Please enter your phone number. Example: 380500500505
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="form-floating mb-2">
                            <div class="has-validation">
                                <div class="form-floating mb-2">
                                    <input type="password" class="form-control" id="password" name="password" placeholder="$x///\mpl@U$R123" required>
                                    <label for="password">Password</label>
                                    <div class="valid-feedback mb-4">
                                        Looks good!
                                    </div>
                                    <div class="invalid-feedback mb-4">
                                        Please enter your password
                                    </div>
                                </div>
                            </div>
                        </div>

                        <button class="mt-3 w-100 btn btn-lg btn-primary" type="submit">Sign up</button>
                    </form>
                    <c:if test="${not empty requestScope.error}">
                        <div class="alert alert-danger mt-3" role="alert">
                            <c:out value="${requestScope.error}"/>
                        </div>
                    </c:if>
                </div>

            </div>

        </div>
    </div>
</c:if>

<c:if test="${not empty sessionScope.user}">
    <div class="container my-5">
        <div class="row justify-content-center">
            <div class="col-md-4">
                <div class="card-header pb-4 text-center">
                    <h3>You are already logged in!</h3>
                </div>
            </div>
        </div>
    </div>
</c:if>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
<script>
    (() => {
        'use strict'

        // Fetch all the forms we want to apply custom Bootstrap validation styles to
        const forms = document.querySelectorAll('.needs-validation')

        // Loop over them and prevent submission
        Array.from(forms).forEach(form => {
            form.addEventListener('submit', event => {
                if (!form.checkValidity()) {
                    event.preventDefault()
                    event.stopPropagation()
                }

                form.classList.add('was-validated')
            }, false)
        })
    })()
</script>
</body>
</html>
