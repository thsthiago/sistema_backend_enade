package br.com.apirest.sisEnade.repository;

import br.com.apirest.sisEnade.model.Anotacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnotacaoRepository extends JpaRepository<Anotacao, Long> {
}
