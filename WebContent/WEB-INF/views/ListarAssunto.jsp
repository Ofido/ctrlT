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
			ID
		</th>
		<th>
			NOME
		</th>
		<th>
			Discilina
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
				${p.idAssunto }
			</td>
			<td>
				${p.nomeAssunto }
			</td>
			<td>
				${p.disciplinaAssunto.nomeDisciplina }
			</td>
			<td>
				<a href="ExcluirAssunto?id=${p.idAssunto }">Excluir</a>
			</td>
			<td>
				<a href="CadastrandoAssunto?id=${p.idAssunto }">Alterar</a>
			</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>