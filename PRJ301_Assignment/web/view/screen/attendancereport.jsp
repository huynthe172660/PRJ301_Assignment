<%-- 
    Document   : attendancereport
    Created on : Oct 31, 2023, 5:43:11 PM
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
            a:hover {
                color: #FF8C00 ; /* Thay đổi màu khi di chuột qua link */
                font-size: 1.1em; /* Phóng to kích thước chữ */
                transition: all 0.3s ease; /* Hiệu ứng chuyển động mềm mại */
            }
            h2 {
                font-family: Arial, sans-serif; /* Sử dụng font chữ khác */
            }

            /* Cấu hình kích thước và căn chỉnh chữ */
            table {
                width: 80%;
                margin: 20px auto;
                border-collapse: collapse;
                border: 2px solid #FFA500;
            }
            body{
                font-family:Arial, sans-serif
            }

            table th, table td {
                padding: 10px;
                border: 1px solid #ccc;
            }
            header {
                background-color: #FF8C00;
                color: #fff;
                padding: 20px;
                text-align: center;
            }
            .header {
                text-align: center;
                padding: 20px;
                background-color: #3498db;
            }

            .header img {
                max-width: 100%;
                background-color: white;
            }
            img{
                background-color: #fff;
            }
            span{
                font-size: 24px;
                font-weight: bold;
            }
            .title{
                font-size: 30px
            }
            .class-name-box {
                border: 1px solid #ccc; /* Đường viền */
                padding: 10px; /* Khoảng cách nội dung với viền */
                margin: 10px auto; /* Căn giữa theo chiều ngang */
                background-color: #f9f9f9; /* Màu nền */
                max-width: 300px; /* Giới hạn chiều rộng tối đa */
                overflow: hidden; /* Ẩn phần vượt quá chiều rộng */
            }
            .low-attendance {
                color: red;
                font-weight: bold;
            }
            a{
                text-decoration: none;
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

        </style>
    </head>
    <body>
        <header>
            <img src="https://upload.wikimedia.org/wikipedia/commons/6/68/Logo_FPT_Education.png" alt="Login Image" width="300px" height="150px">
            <br>
            <span class="title">Attendance Report</span>
            <br>
            <span>Lecturer: ${requestScope.accname}</span>
            <br>
            <span>Please select the class you want to view the report</span>
            <br>
            <c:forEach items="${requestScope.gs}" var="g">
                <div class="class-name-box">
                    <a href="attendancereport?id=${g.id}">${g.name}</a>
                </div>
            </c:forEach>
                        <div class="home-box">
            <i class="fas fa-home home-icon"></i>
            <a href="home" style="color: #FF8C00; text-decoration: none;">Home</a>
            </div>
        </header>

        <c:if test="${requestScope.id != null}">
            <table border="1px">
                <tr>
                    <th>Name</th>
                    <th>Attendance Percentage</th>
                        <c:forEach begin="1" end="${requestScope.number}" varStatus="loop">
                        <th>Slot ${loop.index}</th>
                        </c:forEach>
                </tr>
                <c:forEach items="${requestScope.attrp}" var="a">
                    <c:set var="totalSessions" value="${a.value.size()}" />
                    <c:set var="attendedSessions" value="0" />
                    <c:forEach items="${a.value}" var="s">
                        <c:if test="${s.status}">
                            <c:set var="attendedSessions" value="${attendedSessions + 1}" />
                        </c:if>
                    </c:forEach>
                    <c:set var="attendancePercentage" value="${(attendedSessions * 100) / totalSessions}" />
                    <tr>
                        <td class="<c:if test='${attendancePercentage < 80}'>low-attendance</c:if>">${a.key}</td>
                            <td>
                            ${attendancePercentage} % <!-- Hiển thị phần trăm -->
                        </td>
                        <c:forEach items="${a.value}" var="s">
                            <td>
                                <c:if test="${s.status}">
                                    <i class="fas fa-check" style="color: green;"></i>
                                </c:if>
                                <c:if test="${!s.status}">
                                    <i class="fas fa-times" style="color: red;"></i>
                                </c:if>
                            </td>
                        </c:forEach>
                    </tr>
                </c:forEach>
            </table>
        </c:if>

    </body>
</html>



