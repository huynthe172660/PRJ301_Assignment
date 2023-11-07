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
        <table border="1px">
            <tr>
                <th>Name</th>
                <c:forEach begin="1" end="${requestScope.number}" varStatus="loop" >
                    <th>Slot ${loop.index}</th>
                </c:forEach>
            </tr>
        </table>
            

    </body>
</html>
