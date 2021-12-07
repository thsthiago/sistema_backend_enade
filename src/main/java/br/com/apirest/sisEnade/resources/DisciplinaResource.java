package br.com.apirest.sisEnade.resources;

import br.com.apirest.sisEnade.models.Disciplina;
import br.com.apirest.sisEnade.models.utils.PagingHeaders;
import br.com.apirest.sisEnade.models.utils.PagingResponseDisciplina;
import org.springframework.http.HttpHeaders;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;

import br.com.apirest.sisEnade.services.DisciplinaService;
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
@Api(value = "API REST Disciplinas")
@CrossOrigin(origins = "*")
public class DisciplinaResource {

    private final DisciplinaService disciplinaService;

    @Autowired
    public DisciplinaResource(DisciplinaService disciplinaService) {
        this.disciplinaService = disciplinaService;
    }

    @Transactional
    @GetMapping("/disciplinas")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Lista todas as disciplinas")
    public PagingResponseDisciplina listarDisciplinas(
        @And({
            @Spec(path = "nome", params = "nome", spec = Like.class),
            @Spec(path = "curso.id", params = "curso", spec = Equal.class),
            @Spec(path = "createdAt", params = "createdAt", spec = In.class),
            @Spec(path = "updatedAt", params = "updatedAt", spec = Like.class),
            @Spec(path = "createdAt", params = {"createdAtGt", "createdAtLt"}, spec = Equal.class),
            @Spec(path = "updatedAt", params = {"updatedAtGt", "updatedAtLt"}, spec = Equal.class)
        }) Specification<Disciplina> spec,
        Sort sort,
        @RequestHeader HttpHeaders headers){
        final PagingResponseDisciplina response = disciplinaService.get(spec, headers, sort);
        return  response;
    }

    @Transactional
    @GetMapping("/disciplina/{id}")
    @ApiOperation(value = "Lista uma unica Disciplina")
    public Disciplina listarDisciplinaUnica(@PathVariable(value = "id") Integer id){
        return disciplinaService.get(id);
    }

    @Transactional
    @PostMapping("/disciplina")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Salva uma disciplina")
    public Disciplina salvarDisciplina(@RequestBody Disciplina disciplina){
        return disciplinaService.create(disciplina);
    }

    @Transactional
    @DeleteMapping("/disciplina/{id}")
    @ApiOperation(value = "Delata uma disciplina")
    public void deletaDisciplina(@PathVariable(value = "id") Integer id){
        disciplinaService.delete(id);
    }

    @Transactional
    @PutMapping("/disciplina/{id}")
    @ApiOperation(value = "Atualiza uma disciplina")
    public Disciplina atualizarDisciplina(@PathVariable(name="id") Integer id, @RequestBody Disciplina disciplina){
        return disciplinaService.update(id, disciplina);
    }

    public HttpHeaders returnHttpHeaders(PagingResponseDisciplina response) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(PagingHeaders.COUNT.getName(), String.valueOf(response.getCount()));
        headers.set(PagingHeaders.PAGE_SIZE.getName(), String.valueOf(response.getPageSize()));
        headers.set(PagingHeaders.PAGE_OFFSET.getName(), String.valueOf(response.getPageOffset()));
        headers.set(PagingHeaders.PAGE_NUMBER.getName(), String.valueOf(response.getPageNumber()));
        headers.set(PagingHeaders.PAGE_TOTAL.getName(), String.valueOf(response.getPageTotal()));
        return headers;
    }
}
