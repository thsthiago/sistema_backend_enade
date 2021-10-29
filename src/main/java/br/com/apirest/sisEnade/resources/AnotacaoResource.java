package br.com.apirest.sisEnade.resources;

import br.com.apirest.sisEnade.models.Anotacao;
import br.com.apirest.sisEnade.repository.AnotacaoRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Api(value = "API REST Anotações")
@CrossOrigin(origins = "*")
public class AnotacaoResource {

    @Autowired
    AnotacaoRepository anotacaoRepository;


    @GetMapping("/anotacoes")
    @ApiOperation(value = "Lista todas as Anotacoes")
    public List<Anotacao> listaAnotacao(){
        return anotacaoRepository.findAll();
    }

    @GetMapping("/anotacao/{id}")
    @ApiOperation(value = "Lista uma unica Anotacao")
    public Anotacao listaAnotacaoUnica(@PathVariable("id") long id){
        return anotacaoRepository.findById(id);
    }

    @PostMapping("/anotacao")
    @ApiOperation(value = "Salva uma Anotacao")
    public Anotacao salvaAnotacao(@RequestBody Anotacao anotacao){
        return anotacaoRepository.save(anotacao);
    }

    @DeleteMapping("/anotacao")
    @ApiOperation(value = "Delata uma Anotacao")
    public void deletaAnotacao(@RequestBody Anotacao anotacao){
        anotacaoRepository.delete(anotacao);
    }

    @PutMapping("/anotacao")
    @ApiOperation(value = "Atualiza uma Anotacao")
    public Anotacao atualizaAnotacao(@RequestBody Anotacao anotacao){
        return anotacaoRepository.save(anotacao);
    }


}
