<html>
<head>
<meta charset="utf-8" />
<meta name="description" content="ProxiBanqueSI">
<meta name="author"
	content="Farhad NIKFARJAM, Alexandre NEVES et Romain EUVRARD">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="style.css" />
<script type="text/javascript" src="test.js"></script>
<title>Proxi Banque SI - login</title>
</head>
<body>
	<div class="example1">
		<h1>Bienvenue chez ProxiBanqueSI ! Veuillez vous authentifier.</h1>
	</div>
	<div class="login" style="width: 250px;">

		<form onsubmit="return test();" id="f1" name="f1" action="listClients"
			method="POST">
			<img src="images/logopb.png" alt="Logo Proxi Banque" class="logo" />
			<h2>Connexion</h2>
			<label><b>Nom de l'employ&eacute;</b></label> <input type="text"
				class="text" placeholder="Entrez le nom du conseiller" id="username"
				name="username" /> <label><b>Mot de passe</b></label> <input
				type="password" class="text" placeholder="Entrez le mot de passe"
				id="password" name="password" />

			<p style="text-align: center;">
				<input type="submit" class="submit" value="S'identifier" /> <input
					type="reset" class="cancelbtn" value="Reset" /> <br />
				<br /> <span id="logMsg" style="color: red"></span>
			</p>
		</form>
	</div>
	<div class="affdate">
		<span class="date" id="date_heure"></span>
		<script type="text/javascript">
			window.onload = date_heure('date_heure');
		</script>
	</div>
</body>
</html>
