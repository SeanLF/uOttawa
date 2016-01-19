<%-- 
    Document   : queryParamsBook
    Created on : 26-Sep-2014, 8:21:32 PM
    Author     : ssome
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title></title>
        <style>
        h1 {
           color: #101010;
           line-height: 90%;
        }
        #params {
            font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
            padding: 15px;
        }
        #params td {
            height: 20px;
            vertical-align: bottom;
        }
        #title {
            background-color: #F0F0F0;
            font-size: 1.1em;
        }
        </style>
    </head>
    <body>
    <h1>Lookup Parameters</h1>
        <table id="params">
            <tbody>
                <tr>
                    <td id="title">Id </td>
                    <td><c:out value="${bookData.id}"/></td>
                </tr>
                <tr>
                    <td id="title">Name </td>
                    <td><c:out value="${bookData.name}"/></td>
                </tr>
                <tr>
                    <td id="title">Author </td>
                    <td><c:out value="${bookData.author}"/></td>
                </tr>
                <tr>
                    <td id="title">Price </td>
                    <td><c:out value="${bookData.price}"/></td>
                </tr>
                <tr>
                    <td id="title">Description </td>
                    <td><c:out value="${bookData.description}"/></td>
                </tr>
                <tr>
                    <td id="title">Type </td>
                    <td><c:out value="${bookData.type}"/></td>
                </tr>
            </tbody>
        </table>
</html>
