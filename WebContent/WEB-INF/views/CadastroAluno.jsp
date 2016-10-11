<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastro de Aluno</title>
</head>
<body>
<form action="CadastroAluno" enctype="multipart/form-data" method="post">
<input type="hidden" name="idAluno" value="${alterando.idAluno }">
<label for="nomeAluno">nomeAluno</label>
<input type="text" name="nomeAluno" value="${alterando.nomeAluno }"></br></br>
<label for="emailAluno">emailAluno</label>
<input type="text" name="emailAluno" value="${alterando.emailAluno }"></br></br>
<label for="senhaAluno">senhaAluno</label>
<input type="text" name="senhaAluno" value="${alterando.senhaAluno }"></br></br>
<label for="telefoneAluno">telefoneAluno</label>
<input type="text" name="telefoneAluno" value="${alterando.telefoneAluno }"></br></br>
<label for="cpfAluno">cpfAluno</label>
<input type="text" name="cpfAluno" value="${alterando.cpfAluno }"></br></br>
<label for="cepAluno">cepAluno</label>
<input type="text" name="cepAluno" value="${alterando.cepAluno }"></br></br>
<label for="estadoAluno">estadoAluno</label>
<input type="text" name="estadoAluno" value="${alterando.estadoAluno }"></br></br>
<label for="cidadeAluno">cidadeAluno</label>
<input type="text" name="cidadeAluno" value="${alterando.cidadeAluno }"></br></br>
<label for="bairroAluno">bairroAluno</label>
<input type="text" name="bairroAluno" value="${alterando.bairroAluno }"></br></br>
<label for="ruaAluno">ruaAluno</label>
<input type="text" name="ruaAluno" value="${alterando.ruaAluno }"></br></br>
<label for="numeroAluno">numeroAluno</label>
<input type="text" name="numeroAluno" value="${alterando.numeroAluno }"></br></br>
<label for="complementoAluno">complementoAluno</label>
<input type="text" name="complementoAluno" value="${alterando.complementoAluno }"></br></br>
<label for="aniversarioAluno">nascimentoAluno</label>
<input type="Date" name="aniversarioAluno" value="${alterando.aniversarioAluno }"></br></br>
<label for="fotoAluno">foto :</label>
<input type="file" name="arquivo" accept="image/jpeg"></br></br>
<input type="submit" value="Cadastro">
</form>
</body>
</html>