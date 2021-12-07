package br.com.apirest.sisEnade.resources;

import br.com.apirest.sisEnade.models.Questao;
import br.com.apirest.sisEnade.models.utils.PagingHeaders;
import br.com.apirest.sisEnade.models.utils.PagingResponseQuestao;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import br.com.apirest.sisEnade.services.QuestaoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.In;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;

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
    @GetMapping("/questoes")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Lista todas as Questoes")
    public PagingResponseQuestao listarQuestao(
        @And({
            @Spec(path = "id", params = "id", spec = Like.class),
            @Spec(path = "nome", params = "nome", spec = Like.class),
            @Spec(path = "createdAt", params = "createdAt", spec = In.class),
            @Spec(path = "updatedAt", params = "updatedAt", spec = Like.class),
            @Spec(path = "createdAt", params = {"createdAtGt", "createdAtLt"}, spec = Equal.class),
            @Spec(path = "updatedAt", params = {"updatedAtGt", "updatedAtLt"}, spec = Equal.class)
        }) Specification<Questao> spec,
        Sort sort,
        @RequestHeader HttpHeaders headers){
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
    @DeleteMapping("/questao")
    @ApiOperation(value = "Delata uma Questao")
    public void deletarQuestao(@RequestBody Integer id){
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
