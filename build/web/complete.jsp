<%-- 
    Document   : complete
    Created on : Mar 11, 2024, 4:24:39 PM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
            }

            .container {
                width: 80%;
                margin: auto;
                overflow: hidden;
            }

            h1, h2, h3 {
                color: #333;
                margin-bottom: 20px;
            }

            h2 {
                color: red;
            }

            table {
                width: 100%;
                border-collapse: collapse;
                margin-bottom: 20px;
            }

            table, th, td {
                border: 1px solid #ddd;
            }

            th, td {
                padding: 10px;
                text-align: left;
            }

            th {
                background-color: #4CAF50;
                color: white;
            }

            a {
                display: inline-block;
                margin: 10px;
                padding: 10px 20px;
                text-decoration: none;
                color: #fff;
                background-color: #007bff;
                border-radius: 5px;
            }

            a:hover {
                background-color: #0056b3;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <c:set var="order" value="${requestScope.ORDER}"></c:set>
            <c:if test="${empty order}">
                <h2>
                    <font color="red">
                    Invoice does not exist!!!!
                    </font>
                </h2>
            </c:if>
            <c:if test="${not empty order}">
                <h1>The invoice has been created successfully</h1>
                <table border="1">
                    <thead>
                        <tr>
                            <th>Order ID</th>
                            <th>Date</th>
                            <th>Customer</th>
                            <th>Address</th>
                            <th>Total</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>${order.id}</td>
                            <td>
                                ${order.date}
                            </td>
                            <td>
                                ${order.customer}
                            </td>
                            <td>${order.address}</td>
                            <td>
                                ${order.total}
                            </td>
                        </tr>
                    </tbody>
                </table>
            </c:if>


            <c:set var="orderdetail" value="${requestScope.ORDER_DETAIL}"></c:set>
            <c:if test="${empty orderdetail}">
                <h2>
                    <font color="red">
                    Invoice does not exist!!!!
                    </font>
                </h2>
            </c:if>
            <c:if test="${not empty orderdetail}">
                <h3>Invoice details</h3>
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Product ID</th>
                            <th>Unit Price</th>
                            <th>Quantity</th>
                            <th>Total</th>
                            <th>Order ID</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="dto" items="${orderdetail}" varStatus="counter">
                            <tr>
                                <td>${counter.count}</td>
                                <td>
                                    ${dto.productId}
                                </td>
                                <td>
                                    ${dto.unitPrice}
                                </td>
                                <td>${dto.quantity}</td>
                                <td>
                                    ${dto.total}
                                </td>
                                <td>
                                    ${dto.orderId}
                                </td>
                            </tr>

                        </c:forEach>
                    </tbody>
                </table>
            </c:if>
            <br/>
            <a href="DispatchServlet?btAction=Product">Continue go to Shopping</a> 
            <a href="DispatchServlet?action=LOGOUT">Logout</a>
        </div>
    </body>
</html>
