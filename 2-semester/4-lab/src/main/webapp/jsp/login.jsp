<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
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
    <form method="post" action="/Gradle___com_kpi___4_lab_1_0_SNAPSHOT_war/login">
        <%
            ServletContext context = request.getServletContext();
            String message = (String) context.getAttribute("message");
            String error = (String) context.getAttribute("error");
            if (message != null) {
        %>
            <span><jsp:text>${message}</jsp:text></span>
        <%
            }
            if (error != null) {
        %>
            <span><jsp:text>${error}</jsp:text></span>
        <%
            }
            context.removeAttribute("message");
            context.removeAttribute("error");
        %>
        <span>Login</span>

        <label>Username</label>
        <input type="text" name="username" id="username">

        <label>Password</label>
        <input type="password" name="password" id="password">

        <button type="submit">Next</button>
        <a href="/Gradle___com_kpi___4_lab_1_0_SNAPSHOT_war/register">Register</a>
    </form>
</body>
</html>
