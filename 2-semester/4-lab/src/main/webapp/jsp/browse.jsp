<%@ page import="java.util.List" %>
<%@ page import="com.kpi.lab4.entities.Room" %>
<%@ page import="com.kpi.lab4.dto.Page" %>
<%@ page import="java.net.URL" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
    .header span {
        margin: 0.5em;
    }
    .page {
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
        <div>
            <a href="${pageContext.request.contextPath}/browse" class="header_button">Browse</a>
            <a href="${pageContext.request.contextPath}/request" class="header_button">Make request</a>
            <a href="${pageContext.request.contextPath}/order" class="header_button">Make order</a>
        </div>
        <%String username = (String)request.getSession().getAttribute("username");%>
        <div class="block">
            <span>${username}</span>
            <a href="${pageContext.request.contextPath}/logout">
                <img src="https://img.icons8.com/android/24/ffffff/logout-rounded.png" alt="LogOut"/>
            </a>
        </div>
    </div>
    <div class="page">
        <div class="content">
            <form class="search">
                <div>
                    <label for="priceOrder">Price order</label>
                    <select name="price" id="priceOrder">
                        <c:choose>
                            <c:when test="${param.priceOrder=='asc'}">
                                <option value="asc" selected>Asc</option>
                                <option value="desc">Desc</option>
                                <option value="none">None</option>
                            </c:when>
                            <c:when test="${param.priceOrder=='desc'}">
                                <option value="asc">Asc</option>
                                <option value="desc" selected>Desc</option>
                                <option value="none">None</option>
                            </c:when>
                            <c:otherwise>
                                <option value="none">None</option>
                                <option value="asc">Asc</option>
                                <option value="desc">Desc</option>
                            </c:otherwise>
                        </c:choose>
                    </select>
                </div>
                <div>
                    <label for="seats">Seat order</label>
                    <select name="seats" id="seats">
                        <c:choose>
                            <c:when test="${param.seats=='asc'}">
                                <option value="asc" selected>Asc</option>
                                <option value="desc">Desc</option>
                                <option value="none">None</option>
                            </c:when>
                            <c:when test="${param.seats=='desc'}">
                                <option value="asc">Asc</option>
                                <option value="desc" selected>Desc</option>
                                <option value="none">None</option>
                            </c:when>
                            <c:otherwise>
                                <option value="none" selected>None</option>
                                <option value="asc">Asc</option>
                                <option value="desc">Desc</option>
                            </c:otherwise>
                        </c:choose>
                    </select>
                </div>
                <div class="options">
                    <c:set var="types" value="${fn:join(paramValues.get('types'), '-')}"/>
                    <label>
                        <c:choose>
                            <c:when test="${fn:contains(types, 'ROOM')}">
                                <input type="checkbox" value="ROOM" checked name="types">
                            </c:when>
                            <c:otherwise>
                                <input type="checkbox" value="ROOM" name="types">
                            </c:otherwise>
                        </c:choose>
                        <span>Room</span>
                    </label>
                    <label>
                        <c:choose>
                            <c:when test="${fn:contains(types, 'VIP')}">
                                <input type="checkbox" value="VIP" name="types" checked>
                            </c:when>
                            <c:otherwise>
                                <input type="checkbox" value="VIP" name="types">
                            </c:otherwise>
                        </c:choose>
                        <span>Vip</span>
                    </label>
                    <label>
                        <c:choose>
                            <c:when test="${fn:contains(types, 'LUX')}">
                                <input type="checkbox" value="LUX" name="types" checked>
                            </c:when>
                            <c:otherwise>
                                <input type="checkbox" value="LUX" name="types">
                            </c:otherwise>
                        </c:choose>
                        <span>Lux</span>
                    </label>
                    <label>
                        <c:choose>
                            <c:when test="${fn:contains(types, 'PRESIDENT')}">
                                <input type="checkbox" value="PRESIDENT" name="types" checked>
                            </c:when>
                            <c:otherwise>
                                <input type="checkbox" value="PRESIDENT" name="types">
                            </c:otherwise>
                        </c:choose>
                        <span>President</span>
                    </label>
                </div>
                <div class="options">
                    <c:set var="statuses" value="${fn:join(paramValues.get('statuses'), '-')}"/>
                    <label>
                        <c:choose>
                            <c:when test="${fn:contains(statuses, 'AVAILABLE')}">
                                <input type="checkbox" value="AVAILABLE" checked name="statuses">
                            </c:when>
                            <c:otherwise>
                                <input type="checkbox" value="AVAILABLE" name="statuses">
                            </c:otherwise>
                        </c:choose>
                        <span>Available</span>
                    </label>
                    <label>
                        <c:choose>
                            <c:when test="${fn:contains(statuses, 'REPAIR')}">
                                <input type="checkbox" value="REPAIR" name="statuses" checked>
                            </c:when>
                            <c:otherwise>
                                <input type="checkbox" value="REPAIR" name="statuses">
                            </c:otherwise>
                        </c:choose>
                        <span>Repair</span>
                    </label>
                    <label>
                        <c:choose>
                            <c:when test="${fn:contains(statuses, 'BOOKED')}">
                                <input type="checkbox" value="BOOKED" name="statuses" checked>
                            </c:when>
                            <c:otherwise>
                                <input type="checkbox" value="BOOKED" name="statuses">
                            </c:otherwise>
                        </c:choose>
                        <span>Booked</span>
                    </label>
                </div>
                <button type="submit" class="button">Search</button>
            </form>
            <div class="result">
                <%Page<Room> pageData = (Page<Room>)context.getAttribute("page");%>
                <%List<Room> rooms = pageData.getData();%>
                <%
                    System.out.println("size: " + rooms.size());
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
