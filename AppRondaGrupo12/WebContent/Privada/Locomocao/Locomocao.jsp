<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">

</head>
<body>
	<jsp:include page="../fragmentos/Cabecalho.jsp"></jsp:include>
	
		<script type="text/javascript">
	    function confirmar(excluir){
	    	$.confirm({
	    	    title: 'Confirmar',
	    	    content: 'Confirmar a exclus�o?',
	    	    type: 'orange',
	    	    typeAnimated: true,
	    	    buttons: {
	    	        Sim: { 
	    	        	text: 'Sim',
	    	            btnClass: 'btn-orange',
	    	            action: function(){ 
	    	            	$(excluir).attr("type", "submit");
	    	            	excluir.click();
	    	        	}
	    	        },
	    	        Nao: { 
	    	        	text: 'N�o',
	    	            btnClass: 'btn-dark',
	    	            action: function(){ 
	    	            	// programar algo se clicou n�o
	    	        	}
	    	        },
	    	    }
	    	});
	    	
	    	
	    }
	</script>
	
	
	<h1>LISTAGEM DE LOCOMO��O</h1>
	
	<form action="LocomocaoCon">
		
		<button type="submit" name="voltar"><i class="fas fa-plus-circle"></i> Voltar</button>
		<br>
		
		<select name="locomocao">
		    <c:forEach items="${pessoas}" var="p" varStatus="cont">
		       <option value="${p.id}">${p.nome}</option>
		    </c:forEach>
		</select>
		
		<button type="submit" name="incluirLocomocao"><i class="fas fa-plus-circle"></i> Incluir</button>
		
		<input type="hidden" name="idLocomocao" value="${obj.id}">
		
		
		<table border="1" class="table table-hover table-condensed">
		    <thead>
		       <tr>
		           <td>Id</td>
		           <td>Descri��o</td>
		           <td>Placa</td>
		       </tr>
		    </thead>
			<c:forEach items="${obj.locomocao}" var="p" varStatus="cont">
			   <tr>
			      <td>${p.id}</td>
			      <td>${p.Descri��o}</td> 
			      <td>${p.Placa}</td>    
			      <td><button type="button" onclick="confirmar(this)" name="excluirLocomocao" value="${p.id}">Excluir</button></td>
			   </tr>
		    </c:forEach>
		</table>
	</form>

</body>
</html>