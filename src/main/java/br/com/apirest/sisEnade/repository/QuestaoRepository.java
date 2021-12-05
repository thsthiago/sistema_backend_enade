package br.com.apirest.sisEnade.repository;

import br.com.apirest.sisEnade.models.Questao;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface QuestaoRepository extends PagingAndSortingRepository<Questao, Integer>, JpaSpecificationExecutor<Questao> {
}
