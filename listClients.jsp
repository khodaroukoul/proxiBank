<html>
<head>
<%@page language="java" import="java.util.*"%>
<meta charset="utf-8" />
<meta name="description" content="ProxiBanqueSI">
<meta name="author"
	content="Farhad NIKFARJAM, Alexandre NEVES et Romain EUVRARD">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="style.css" />
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
<title>Proxi Banque SI - liste-clients</title>
<script type="text/javascript" src="test.js"></script>
</head>
<body>

	<%@ include file="menu.jsp"%>

	<div class="menu-center" style="width: auto;">
		<h2>Liste clients</h2>
		<table style="margin: 0 auto;">
			<tr>
				<th>idClient</th>
				<th>Nom</th>
				<th>Pr&eacute;nom</th>
				<th>Adresse</th>
				<th>Code postal</th>
				<th>Ville</th>
				<th>T&eacute;l&eacute;phone</th>
				<th>Mail</th>
				<th>Type Client</th>
				<th colspan="3">Op&eacute;rations</th>
				<th>Découvert</th>
				
			</tr>
			<%
				Iterator itr;
				List data = (List) session.getAttribute("lesClients");
				for (itr = data.iterator(); itr.hasNext();) {
					Integer idCl = (Integer) itr.next();
					String nomCl = (String) itr.next();
					String pnomCl = (String) itr.next();
					String adCl = (String) itr.next();
					Integer cpCl = (Integer) itr.next();
					String villeCl = (String) itr.next();
					String telCl = (String) itr.next();
					String melCl = (String) itr.next();
					String typeCl = (String) itr.next();
			%>
			<tr>
				<td align="center"><%=idCl%></td>
				<td align="center"><%=nomCl%></td>
				<td align="center"><%=pnomCl%></td>
				<td align="center"><%=adCl%></td>
				<td align="center"><%=cpCl%></td>
				<td align="center"><%=villeCl%></td>
				<td align="center"><%=telCl%></td>
				<td align="center"><%=melCl%></td>
				<td align="center"><%=typeCl%></td>

				<td align="center">
					<form action="editClient.jsp" method="post">
						<input type="hidden" name="idCl" value="<%=idCl%>" /> <input
							type="hidden" name="nomCl" value="<%=nomCl%>" /> <input
							type="hidden" name="pnomCl" value="<%=pnomCl%>" /> <input
							type="hidden" name="adCl" value="<%=adCl%>" /> <input
							type="hidden" name="cpCl" value="<%=cpCl%>" /> <input
							type="hidden" name="villeCl" value="<%=villeCl%>" /> <input
							type="hidden" name="telCl" value="<%=telCl%>" /> <input
							type="hidden" name="melCl" value="<%=melCl%>" /> <input
							type="hidden" name="typeCl" value="<%=typeCl%>" />
						<button type="submit" class="submit"
							title="Modification des informations client">
							<i class="material-icons"
								style="font-size: 26px; color: #00BFFF;">edit</i>
						</button>
					</form>
				</td>

				<td align="center">
					<form action="affCompte" method="post">
						<input type="hidden" name="idCl" value="<%=idCl%>" /> <input
							type="hidden" name="nomCl" value="<%=nomCl%> <%=pnomCl%>" />

						<button type="submit" class="submit"
							title="Affichage des comptes client">
							<i class="material-icons"
								style="font-size: 26px; color: #00BFFF;">list</i>
						</button>
					</form>
				</td>
				<td align="center">

					<form action="virement" method="post">
						<input type="hidden" name="idCl" value="<%=idCl%>" /> <input
							type="hidden" name="nomCl" value="<%=nomCl%> <%=pnomCl%>" />

						<button type="submit" class="submit"
							title="Virement compte &agrave; compte">
							<i class="material-icons"
								style="font-size: 26px; color: #00BFFF;">payment</i>
						</button>
					</form>
				</td>
		<td align="center">
				<%
					Iterator itrCmp;
						List listSolde = (List) session.getAttribute("Soldes");
						for (itrCmp = listSolde.iterator(); itrCmp.hasNext();) {
							String idCompte = (String) itrCmp.next();
							String typeCompte = (String) itrCmp.next();
							String noCompte = (String) itrCmp.next();
							String dateCompte = (String) itrCmp.next();
							String soldeCompte = (String) itrCmp.next();
							String idC = (String) itrCmp.next();
							String idAgence = (String) itrCmp.next();
							if (Double.parseDouble(soldeCompte) < 0 && idCl.toString().equals(idC)) {
				%>
								<button type="submit" class="submit" disabled="disabled" title="Client à Découvert"><i class="material-icons"
								style="font-size: 26px; color: red;">warning</i></button>
								
				<%
							}
						}
				%>
				</td>
				</tr>
			

			<%
				}
			%>
		</table>
		
		<form action="creerClient.jsp" method="post">
			<br /><br /><b>Nombre de Client : <%=session.getAttribute("nbCl")%> </b>&nbsp;&nbsp;
			<input type="hidden" class="text" id="nbCl"
				value="<%=session.getAttribute("nbCl")%>" name="nbCl" />
			<input type="submit" class="submit" id="creerCl" value="Creer Client" />
		</form>
	</div>

	<script type="text/javascript">
		window.onload = nbCl();
	</script>
</body>
</html>