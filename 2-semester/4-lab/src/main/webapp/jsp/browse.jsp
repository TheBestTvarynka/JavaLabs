<%@ page import="java.util.List" %>
<%@ page import="com.kpi.lab4.entities.Room" %>
<%@ page import="com.kpi.lab4.dto.Page" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Browse</title>
    <link href="<c:url value="/css/forms.css" />" rel="stylesheet">
    <link href="<c:url value="/css/pagination.css" />" rel="stylesheet">
    <link href="<c:url value="/css/header.css" />" rel="stylesheet">
    <link href="<c:url value="/css/browse_page_styles.css" />" rel="stylesheet">
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
        <div class="content">
            <form class="search">
                <c:set var="error" value="${applicationScope.get('error')}" scope="request"/>
                <c:set var="error1" value="${requestScope.get('error')}" scope="request"/>
                <span class="error_message">${error1}</span>
                <c:set var="message" value="${applicationScope.get('message')}" scope="request"/>
                <c:set var="message1" value="${requestScope.get('message')}" scope="request"/>
                <span class="info_message">${message1}</span>
                <c:remove var="error"/>
                <c:remove var="message"/>
                <div class="search_block">
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
                </div>
                <button type="submit" class="button">Search</button>
            </form>
            <div class="result">
                <%Page<Room> pageData = (Page<Room>)request.getServletContext().getAttribute("page");
                if (pageData == null || pageData.getData().size() < 1) {%>
                    <span>No rooms found :(</span>
                <%
                } else {
                    List<Room> rooms = pageData.getData();
                    for (Room room: rooms) {%>
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
                    System.out.println("pageData: " + pageData);
                    int pages = 1;
                    int pageN = 1;
                    String query = "";
                    if (pageData != null) {
                        pages = (int) Math.ceil((pageData.getCount() + 0.0) / pageData.getOffset());
                        pageN = pageData.getPage();
                        query = request.getQueryString();
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
                    }
                %>
                <%if (pageN != 1) {%>
                <a class="pag_item" href=<%="${pageContext.request.contextPath}/browse?" + query + (pageN - 1)%>>&lt;</a>
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
                <%}
                request.getServletContext().removeAttribute("page");
                %>
            </div>
        </div>
    </div>
</body>
</html>
