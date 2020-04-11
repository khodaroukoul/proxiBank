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
<script type="text/javascript" src="test.js"></script>
</head>
<body>
	<%@ include file="menu.jsp"%>
	<div class="menu-center2" style="text-align:center;">
		<form action="Recup" method="post">
			<h2>Comptes client</h2>

			<label><b>Client</b></label> <input type="text" class="text"
				value="<%=request.getParameter("nomCl")%> " name="nomCl"
				readonly="readonly" /> <br />
			<br />
			<br />
			<table style="margin: 0 auto;">
				<tr>
					<th>idCompte</th>
					<th>Type Compte</th>
					<th>N° Compte</th>
					<th>Date</th>
					<th>Solde</th>
				</tr>
				<%
					Iterator itrCmp;
					List data = (List) session.getAttribute("lesComptes");
					for (itrCmp = data.iterator(); itrCmp.hasNext();) {
						String idCompte = (String) itrCmp.next();
						String typeCompte = (String) itrCmp.next();
						String noCompte = (String) itrCmp.next();
						String dateCompte = (String) itrCmp.next();
						String soldeCompte = (String) itrCmp.next();
						String idCl = (String) itrCmp.next();
						String idAgence = (String) itrCmp.next();
				%>
				<tr>
					<td align="center"><%=idCompte%></td>
					<td align="center"><%=typeCompte%></td>
					<td align="center"><%=noCompte%></td>
					<td align="center"><%=dateCompte%></td>
					<td align="center"><%=soldeCompte%> &euro;</td>
				</tr>
				<%
				}
				%>
			</table><br /><br />
			<input type="hidden" name="idCl" value="<%=request.getParameter("idCl")%>" />
			<input type="submit" class="submit"	value="Creer Compte" style="text-align:center;width:120px"/>
			<a href="listClients.jsp"><input type="button" class="submit" value="Retour" /></a>
		</form>
	</div>
</body>
</html>