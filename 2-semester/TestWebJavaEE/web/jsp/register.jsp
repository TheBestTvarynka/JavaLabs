<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<style>
    body {
        width: 100%;
        display: inline-flex;
        justify-content: center;
        align-items: center;
    }
    form {
        width: 40%;
        display: flex;
        flex-direction: column;
        justify-content: flex-start;
        margin-top: 3em;
    }
    span {
        width: 100%;
        text-align: center;
        font-size: 20px;
    }
    label {
        margin-top: 0.5em;
    }
    button {
        margin-top: 1em;
    }
</style>
<body>
    <form method="post" action="/web_war_exploded/register">
        <span>Register</span>

        <label>Username</label>
        <input type="text" name="username">

        <label>Full Name</label>
        <input type="text" name="fullName">

        <label>Email</label>
        <input type="email" name="email">

        <label>Password</label>
        <input type="password" name="password">

        <label>Repeat Password</label>
        <input type="password" name="password_repeat">

        <button type="submit">Next</button>
        <a href="/web_war_exploded/login">Login</a>
    </form>
</body>
</html>
