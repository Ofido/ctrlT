<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<!-- <script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="https://code.google.com/p/jquery-cascade"></script> -->
<script type="text/javascript">
	/* FAZ A CONSULTA DOS ASSUNTOS QUE PERTENCEM A CADA DISCIPLINA */
	function consultaAssuntos(assuntosIds) {
		var selectBoxDisciplina = document.getElementById("selectBoxDisciplina");
		var selectBoxAssunto = document.getElementById("selectBoxAssunto");
		var selectBoxAuxiliar;
		var increment = 0;
		
		//alert(selectBoxDisciplina.options[selectBoxDisciplina.selectedIndex].text + "com ID de : " + selectBoxDisciplina.options[selectBoxDisciplina.selectedIndex].value);
		//alert(assuntosIds);

		/* ADICIONAR ELEMENTOS */
		  for (i = 0; i < selectBoxAssunto.length; i++) {
			  //alert("Id da Disciplina: " + selectBoxDisciplina.options[selectBoxDisciplina.selectedIndex].value + "Id do AssuntoDisciplina: " + assuntosIds[i-1] + "Id da Assunto: " + selectBoxAssunto.options[i].value);
				
			  if (selectBoxAssunto.options[i].value ==  'Selecione um Assunto'){
				
			     } else {
			    	
			if (selectBoxDisciplina.options[selectBoxDisciplina.selectedIndex].value == assuntosIds[i-1]) {
				//alert("entrou no if true "+selectBoxAssunto.options[i].value);
				selectBoxAssunto.options[i].style="display: inline;"
			}else{
				//alert("entrou no if false "+selectBoxAssunto.options[i].value);
				selectBoxAssunto.options[i].style="display: none;"
			}
			}

		} 
		increment = null;
	}

	function teste(){
		var selectBoxAssunto = document.getElementById("selectBoxAssunto");
		selectBoxAssunto.options[0].selected="selected";
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
			for="questao"> Enunciado:</label> <input type="text" name="questao"
			value="${alterando.questao }"></br> </br>
		<!-- TODO MUDAR PARA TEXT AREA -->
		<label>Disciplina</label><select name="idDisciplina"
			id="selectBoxDisciplina"
			onchange="consultaAssuntos(${assunto});teste();">
			<option value="0">Selecione uma disciplina</option>
			<c:if test="${alterando.disciplinaQuestao != null}">
				<option selected="selected"
					value="${alterando.disciplinaQuestao.idDisciplina}">${alterando.disciplinaQuestao.nomeDisciplina}</option>
			</c:if>
			<c:forEach items="${disciplinas}" var="disc">
				<option value="${disc.idDisciplina}">${disc.nomeDisciplina}</option>
			</c:forEach>
		</select></br> <label>Assunto</label> <select id="selectBoxAssunto" name="idAssunto">
			<option value="0">Selecione um Assunto</option>
			<c:if test="${alterando.assuntoQuestao != null}">
				<option selected="selected"
					value="${alterando.assuntoQuestao.idAssunto}">${alterando.assuntoQuestao.nomeAssunto}</option>
			</c:if>
			<c:forEach items="${assunto}" var="a">
				<option value="${a.idAssunto}" style="display: none">${a.nomeAssunto}</option>
			</c:forEach>
		</select></br> <label>Tipo Questao:</label> <input type="radio" value="UNICA"
			name="TipoQuestao" checked="checked">UNICA <input
			type="radio" value="DISSERTATIVA" name="TipoQuestao"
			<c:if test="alterando.tipoQuestao">checked="checked"</c:if>>DISSERTATIVA
		<input type="radio" value="VERDADEIROFALSO" name="TipoQuestao">VERDADEIROFALSO
		<input type="radio" value="MULTIPLA" name="TipoQuestao">MULTIPLA
		<br /> <br /> <label>Dificuldade da questão: </label><br /> <input
			type="radio" name="nivelQuestao" value="0" />1 <input type="radio"
			name="nivelQuestao" value="1" />2 <input type="radio"
			name="nivelQuestao" value="2" />3 <input type="radio"
			name="nivelQuestao" value="3" />4 <input type="radio"
			name="nivelQuestao" value="4" />5 <input type="radio"
			name="nivelQuestao" value="5" />6 <input type="radio"
			name="nivelQuestao" value="6" />7 <input type="radio"
			name="nivelQuestao" value="7" />8 <input type="radio"
			name="nivelQuestao" value="8" />9 <input type="radio"
			name="nivelQuestao" value="9" />10<br /> <br /> <br /> <label>Tempo
			Questao: (min)</label> <input type="text" name="tempo" value="${alterando.tempoQuestao}"><br /> <br />
		<br /> <label>Complemento:</label> <input type="file" name="arquivo"
			accept="image/*"></br> </br> <input type="hidden" id="tipo"
			name="tipoCadastro"> <input type="submit" value="Continuar">
	</form>
</body>
</html>