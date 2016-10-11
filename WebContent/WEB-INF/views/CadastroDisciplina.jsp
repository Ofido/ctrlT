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
<form action="CadastroDisciplina" method="post">
<input type="hidden" name="idDisciplina" value="${alterando.idDisciplina }">
<label for="nomeDisciplina">nomeDisciplina</label>
<input type="text" name="nomeDisciplina" value="${alterando.nomeDisciplina }"></br></br>
<input type="submit" value="Cadastro">
</form>
</body>
</html>