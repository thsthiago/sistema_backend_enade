package br.com.apirest.sisEnade.repository;

import br.com.apirest.sisEnade.models.Disciplina;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DisciplinaRepository extends PagingAndSortingRepository<Disciplina, Integer>, JpaSpecificationExecutor<Disciplina> {
}
