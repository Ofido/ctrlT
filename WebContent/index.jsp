<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>OLÁ MUNDO</title>
</head>
<body>
	<form action="efetuaLogin">
		<input type="text" name="email" value="a@a.com"><input
			type="text" name="senha" value="1"> <input type="submit"
			value="LoginAluno">
	</form>
	<form action="efetuaLogin">
		<input type="text" name="email" value="1@1"><input type="text"
			name="senha" value="1"> <input type="submit" value="LoginAdm">
	</form>
	<form action="efetuaLogin">
		<input type="text" name="email" value="p@p.com"><input
			type="text" name="senha" value="1234"> <input type="submit"
			value="LoginProf">
	</form>
	<form action="efetuaLogin">
		<input type="text" name="email" value="e@e.com"><input
			type="text" name="senha" value="1"> <input type="submit"
			value="LoginEspecialista">
	</form>
	<p>
		<a href="CadastrandoProfessor"> auto cadastro prof </a>
	</p>
	<p>
		<a href="ListandoProfessor"> listar </a>
	</p>
	<p>
		<a href="CadastroAluno"> auto cadastro </a>
	</p>
</body>
</html>