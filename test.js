function test() {
	if (document.getElementById('username').value == ""	||
		document.getElementById('password').value == "") {

		affWarning();
		return false;
	} else {
		document.f1.submit;
	}
}

function affWarning() {
	var url = "http://192.168.1.16:8080/pv4/loginWarning";

	if (window.XMLHttpRequest) {
		requete = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		requete = new ActiveXObject("Microsoft.XMLHTTP");
	}

	requete.open("GET", url, true);
	requete.onreadystatechange = warningRep;
	requete.send(null);
}

function warningRep() {
	var message = "";

	if (requete.readyState == 4) {
		if (requete.status == 200) {

			var messageTag = requete.responseXML
					.getElementsByTagName("message")[0];
			var message = messageTag.childNodes[0].textContent;

			mdiv = document.getElementById("logMsg");
			if (message != 0) {
				mdiv.innerHTML = message;
			} else {
				alert('Mauvais Msg');
			}
		}
	}
}

function date_heure(id) {
	date = new Date;
	annee = date.getFullYear();
	moi = date.getMonth();
	mois = new Array('Janvier', 'F&eacute;vrier', 'Mars', 'Avril', 'Mai',
			'Juin', 'Juillet', 'Ao&ucirc;t', 'Septembre', 'Octobre',
			'Novembre', 'D&eacute;cembre');
	j = date.getDate();
	jour = date.getDay();
	jours = new Array('Dimanche', 'Lundi', 'Mardi', 'Mercredi', 'Jeudi',
			'Vendredi', 'Samedi');
	h = date.getHours();
	if (h < 10) {
		h = "0" + h;
	}
	m = date.getMinutes();
	if (m < 10) {
		m = "0" + m;
	}
	s = date.getSeconds();
	if (s < 10) {
		s = "0" + s;
	}
	resultat = jours[jour] + ' ' + j + ' ' + mois[moi] + ' ' + annee + ' - '
			+ h + ':' + m + ':' + s;
	document.getElementById(id).innerHTML = resultat;
	setTimeout('date_heure("' + id + '");', '1000');
	return true;
}

function nbCl() {
	var nbCl = document.getElementById("nbCl").value;
	if (nbCl == 10) {
		nbCl = document.getElementById("creerCl").disabled = true;
	}
}

function ajax(){
	var idCl = document.getElementById("idCl");
	var nomCl = document.getElementById("nomCl");
	var prenomCl = document.getElementById("prenomCl");
	var adresseCl = document.getElementById("adresseCl");
	var villeCl = document.getElementById("villeCl");
	var cpCl = document.getElementById("cpCl");
	var telCl = document.getElementById("telCl");
	var melCl = document.getElementById("melCl");
	
	var url = "http://192.168.1.16:8080/pv4/Verif?idCl="+escape(idCl.value)+"&nomCl="+escape(nomCl.value)+
				"&prenomCl="+escape(prenomCl.value)+
				"&adresseCl="+escape(adresseCl.value)+"&villeCl="+escape(villeCl.value)+
				"&cpCl="+escape(cpCl.value)+"&telCl="+escape(telCl.value)+
				"&melCl="+escape(melCl.value);
	
	if (window.XMLHttpRequest) {
	       requete = new XMLHttpRequest();
	   } else if (window.ActiveXObject) {
	       requete = new ActiveXObject("Microsoft.XMLHTTP");
	   }
	
	   requete.open("GET", url, true);
	  
	   requete.onreadystatechange = modif;
	  
	   requete.send(null);
}

function rollback(){
	var idCl = document.getElementById("idCl");
	var nomCl = document.getElementById("nomClAn");
	var prenomCl = document.getElementById("prenomClAn");
	var adresseCl = document.getElementById("adresseClAn");
	var villeCl = document.getElementById("villeClAn");
	var cpCl = document.getElementById("cpClAn");
	var telCl = document.getElementById("telClAn");
	var melCl = document.getElementById("melClAn");
	
	var url = "http://192.168.1.16:8080/pv4/Verif?idCl="+escape(idCl.value)+"&nomCl="+escape(nomCl.value)+
				"&prenomCl="+escape(prenomCl.value)+
				"&adresseCl="+escape(adresseCl.value)+"&villeCl="+escape(villeCl.value)+
				"&cpCl="+escape(cpCl.value)+"&telCl="+escape(telCl.value)+
				"&melCl="+escape(melCl.value);
	
	if (window.XMLHttpRequest) {
	       requete = new XMLHttpRequest();
	   } else if (window.ActiveXObject) {
	       requete = new ActiveXObject("Microsoft.XMLHTTP");
	   }
	
	   requete.open("GET", url, true);
	  
	   requete.onreadystatechange = modif;
	  
	   requete.send(null);
}

function modif(){
	 var message = "";
	 
	  if (requete.readyState == 4) {
	  	
	    if (requete.status == 200) {
	    	
	      // exploitation des données de la réponse
	    	var messageTag = requete.responseXML.getElementsByTagName("message")[0];
	  	  	var nom = messageTag.childNodes[0].textContent;    
	  	  	var pnom = messageTag.childNodes[1].textContent;
	        var adCl = messageTag.childNodes[2].textContent;
	        var villeCl = messageTag.childNodes[3].textContent;  
	        var cpCl = messageTag.childNodes[4].textContent;
	        var telCl = messageTag.childNodes[5].textContent;
	        var melCl = messageTag.childNodes[6].textContent;
	        
		    mdiv = document.getElementById("nomCl_mod");
    		mdiv2 = document.getElementById("pnomCl_mod");
     		mdiv3 = document.getElementById("adCl_mod");
     		mdiv4 = document.getElementById("villeCl_mod");
     		mdiv5 = document.getElementById("cpCl_mod");
     		mdiv6 = document.getElementById("telCl_mod");
		    mdiv7 = document.getElementById("melCl_mod");
     		
       		mdiv.innerHTML = nom;
      		mdiv2.innerHTML = pnom;
     		mdiv3.innerHTML = adCl;
      		mdiv4.innerHTML = villeCl;
      		mdiv5.innerHTML = cpCl;
   			mdiv6.innerHTML = telCl;
    		mdiv7.innerHTML = melCl;
   	    }
 	  }  
 }


function verifErrorMsg() {
	if (document.getElementById('msgError').value.includes("existe")) {
		document.formError.action="affCompte"		
	} else {
		document.formError.action="virement";
	}
	return true;
}

function alerte(){
	if (confirm("Etes-vous sur de vouloir supprimer ce Client ?")){
		document.formSupp.submit;
		return true;	
	}else{
		//document.formSupp.action="listClient.jsp";
		return false;
	}
	
}