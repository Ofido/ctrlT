<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Formulário Respostas</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script>
	$(document)
			.ready(
					function() {
						var max_fields = 1000; //maximum input boxes allowed
						var wrapper = $(".input_fields_wrap"); //Fields wrapper
						var wrapperSubmit = $(".wrapperSubmit"); //Fields wrapper
						var add_button = $(".add_field_button"); //Add button ID
						var enviar = $(".enviar");

						var x = 1; //initlal text box count
						$(add_button)
								.click(
										function(e) { //on add input button click
											e.preventDefault();
											var length = wrapper
													.find("input:text").length;

											if (x < max_fields) { //max input box allowed
												x++; //text box increment
												$(wrapper)
														.append(
																'<div><input type="text" id="Texto1" name="textoAlternativa" placeholder="alternativa incorreta"/><a href="#" class="excluir">X</a></div>'); //add input box
												//document.getElementById('Texto1').value = $('#Texto1').val();

											}

										});

						$(wrapperSubmit).on("click", ".salvar_todos",
								function(e) { //click para salvar todos
									verificarSelecionados();
									$("#target").submit()
									// e.preventDefault();
									// $(this).parent('div').remove();
									x--;
								})

						$(wrapper).on("click", ".excluir", function(e) { //click para salvar todos
							e.preventDefault();
							$(this).parent('div').remove();
							x--;
						})

						// $( "#target" ).submit(function( event ) {
						//	 var form_values =  $( this ).serialize();  MÉTODO PARA FAZER O SPLIT AUTOMATICO
						//	 alert(form_values);
						//	 return false;
						//	});

					});

	function verificarSelecionados() {
		var input = document.getElementById("Texto1").value;
		return false;
	}
</script>
</head>
<body>
	<form id="target" action="respostasAdiciona">
		<input type="hidden" name="idQuestao" value="${questao }">
		<div class="input_fields_wrap">
			<div>

				<!-- PARAMOS AQUI (Texto1), necessita fazer salvar tudo em uma string separado por vírgula  -->
				<input type="text" name="textoAlternativa"
					placeholder="Alternativa Correta">
				<button class="add_field_button">+</button>
			</div>
		</div>
		<div class="wrapperSubmit">
			<a href="#" class="salvar_todos">Enviar</a>
		</div>
	</form>
</body>
</html>