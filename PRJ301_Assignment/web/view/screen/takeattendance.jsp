<%-- 
    Document   : takeattendance
    Created on : Oct 29, 2023, 11:56:13 PM
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
         ${requestScope.ses.group.name}-${requestScope.ses.group.subject.name}-${requestScope.ses.room.name}
        <form action="takeattendance" method="POST">
            <table border="1px">
                <tr>
                    <td>Student</td>
                    <td>Status</td>
                    <td>Description</td>
                    <td>Taking Time</td>
                </tr>
                <c:forEach items="${requestScope.atts}" var="a">
                    <tr>
                        <td>
                            ${a.student.name}
                            <input type="hidden" name="stuid" value="${a.student.id}"/>
                        </td>
                        <td>
                            <input type="radio"
                                   <c:if test="${!a.status}">
                                       checked="checked"
                                   </c:if>
                                   name="status${a.student.id}" value="absent" /> absent
                            <input type="radio"
                                   <c:if test="${a.status}">
                                       checked="checked"
                                   </c:if>
                                   name="status${a.student.id}" value="present" /> present
                        </td>
                        <td><input type="text" value="${a.comment}" name="description${a.student.id}"/></td>
                        <td>${a.datetime}</td>
                    </tr>   
                </c:forEach>

            </table>
            <input type="hidden" value="${requestScope.ses.id}" name="sesid"/>
            <input type="submit" value="Save"/>
        </form>
    </body>
</html>
