<%-- 
    Document   : timetable
    Created on : Oct 26, 2023, 2:59:33 PM
    Author     : AD
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="timetable" method="POST">
            <input type="hidden" name="id" value="${param.id}"/>
            From <input type="date" value="${requestScope.from}" name="from"/> 
            To <input type="date" value="${requestScope.to}" name="to"/> 
            <input type="submit" value="View"/>
        </form>
        <table border="1px">
            <tr>
                <td></td>
            <c:forEach items="${requestScope.dates}" var="d">
                    <td>
                        ${d}
                    </td>
                </c:forEach>
            </tr>
            <c:forEach items="${requestScope.slots}" var="s" varStatus="loop">
                <tr>
                    <td>${s.id}-${s.description}</td>
                    <c:forEach items="${requestScope.dates}" var="d">
                        <td>
                            <c:forEach items="${requestScope.sessions}" var="k">
                                <c:if test="${k.date eq d and k.slot.id eq s.id}">
                                    <a href="att?id=${k.id}">
                                        ${k.group.name}-${k.group.subject.name}-${k.room.id}
                                        <c:if test="${k.isAtt}">
                                            (attended)
                                        </c:if>
                                        <c:if test="${!k.isAtt}">
                                            (not yet)
                                        </c:if>
                                    </a>
                                </c:if>
                            </c:forEach>
                        </td>
                    </c:forEach>
                </tr>
            </c:forEach>

        </table>
    </body>
</html>

