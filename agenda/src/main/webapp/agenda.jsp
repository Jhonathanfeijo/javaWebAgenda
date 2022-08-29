<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="model.JavaBeans" %>
    <%@page import="java.util.ArrayList" %>
    <% ArrayList<JavaBeans> lista = (ArrayList<JavaBeans>) request.getAttribute("lista"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Agenda</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
<h1>Agenda de Contatos</h1>
<a href="novo.html" class="Botao1">Novo Contato </a>
<table id="tabela">
	<thead>
		<tr>
			<th>Id</th>
			<th>Nome</th>
			<th>Email</th>
			<th>Telefone</th>
		</tr>
	</thead>
	<tbody>
	<% for(JavaBeans contato : lista ){ %>
	<tr>
		<td><%= contato.getId() %><td>
		<td><% out.println(contato.getNome());  %><td>
		<td><% out.println(contato.getEmail());  %><td>
		<td><% out.println(contato.getFone());  %><td>
		<td><a href="select?id=<% out.println(contato.getId());%>" class="Botao1">Alterar</a></td>
		<td><a href="delete?id=<% out.println(contato.getId());%>" class="Botao1">Delete</a></td>
	</tr>
	<%} %>
	</tbody>
</table>


</body>
</html>