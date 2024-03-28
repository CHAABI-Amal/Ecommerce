<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 26/02/2024
  Time: 23:09
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
        <div class="panel-heading">Edit d'un produit</div>
        <div class="panel-body">
            <form action="UpdateProduit.php" method="post">
                <div class="form-group">
                    <label class="control-label">ID</label>
                    <input type="text" name="id" value="${produit.id}" class="form-control" readonly/>
                    <span></span>
                </div>
                <div class="form-group">
                    <label class="control-label">Designation</label>
                    <input type="text" name="designation" value="${produit.designation}" class="form-control" required="required"/>
                    <span></span>
                </div>
                <div class="form-group">
                    <label class="control-label">Prix</label>
                    <input type="text" name="prix" value="${produit.prix}" class="form-control"/>
                    <span></span>
                </div>
                <div class="form-group">
                    <label class="control-label">Quantite</label>
                    <input type="text" name="quantite" value="${produit.quantite}" class="form-control"/>
                    <span></span>
                </div>
                <div class="form-group">
                    <label class="control-label">Categorie 1:Electronics, 2:Clothing and Apparel 3:Home and Kitchen</label>
                    <input type="text" name="idCategorie" value="${produit.idCategorie}" class="form-control" required="required"/>
                    <span></span>
                </div>
                <div>
                    <button type="submit" class="btn btn-primary">Save</button>
                </div>
            </form>
        </div>

    </div>
</div>
</body>

</html>

