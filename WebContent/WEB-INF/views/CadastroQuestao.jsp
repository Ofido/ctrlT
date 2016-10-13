<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="https://code.google.com/p/jquery-cascade"></script>
<script type="text/javascript">
	function consultaDisciplinas() {
		// Recupera o id da ta de selects de Assunto
		var idDisciplina = document.getElementById("idDisciplina");
		// Recupera o nome do valor selecionado na lista de Disciplina
		var selectedValue = idDisciplina.options[idDisciplina.selectedIndex].value;
		// Insere a disciplina selecionada em outro form para enviá-la e compará-la no banco de dados
		var aux = document.getElementById("aux");
		aux.value = selectedValue;
		document.getElementById("botao").click();

	}

	function verifica() {

		// Recupera o id da lista de selects de Assunto
		var idAssunto = document.getElementById("idAssunto");
		// Recupera o nome do valor selecionado na lista de Assunto
		var valorSelecionadoAssunto = idAssunto.options[idAssunto.selectedIndex].value;
		// Recupera o id da lista de selects de Disciplina
		var idDisciplina = document.getElementById("idDisciplina");
		// Recupera o nome do valor selecionado na lista de Disciplina
		var valorSelecionadoDisciplina = idDisciplina.options[idDisciplina.selectedIndex].text;
		// Compara e faz a validação se a Disciplina não foi selecionada
		if (valorSelecionadoDisciplina == "Selecione uma Disciplina") {
			alert("Disciplina inválida");
			return false;
		}
		// Compara e faz a validação se o Assunto não foi selecionado
		if (valorSelecionadoAssunto == "Selecione um Assunto") {
			alert("Assunto inválido");
			return false;
		}
		return false;
	}
</script>
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
			value="${alterando.questao }"></br> </br>
		<!-- TODO MUDAR PARA TEXT AREA -->
		<label>Disciplina</label><select name="idDisciplina"
			id="idDisciplina" ><!-- onchange="consultaDisciplinas();" TODO -->
			<option value="0">Selecione uma disciplina</option>
			<c:forEach items="${disciplinas}" var="disc">
				<c:if
					test="${disc.idDisciplina == disciplinaSelecionada.disciplina.idDisciplina}">
					<option selected="selected" value="${disc.idDisciplina}">${disc.nomeDisciplina}</option>
				</c:if>
				<c:if
					test="${disc.idDisciplina != disciplinaSelecionada.disciplina.idDisciplina}">
					<option value="${disc.idDisciplina}">${disc.nomeDisciplina}</option>
				</c:if>
			</c:forEach>
		</select></br> <label>Assunto</label> <select id="idAssunto" name="idAssunto">
			<option value="0">Selecione um Assunto</option>
			<c:forEach items="${assunto}" var="a">
				<option value="${a.idAssunto}">${a.nomeAssunto}</option>
			</c:forEach>
		</select></br>
		<label>Tipo Questao:</label>
		<input type="radio" value="UNICA" name="TipoQuestao">
		<input type="radio" value="DISSERTATIVA" name="TipoQuestao"> <input
			type="radio" value="VERDADEIROFALSO" name="TipoQuestao"> <input
			type="radio" value="MULTIPLA" name="TipoQuestao">
			<br /><br />
		<label>Dificuldade da questão: </label><br />
		 <input type="radio" name="nivelQuestao" value="0" required />1
		 <input type="radio" name="nivelQuestao" value="1" required />2
		 <input type="radio" name="nivelQuestao" value="2" required />3
		 <input type="radio" name="nivelQuestao" value="3" required />4
		 <input type="radio" name="nivelQuestao" value="4" required />5
		 <input type="radio" name="nivelQuestao" value="5" required />6
		 <input type="radio" name="nivelQuestao" value="6" required />7
		 <input type="radio" name="nivelQuestao" value="7" required />8
		 <input type="radio" name="nivelQuestao" value="8" required />9
		 <input type="radio" name="nivelQuestao" value="9" required />10<br/><br /><br />
		 
		 <label>Tempo Questao: (min)</label>
		 <input type="text" name="tempo"><br/><br /><br />
		 
		 <label>Complemento:</label>
			<input type="file" name="arquivo" accept="image/*"></br></br>
			<input type="submit" value="Cadastro">
	</form>
	<form action="consultarAssunto">
		<input id="aux" type="text" name="id" hidden="true"> <input
			id="botao" type="submit" hidden="true">
	</form>
</body>
</html>