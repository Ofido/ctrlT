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
<form action="CadastroProfessor" enctype="multipart/form-data" method="post">
<input type="hidden" name="idProfessor" value="${alterando.idProfessor }">
<label for="nomeProfessor">nomeProfessor</label>
<input type="text" name="nomeProfessor" value="${alterando.nomeProfessor }"></br></br>
<label for="emailProfessor">emailProfessor</label>
<input type="text" name="emailProfessor" value="${alterando.emailProfessor }"></br></br>
<label for="senhaProfessor">senhaProfessor</label>
<input type="text" name="senhaProfessor" value="${alterando.senhaProfessor }"></br></br>
<label for="telefoneProfessor">telefoneProfessor</label>
<input type="text" name="telefoneProfessor" value="${alterando.telefoneProfessor }"></br></br>
<label for="cpfProfessor">cpfProfessor</label>
<input type="text" name="cpfProfessor" value="${alterando.cpfProfessor }"></br></br>
<label for="escolaProfessor">escolaProfessor</label>
<input type="text" name="escolaProfessor" value="${alterando.escolaProfessor }"></br></br>
<label for="tagProfessor">tagProfessor</label>
<input type="text" name="tagProfessor" value="${alterando.tagProfessor }"></br></br>
<label for="licenca">licenca</label>
<input type="radio" name="licenca" value="true" <c:if test="${alterando.licenca }">checked="checked"</c:if>>
<input type="radio" name="licenca" value="false" <c:if test="!${alterando.licenca }">checked="checked"</c:if>>
<label for="fotoProfessor">foto :</label>
<input type="file" name="arquivo" accept="image/jpeg"></br></br>
<input type="submit" value="Cadastro">
</form>
</body>
</html>