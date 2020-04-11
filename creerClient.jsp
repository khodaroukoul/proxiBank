<html>
<head>
<meta charset="utf-8" />
<meta name="description" content="ProxiBanqueSI">
<meta name="author" content="Domenico PALAMARA">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" href="style.css" />
<script type="text/javascript" src="test.js"></script>
<title>Proxi Banque SI</title>
</head>
<body>
	<%@ include file="menu.jsp" %>
	<div class="menu-center" style="width:500px;">

		<form  action="CreerClient" method="post">
			<h2>Saisir les informations client</h2>
			<input type="hidden" class="text" 
			id="idCons" name="idCons" readonly="readonly" value="<%=session.getAttribute("idCons")%>"/>
			<br />
			<label><b>Nom: </b></label><input type="text" class="text" id="nomCl" name="nomCl" required="required"/>
			<br />
			<label><b>Prenom: </b></label> <input type="text" class="text" id="prenomCl" name="prenomCl" required="required"/> 
			<br />
			<label><b>Adresse: </b></label> <input type="text" class="text" id="adresseCl" name="adresseCl" required="required"/>
			<br /> 
			<label><b>Code postal: </b></label> <input type="text" class="text" id="cpCl" name="cpCl" required="required"/>
			<br />
			<label><b>Ville: </b></label> <input type="text" class="text" id="villeCl" name="villeCl" required="required"/>
			<br />
			<label><b>Telephone: </b></label> <input type="tel" class="text" id="telCl" name="telCl" required="required"/>
			<br />
			<label><b>Courriel: </b></label> <input type="email" class="text" id="melCl" name="melCl" required="required"/>	
			<br />	
			<label><b>Type de client: </b></label>	
			<select id="typeCl" name="typeCl" class="select" required="required">
			<option value="" selected="selected">Type Client</option>
			<option value="particulier" >particulier</option>
			<option value="entreprise" >entreprise</option>
			</select> 
			<br />
			<p style="text-align:center;">
			<input type="submit" class="submit" id="valider" value="Valider" />
			<a href="listClients.jsp"><input type="button" class="submit" value="Retour" /></a>
			</p>
		</form>
	</div>
</body>
</html>