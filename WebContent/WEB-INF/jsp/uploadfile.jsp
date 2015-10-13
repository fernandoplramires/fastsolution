<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
		<h1>Processamento de arquivo trimestral para geracao de relatorio</h1>
		<div
			style="font-family: verdana; line-height: 25px; padding: 5px 10px; border-radius: 10px; border: 1px dotted #A4A4A4; width: 50%; font-size: 12px;">
			<form action="uploadprocess.html" method="post"
				enctype="multipart/form-data">
				<p>
					<label for="file">Selecione o arquivo bath de dados do
						trimeste para fazer o upload: </label> <input type="file" name="file" />
					<br /> <br /> <input type="submit" name="submit" value="Gerar Relatorio" />
			</form>
		</div>
		<br />
	</div>
</body>
</html>