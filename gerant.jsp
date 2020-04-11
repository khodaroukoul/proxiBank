<html>
<head>
<%@page language="java" import="java.util.*"%>
<meta charset="utf-8" />
<meta name="description" content="ProxiBanqueSI">
<meta name="author"
	content="Farhad NIKFARJAM, Alexandre NEVES et Romain EUVRARD">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="style.css" />
<link rel="stylesheet"
	href="https://fonts.googleapis.com/icon?family=Material+Icons">
<title>Proxi Banque SI - G&eacute;rant</title>
<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>
<script src="https://code.highcharts.com/modules/export-data.js"></script>
<script src="https://code.highcharts.com/modules/highcharts.css"></script>
<script type="text/javascript" src="test.js"></script>
<script type="text/javascript" src="histogramme.js"></script>
</head>
<body>
	<%@ include file="menuGerant.jsp"%>

	<div class="menu-center" style="width: auto; ">
		<h2>Liste conseillers</h2>
		<table>
			<tr>
				<th>id</th>
				<th>Nom</th>
				<th>Pr&eacute;nom</th>
				<th>Adresse</th>
				<th>Code postal</th>
				<th>Ville</th>
				<th>T&eacute;l&eacute;phone</th>
				<th>idAgence</th>
				<th>Password</th>
				<th>Profil</th>
				
			</tr>
			<% 
					Iterator itr;
					List data= (List) session.getAttribute("lesConseillers");
					for (itr=data.iterator(); itr.hasNext(); ){
						int idCons = (Integer) itr.next();
						String nomCons = (String) itr.next();
						String prenomCons = (String) itr.next();
						String adresseCons = (String) itr.next();
						int cpCons = (Integer) itr.next();
						String villeCons = (String) itr.next();
						String telCons = (String) itr.next();
						String idAgenceCons = (String) itr.next();
						String passwordCons = (String) itr.next();
						String profilCons = (String) itr.next();
			%>
			<tr>
				<td><%=idCons%></td>
				<td><%=nomCons%></td>
				<td><%=prenomCons%></td>
				<td><%=adresseCons%></td>
				<td><%=cpCons%></td>
				<td><%=villeCons%></td>
				<td><%=telCons%></td>
				<td><%=idAgenceCons%></td>
				<td><%=passwordCons%></td>
				<td><%=profilCons%></td>				
			</tr>
			<%
			}
			%>
		</table>
	</div>
	
	<div class="his-button">
	<input type="button" class="submit" value="Voir histogramme mensuel" id="switch" onclick="toggle();" style="width:250px"/>
	</div>
	<span class="histogramme" id="semaine"></span>
	<span class="histogramme" id="mois" style="display:none;" ></span>
	
<%
	String[] virHistoryWeek = (String[]) session.getAttribute("lesVirWeek");
			String[] count=new String[7];
			String[] montant=new String[7];
			String[] date=new String[7];
			for (int i=0; i<virHistoryWeek.length; i += 3) {
		count[i / 3] = virHistoryWeek[i];
		montant[i / 3] = virHistoryWeek[i + 1];
		date[i / 3] = virHistoryWeek[i + 2];
	}
%>
<script type="text/javascript">
Highcharts.chart('semaine', {
	  chart: {
	    zoomType: 'xy'    
	  },
	  title: {
	    text: 'Historique des virements'
	  },
	  exporting: { enabled: false },
	  
	  legend: {
	      align: 'center',
	      verticalAlign: 'bottom',
	      floating: true,
	  },
	  
	  xAxis: [{
	    categories: ['<%=date[0]%>', '<%=date[1]%>', '<%=date[2]%>', '<%=date[3]%>', '<%=date[4]%>', '<%=date[5]%>',
	      '<%=date[6]%>'],
	    crosshair: true
	  }],
	  yAxis: [{ // Primary yAxis
		  
	    labels: {
	      format: '{value} 	\u20ac',
	      style: {
	        color: Highcharts.getOptions().colors[1]
	      }
	    },
	    title: {
	      text: 'Montant',
	      style: {
	        color: Highcharts.getOptions().colors[1]
	      }
	    }
	  }, { // Secondary yAxis
		
	    title: {
	      text: 'Nombre de virements',
	      style: {
	        color: Highcharts.getOptions().colors[0]
	      }
	    },
	    tickInterval:1,
	    labels: {
	    
	      style: {
	        color: Highcharts.getOptions().colors[0]
	      }
	    },
	    opposite: true
	  }],
	  tooltip: {
	    shared: true
	  },
	  series: [{
		showInLegend: false, 
	    name: 'Nb virement',
	    type: 'column',
	    yAxis: 1,
	    data: [<%=count[0]%>, <%=count[1]%>, <%=count[2]%>, <%=count[3]%>, <%=count[4]%>, <%=count[5]%>,
	        <%=count[6]%>],
	    tooltip: {
	      valueSuffix: ''
	    }

	  }, {
		showInLegend: false, 
	    name: 'Montant',
	    type: 'spline',
	    data: [<%=montant[0]%>, <%=montant[1]%>, <%=montant[2]%>, <%=montant[3]%>, <%=montant[4]%>, <%=montant[5]%>,
	    	<%=montant[6]%>],
	    tooltip: {
	      valueSuffix: '\u20ac'
	    }
	  }]
	});
</script>

<%
	String[] virHistoryMonths = (String[]) session.getAttribute("lesVirMonths");
			for (int i=0; i<virHistoryMonths.length; i += 3) {
		count[i / 3] = virHistoryMonths[i];
		montant[i / 3] = virHistoryMonths[i + 1];
		date[i / 3] = virHistoryMonths[i + 2];
	}
%>
<script type="text/javascript">
Highcharts.chart('mois', {
	  chart: {
	    zoomType: 'xy'    
	  },
	  title: {
	    text: 'Historique des virements'
	  },
	  exporting: { enabled: false },
	  
	  legend: {
	      align: 'center',
	      verticalAlign: 'bottom',
	      floating: true,
	  },
	  
	  xAxis: [{
	    categories: ['<%=date[0]%>', '<%=date[1]%>', '<%=date[2]%>'],
	    crosshair: true
	  }],
	  yAxis: [{ // Primary yAxis
		  
	    labels: {
	      format: '{value} \u20ac',
	      style: {
	        color: Highcharts.getOptions().colors[1]
	      }
	    },
	    title: {
	      text: 'Montant',
	      style: {
	        color: Highcharts.getOptions().colors[1]
	      }
	    }
	  }, { // Secondary yAxis
		
	    title: {
	      text: 'Nombre de virements',
	      style: {
	        color: Highcharts.getOptions().colors[0]
	      }
	    },
	    tickInterval:1,
	    labels: {
	    
	      style: {
	        color: Highcharts.getOptions().colors[0]
	      }
	    },
	    opposite: true
	  }],
	  tooltip: {
	    shared: true
	  },
	  series: [{
		showInLegend: false, 
	    name: 'Nb virement',
	    type: 'column',
	    yAxis: 1,
	    data: [<%=count[0]%>, <%=count[1]%>, <%=count[2]%>],
	    tooltip: {
	      valueSuffix: ''
	    }

	  }, {
		showInLegend: false, 
	    name: 'Montant',
	    type: 'spline',
	    data: [<%=montant[0]%>, <%=montant[1]%>, <%=montant[2]%>],
	    tooltip: {
	      valueSuffix: '\u20ac'
	    }
	  }]
	});
</script>

<div class="Dec">
	<h2 style="text-align:center;">Clients &agrave; D&eacute;couvert :</h2><br />
					<table style="margin: 0 auto;">
						<tr>
						<th>id Client</th>
						<th>N&deg; Compte</th>
						<th>Solde</th>
				
						</tr>
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
							if (Double.parseDouble(soldeCompte) < 0 ) {
				%>
						<tr>
							<td align="center"><%=idC%></td>
							<td align="center"><%=noCompte%></td>
							<td align="center"><%=soldeCompte%> &euro;</td>
						</tr>	
				<%
							}
						
						}
				%>
				</table>
		</div>
<script>
function toggle() {
    var semaine = document.getElementById("semaine");
    var mois = document.getElementById("mois");
    var bouton = document.getElementById("switch");
    
    if(mois.style.display == 'none'){
        semaine.style.display = 'none';
        mois.style.display = 'block';
        bouton.value = "Voir histogramme hebdomadaire"
    }else{
        semaine.style.display = 'block';
        mois.style.display = 'none';
        bouton.value = "Voir histogramme mensuel"
    }
}
</script>		
	
</body>
</html>