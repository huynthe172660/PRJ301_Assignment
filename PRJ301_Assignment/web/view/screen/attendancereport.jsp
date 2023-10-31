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
        <div class="container">
            <div>
                <h2>FPT University Academic Portal</h2>
                <hr style="border-top: 1px solid black;">
            </div>
            <div class="p-3 mb-2 bg-secondary bg-gradient text-white">
                <div class="row">
                    <div class="col">
                        <a class="btn btn-success" style="margin-right: 10px ;" href="lecturer_home" role="button">Home</a>
                        <h5 style="display: inline ;">Track Attendance</h5>
                    </div>
                    <div class="col d-flex justify-content-end">
                        <a class="btn btn-success" style="margin-right: 10px ;" href=" # " role="button ">${sessionScope.account.username}</a>
                        <a class="btn btn-success " href="logout" role="button ">Logout</a>
                    </div>
                </div>

            </div>

            <div class="row">
                <div class="col">Select a campus/program, term, course ...</div>
            </div>

            <table border="1" style="margin-bottom: 10px">
                <thead>
                    <tr>
                        <th>TERM</th>
                        <th>COURSE</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>
                            <c:forEach items="${requestScope.term}" var="t">
                                <a href="attendance_report_for_lecture?lid=${requestScope.lid}&termid=${t.tearmId}&gid=-1">${t.semester.semesterName} ${t.year.yearName}</a> <br/>
                            </c:forEach>
                        </td>
                        <td>
                            <c:forEach items="${requestScope.group}" var="g">
                                <a href="attendance_report_for_lecture?lid=${requestScope.lid}&termid=${g.term.tearmId}&gid=${g.id}">${g.subject.fullName} (${g.subject.name})(${g.name} start ${g.startDate} )
                                </a>
                                <br/>
                            </c:forEach>
                        </td>
                    </tr>
                </tbody>
            </table>

            <div class="row">
                <div class="col">... then see report</div>
            </div>

            <table border="1" class="table">
                <thead>
                    <tr>
                        <th></th>
                <c:forEach items="${requestScope.date}" var="d">
                            <th>${d.date} Slot ${d.index}</th>
                            </c:forEach>
                    </tr>
                </thead>
                <tbody>

                    <c:forEach items="${requestScope.student}" var="s">
                        <tr>
                            <td>${s.name} ${s.id}</td>
                            <c:forEach items = "${s.attandances}" var="a">
                                <td>
                                    <div class="d-flex justify-content-center">
                                        <c:if test="${a.isPresentInterger() == 1}">
                                            <img style="width: 15px; height: auto" src="img/check.png">
                                        </c:if>
                                        <c:if test="${a.isPresentInterger() == 0}">
                                            <img style="width: 15px; height: auto" src="img/close.png">
                                        </c:if>
                                        <c:if test="${a.isPresentInterger() == -1}">

                                            <img style="width: 15px; height: auto" src="img/search.png">
                                        </c:if>
                                    </div>
                                </td>
                            </c:forEach>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

        </div>
    </body>
</html>
