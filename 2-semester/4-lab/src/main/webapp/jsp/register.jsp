<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
    <link href="<c:url value="/css/forms.css" />" rel="stylesheet">
</head>
<style>
    body {
        width: calc(100% - 2em);
        height: calc(100vh - 2em);
        display: inline-flex;
        justify-content: center;
        align-items: center;
        background: #f6f6f6;
    }
</style>
<body>
    <form method="post" action="${pageContext.request.contextPath}/register" name="registration" class="login_form">
        <span class="error_message">${requestScope.get('error')}</span>
        <span class="error_message">${requestScope.get('message')}</span>
        <span class="login_form_title">Register</span>

        <label>Username</label>
        <input type="text" name="username" id="username" class="input" placeholder="e. g. cap_map" oninput="checkAll(registration)" required>

        <label>Full Name</label>
        <input type="text" name="fullName" id="full_name" class="input" placeholder="Cap Makse" required>

        <label>Email</label>
        <input type="email" name="email" id="email" class="input" placeholder="cap_map@example.com" oninput="checkAll(registration)" required>

        <label>Password</label>
        <input type="password" name="password" id="password" class="input" oninput="checkAll(registration)" required>

        <label>Repeat Password</label>
        <input type="password" name="password_repeat" id="password_repeat" class="input" oninput="checkAll(registration)" required>

        <button type="submit" id="submit" class="button">Next</button>
        <a href="${pageContext.request.contextPath}/login" class="redirect">Login</a>
    </form>
    <script>
        const checkEmail = form => {
            const re_mail = /^(([^<>()\[\]\.,;:\s@\"]+(\.[^<>()\[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i;
            const input = document.getElementById('email');
            if (re_mail.test(form.email.value) && form.email.value !== 0) {
                input.setAttribute('class', 'input');
                return true;
            } else {
                input.setAttribute('class', 'input_error');
                return false;
            }
        };
        const checkUsername = form => {
            const re_name = /^[a-zA-Z0-9]+$/;
            const input = document.getElementById('username');
            if (re_name.test(form.username.value) && form.username.value !== 0) {
                input.setAttribute('class', 'input');
                return true;
            } else {
                input.setAttribute('class', 'input_error');
                return false;
            }
        };
        const checkPasswords = form => {
            const input = document.getElementById('password');
            const input_repeat = document.getElementById('password_repeat');
            if (form.password.value === form.password_repeat.value && form.password.value.length !== 0) {
                input.setAttribute('class', 'input');
                input_repeat.setAttribute('class', 'input');
                return true;
            } else {
                input.setAttribute('class', 'input_error');
                input_repeat.setAttribute('class', 'input_error');
                return false;
            }
        };
        const checkAll = form => {
            const submitButton = document.getElementById('submit');
            const ce = checkEmail(form);
            const cp = checkPasswords(form);
            const cu = checkUsername(form);
            if (ce && cp && cu) {
                submitButton.removeAttribute('disabled');
            } else {
                submitButton.setAttribute('disabled', 'true');
            }
        };
    </script>
</body>
</html>
