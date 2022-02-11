package dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import model.Curso;

public interface CursosDao extends JpaRepository<Curso,Integer>{
	
	@Query("select c from Curso c where c.denominacion=?1")
	Curso findByName(String name);
	//Curso findByDenominacion(String name);
	@Query("select c from Curso c where c.fechaInicio>=?1 and c.fechaInicio<=?2")
	List<Curso> findByDates(Date f1, Date f2);
	//List<Curso> findByFechaInicioBetween(Date f1, Date f2);
	@Transactional
	@Modifying
	@Query("update Curso c set c.precio=c.precio*((100-?1)/100) where c.duracion>?2")
	void updateByDuration(int percent, int duration);
	@Query("Select c from Curso c where c.duracion<?1")
	List<Curso> findByDurationMax(int duration);
	//List<Curso> findByLessThanDuration(int duration);
	@Query("select c from Curso c join c.alumnos a where a.dni=?1")
	Curso findByAlumno(String dni);
}
