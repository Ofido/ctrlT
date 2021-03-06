<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Lista de Assuntos</title>
</head>
<script type="text/javascript">
	function salvar(aq) {
		document.getElementById("a"+aq).click();
	}
	function salvar2(aq) {
		document.getElementById("b"+aq).click();		
	}
</script>
<body>
	<table border="2" bordercolor="RED">
		<tr>
			<th>ID</th>
			<c:if test="!${tipoQuestao == 'DISSERTATIVA'}">
				<th>CORRETA</th> 
			</c:if>
			<th>RESPOSTA</th>
			<th>COMPLEMENTO</th>
			<th>ADD COMPLEMENTO</th>
			<th>ALTERA</th>
			<th>EXCLUI</th>
		</tr>
		<c:forEach items="${lista }" var="p">
			<tr>
				<td>${p.idResposta }</td>
				<c:if test="!${tipoQuestao == 'DISSERTATIVA'}">
					<td><c:if test="${tipoQuestao == 'MULTIPLA'}">
							<form action="CadastroResposta" enctype="multipart/form-data"
								method="post">
								<input type="hidden" name="idQuestao" value="${questao }">
								<input type="hidden" name="idResposta" value="${p.idResposta }">
								<input type="checkbox" name="corretaResposta"
									onclick="salvar(${p.idResposta })"
									<c:if test="${p.corretaResposta }">checked="checked"</c:if>>
								<input type="hidden" name="alterandoCorreta" value="true">
								<input type="submit" id="a${p.idResposta }"
									style="display: none;">
							</form>
						</c:if> <c:if test="${tipoQuestao == 'VERDADEIROFALSO'}">	
							<form action="CadastroResposta" enctype="multipart/form-data"
								method="post">
								<input type="hidden" name="idQuestao" value="${questao }">
								<input type="hidden" name="idResposta" value="${p.idResposta }">
								<input type="checkbox" name="corretaResposta"
									onclick="salvar(${p.idResposta })"
									<c:if test="${p.corretaResposta }">checked="checked"</c:if>>
								<input type="hidden" name="alterandoCorreta" value="true">
								<input type="submit" id="a${p.idResposta }"
									style="display: none;">
							</form>
						</c:if> <c:if test="${tipoQuestao == 'UNICA'}">
				${p.corretaResposta}
			</c:if></td>
				</c:if>
				<td>${p.resposta}</td>
				<td><img src="data:image/*;base64, ${p.foto64 }"></td>
				<td>
					<form action="CadastroResposta" enctype="multipart/form-data"
						method="post">
						<input type="hidden" name="idQuestao" value="${questao }">
						<input type="hidden" name="idResposta" value="${p.idResposta }">
						<input type="file" onchange="salvar2(${p.idResposta })"
							name="arquivo" accept="image/*"> <input type="hidden"
							name="alterandoImg" value="true"> <input type="submit"
							id="b${p.idResposta }" style="display: none;">
					</form>
				</td>
				<td><a
					href="ExcluirResposta?id=${p.idResposta }&idQuestao=${questao }">Excluir</a>
				</td>
				<td><a href="CadastrandoResposta?id=${p.idResposta }">Alterar</a>
				</td>
			</tr>
		</c:forEach>
	</table>
	<a href="prof"><input type="submit" value="concluido >>>"></a>
</body>
</html>