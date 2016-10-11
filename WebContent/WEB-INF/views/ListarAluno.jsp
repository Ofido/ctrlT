<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Lista de Alunos</title>
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
			EMAIL
		</th>
		<th>
			SENHA
		</th>
		<th>
			TELEFONE
		</th>
		<th>
			CPF
		</th>
		<th>
			CEP
		</th>
		<th>
			ESTADO
		</th>
		<th>
			CIDADE
		</th>
		<th>
			BAIRRO
		</th>
		<th>
			RUA
		</th>
		<th>
			NÚMERO
		</th>
		<th>
			COMPLEMENTO
		</th>
		<th>
			DATA DE NASCIMENTO
		</th>
		<th>
			FOTO
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
				${p.idAluno }
			</td>
			<td>
				${p.nomeAluno }
			</td>
			<td>
				${p.emailAluno }
			</td>
			<td>
				${p.senhaAluno }
			</td>
			<td>
				${p.telefoneAluno }
			</td>
			<td>
				${p.cpfAluno }
			</td>
			<td>
				${p.cepAluno }
			</td>
			<td>
				${p.estadoAluno }
			</td>
			<td>
				${p.cidadeAluno }
			</td>
			<td>
				${p.bairroAluno }
			</td>
			<td>
				${p.ruaAluno }
			</td>
			<td>
				${p.numeroAluno }
			</td>
			<td>
				${p.complementoAluno }
			</td>
			<td>
				${p.aniversarioAluno }
			</td>
			<td>
				<img src="data:image/*;base64, ${p.foto64 }">
			</td>
			<td>
				<a href="ExcluirAluno?id=${p.idAluno }">Excluir</a>
			</td>
			<td>
				<a href="CadastrandoAluno?id=${p.idAluno }">Alterar</a>
			</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>