<html>
<head>
<meta charset="utf-8" />
<meta name="description" content="ProxiBanqueSI">
<meta name="author"
	content="Farhad NIKFARJAM, Alexandre NEVES et Romain EUVRARD">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" href="style.css" />
<script type="text/javascript" src="test.js"></script>
<title>Proxi Banque SI</title>
</head>
<body>

	<%@ include file="menu.jsp"%>
	<div class="menu-center1" style="width: 500px;">
		<form action="" method="post">
			<h2>Modification des informations client</h2>

			<label><b>id</b></label><input type="text" class="text" id="idCl"
				name="idCl" readonly="readonly"	value="<%=request.getParameter("idCl")%>" /> <br />
			<label><b>Nom:<span id="nomCl_mod"><%=request.getParameter("nomCl")%></span>
			</b></label><input type="text" class="text" id="nomCl" name="nomCl" /> <br />
			<label><b>Pr&eacute;nom: <span id="pnomCl_mod"><%=request.getParameter("pnomCl")%></span></b></label>
			<input type="text" class="text" id="prenomCl" name="prenomCl" /> <br />
			<label><b>Adresse: <span id="adCl_mod"><%=request.getParameter("adCl")%></span></b></label>
			<input type="text" class="text" id="adresseCl" name="adresseCl" /> <br />
			<label><b>Code postal: <span id="cpCl_mod"><%=request.getParameter("cpCl")%></span></b></label>
			<input type="text" class="text" id="cpCl" name="cpCl" /> <br> <label><b>Ville:
					<span id="villeCl_mod"><%=request.getParameter("villeCl")%></span>
			</b></label> <input type="text" class="text" id="villeCl" name="villeCl" /> <br />
			<label><b>T&eacute;l&eacute;phone: <span id="telCl_mod"><%=request.getParameter("telCl")%></span></b></label>
			<input type="tel" class="text" id="telCl" name="telCl" /> <br />
			<label><b>Mail: <span id="melCl_mod"><%=request.getParameter("melCl")%></span></b></label>
			<input type="email" class="text" id="melCl" name="melCl" /> <br />
			<label><b>Type Client:</b></label>
			<input type="text" class="text" id="typeCl" name="typeCl" 
					value="<%=request.getParameter("typeCl")%>" readonly="readonly"/>
					
			<input type="hidden" class="text" id="nomClAn" name="nomClAn" value="<%=request.getParameter("nomCl")%>" />
			<input type="hidden" class="text" id="prenomClAn" name="prenomClAn" value="<%=request.getParameter("pnomCl")%>" />
			<input type="hidden" class="text" id="adresseClAn" name="adresseClAn" value="<%=request.getParameter("adCl")%>" />
			<input type="hidden" class="text" id="cpClAn" name="cpClAn" value="<%=request.getParameter("cpCl")%>" />
			<input type="hidden" class="text" id="villeClAn" name="villeClAn" value="<%=request.getParameter("villeCl")%>" />
			<input type="hidden" class="text" id="telClAn" name="telClAn" value="<%=request.getParameter("telCl")%>" />
			<input type="hidden" class="text" id="melClAn" name="melClAn" value="<%=request.getParameter("melCl")%>" />
			
			<p style="text-align: center;">
				<input type="button" class="submit" id="valider" value="Valider"
					onclick="ajax();" />
				<input type="button" class="submit" id="Annuler" value="Annuler"
					onclick="rollback();" />
				<a href="listClients.jsp"><input type="button" class="submit"
					value="Retour" /></a>
			</p>
			
		</form>
		<div align="center" style="border:none!important;box-shadow:0px 0px 0px 0px rgba(0, 0, 0, 0);width:450px">
		<form  onsubmit="return alerte();" action="SuppCl" method="post" name="Supp">
		<input type="hidden" name="idCl" value="<%=request.getParameter("idCl")%>" />
			<input type="submit" class="submit"
					value="Supprimer Client" style="text-align:center;width:150px"/>
		</form>
		</div>
	</div>
</body>
</html>