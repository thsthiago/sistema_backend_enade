package br.com.apirest.sisEnade.resources;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.apirest.sisEnade.models.Curso;
import br.com.apirest.sisEnade.models.utils.PagingHeaders;
import br.com.apirest.sisEnade.models.utils.PagingResponseCurso;
import br.com.apirest.sisEnade.services.CursoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.In;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;

@RestController
@RequestMapping(value = "/api")
@Api(value = "API REST Cursos")
@CrossOrigin(origins = "*")
public class CursoResource {

    private final CursoService cursoService;

    @Autowired
    public CursoResource(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @Transactional
    @GetMapping("/cursos")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Retorna uma Lista de cursos")
    public PagingResponseCurso listarCurso(
        @And({
            @Spec(path = "id", params = "id", spec = Like.class),
            @Spec(path = "nome", params = "nome", spec = Like.class),
            @Spec(path = "createdAt", params = "createdAt", spec = In.class),
            @Spec(path = "updatedAt", params = "updatedAt", spec = Like.class),
            @Spec(path = "createdAt", params = {"createdAtGt", "createdAtLt"}, spec = Equal.class),
            @Spec(path = "updatedAt", params = {"updatedAtGt", "updatedAtLt"}, spec = Equal.class)
        }) Specification<Curso> spec,
        Sort sort,
        @RequestHeader HttpHeaders headers){
        final PagingResponseCurso response = cursoService.get(spec, headers, sort);
        return  response;
    }

    @Transactional
    @GetMapping(value= "/curso/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Retorna um curso unico")
    public Curso listarCursoUnico(@PathVariable(value = "id") Integer id){
        return cursoService.get(id);
    }

    @Transactional
    @PostMapping("/curso")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Salva um curso")
    public Curso salvaCurso(@RequestBody Curso curso){
        return cursoService.create(curso);
    }

    @Transactional
    @DeleteMapping("/curso/{id}")
    @ApiOperation(value = "Deleta um curso")
    public void deletaCurso(@PathVariable(value = "id") Integer id){
        cursoService.delete(id);
    }

    @Transactional
    @PutMapping("/curso/{id}")
    @ApiOperation(value = "Atualiza um curso")
    public Curso atualizaCurso(@PathVariable(name="id") Integer id, @RequestBody Curso curso){
        return cursoService.update(id ,curso);
    }

    public HttpHeaders returnHttpHeaders(PagingResponseCurso response) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(PagingHeaders.COUNT.getName(), String.valueOf(response.getCount()));
        headers.set(PagingHeaders.PAGE_SIZE.getName(), String.valueOf(response.getPageSize()));
        headers.set(PagingHeaders.PAGE_OFFSET.getName(), String.valueOf(response.getPageOffset()));
        headers.set(PagingHeaders.PAGE_NUMBER.getName(), String.valueOf(response.getPageNumber()));
        headers.set(PagingHeaders.PAGE_TOTAL.getName(), String.valueOf(response.getPageTotal()));
        return headers;
    }
}
