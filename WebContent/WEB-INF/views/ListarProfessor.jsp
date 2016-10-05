<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Lista</title>
</head>
<body>
<table border="2" bordercolor="RED">
	<tr>
		<th>
			TAG
		</th>
		<th>
			FOTO
		</th>
		<th>
			NOME
		</th>
		<th>
			EMAIL
		</th>
		<th>
			TELEFONE
		</th>
		<th>
			CPF
		</th>
		<th>
			LICENCA
		</th>
		<th>
			ESCOLA
		</th>
		<th>
			EXCLUI
		</th>
		<th>
			ALTERA
		</th>
	</tr>
	<c:forEach items="${lista }" var="p">
		<tr>
			<td>
				${p.tagProfessor }
			</td>
			<td>
				<img src="data:image/*;base64, ${p.foto64 }">
			</td>
			<td>
				${p.nomeProfessor }
			</td>
			<td>
				${p.emailProfessor }
			</td>
			<td>
				${p.telefoneProfessor }
			</td>
			<td>
				${p.cpfProfessor }
			</td>
			<td>
				${p.licenca }
			</td>
			<td>
				${p.escolaProfessor }
			</td>
			<td>
				<a href="ExcluirProfessor?id=${p.idProfessor }">Excluir</a>
			</td>
			<td>
				<a href="CadastrandoProfessor?id=${p.idProfessor }">Alterar</a>
			</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>