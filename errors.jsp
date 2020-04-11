<html>
<head>
<%@page language="java" import="java.util.*"%>
<meta charset="utf-8" />
<meta name="description" content="ProxiBanqueSI">
<meta name="author"
	content="Farhad NIKFARJAM, Alexandre NEVES et Romain EUVRARD">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="style.css" />
<title>Proxi Banque SI - errors</title>
<script type="text/javascript" src="test.js"></script>
</head>
<body>
	<%@ include file="menu.jsp"%>
	<div class="menu-center" style="width: 500px;">
		<form onsubmit="return verifErrorMsg();" method="post" id="formError" name="formError">
			<p style="text-align: center;">
				<input type="hidden" name="idCl"
					value="<%=request.getAttribute("idCl")%>" /> <input type="hidden"
					name="nomCl" value="<%=request.getAttribute("nomCl")%>" /> <input
					type="button" class="errorbtn" value="Echec du virement" disabled />
				<input type="button" class="errorbtn" id="msgError"
					value="<%=request.getAttribute("errMsg")%>" disabled /> <br />
				<br /> <input type="submit" class="submit" value="Retour" />
			</p>
		</form>
	</div>

</body>
</html>