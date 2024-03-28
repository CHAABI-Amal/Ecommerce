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
    <div class="panel-heading">SAISISSEZ LES DONNEES DE VOTRE CARTE</div>
    <div class="panel-body">
      <form action="ToIndex.php" method="post">
        <div class="form-group">
          <label class="control-label">Numero de carte</label>
          <input type="text" name="numero"  class="form-control" />
          <span></span>
        </div>
        <div class="form-group">
          <label class="control-label">Date</label>
          <input type="date" name="date" class="form-control" required="required"/>
          <span></span>
        </div>
        <div class="form-group">
          <label class="control-label">Titulaire de la carte</label>
          <input type="text" name="titulaire" class="form-control" required="required"/>
          <span></span>
        </div>
        <div class="form-group">
          <label class="control-label">Numero de CVV</label>
          <input type="text" name="cvv" class="form-control" required="required"/>
          <span></span>
        </div>
        <div>
          <button type="submit" class="btn btn-primary">Valider</button>
        </div>
      </form>
    </div>

  </div>
</div>
</body>

</html>

