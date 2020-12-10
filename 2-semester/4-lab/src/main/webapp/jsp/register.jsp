<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
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
    form {
        background: white;
        display: flex;
        font-size: 20px;
        width: 35%;
        flex-direction: column;
        flex-wrap: wrap;
        justify-content: center;
        padding: 1em;
        box-shadow: 0 10px 12px -4px grey;
        border-radius: 1em;
    }
    form label {
        color: #36333a;
        font-size: 15px;
        margin-top: 0.5em;
    }
    .title {
        width: calc(100% - 2em);
        text-align: center;
        color: #36333a;
    }
    .input {
        width: calc(100%);
        font-size: 15px;
        background: rgb(210, 210, 210);
        border: none;
        border-bottom: 3px solid rgb(210, 210, 210);
        border-radius: 0.3em;
        padding: 12px 20px;
    }
    .input:focus {
        border-bottom: 3px solid black;
    }
    .input_error {
        font-size: 15px;
        background: #eb624d;
        border: none;
        border-bottom: 3px solid #eb624d;
        border-radius: 5px;
        padding: 12px 20px;
    }
    .input_error:focus {
        border-bottom: 3px solid #bd3635;
    }
    .button {
        background: black;
        color: white;
        border: 1px solid black;
        border-radius: 0.3em;
        font-size: 15px;
        margin-top: 1em;
        padding-top: 0.5em;
        padding-bottom: 0.5em;
        width: calc(100%);
        transition: 0.2s;
    }
    .button:hover {
        background: rgb(50, 50, 50);
        cursor: pointer;
    }
    .button:active {
        background: rgb(20, 20, 20);
    }
    .button:disabled {
        background: rgb(50, 50, 50);
        color: black;
    }
    .redirect {
        background: transparent;
        border: none;
        color: black;
        margin-top: 1em;
        font-size: 15px;
        text-decoration: underline;
        font-weight: bold;
        cursor: pointer;
    }
    .error_message {
        color: red;
        font-size: 15px;
    }
    .info_message {
        color: cornflowerblue;
        font-size: 15px;
    }
</style>
<body>
    <form method="post" action="${pageContext.request.contextPath}/register" name="registration">
        <c:set var="error" value="${applicationScope.get('error')}" scope="request"/>
        <c:set var="error1" value="${requestScope.get('error')}" scope="request"/>
        <span class="error_message">${error1}</span>
        <c:set var="message" value="${applicationScope.get('message')}" scope="request"/>
        <c:set var="message1" value="${requestScope.get('message')}" scope="request"/>
        <span class="info_message">${message1}</span>
        <c:remove var="error"/>
        <c:remove var="message"/>
        <span class="title">Register</span>

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
