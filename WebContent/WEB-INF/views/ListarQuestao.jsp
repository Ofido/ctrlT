<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Lista de Questões</title>
</head>
<body>
<table border="2" bordercolor="RED">
	<tr>
		<th>
			ID
		</th>
		<th>
			VALIDAÇÃO
		</th>
		<th>
			QUESTÃO
		</th>
		<th>
			TIPO DA QUESTÃO
		</th>
		<th>
			DISCIPLINA
		</th>
		<th>
			ASSUNTO
		</th>
		<th>
			CRIADOR DA QUESTÃO
		</th>
		<th>
			VALIDADOR DA QUESTÃO
		</th>
		<th>
			QUANTIDADE DE RESPOSTAS
		</th>
		<th>
			NÍVEL DA QUESTÃO
		</th>
		<th>
			TEMPO MÉDIO PRA CADA QUESTÃO
		</th>
		<th>
			ÚLTIMO USO DA QUESTÃO
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
				${p.idQuestao }
			</td>
			<td>
				${p.validadaQuestao }
			</td>
			<td>
				${p.questao }
			</td>
			<td>
				${p.tipoQuestao }
			</td>
			<td>
				${p.disciplinaQuestao.nomeDisciplina }
			</td>
			<td>
				${p.assuntoQuestao.nomeAssunto }
			</td>
			<td>
				${p.criadorQuestao.nomeProfessor }
			</td>
			<td>
				${p.validadorQuestao.nomeEspecialista }
			</td>
			<td>
				${p.respostasQuestao.length }
			</td>
			<td>
				${p.nivelQuestao+1}
			</td>
			<td>
				<fmt:formatDate pattern="HH:mm:ss" value="${p.tempoQuestao.time}" var="dataFormatada" />
				${dataFormatada }
			</td>
			<td>
				<fmt:formatDate pattern="yyyy-MM-dd(HH:mm:ss)" value="${p.ultimoUsoQuestao.time}" var="ultimoUsoQuestao" />
				${ultimoUsoQuestao }
			</td>
			<td>
				<a href="ExcluirQuestao?id=${p.idQuestao }">Excluir</a>
			</td>
			<td>
				<a href="CadastrandoQuestao?id=${p.idQuestao }">Alterar</a>
			</td>
		</tr>
	</c:forEach>
	</table>
</body>
</html>