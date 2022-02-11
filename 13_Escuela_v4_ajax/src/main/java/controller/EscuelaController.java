package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import model.Alumno;
import service.AlumnosService;
import service.CursosService;

@Controller
public class EscuelaController {
	
	@Autowired
	AlumnosService serviceAlumnos;
	@Autowired
	CursosService serviceCursos;
	
	@PostMapping("altaAlumno") //gestiona la pulsación del botón de alta
	public String altaAlumno(@ModelAttribute("alumno") Alumno alumno, @RequestParam("idCurso") int idCurso) {
		serviceAlumnos.altaAlumno(alumno, idCurso);
		return "inicio";
	}
	
	// 1 - *** Gestiona petición AJAX.
	/* La biblioteca Jackson gestiona y traduce la List<Alumno> en un JSON. 
	 * AJAX introduce en el body la respuesta List<Alumno>. */
	@GetMapping(value="buscarAlumnos",produces=MediaType.APPLICATION_JSON_VALUE) //gestiona la pulsación del botón de búsqueda
	public @ResponseBody List<Alumno> alumnosCurso(@RequestParam("idCurso") int idCurso, HttpServletRequest request) {
		/*request.setAttribute("alumnos",serviceAlumnos.alumnosCurso(idCurso));
		return "forward:/paginaBusqueda";*/
		return serviceAlumnos.alumnosCurso(idCurso);
	}
	@GetMapping("paginaBusqueda") //pulsación del enlace a página de busqueda
	public String paginaBusqueda(HttpServletRequest request) {
		request.setAttribute("cursos", serviceCursos.listaCursos());
		return "seleccionAlumnos";
	}
	@GetMapping("paginaAlta") //pulsación del enlace a página de alta
	public String paginaAta(HttpServletRequest request) {
		request.setAttribute("cursos", serviceCursos.listaCursos());
		return "altaAlumnos";
	}
}
