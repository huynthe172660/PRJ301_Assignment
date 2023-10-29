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
        <style>
            header {
                background-color: #FF8C00;
                color: #fff;
                padding: 20px;
                text-align: center;
            }
            body {
                background-color: #f0f0f0;
                font-family: 'Verdana', sans-serif;
                margin: 0;
                padding: 0;
            }

            .header {
                text-align: center;
                padding: 20px;
                background-color: #3498db;
            }

            .header img {
                max-width: 100%;
            }

            h1 {
                color: #fff;
            }

            table {
                width: 80%;
                margin: 20px auto;
                border-collapse: collapse;
                border: 2px solid #FFA500;
            }

            th {
                background-color: #3498db;
                color: #fff;
                text-align: center;
                padding: 10px;
            }

            td {
                background-color: #ecf0f1;
                color: #333;
                text-align: center;
                padding: 10px;
            }
            img{
                background-color: #fff;
            }

            /* Custom style for links */
            a {
                color: #ff5733;
                text-decoration: none;
            }

            a:hover {
                text-decoration: underline;
            }
        </style>
    </head>
    <body>
        <header>
            <img src="https://upload.wikimedia.org/wikipedia/commons/6/68/Logo_FPT_Education.png" alt="Login Image" width="300px" height="150px">
            <br>
            Lecturer: ${requestScope.accname}
            <form action="timetable" method="GET">
                <input type="hidden" name="id" value="${param.id}"/>
                From <input type="date" value="${requestScope.from}" name="from"/> 
                To <input type="date" value="${requestScope.to}" name="to"/> 
                <input type="submit" value="View"/>
                <br>
                Please go to teach on time and don't forget to take attendance before 12AM
            </form>
            
        </header>

        <c:if test="${requestScope.dates.size()!=null}">
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
                                        <a href="takeattendance?id=${k.id}">
                                            ${k.group.name}-${k.group.subject.name}-${k.room.name}
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
        </c:if>
    </body>
</html>

