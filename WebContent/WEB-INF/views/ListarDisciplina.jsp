<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Lista de Disciplinas</title>
</head>
<body>
<table border="2" bordercolor="RED">
	<tr>
		<th>
			ID
		</th>
		<th>
			NOME
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
				${p.idDisciplina }
			</td>
			<td>
				${p.nomeDisciplina }
			</td>
			<td>
				<a href="ExcluirDisciplina?id=${p.idDisciplina }">Excluir</a>
			</td>
			<td>
				<a href="CadastrandoDisciplina?id=${p.idDisciplina }">Alterar</a>
			</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>