<%-- 
    Document   : timetable
    Created on : Oct 26, 2023, 2:59:33 PM
    Author     : AD
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
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
                font-family: Arial, sans-serif;
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
                background-color: #fff;
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
                color: black;
            }
            .home-box {
                border: 1px solid #ccc; /* Đường viền */
                padding: 10px; /* Khoảng cách nội dung với viền */
                margin: 10px auto; /* Căn giữa theo chiều ngang */
                background-color: white; /* Màu nền */
                max-width: 100px; /* Giới hạn chiều rộng tối đa */
                overflow: hidden; /* Ẩn phần vượt quá chiều rộng */
            }
            .home-icon{
                color: #FF8C00;
            }
            a:hover {
                color: #FF8C00 ; /* Thay đổi màu khi di chuột qua link */
                font-size: 1.1em; /* Phóng to kích thước chữ */
                transition: all 0.3s ease; /* Hiệu ứng chuyển động mềm mại */
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
            <div class="home-box">
                <i class="fas fa-home home-icon"></i>
                <a href="home" style="color: #FF8C00; text-decoration: none;">Home</a>
            </div>

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
                        <td>Slot ${s.id} (${s.description})</td>
                        <c:forEach items="${requestScope.dates}" var="d">
                            <td>
                                <c:forEach items="${requestScope.sessions}" var="k">
                                    <c:if test="${k.date eq d and k.slot.id eq s.id}">
                                        <a href="takeattendance?id=${k.id}">
                                            <span style="color: black;">
                                                ${k.group.name}-${k.group.subject.name}-${k.room.name}
                                            </span>

                                            <c:if test="${k.isAtt}">
                                                <span style="color: green;"> 
                                                    (attended) 
                                                </span>
                                            </c:if>
                                            <c:if test="${!k.isAtt}">
                                                <span style="color: red;"> 
                                                    (not yet) 
                                                </span>
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

