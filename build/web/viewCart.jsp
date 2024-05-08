<%-- 
    Document   : viewCart
    Created on : Feb 27, 2024, 9:06:49 AM
    Author     : HP
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.Map"%>
<%@page import="hunglq.cart.CartObject"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cart</title>
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
                width: 80%; /* Đặt chiều rộng của form */
                max-width: 600px; /* Đặt chiều rộng tối đa của form */
                margin-bottom: 20px;
            }

            table {
                width: 100%;
                border-collapse: collapse;
                margin-bottom: 20px;
            }

            th, td {
                border: 1px solid #ddd;
                padding: 8px;
                text-align: left;
            }

            th {
                background-color: #f2f2f2;
            }

            input[type="checkbox"] {
                margin-left: 5px;
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
        <h1>Book Store</h1>

        <c:if test="${sessionScope ne null}">
            <c:set var="cart" value="${sessionScope.CART}"></c:set>
            <c:if test="${cart ne null}">
                <c:set var="items" value="${cart.items}" />
                <c:if test="${not empty items}">
                    <form action="DispatchServlet">                       
                        <table border="1">
                            <thead>
                                <tr>
                                    <th>No.</th>
                                    <th>Name</th>
                                    <th>Quantity</th>
                                    <th>Unit Price</th>
                                    <th>Total</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:set var="total" value="0" />
                                <c:forEach var="item" items="${items}" varStatus="counter">
                                    <tr>
                                        <td>${counter.count}</td>
                                        <td>${item.value.name}</td>
                                        <td>${item.value.quantity}</td>
                                        <td>${item.value.unitPrice}</td>
                                        <td>${item.value.unitPrice * item.value.quantity}</td>
                                        <td><input type="checkbox" name="chkItem" value="${item.key}"></td>
                                        <c:set var="total" value="${total + (item.value.unitPrice * item.value.quantity)}" />
                                    </tr>
                                </c:forEach>
                                <tr>
                                    <td colspan="4">
                                        <a href="DispatchServlet?btAction=Product">Add More Books to Your Cart</a>
                                    </td>
                                    <td>Total All: ${total}</td>
                                    <td><input type="submit" value="Remove Selected Items" name="btAction"></td>
                                </tr>
                            </tbody>
                        </table>
                    </form>
                    <br>
                    <form action="DispatchServlet">
                        <c:set var="errors" value="${requestScope.ORDER_ERRORS}"></c:set>
                        Name <input type="text" name="txtName" value="" /> (6 - 30 chars) <br/>
                        <c:if test="${not empty errors.nameLengthError}">
                            <font color="red">
                            ${errors.nameLengthError}
                            </font><br/>
                        </c:if> <br/>
                        Address <input type="text" name="txtAddress" value="" /> (10 - 50 chars) <br/>
                        <c:if test="${not empty errors.addressLengthError}">
                            <font color="red">
                            ${errors.addressLengthError}
                            </font><br/>
                        </c:if>
                        <input type="submit" value="Check out" name="btAction" />
                    </form>
                </c:if>
            </c:if>
        </c:if>

        <c:if test="${empty items}">
            <h2><font color="red">No cart is existed!!!!!</font></h2>
            <a href="DispatchServlet?btAction=Product">Go to Shopping</a>
        </c:if>

        <%--    <%
                //1. Cus goes to his/her cart place
                if (session != null){
                    //2. Cus takes his/her cart
                    CartObject cart = (CartObject) session.getAttribute("CART");
                    if (cart != null){
                        //3. Cus gets items 
                        Map<String, Integer> items = cart.getItems();
                        if (items != null){
                            //4. Cus shows all items
                            %>
                            <form action="DispatchServlet">                       
                            <table border="1">
                                <thead>
                                    <tr>
                                        <th>No.</th>
                                        <th>Name</th>
                                        <th>Quantity</th>
                                        <th>Action</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <% 
                                        int count = 0;
                                        for (String key : items.keySet()){
                                            %>
                                    <tr>
                                        <td><%= ++count %></td>
                                        <td><%= key %></td>
                                        <td><%= items.get(key) %></td>
                                        <td><input type="checkbox" name="chkItem" value="<%= key %>"></td>
                                    </tr>
                                    <%
                                        }//travaerse items
                                    %>
                                    <tr>
                                        <td colspan="3">
                                            <a href="product.html">Add More Books to Your Cart</a>
                                        </td>
                                        <td><input type="submit" value="Remove Selected Items" name="btAction"></td>
                                        
                                    </tr>
                                </tbody>
                            </table>
                            </form>
            <%
                            return;
                        }   
                    }
                }
            %>
            
            <h2>
                <font color="red">
                    No cart is existed!!!!!
                </font>
            </h2>
        --%>
    </body>
</html>
