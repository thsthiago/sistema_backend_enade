package br.com.apirest.sisEnade.repository;

import br.com.apirest.sisEnade.models.Anotacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnotacaoRepository extends JpaRepository<Anotacao, Long> {

    Anotacao findById(long id);
}
