<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Agenda</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
	<h1>Alterar novo contato</h1>
	<form name="frmContato" action="update">
		<table>
			<tr>
				<td><input type="text" name="id" readonly value="<% out.println(request.getAttribute("id"));%>">	</td>
			</tr>
			<tr>	
				<td><input type="text" name="nome" placeholder="Novo nome" class="Caixa1" value="<% out.println(request.getAttribute("nome")); %>">
				</td>
			</tr>
			<tr>
				<td><input type="text" name="email" placeholder="Novo e-mail" class="Caixa1" value="<% out.println(request.getAttribute("email")); %>">
				</td>
			</tr>
			<tr>
			<td>
				<input type="text" name="fone" placeholder="Novo telefone" class="Caixa2" value="<% out.println(request.getAttribute("fone")); %>">
			</td>
			</tr>
		</table>
		<input type="button" value="Adicionar" class="Botao1" onclick="validar()">
	
	</form>	<script src="scripts/validador.js"></script>
</body>
</html>