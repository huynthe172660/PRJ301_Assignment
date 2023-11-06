<%-- 
    Document   : takeattendance
    Created on : Oct 29, 2023, 11:56:13 PM
    Author     : AD
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Take Attendance</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f4f4f4;
                color: #333;
                margin: 20px;
            }
            header {
                background-color: #FF8C00;
                color: #fff;
                padding: 20px;
                text-align: center;
            }
            .header img {
                max-width: 100%;
            }
            img{
                background-color: #fff;
            }
            .header {
                text-align: center;
                padding: 20px;
                background-color: #3498db;
            }
            table {
                width: 80%;
                margin: 20px auto;
                border-collapse: collapse;
                border: 2px solid #FFA500;
            }

            table th, table td {
                padding: 10px;
                border: 1px solid #ccc;
            }
            .present {
                color: green;
                font-weight: bold;
            }
            .absent {
                color: red;
                font-weight: bold;
            }
            input[type="text"], input[type="date"] {
                width: 100%;
                padding: 8px;
                border: 1px solid #ccc;
                border-radius: 4px;
            }
            input[type="submit"] {
                padding: 10px 20px;
                background-color: #4CAF50;
                color: white;
                border: none;
                border-radius: 4px;
                cursor: pointer;
            }
            input[type="submit"]:hover {
                background-color: #45a049;
            }
        </style>
    </head>
    <body>
        <header>
            <img src="https://upload.wikimedia.org/wikipedia/commons/6/68/Logo_FPT_Education.png" alt="Login Image" width="300px" height="150px">
            <br>
            <h1>Attendance for ${requestScope.ses.group.name}-${requestScope.ses.group.subject.name}-${requestScope.ses.room.name}</h1>
        </header>

            <table>
                <tr>
                    <th>Student</th>
                    <th>Status</th>
                    <th>Description</th>
                    <th>Taking Time</th>
                </tr>
                <c:forEach items="${requestScope.atts}" var="a">
                    <tr>
                        <td>${a.student.name}
                            <input type="hidden" name="stuid" value="${a.student.id}"/>
                        </td>
                        <td>
                            <input type="radio" id="present_${a.student.id}" name="status${a.student.id}" value="present"
                                   <c:if test="${a.status}">checked="checked"</c:if>/>
                            <label for="present_${a.student.id}" class="present">Present</label>
                            <input type="radio" id="absent_${a.student.id}" name="status${a.student.id}" value="absent"
                                   <c:if test="${!a.status}">checked="checked"</c:if>/>
                            <label for="absent_${a.student.id}" class="absent">Absent</label>
                        </td>
                        <td><input type="text" value="${a.comment}" name="description${a.student.id}" style="width: 90%; padding: 10px;"/></td>
                        <td>${a.datetime}</td>
                    </tr>
                </c:forEach>
            </table>
            <input type="hidden" value="${requestScope.ses.id}" name="sesid"/>
    </body>
</html>
