<html>
<head>
<meta charset="utf-8" />
<meta name="description" content="ProxiBanqueSI">
<meta name="author"
	content="Farhad NIKFARJAM, Alexandre NEVES et Romain EUVRARD">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" href="style.css" />

<title>Proxi Banque SI</title>
<script type="text/javascript" src="test.js"></script>
</head>
<body>

	<%@ include file="menu.jsp"%>
	<div class="menu-center1" style="width: 500px; text-align: center;border:none;box-shadow:none;">
		<div class="button-container" style="width: 450px; text-align: center;">
		<form action="CreerCompte" method="post">
			<h2>Création de Compte</h2>

			<label><b>id Client</b></label><input type="text" class="text"
				id="idCl" name="idCl" readonly="readonly"
				value="<%=request.getAttribute("idCl")%>" /><br />
			<label><b>Nom Client</b></label><input type="text" class="text"
				id="nomCl" name="nomCl" readonly="readonly"
				value="<%=request.getAttribute("nomCl")%> " /> <br />
				
			<label><b>Type de Compte: </b></label>	
			<select id="typeCompte" name="typeCompte" class="select" required="required">
			<option value="" selected="selected">Type Compte</option>
			<option value="courant" >courant</option>
			<option value="epargne" >epargne</option>
			</select><br />
			<label><b>Numero de	Compte: </b></label> <input type="text" class="text" id="numeroCompte"
				name="numeroCompte" required="required"/> <br />
			<label><b>Date de création: </b></label> <input type="date" class="text" id="date" name="date" required="required"/>
			<br /> <label><b>Montant versé: </b></label> <input type="text"
				class="text" id="solde" name="solde" required="required"/> <br> <label><b>idAgence:
			</b></label> <input type="text" class="text" id="idAgence" value="<%=session.getAttribute("idAgence")%>" name="idAgence" readonly="readonly"/> <br /><br />

			<input type="submit" class="submit" id="valider" value="Valider" />
		</form>
				
    	<form action="affCompte.jsp" method="post">
        		<input type="hidden" name="idCl" value="<%=request.getAttribute("idCl")%>" />
        		<input type="hidden" name="nomCl" value="<%=request.getAttribute("nomCl")%>" />
            	<input type="submit" class="submit" value="Retour" />
    	</form>
    	</div>
    </div>
</body>
</html>