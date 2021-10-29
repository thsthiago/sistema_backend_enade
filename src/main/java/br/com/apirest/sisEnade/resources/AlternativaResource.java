package br.com.apirest.sisEnade.resources;

import br.com.apirest.sisEnade.models.Alternativa;
import br.com.apirest.sisEnade.repository.AlternativaRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
@Api(value = "API REST Alternativas")
@CrossOrigin(origins = "*")
public class AlternativaResource {

    @Autowired
    AlternativaRepository alternativaRepository;



    @GetMapping("/alternativas")
    @ApiOperation(value = "Lista todas as Alternativas")
    public List<Alternativa> listaAlternativa(){
        return alternativaRepository.findAll();
    }

    @GetMapping("/alternativa/{id}")
    @ApiOperation(value = "Lista uma unica Alternativa")
    public Alternativa listaAlternativaUnica(@PathVariable("id") long id){
        return alternativaRepository.findById(id);

    }

    @PostMapping("/alternativa")
    @ApiOperation(value = "Salva uma Alternativa")
    public Alternativa salvaAlternativa(@RequestBody Alternativa alternativa){
        return alternativaRepository.save(alternativa);
    }

    @DeleteMapping("/alternativa")
    @ApiOperation(value = "Delata uma Alternativa")
    public void deletarAlternativa(@RequestBody Alternativa alternativa){
        alternativaRepository.delete(alternativa);
    }

    @PutMapping("alternativa")
    @ApiOperation(value = "Atualiza uma Alternativa")
    public Alternativa atualizarAlternativa(@RequestBody Alternativa alternativa){
        return alternativaRepository.save(alternativa);
    }
}
