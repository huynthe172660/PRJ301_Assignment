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
            background-color: #FF8C00;
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
            color: #FFA500;
        }
        img{
            background-color: #fff;
        }
    </style>
</head>
<body>
    <header>
        <img src="https://upload.wikimedia.org/wikipedia/commons/6/68/Logo_FPT_Education.png" alt="Login Image" width="300px" height="150px">
        <h1>Welcome to FPT Academic Portal</h1>
    </header>

    <div class="container">
        <a href="sameLink.jsp">
            <div class="box">
                <h2>Weekly timetable</h2>
            </div>
        </a>
    </div>

    <div class="container">

        <a href="sameLink.jsp">
            <div class="box">
                <h2>Take Attendance</h2>
            </div>
        </a>
    </div>
    
    <div class="container">
        <a href="sameLink.jsp">
            <div class="box">
                <h2>Attendance Report</h2>
            </div>
        </a>
    </div>

    <a href="logout" style="display: block; text-align: center; text-decoration: none; color: #FFA500; padding: 10px;">Logout</a>
</body>
</html>