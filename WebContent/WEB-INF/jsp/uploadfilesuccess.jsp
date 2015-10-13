<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>FAST SOLUTION EXAME</title>
<style type="text/css"></style>
</head>
<body>
	<br>
	<br>
	<div align="center">
		<h1>Resultado do relatorio trimestral:</h1>
		<div
			style="font-family: verdana; line-height: 25px; padding: 5px 10px; border-radius: 10px; border: 1px dotted #A4A4A4; width: 50%; font-size: 12px;">
			<c:forEach items="${relatorioTrimestral}" var="relatorioTrimestral">
				<div style="font-weight: bold">${relatorioTrimestral.key}</div>
        	${relatorioTrimestral.value} 
        	<br />
			</c:forEach>
		</div>
		<br /> <a href="upload.html"><input type="button" value="OK" /></a>
	</div>
</body>
</html>