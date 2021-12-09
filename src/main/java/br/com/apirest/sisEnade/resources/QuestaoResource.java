package br.com.apirest.sisEnade.resources;

import br.com.apirest.sisEnade.models.Questao;
import br.com.apirest.sisEnade.models.utils.PagingHeaders;
import br.com.apirest.sisEnade.models.utils.PagingResponseQuestao;
import net.kaczmarzyk.spring.data.jpa.web.annotation.*;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import br.com.apirest.sisEnade.services.QuestaoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.In;
import net.kaczmarzyk.spring.data.jpa.domain.Like;

@RestController
@RequestMapping(value = "/api")
@Api(value = "API REST Questoes")
@CrossOrigin(origins = "*")
public class QuestaoResource {

    private final QuestaoService questaoService;

    @Autowired
    public QuestaoResource(QuestaoService questaoService) {
        this.questaoService = questaoService;
    }

    @Transactional
    @GetMapping(value = "/questoes", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Lista todas as Questoes")
    public PagingResponseQuestao listarQuestao(
            @Join(path = "disciplina", alias = "disci")
            @Disjunction(value = {
                @And({
                    @Spec(path = "disci.nome", params = "search", spec = Like.class),
                    @Spec(path = "curso.id", params = "teste", spec = In.class),
                    @Spec(path = "id", params = "id", spec = In.class),
                    @Spec(path = "edicao", params = "edicao", spec = Equal.class),
                    @Spec(path = "numQuestao", params = "numeroQuestao", spec = Equal.class),
                    @Spec(path = "tipoQuestao", params = "tipoQuestao", spec = Like.class),
                    @Spec(path = "createdAt", params = "createdAt", spec = In.class),
                    @Spec(path = "updatedAt", params = "updatedAt", spec = Like.class),
                    @Spec(path = "createdAt", params = {"createdAtGt", "createdAtLt"}, spec = Equal.class),
                    @Spec(path = "updatedAt", params = {"updatedAtGt", "updatedAtLt"}, spec = Equal.class),
                    @Spec(path = "disci.id", params = "disciplina", spec = In.class)
                })
            }) Specification<Questao> spec, Sort sort, @RequestHeader HttpHeaders headers){
        final PagingResponseQuestao response = questaoService.get(spec, headers, sort);
        return  response;
    }

    @Transactional
    @GetMapping("/questao/{id}")
    @ApiOperation(value = "Lista uma unica Questao")
    public Questao listaQuestaoUnica(@PathVariable(value = "id") Integer id){
        return questaoService.get(id);
    }

    @Transactional
    @PostMapping("/questao")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Salva uma Questao")
    public Questao salvaQuestao(@RequestBody Questao questao){
        return questaoService.create(questao);
    }

    @Transactional
    @DeleteMapping("/questao/{id}")
    @ApiOperation(value = "Delata uma Questao")
    public void deletarQuestao(@PathVariable(value = "id") Integer id){
        questaoService.delete(id);
    }

    @Transactional
    @PutMapping("/questao/{id}")
    @ApiOperation(value = "Atualiza uma Questao")
    public Questao altualizarQuestao(@PathVariable(name="id") Integer id, @RequestBody Questao questao){
        return questaoService.update(id, questao);
    }

    public HttpHeaders returnHttpHeaders(PagingResponseQuestao response) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(PagingHeaders.COUNT.getName(), String.valueOf(response.getCount()));
        headers.set(PagingHeaders.PAGE_SIZE.getName(), String.valueOf(response.getPageSize()));
        headers.set(PagingHeaders.PAGE_OFFSET.getName(), String.valueOf(response.getPageOffset()));
        headers.set(PagingHeaders.PAGE_NUMBER.getName(), String.valueOf(response.getPageNumber()));
        headers.set(PagingHeaders.PAGE_TOTAL.getName(), String.valueOf(response.getPageTotal()));
        return headers;
    }
}
