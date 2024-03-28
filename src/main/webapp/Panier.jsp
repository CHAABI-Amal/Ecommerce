<%@ page import="com.example.ecommerce.entities.Produit" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    String errorMessage = (String) request.getAttribute("errorMessage");
    if (errorMessage != null) {
        session.setAttribute("errorMessage", errorMessage);
    }
%>

<html>
<head>
    <title>Produits</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
<%@include file="header.jsp" %>
<div class="container col-md-10 col-md-offset-1">
    <div class="panel panel-primary">
        <div class="panel-heading">Votre panier</div>
        <c:if test="${not empty sessionScope.errorMessage}">
            <div class="alert alert-danger">${sessionScope.errorMessage}</div>
        </c:if>
        <form action="ToIndex.php" method="post">
            <table class="table table-striped">
                <tr>
                    <th>ID</th>
                    <th>Designation</th>
                    <th>Prix</th>
                    <th>Quantite</th>
                </tr>
                <c:if test="${not empty sessionScope.panier}">
                    <c:forEach items="${sessionScope.panier}" var="p">
                        <tr>
                            <td>${p.id}</td>
                            <td>${p.designation}</td>
                            <td>${p.prix}</td>
                            <td>${p.quantite}</td>
                            <td><a onclick="return confirm('Etes vous sure?')" href="SupprimerPanier.php?id=${p.id}">Supprimer </a></td>
                        </tr>
                    </c:forEach>
                </c:if>
            </table>
            <div>
                <button type="submit" class="btn btn-primary">Valider Commande</button>
            </div>
        </form>
    </div>
    <div class="panel-heading">
        <%
            double total = 0;
            List<Produit> produitsDansPanier = (List<Produit>) session.getAttribute("panier");
            if (produitsDansPanier != null) {
                for (Produit produit : produitsDansPanier) {
                    total += produit.getPrix() * produit.getQuantite();
                }
            }
        %>
        <p>Total : <%= total %> Dh </p>
    </div>
</div>
</body
