<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastro de Disciplina</title>
</head>
<body>
<form action="CadastroDisciplina" method="get">
<input type="hidden" name="idDisciplina" value="${alterando.idDisciplina }">validadaDisciplina
<c:if test="${esp }">
<input type="radio" name="validadaDisciplina" value="true">validar
</c:if>
<input <c:if test="${!esp }"> type="hidden" </c:if> <c:if test="${esp }"> type="radio" </c:if> name="validadaDisciplina" value="true">validar
<label for="nomeDisciplina">nomeDisciplina</label>
<input type="text" name="nomeDisciplina" value="${alterando.nomeDisciplina }"></br></br>
<input type="submit" value="Cadastro">
</form>
</body>
</html>