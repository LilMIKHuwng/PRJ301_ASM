<%-- 
    Document   : product
    Created on : Mar 9, 2024, 6:52:45 PM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f4f4f4;
                margin: 0;
                padding: 0;
                display: flex;
                flex-direction: column;
                align-items: center;
                justify-content: center;
                min-height: 100vh;
            }

            h1 {
                color: #333;
                margin-bottom: 20px;
            }

            form {
                display: flex;
                flex-direction: column;
                align-items: center;
                background-color: #fff;
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                width: 300px;
            }

            select {
                width: 100%;
                padding: 8px;
                margin-bottom: 10px;
                border: 1px solid #ccc;
                border-radius: 4px;
                box-sizing: border-box;
            }

            input[type="submit"] {
                background-color: #4CAF50;
                color: white;
                padding: 8px 12px;
                border: none;
                border-radius: 4px;
                cursor: pointer;
                margin-bottom: 10px;
            }

            input[type="submit"]:hover {
                background-color: #45a049;
            }

            a {
                text-decoration: none;
                color: #007BFF;
                padding: 5px;
                border: 1px solid #007BFF;
                border-radius: 4px;
            }

            a:hover {
                background-color: #007BFF;
                color: white;
            }
        </style>
    </head>
    <body>
        <h1>Book store</h1>
        <form action="DispatchServlet">
            Choose <select name="sboBook">
                <c:set var="listProduct" value="${requestScope.listProduct}"  />
                <c:forEach var="dto" items="${listProduct}">
                    <option value="${dto.sku}">${dto.name}</option>
                </c:forEach>
            </select>
            <input type="submit" value="Add Book to Your Cart" name="btAction" />
            <input type="submit" value="View Your Cart" name="btAction" /> <br/>
            <br/>
            <a href="search.jsp">Go back Search Page</a>
        </form>
    </body>
</html>
