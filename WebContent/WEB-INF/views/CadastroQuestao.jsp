<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="CadastroQuestao" enctype="multipart/form-data"
		method="post">
		<input type="hidden" name="idQuestao" value="${alterando.idQuestao }">
		<input type="hidden" name="idProfessor"
			value="${alterando.criadorQuestao.idProfessor }"> <input
			type="hidden" name="idEspecialista"
			value="${alterando.validadorQuestao.idEspecialista }"> <label
			for="questao">Enunciado:</label> <input type="text" name="questao"
			value="${alterando.questao }"></br>
		</br>
		<!-- TODO MUDAR PARA TEXT AREA -->
		<label for="Disciplina">Disciplina</label> <select name="idDisciplina">
			<c:forEach items="${disciplinas}" var="d">
				<option value="${d.idDisciplina}">${d.nomeDisciplina}</option>
			</c:forEach>
		</select>
		<!-- TODO colocar select de assunto -->
		<label></label>
		<input type="radio" value="TipoQuestao" name="UNICA">
		<input type="submit" value="Cadastro">
	</form>
</body>
</html>