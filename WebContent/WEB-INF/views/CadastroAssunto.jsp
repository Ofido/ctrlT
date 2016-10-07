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
<form action="CadastroAssunto" enctype="multipart/form-data" method="post">
<input type="hidden" name="idAssunto" value="${alterando.idAssunto }">
<label for="nomeAssunto">nomeAssunto</label>
<input type="text" name="nomeAssunto" value="${alterando.nomeAssunto }"></br></br>
<label for="Disciplina">Disciplina</label>
<select name="idDisciplina">
	<c:forEach items="${disciplinas}" var="d">
		<option value="${d.idDisciplina}">${d.nomeDisciplina}</option>
	</c:forEach>
</select>
<input type="submit" value="Cadastro">
</form>
</body>
</html>