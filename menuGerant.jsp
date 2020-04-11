<html>
<head>
<%@page language="java" import="java.util.*"%>
<meta charset="utf-8" />
<meta name="description" content="ProxiBanqueSI">
<meta name="author"
	content="Farhad NIKFARJAM, Alexandre NEVES et Romain EUVRARD">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="style.css" />
<title>Proxi Banque SI</title>

</head>
<body>
	<div class="menu-left">
		<img src="images/avatar.png" alt="Avatar" class="avatar" />
		<h3>ProxiBanqueSI</h3>
		<br />
		<h4>
			Gérant<br />
			<%
				String nom = (String) session.getAttribute("nomCons");
			%>
			<%=nom%>
			<%
				String prenom = (String) session.getAttribute("prenomCons");
			%>
			<%=prenom%>
		</h4>
		<br /> <br />
		<div class="affdate1">
			<span class="date" id="date_heure"></span>
			<script type="text/javascript">
				window.onload = date_heure('date_heure');
			</script>
		</div>
		<br /> <br /> <br />
		<!-- <input type="button" class="submit" value="Connect&eacute;" /> -->
		<p style="text-align: center;">
			<a href="login.jsp"><input type="button"
				class="cancelbtn" value="D&eacute;connexion" /></a>
		</p>
	</div>


</body>
</html>