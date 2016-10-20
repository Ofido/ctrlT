<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Lista de Quest�es</title>
</head>
<body>
<table border="2" bordercolor="RED">
	<tr>
		<th>
			ID
		</th>
		<th>
			VALIDA��O
		</th>
		<th>
			QUEST�O
		</th>
		<th>
			TIPO DA QUEST�O
		</th>
		<th>
			DISCIPLINA
		</th>
		<th>
			ASSUNTO
		</th>
		<th>
			CRIADOR DA QUEST�O
		</th>
		<th>
			VALIDADOR DA QUEST�O
		</th>
		<th>
			QUANTIDADE DE RESPOSTAS
		</th>
		<th>
			N�VEL DA QUEST�O
		</th>
		<th>
			TEMPO M�DIO PRA CADA QUEST�O
		</th>
		<th>
			�LTIMO USO DA QUEST�O
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