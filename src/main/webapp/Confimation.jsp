<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 26/02/2024
  Time: 20:51
  To change this template use File | Settings | File Templates.
--%>
<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 24/02/2024
  Time: 22:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
    <title>Produits</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<body>
<%@include file="header.jsp" %>
<div class="contrainer col-md-8 col-md-offset-2 col-xs-12">
    <div class="panel panel-primary">
        <div class="panel-heading">Confirmation</div>
        <div class="panel-body">
            <div class="form-group">
                <label>ID:</label>
                <label>${produit.id}</label>
            </div>
            <div class="form-group">
                <label>Designation:</label>
                <label>${produit.designation}</label>
            </div>
            <div class="form-group">
                <label>Prix:</label>
                <label>${produit.prix}</label>
            </div>
            <div class="form-group">
                <label>Quantite:</label>
                <label>${produit.quantite}</label>
            </div>
            <div class="form-group">
                <label>Categorie:</label>
                <label>${produit.idCategorie}</label>
            </div>
        </div>

    </div>
</div>
</body>

</html>
