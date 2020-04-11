<html>
    <head>
	<%@page language="java" import="java.util.*" %>
		<meta charset="utf-8" />
	    <meta name="description" content="ProxiBanqueSI">
		<meta name="author" content="Farhad NIKFARJAM, Alexandre NEVES et Romain EUVRARD">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="style.css" />
		<title>Proxi Banque SI - virement</title>
		<script type="text/javascript" src="test.js"></script>
    </head>
    <body>
		<%@ include file="menu.jsp" %>		
		<div class="menu-center3" style="width:500px;">
		 
			<form action="updateVir" method="post">
			    <h2>Virement compte &agrave; compte</h2>
			    
			    <input type="hidden" class="text" 
			    value="<%=request.getParameter("idCl")%>" 
			    name="idCl" readonly="readonly" />
			    
			    <label><b>Client</b></label>
			    <input type="text" class="text" 
			    value="<%=request.getParameter("nomCl")%>" 
			    name="nomCl" readonly="readonly" />
				<br/><br/>
				<label><b>Insérez le montant &agrave; virer</b></label>
                <input type="number" class="text" step ="0.01" min="0" placeholder="montant" name="montant" required />
				<br /><br />
				
				<!--
				label><b>Selectionnez le num&eacute;ro du compte &agrave; d&eacute;biter</b></label>
				-->
				<label><b>Compte d&eacute;bit&eacute;</b></label>
				<select id="compteDeb" name="compteDeb" class="select" required="required">
				<option value="" selected="selected">compte d&eacute;biteur</option>
				<%
		    	Iterator itr;
			    List data = (List) session.getAttribute("lesComptesCl");
				for (itr=data.iterator(); itr.hasNext(); ) {
					String idCompte = (String) itr.next();
					String typeCompte = (String) itr.next();
					String noCompte = (String) itr.next();
					String dateCompte = (String) itr.next();
					String soldeCompte = (String) itr.next();
					String idCl = (String) itr.next();
					String idAgence = (String) itr.next();
				%>
			      <option value="<%=noCompte%>">Compte <%=typeCompte%> numero: <%=noCompte%> 
			      (Solde: <%=soldeCompte%>)</option>
       			<% } %>
       			</select><br /><br />
       			<!--
				
                <input type="text" class="text" placeholder="compte cr&eacute;diteur" 
                name="compteCr" required />
                -->
                <label><b>Compte cr&eacute;dit&eacute;</b></label>
				<select id="compteCr" name="compteCr" class="select" required="required">
				<option value="" selected="selected">compte cr&eacute;diteur</option>
                <%
                List allData = (List) request.getAttribute("lesComptesAll");
                for (itr=allData.iterator(); itr.hasNext(); ) {
                	String idCompte = (String) itr.next();
                	String typeCompte = (String) itr.next();
                	String noCompte = (String) itr.next();
                	String dateCompte = (String) itr.next();
                	String soldeCompte = (String) itr.next();
                	String idCl = (String) itr.next();
                	String idAgence = (String) itr.next();
                	
				%>
			      <option value="<%=noCompte%>">Compte <%=typeCompte%> numero: <%=noCompte%></option>
       			<% } %>       			
       			</select><br /><br />
				
				<p style="text-align:center;">
				<input type="submit" class="submit" value="Valider" />
								
				<a href="listClients.jsp"><input type="button" class="submit" value="Retour" /></a>
				</p>
            </form>
		</div>
</body>
</html>