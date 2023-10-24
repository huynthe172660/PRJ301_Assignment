<%-- 
    Document   : home
    Created on : Oct 24, 2023, 5:05:11 PM
    Author     : AD
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Academic Portal - Home</title>
    <style>
        body {
            margin: 0;
            padding: 0;
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
        }
        
        header {
            background-color: #007BFF;
            color: #fff;
            padding: 20px;
            text-align: center;
        }

        .container {
            display: flex;
            justify-content: space-around;
            flex-wrap: wrap;
            margin: 20px;
        }

        .box {
            background-color: #fff;
            padding: 20px;
            margin: 10px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            flex: 1;
        }

        .box h2 {
            color: #007BFF;
        }
    </style>
</head>
<body>
    <header>
        <h1>Welcome to FPT Academic Portal</h1>
    </header>

    <div class="container">
        <a href="sameLink.jsp">
            <div class="box">
                <h2>Your Timetable:</h2>
                <table style="width: 100%; border-collapse: collapse;">
                    <!-- Display the timetable here -->
                    <!-- You can fetch and display the timetable from a database -->
                </table>
            </div>
        </a>

        <a href="sameLink.jsp">
            <div class="box">
                <h2>Course Listings:</h2>
                <ul style="list-style-type: none; padding: 0;">
                    <li><a href="sameLink.jsp">Course 1</a></li>
                    <li><a href="sameLink.jsp">Course 2</a></li>
                    <li><a href="sameLink.jsp">Course 3</a></li>
                </ul>
            </div>
        </a>

        <a href="sameLink.jsp">
            <div class="box">
                <h2>Take Attendance:</h2>
                <form action="sameLink.jsp" method="post">
                    <!-- Include a form to take attendance -->
                    <!-- It should have fields for date, course, and attendance status -->
                    <!-- For example: Date, Course, Present/Absent -->
                    <!-- You will need to create an "sameLink.jsp" to process this form -->
                </form>
            </div>
        </a>

        <a href="sameLink.jsp">
            <div class="box">
                <h2>Attendance Report:</h2>
            </div>
        </a>
    </div>

    <a href="sameLink.jsp" style="display: block; text-align: center; text-decoration: none; color: #007BFF; padding: 10px;">Logout</a>
</body>
</html>