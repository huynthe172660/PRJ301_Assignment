<%-- 
    Document   : attendancereport
    Created on : Oct 31, 2023, 5:43:11 PM
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
        <!DOCTYPE html>
    <html>
        <head>
            <title>Attendance Report</title>
            <style>
                table {
                    width: 100%;
                    border-collapse: collapse;
                }

                table, th, td {
                    border: 1px solid black;
                }

                th, td {
                    padding: 8px;
                    text-align: center;
                }

                th {
                    background-color: #f2f2f2;
                }
            </style>
        </head>
        <body>
            <h2>Attendance Report</h2>
            <table border="1px">
                <tr>
                    <th>Name</th>
                        <c:forEach begin="1" end="${requestScope.number}" varStatus="loop" >
                        <th>Slot ${loop.index}</th>
                        </c:forEach>
                </tr>
                <c:forEach items="${requestScope.attrp}" var="a">
                    <tr>
                        <td>${a.key}</td>
                        <c:forEach items="${a.value}" var="s">
                            <td>
                                ${s.status}
                            </td>
                        </c:forEach>
                    </tr>
                </c:forEach>
            </table>
        </body>
    </html>


</body>
</html>
