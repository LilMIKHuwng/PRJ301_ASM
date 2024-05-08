<%-- 
    Document   : search
    Created on : Jan 30, 2024, 8:58:12 AM
    Author     : HP
--%>

<%@page import="hunglq.registration.LoginnDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 0;
                display: flex;
                flex-direction: column;
                align-items: center;
                justify-content: center;
                min-height: 100vh;
                background-color: #f4f4f4;
                margin-top: 20px;
            }

            h1 {
                color: #333;
            }

            form {
                margin-bottom: 20px;
            }

            input[type="text"],
            input[type="password"] {
                width: 200px;
                padding: 5px;
                margin: 5px 0;
            }

            input[type="submit"],
            input[type="reset"] {
                background-color: #4CAF50;
                color: white;
                padding: 8px 12px;
                border: none;
                border-radius: 4px;
                cursor: pointer;
            }

            input[type="submit"]:hover,
            input[type="reset"]:hover {
                background-color: #45a049;
            }

            a {
                text-decoration: none;
                color: #007BFF;
                margin-right: 10px;
                padding: 5px;
                border: 1px solid #007BFF;
                border-radius: 4px;
                margin-bottom: 10px;
            }

            a:hover {
                background-color: #007BFF;
                color: white;
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
        </style>
    </head>
    <body>
        <font color="red">
        Welcome, ${sessionScope.USER.fullName}
        </font>
        <h1>Search PAGE</h1>
        <form action="DispatchServlet">
            Search Value <input type="text" name="txtSearchValue" 
                                value="${param.txtSearchValue}" />
            <input type="submit" value="Search" name="btAction" />
        </form>
        <br>

        <c:set var="searchValue" value="${param.txtSearchValue}"></c:set>
        <c:if test="${not empty searchValue}">
            <c:set var="result" value="${requestScope.SEARCH_RESULT}"></c:set>
            <c:if test="${empty result}">
                <h2>
                    <font color="red">
                    No record is matched!!!!
                    </font>
                </h2>
            </c:if>
            <c:if test="${not empty result}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Username</th>
                            <th>Password</th>
                            <th>Full name</th>
                            <th>Role</th>
                            <th>Delete</th>
                            <th>Update</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="dto" items="${result}" varStatus="counter">
                        <form action="DispatchServlet" method="POST">
                            <tr>
                                <td>${counter.count}</td>
                                <td>
                                    ${dto.username}
                                    <input type="hidden" value="${dto.username}" name="txtUsername"/>
                                </td>
                                <td>
                                    <input type="text" value="${dto.password}" name="txtPassword"/>
                                </td>
                                <td>${dto.fullName}</td>
                                <td>
                                    <input type="checkbox" name="chkAdmin" value="ON" 
                                           <c:if test="${dto.role}">
                                               checked="checked"
                                           </c:if>
                                           />
                                </td>
                                <td>
                                    <c:url var="deteleLink" value="DispatchServlet">
                                        <c:param name="btAction" value="delete"/>
                                        <c:param name="pk" value="${dto.username}"/>
                                        <c:param name="lastSearchValue" value="${param.txtSearchValue}"/>
                                    </c:url>
                                    <a href="${deteleLink}">Delete</a>
                                </td>
                                <td>
                                    <input type="submit" value="Update" name="btAction">
                                    <input type="hidden" name="lastSearchValue" 
                                           value="${param.txtSearchValue}">
                                </td>
                            </tr>
                        </form>

                    </c:forEach>
                </tbody>
            </table>
        </c:if>
    </c:if>
    <%-- <% 
        Cookie[] cookies = request.getCookies();
        if (cookies != null){
            Cookie lastCookie = cookies[cookies.length - 1];
            String username = lastCookie.getName();
            %>
            <font color="red">
            Welcome, <%= username %>
            </font>
    <%
        }//login is ok
    %>
    <h1>Search PAGE</h1>
    <form action="DispatchServlet">
        Search Value <input type="text" name="txtSearchValue" 
                            value="<%= request.getParameter("txtSearchValue") %>" /> <br>
        <input type="submit" value="Search" name="btAction" />
    </form>
        <br/>
    <% 
        String searchValue = request.getParameter("txtSearchValue");
        if (searchValue != null){
            List<LoginnDTO> result = 
                    (List<LoginnDTO>) request.getAttribute("SEARCH_RESULT");
            if (result != null){//information is found
                %>
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Username</th>
                            <th>Password</th>
                            <th>Fullname</th>
                            <th>Role</th>
                            <th>Delete</th>
                            <th>Update</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% 
                            int count = 0;
                            for (LoginnDTO dto : result){
                                String urlRewriting = "DispatchServlet"
                                        + "?btAction=delete"
                                        + "&pk=" + dto.getUsername()
                                        + "&lastSearchValue=" + searchValue;
                                //xoa thi refresh lai
                                %>
                    <form action="DispatchServlet" method="POST">
                                <tr>
                            <td>
                                <%= ++count %>
                            </td>
                            <td>
                                <%= dto.getUsername() %>
                                <input type="hidden" value="<%= dto.getUsername() %>" name="txtUsername"/>
                            </td>
                            <td>
                                <input type="text" value="<%= dto.getPassword() %>" name="txtPassword"/>
                            </td>
                            <td>
                                <%= dto.getFullName() %>
                            </td>
                            <td>
                                <input type="checkbox" name="chkAdmin" value="ON" 
                                       <% 
                                        if (dto.isRole()){
                                            %>
                                            checked="checked"
                                       <%
                                        }//role is an admin
                                       %>
                                       />
                                
                            </td>
                            <td>
                                <a href="<%= urlRewriting %>">Delete</a>
                            </td>
                            <td>
                                <input type="submit" value="Update" name="btAction">
                                <input type="hidden" name="lastSearchValue" 
                                       value="<%= searchValue %>" />
                            </td>
                        </tr>        
                    </form>
                        <%
                            }//end get each dto in result
                        %>
                    </tbody>
                </table>

            <%
                } else {// no record is matched 
                    //vung code viet html, fragment code
                    %>
                    <h2>
                        <font color="red">
                            No record is matched!!!!
                        </font>
                    </h2>
            <%
                } //end no record is matched
            }//end search is called second time or request from user
        %>
    --%>
    <a href="DispatchServlet?btAction=Product">Go to Shopping</a> 
    <a href="DispatchServlet?action=LOGOUT">Logout</a>
</body>
</html>
