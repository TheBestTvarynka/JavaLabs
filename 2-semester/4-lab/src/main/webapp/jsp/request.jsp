<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Request</title>
    <link href="<c:url value="/css/forms.css" />" rel="stylesheet">
    <link href="<c:url value="/css/header.css" />" rel="stylesheet">
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
    .input_error {
        font-size: 15px;
        background: #eb624d;
        border: none;
        border-bottom: 3px solid #eb624d;
        border-radius: 5px;
        padding: 12px 20px;
    }
    .input_error:focus {
        border-bottom: 3px solid red;
    }
    .title {
        width: 100%;
        text-align: center;
        font-size: 20px;
        color: #1b191d;
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
        <span class="error_message">${requestScope.get('error')}</span>
        <span class="info_message">${requestScope.get('message')}</span>
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
