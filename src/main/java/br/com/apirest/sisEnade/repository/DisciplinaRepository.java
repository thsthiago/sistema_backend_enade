package br.com.apirest.sisEnade.repository;

import br.com.apirest.sisEnade.model.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {
}
