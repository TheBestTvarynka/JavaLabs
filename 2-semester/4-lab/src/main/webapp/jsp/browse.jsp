<%@ page import="java.util.List" %>
<%@ page import="com.kpi.lab4.entities.Room" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Browse</title>
</head>
<style>
    body {
        width: 100%;
        height: 100%;
        display: flex;
        flex-direction: column;
        padding: 0;
        margin: 0;
    }
    .header {
        height: 3em;
        width: 100%;
        box-shadow: 0 4px 10px -6px grey;
        z-index: 3;
        background: #4f4b55;
        display: inline-flex;
        justify-content: flex-end;
    }
    .header span {
        margin: 0.5em;
    }
    .page {
        height: calc(100% - 3em);
        width: 100%;
        display: inline-flex;
        justify-content: center;
        align-content: center;
    }
    .content {
        width: 60%;
        display: flex;
        flex-direction: column;
    }
    .search {
        width: 100%;
        margin-top: 1em;
        display: inline-flex;
        justify-content: space-between;
        align-items: center;
        flex-wrap: wrap;
        border: 2px solid #4f4b55;
        border-radius: 0.25em;
        padding: 0.5em;
        box-shadow: 0 10px 12px -4px grey;
    }
    .result {
        border: 2px solid #4f4b55;
        box-shadow: 0 10px 12px -4px grey;
        margin-bottom: 1em;
        padding: 0.5em;
        border-radius: 0.25em;
        width: 100%;
        display: flex;
        flex-direction: column;
    }
    .room {
        display: grid;
        grid-gap: 0;
        grid-template-columns: 40% 60%;
        grid-template-rows: auto;
        margin: 0.25em;
        padding: 0.25em;
        border-radius: 0.25em;
        background: #d3d3d3;
        border-bottom: 2px solid #d3d3d3;
    }
    .room:hover {
        background: #b1b1b1;
        border-bottom: 2px solid #4f4b55;
        cursor: pointer;
    }
    .room span {
        width: 100%;
        text-align: left;
    }
</style>
<body>
    <%
        ServletContext context = request.getServletContext();
    %>
    <div class="header">
        <%
            String username = (String)request.getSession().getAttribute("username");
        %>
        <span>${username}</span>
        <span>LogOut</span>
    </div>
    <div class="page">
        <div class="content">
            <form class="search">
                <select name="price" id="priceOrder">
                    <option value="asc" selected>Asc</option>
                    <option value="desc">Desc</option>
                </select>
                <select name="seat" id="seatOrder">
                    <option value="asc" selected>Asc</option>
                    <option value="desc">Desc</option>
                </select>
                <div>
                    <label>
                        <input type="checkbox" value="ROOM" checked name="types">
                        <span>Room</span>
                    </label>
                    <label>
                        <input type="checkbox" value="VIP" name="types">
                        <span>Vip</span>
                    </label>
                </div>
                <div>
                    <label>
                        <input type="checkbox" value="AVAILABLE" checked name="statuses">
                        <span>Available</span>
                    </label>
                    <label>
                        <input type="checkbox" value="REPAIR" name="statuses">
                        <span>Repair</span>
                    </label>
                </div>
                <button type="submit">Search</button>
            </form>
            <div class="result">
                <%
                    List<Room> rooms = (List<Room>)context.getAttribute("rooms");
                    System.out.println(rooms);
                    if (rooms == null || rooms.size() < 1) {
                    %>
                        <span>No rooms found :(</span>
                    <%
                    } else {
                        System.out.println(rooms.size());
                        %>
                        <c:forEach items="${rooms}" var="room">
                            <div class="room">
                                <span>${room.getNumber()}</span>
                                <span>${room.getType()}</span>
                                <span>${room.getSeatNumber()}</span>
                                <span>${room.getStatus()}</span>
                                <span>${room.getPrice()}</span>
                            </div>
                        </c:forEach>
                        <%
                        System.out.println(rooms.size());
                    }
                %>
            </div>
        </div>
    </div>
</body>
</html>
