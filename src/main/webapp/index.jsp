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
<div class="contrainer col-md-10 col-md-offset-1">
    <div class="panel panel-primary">
        <div class="panel-heading">Recherche des produits</div>
        <div class="panel-body">
            <form action="chercher.php" method="get">
                <label>Mot Cle</label>
                <input type="text" name="motCle" value="${model.motCle}"/>
                <button type="submit" class="btn btn-primary">Chercher</button>
            </form>
        </div>
        <table class="table table-striped">
            <tr>
                <th>ID</th>
                <th>Designation</th>
                <th>Prix</th>
                <th>Quantite</th>
            </tr>
            <c:forEach items="${model.produitsFinal}" var="p">
                <tr>
                    <td>${p.id}</td>
                    <td>${p.designation}</td>
                    <td>${p.prix}</td>
                    <td>${p.quantite}</td>
                    <td><a onclick="return confirm('Etes vous sure?')" href="Supprimer.php?id=${p.id}">Supprimer </a></td>
                    <td><a href="Edit.php?id=${p.id}">Edit </a></td>

                </tr>

            </c:forEach>


        </table>
    </div>
</div>
</body>

</html>