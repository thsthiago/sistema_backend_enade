package br.com.apirest.sisEnade.resources;

import br.com.apirest.sisEnade.models.Questao;
import br.com.apirest.sisEnade.repository.QuestaoRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
@Api(value = "API REST Questoes")
@CrossOrigin(origins = "*")
public class QuestaoResource {


    @Autowired
    QuestaoRepository questaoRepository;

    @GetMapping("/questoes")
    @ApiOperation(value = "Lista todas as Questoes")
    public List<Questao> listarQuestao(){
        return questaoRepository.findAll();
    }

    @GetMapping("/questao/{id}")
    @ApiOperation(value = "Lista uma unica Questao")
    public Questao listaQuestaoUnica(@PathVariable(value = "id") long id){
        return questaoRepository.findById(id);
    }

    @PostMapping("/questao")
    @ApiOperation(value = "Salva uma Questao")
    public Questao salvaQuestao(@RequestBody Questao questao){
        return questaoRepository.save(questao);
    }

    @DeleteMapping("/questao")
    @ApiOperation(value = "Delata uma Questao")
    public void deletarQuestao(@RequestBody Questao questao){
        questaoRepository.delete(questao);
    }

    @PutMapping("/questao")
    @ApiOperation(value = "Atualiza uma Questao")
    public Questao altualizarQuestao(@RequestBody Questao questao){
        return questaoRepository.save(questao);
    }
}
