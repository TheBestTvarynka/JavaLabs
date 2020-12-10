<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Request</title>
</head>
<style>
    a {
        text-decoration: none;
        color: #1b191d;
    }
    body {
        width: 100%;
        margin: 0;
        display: flex;
        flex-direction: column;
        background: #f6f6f6;
    }
    .header {
        height: 3em;
        width: 100%;
        box-shadow: 0 4px 10px -6px grey;
        z-index: 3;
        background: #4f4b55;
        display: inline-flex;
        justify-content: space-between;
        align-items: center;
    }
    .header_button {
        background: #f6f6f6;
        color: #1b191d;
        border: 1px solid #f6f6f6;
        border-radius: 0.1em;
        font-size: 15px;
        margin: 0.25em;
        padding: 0.25em;
        transition: 0.2s;
    }
    .header_button:hover {
        background: #b1b1b1;
        border: 1px solid #b1b1b1;
        cursor: pointer;
        border-radius: 0.5em;
    }
    .block {
        display: inline-flex;
        height: 100%;
        align-items: center;
        color: #f6f6f6;
    }
    .block a {
        margin: 0.25em;
        padding: 0.25em;
        transition: 0.2s;
    }
    .block a:hover {
        background: #68666a;
        border-radius: 0.5em;
    }
    .block img {
        height: 50%;
        width: auto;
    }
    .page {
        width: 100%;
        display: inline-flex;
        justify-content: center;
        align-content: center;
    }
    form {
        background: white;
        display: flex;
        font-size: 20px;
        width: 45%;
        flex-direction: column;
        flex-wrap: wrap;
        justify-content: center;
        padding: 1em;
        box-shadow: 0 10px 12px -4px grey;
        border-radius: 1em;
        margin-top: 3em;
    }
    form label {
        color: #36333a;
        font-size: 15px;
        margin-top: 0.5em;
    }
    .title {
        width: 100%;
        text-align: center;
        font-size: 20px;
        color: #1b191d;
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
<div class="header">
    <div>
        <a href="${pageContext.request.contextPath}/browse" class="header_button">Browse</a>
        <a href="${pageContext.request.contextPath}/request" class="header_button">Make request</a>
        <a href="${pageContext.request.contextPath}/order" class="header_button">Make order</a>
    </div>
    <div class="block">
        <span>${sessionScope.get('username')}</span>
        <a href="${pageContext.request.contextPath}/logout">
            <img src="https://img.icons8.com/android/24/ffffff/logout-rounded.png" alt="LogOut"/>
        </a>
    </div>
</div>
<div class="page">
    <form method="post" action="${pageContext.request.contextPath}/request" name="request">
        <c:set var="error" value="${applicationScope.get('error')}" scope="request"/>
        <c:set var="error1" value="${requestScope.get('error')}" scope="request"/>
        <span class="error_message">${error1}</span>
        <c:set var="message" value="${applicationScope.get('message')}" scope="request"/>
        <c:set var="message1" value="${requestScope.get('message')}" scope="request"/>
        <span class="error_message">${message1}</span>
        <c:remove var="error"/>
        <c:remove var="message"/>
        <span class="title">Create new request</span>

        <label>Phone</label>
        <input type="text" name="phone" id="phone" placeholder="e. g. 0987654321" class="input" oninput="checkAll(request)">

        <label>Enter room type</label>
        <input type="text" name="type" id="type" placeholder="e. g. LUX" class="input" required>

        <label>Enter seat number</label>
        <input type="number" name="seatNumber" id="seatNumber" placeholder="seat number" class="input" min="1" required>

        <label>Enter from date</label>
        <input type="date" name="dateFrom" id="dateFrom" class="input" required>

        <label>Enter to date</label>
        <input type="date" name="dateTo" id="dateTo" class="input" required>

        <button type="submit" id="submit" class="button">Submit</button>
    </form>
</div>
<script>
    const checkPhone = form => {
        const re_phone = /^\d{10}$/;
        const input = document.getElementById('phone');
        if (re_phone.test(form.phone.value)) {
            input.setAttribute('class', 'input');
            return true;
        } else {
            input.setAttribute('class', 'input_error');
            return false;
        }
    };
    const checkAll = form => {
        const submitButton = document.getElementById('submit');
        if(checkPhone(form)) {
            console.log('true');
            submitButton.removeAttribute('disabled');
        } else {
            console.log('false');
            submitButton.setAttribute('disabled', 'true');
        }
    };
</script>
</body>
</html>
