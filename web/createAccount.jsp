<%-- 
    Document   : createAccount
    Created on : Mar 8, 2024, 8:11:44 AM
    Author     : HP
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f4f4f4;
                text-align: center;
                padding: 20px;
            }

            h1 {
                color: #333;
            }

            form {
                max-width: 400px;
                margin: 0 auto;
                background-color: #fff;
                padding: 20px;
                border-radius: 5px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }

            input {
                width: 100%;
                padding: 10px;
                margin-bottom: 15px;
                box-sizing: border-box;
                border: 1px solid #ccc;
                border-radius: 5px;
            }

            input[type="submit"],
            input[type="reset"] {
                background-color: #007bff;
                color: #fff;
                cursor: pointer;
            }

            input[type="submit"]:hover,
            input[type="reset"]:hover {
                background-color: #0056b3;
            }

            font {
                color: red;
            }
        </style>
    </head>
    <body>
        <h1>Registration</h1>
        <form action="DispatchServlet" method="POST">
            <c:set var="errors" value="${requestScope.CREATE_ERRORS}"></c:set>
            Username* <input type="text" name="txtUsername" value="${param.txtUsername}" />(6 - 20 chars) <br>
            <br>
            <c:if test="${not empty errors.usernameIsExisted}">
                <font color="red">
                ${errors.usernameIsExisted}
                </font><br/>
            </c:if>
            <c:if test="${not empty errors.usernameLengthError}">
                <font color="red">
                ${errors.usernameLengthError}
                </font><br/>
            </c:if>
            <c:if test="${not empty errors.passwordLengthError}">
                <font color="red">
                ${errors.passwordLengthError}
                </font><br/>
            </c:if>
            <c:if test="${not empty errors.fullNameLengthError}">
                <font color="red">
                ${errors.fullNameLengthError}
                </font><br/>
            </c:if>
            <c:if test="${not empty errors.confirmLengthError}">
                <font color="red">
                ${errors.confirmLengthError}
                </font><br/>
            </c:if>
            <br>
            Password* <input type="password" name="txtPassword" value="" />(6 - 30 chars) <br>
            <br>
            Confirm* <input type="password" name="txtConfirm" value="" /><br>
            <br>
            Full name* <input type="text" name="txtFullname" value="${param.txtFullname}" />(2 - 40 chars) <br>
            <br>
            <br>
            <input type="submit" value="Create New Account" name="btAction" />
            <input type="reset" value="Reset" />
        </form>
    </body>
</html>
