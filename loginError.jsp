<html>
<head>
<meta charset="utf-8" />
<meta name="description" content="ProxiBanqueSI">
<meta name="author"
	content="Farhad NIKFARJAM, Alexandre NEVES et Romain EUVRARD">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="style.css" />
<title>Proxi Banque SI - login-error</title>
<script type="text/javascript" src="test.js"></script>
</head>
<body>
	<div class="login" style="width: 500px;">

		<form action="" method="post">
			<img src="images/logopb.png" alt="Logo Proxi Banque" class="logo" />
			<h1>ProxiBanqueSI</h1>
			<h2>
				Echec d'authentification!<br />Essayez &agrave; nouveau.
			</h2>
			<p style="text-align: center;">
				<a href="login.jsp"><input type="button" class="submit"
					value="Retour" /></a>
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