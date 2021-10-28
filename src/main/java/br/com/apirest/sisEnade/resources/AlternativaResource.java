package br.com.apirest.sisEnade.resources;

import br.com.apirest.sisEnade.model.Alternativa;
import br.com.apirest.sisEnade.repository.AlternativaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class AlternativaResource {

    @Autowired
    AlternativaRepository alternativaRepository;

    @GetMapping("/Alternativas")
    public List<Alternativa> listaAlternativa(){
        return alternativaRepository.findAll();
    }
}
