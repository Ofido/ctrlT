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
	
	function checkDificult(aqui) {
		switch (aqui) {
		case 0:
			document.getElementById("nivelQuestao1").checked="checked";
			break;
		case 1:
			document.getElementById("nivelQuestao2").checked="checked";
			break;
		case 2:
			document.getElementById("nivelQuestao3").checked="checked";
			break;
		case 3:
			document.getElementById("nivelQuestao4").checked="checked";
			break;
		case 4:
			document.getElementById("nivelQuestao5").checked="checked";
			break;
		case 5:
			document.getElementById("nivelQuestao6").checked="checked";
			break;
		case 6:
			document.getElementById("nivelQuestao7").checked="checked";
			break;
		case 7:
			document.getElementById("nivelQuestao8").checked="checked";
			break;
		case 8:
			document.getElementById("nivelQuestao9").checked="checked";
			break;
		case 9:
			document.getElementById("nivelQuestao10").checked="checked";
			break;	
		default:
			document.getElementById("nivelQuestao1").checked="checked";
			break;
		}
	}
</script>
</head>
<body onload="checkDificult(${alterando.nivelQuestao });">
	<form action="CadastroQuestao" enctype="multipart/form-data"
		method="post">
		<input type="hidden" name="idQuestao" value="${alterando.idQuestao }">
		<input type="text" name="idProfessor" value="${alterando.criadorQuestao.idProfessor }" disabled="disabled">
		<input
			type="hidden" name="idEspecialista"
			value="${alterando.validadorQuestao.idEspecialista }"> <label
			for="questao"> Enunciado:</label> <input type="text" name="questao"
			value="${alterando.questao }" disabled="disabled"></br> </br>
		<!-- TODO MUDAR PARA TEXT AREA -->
		<label>Disciplina</label><select name="idDisciplina"
			id="selectBoxDisciplina" disabled="disabled"
			onchange="consultaAssuntos(${assunto});teste();">
			<option value="0">Selecione uma disciplina</option>
			<c:if test="${alterando.disciplinaQuestao != null}">
				<option selected="selected"
					value="${alterando.disciplinaQuestao.idDisciplina}">${alterando.disciplinaQuestao.nomeDisciplina}</option>
			</c:if>
			<c:forEach items="${disciplinas}" var="disc">
				<option value="${disc.idDisciplina}">${disc.nomeDisciplina}</option>
			</c:forEach>
		</select></br> <label>Assunto</label> <select id="selectBoxAssunto" name="idAssunto" disabled="disabled">
			<option value="0">Selecione um Assunto</option>
			<c:if test="${alterando.assuntoQuestao != null}">
				<option selected="selected"
					value="${alterando.assuntoQuestao.idAssunto}">${alterando.assuntoQuestao.nomeAssunto}</option>
			</c:if>
			<c:forEach items="${assunto}" var="a">
				<option value="${a.idAssunto}" style="display: none">${a.nomeAssunto}</option>
			</c:forEach>
		</select></br> <label>Tipo Questao:</label> <input type="radio" value="UNICA"
			name="TipoQuestao" checked="checked" disabled="disabled">UNICA <input
			type="radio" value="DISSERTATIVA" name="TipoQuestao"
			<c:if test="${alterando.tipoQuestao == DISSERTATIVA}">checked="checked"</c:if> disabled="disabled">DISSERTATIVA
		<input type="radio" value="VERDADEIROFALSO" name="TipoQuestao"
			<c:if test="${alterando.tipoQuestao == VERDADEIROFALSO}">checked="checked"</c:if> disabled="disabled">VERDADEIROFALSO
		<input type="radio" value="MULTIPLA" name="TipoQuestao"
			<c:if test="${alterando.tipoQuestao == MULTIPLA}">checked="checked"</c:if> disabled="disabled">MULTIPLA
		<br /> <br /> <label>Dificuldade da questão: </label><br /> <input
			type="radio" name="nivelQuestao" value="0" id="nivelQuestao1"disabled="disabled"/>1 <input type="radio"
			name="nivelQuestao" value="1" id="nivelQuestao2"disabled="disabled"/>2 <input type="radio"
			name="nivelQuestao" value="2" id="nivelQuestao3"disabled="disabled"/>3 <input type="radio"
			name="nivelQuestao" value="3" id="nivelQuestao4"disabled="disabled"/>4 <input type="radio"
			name="nivelQuestao" value="4" id="nivelQuestao5"disabled="disabled"/>5 <input type="radio"
			name="nivelQuestao" value="5" id="nivelQuestao6"disabled="disabled"/>6 <input type="radio"
			name="nivelQuestao" value="6" id="nivelQuestao7"disabled="disabled"/>7 <input type="radio"
			name="nivelQuestao" value="7" id="nivelQuestao8"disabled="disabled"/>8 <input type="radio"
			name="nivelQuestao" value="8" id="nivelQuestao9"disabled="disabled"/>9 <input type="radio"
			name="nivelQuestao" value="9" id="nivelQuestao10"disabled="disabled"/>10<br /> <br /> <br /> <label>Tempo
			Questao: (min)</label> <input type="text" name="tempo"
			value="${alterando.tempoQuestao}"disabled="disabled"><br /> <br /> <br /> <label>Complemento:</label>
			<img src="data:image/*;base64, ${alterando.foto64 }"> </br> </br>
			<textarea cols="100" rows="100" name="comentario">${alterando.comentario}</textarea>
			<input
			type="hidden" id="tipo" name="tipoCadastro"> <input
			type="submit" value="Validar">
	</form>
</body>
</html>