<%-- 
    Document   : login
    Created on : Oct 24, 2023, 4:31:28 PM
    Author     : AD
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>FPT University Academic Portal</title>
</head>
<body>
    <div style="background-color: #f2f2f2; font-family: Arial, sans-serif; text-align: center;">
        <div style="width: 500px; height: 800px; margin: 0 auto; background-color: #fff; padding: 20px; border-radius: 10px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); text-align: center;">
            <img src="https://upload.wikimedia.org/wikipedia/commons/6/68/Logo_FPT_Education.png" alt="Login Image" width="300px" height="150px">
            <h1 style="color: #333;">FPT University Academic Portal</h1>
            
            <form action="login" method="post">
                <label for="username" style="display: block; margin-bottom: 5px; color: #333;">Username:</label>
                <input type="text" id="username" name="username" required style="width: 100%; padding: 10px; margin-bottom: 15px; border: 1px solid #ccc; border-radius: 5px;">
    
                <label for="password" style="display: block; margin-bottom: 5px; color: #333;">Password:</label>
                <input type="password" id="password" name="password" required style="width: 100%; padding: 10px; margin-bottom: 15px; border: 1px solid #ccc; border-radius: 5px;">
    
                <input type="submit" value="Login" style="background-color: #007BFF; color: #fff; padding: 10px 15px; border: none; border-radius: 5px; cursor: pointer;">
            </form>
        </div>
    </div>
</body>
</html>
