package br.com.apirest.sisEnade.repository;

import br.com.apirest.sisEnade.models.Alternativa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlternativaRepository extends JpaRepository<Alternativa, Long> {

    Alternativa findById(long id);
}
