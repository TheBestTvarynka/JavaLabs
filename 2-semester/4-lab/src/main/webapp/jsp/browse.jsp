<%@ page import="java.util.List" %>
<%@ page import="com.kpi.lab4.entities.Room" %>
<%@ page import="com.kpi.lab4.dto.Page" %>
<%@ page import="java.net.URL" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Browse</title>
</head>
<style>
    a {
        text-decoration: none;
        color: #1b191d;
    }
    body {
        color: #1b191d;
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
        /*height: calc(100% - 3em);*/
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
        justify-content: space-around;
        align-items: center;
        flex-wrap: wrap;
        border: 2px solid #4f4b55;
        border-radius: 0.25em;
        padding: 0.5em;
        box-shadow: 0 10px 12px -4px grey;
    }
    .options {
        display: flex;
        flex-direction: column;
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
    .result {
        border: 2px solid #4f4b55;
        box-shadow: 0 10px 12px -4px grey;
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
    .pagination {
        display: inline-flex;
        margin-top: 1em;
        margin-bottom: 1em;
        justify-content: center;
    }
    .pag_item {
        margin: 0.25em;
        background: #d3d3d3;
        border-radius: 0.25em;
        border: 2px solid #d3d3d3;
        padding: 0.25em;
        text-align: center;
    }
    .pag_item.selected {
        background: #b1b1b1;
        border: 2px solid #4f4b55;
    }
    .pag_item:hover {
        cursor: pointer;
        border: 2px solid #4f4b55;
    }
</style>
<body>
    <%ServletContext context = request.getServletContext();%>
    <div class="header">
        <%String username = (String)request.getSession().getAttribute("username");%>
        <span>${username}</span>
        <span>LogOut</span>
    </div>
    <div class="page">
        <div class="content">
            <form class="search">
                <div>
                    <label for="priceOrder">Price order</label>
                    <select name="price" id="priceOrder">
                        <option value="asc" selected>Asc</option>
                        <option value="desc">Desc</option>
                    </select>
                </div>
                <div>
                    <label for="seatOrder">Seat order</label>
                    <select name="seat" id="seatOrder">
                        <option value="asc" selected>Asc</option>
                        <option value="desc">Desc</option>
                    </select>
                </div>
                <div class="options">
                    <label>
                        <input type="checkbox" value="ROOM" checked name="types">
                        <span>Room</span>
                    </label>
                    <label>
                        <input type="checkbox" value="VIP" name="types">
                        <span>Vip</span>
                    </label>
                    <label>
                        <input type="checkbox" value="LUX" name="types">
                        <span>Lux</span>
                    </label>
                    <label>
                        <input type="checkbox" value="PRESIDENT" name="types">
                        <span>President</span>
                    </label>
                </div>
                <div class="options">
                    <label>
                        <input type="checkbox" value="AVAILABLE" checked name="statuses">
                        <span>Available</span>
                    </label>
                    <label>
                        <input type="checkbox" value="REPAIR" name="statuses">
                        <span>Repair</span>
                    </label>
                    <label>
                        <input type="checkbox" value="BOOKED" name="statuses">
                        <span>Booked</span>
                    </label>
                </div>
                <button type="submit" class="button">Search</button>
            </form>
            <div class="result">
                <%Page<Room> pageData = (Page<Room>)context.getAttribute("page");%>
                <%List<Room> rooms = pageData.getData();%>
                <%
                    if (rooms == null || rooms.size() < 1) {
                    %>
                        <span>No rooms found :(</span>
                    <%
                    } else {
                    %>
                    <%for (Room room: rooms) {%>
                        <div class="room">
                            <span><%=room.getNumber()%></span>
                            <span><%=room.getType()%></span>
                            <span><%=room.getSeatNumber()%></span>
                            <span><%=room.getStatus()%></span>
                            <span><%=room.getPrice()%></span>
                        </div>
                    <%}%>
                <%}%>
            </div>
            <div class="pagination">
                <%
                    int pages = (int) Math.ceil((pageData.getCount() + 0.0) / pageData.getOffset());
                    int pageN = pageData.getPage();
                    String query =request.getQueryString();
                    if (query != null) {
                        int pageIndex = query.indexOf("page=");
                        if (pageIndex != -1) {
                            query = query.substring(0, pageIndex + 5);
                        } else {
                            query += "&page=";
                        }
                    } else {
                        query = "page=";
                    }
                %>
                <%if (pageN != 1) {%>
                <a class="pag_item" href=<%="/Gradle___com_kpi___4_lab_1_0_SNAPSHOT_war/browse?" + query + (pageN - 1)%>>&lt;</a>
                <%}%>
                <%for (int i = 1; i < pageN; i++) {%>
                <a class="pag_item" href=<%="/Gradle___com_kpi___4_lab_1_0_SNAPSHOT_war/browse?" + query + i%>><%=i%></a>
                <%}%>
                <span class="pag_item selected"><%=pageN%></span>
                <%for (int i = pageN + 1; i <= pages; i++) {%>
                <a class="pag_item" href=<%="/Gradle___com_kpi___4_lab_1_0_SNAPSHOT_war/browse?" + query + i%>><%=i%></a>
                <%}%>
                <%if (pageN != pages) {%>
                <a class="pag_item" href=<%="/Gradle___com_kpi___4_lab_1_0_SNAPSHOT_war/browse?" + query + (pageN + 1)%>>&gt;</a>
                <%}%>
            </div>
        </div>
    </div>
</body>
</html>
