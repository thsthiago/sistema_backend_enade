package br.com.apirest.sisEnade.repository;

import br.com.apirest.sisEnade.models.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {

    Disciplina  findById(long id);
}
