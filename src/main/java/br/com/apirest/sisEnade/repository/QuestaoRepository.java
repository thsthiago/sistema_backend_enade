package br.com.apirest.sisEnade.repository;

import br.com.apirest.sisEnade.model.Questao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestaoRepository extends JpaRepository<Questao, Long> {
}
