package br.com.apirest.sisEnade.repository;

import br.com.apirest.sisEnade.models.Curso;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepository extends PagingAndSortingRepository<Curso, Integer>, JpaSpecificationExecutor<Curso> {
}
