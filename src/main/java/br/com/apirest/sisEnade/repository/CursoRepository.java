package br.com.apirest.sisEnade.repository;

import br.com.apirest.sisEnade.models.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {

    Curso findById(long id);
}
