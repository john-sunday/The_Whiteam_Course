<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@taglib uri="http://java.sun.com/jsp/jstl/core"   prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<script>
   function buscarAlumnos(){
       let url="buscarAlumnos?idCurso="+$("#idCurso").val();
        $.get(url,function(data,status){
            let tabla="<table border='1' class='table table-striped'>";
            tabla+="<thead><tr><th>Nombre</th><th>Email</th><th>Edad</th></tr></thead><tbody>";
            for(let al of data){
                tabla+="<tr><td>"+al.nombre+"</td>";
                tabla+="<td>"+al.email+"</td>";
                tabla+="<td>"+al.edad+"</td></tr>";
            }
        tabla+="</tbody></table>";
        $("#tablaAlumnos").html(tabla);
        });
    }

</script>

</head>
<body>
	<div class="container">
			Seleccione curso:
			<select name="idCurso" id="idCurso">
			<c:forEach var="curso" items="${requestScope.cursos}">
				<option value="${curso.idCurso}">${curso.denominacion}</option>
			</c:forEach>
			<br>
			<input type="button" onClick="buscarAlumnos();" value="Buscar"><br><br>
			<!-- Eliminamos la tabla anterior y generamos esta otra que 
				va a generar el Front -->
			<div id="tablaAlumnos">
				
			</div>
		<br><br>
		<a href="toInicio">Volver</a>
		</form>
	</div>
</body>
</html>
