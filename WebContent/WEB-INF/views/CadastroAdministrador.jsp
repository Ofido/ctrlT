<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastro de Administrador</title>
</head>
<body>
<form action="CadastroAdministrador" method="post">
<input type="hidden" name="idAdm" value="${alterando.idAdm}">
<label for="nomeAdm">Nome Administrador</label>
<input type="text" name="nomeAdm" value="${alterando.nomeAdm}"></br></br>
<label for="emailAdm">Email Administrador</label>
<input type="text" name="emailAdm" value="${alterando.emailAdm}"></br></br>
<label for="senhaAdm">Senha Administrador</label>
<input type="text" name="senhaAdm" value="${alterando.senhaAdm}"></br></br>
<label for="telefoneAdm">Telefone Adminisrador</label>
<input type="text" name="telefoneAdm" value="${alterando.telefoneAdm}"></br></br>
<label for="cpfProfessor">CPF Administrador</label>
<input type="text" name="cpfProfessor" value="${alterando.cpfProfessor }"></br></br>
<input type="submit" value="Cadastro">
</form>
</body>
</html>