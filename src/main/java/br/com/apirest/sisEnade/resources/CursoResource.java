package br.com.apirest.sisEnade.resources;
import br.com.apirest.sisEnade.model.Curso;
import br.com.apirest.sisEnade.repository.CursoRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
@Api(value = "API REST Cursos")
@CrossOrigin(origins = "*")
public class CursoResource {


    @Autowired
    CursoRepository cursoRepository;

    @GetMapping("/cursos")
    @ApiOperation(value = "Retorna uma Lista de cursos")
    public List<Curso> listarCurso(){
        return cursoRepository.findAll();
    }

    @GetMapping("/curso/{id}")
    @ApiOperation(value = "Retorna um curso unico")
    public Curso listarCursoUnico(@PathVariable(value = "id") long id){
        return cursoRepository.findById(id);
    }

    @PostMapping("/curso")
    @ApiOperation(value = "Salva um curso")
    public Curso salvaCurso(@RequestBody Curso curso){
        return cursoRepository.save(curso);
    }

    @DeleteMapping("/curso")
    @ApiOperation(value = "Deleta um curso")
    public void deletaCurso(@RequestBody Curso curso){
        cursoRepository.delete(curso);
    }

    @PutMapping("/curso")
    @ApiOperation(value = "Atualiza um curso")
    public Curso atualizaCurso(@RequestBody Curso curso){
        return cursoRepository.save(curso);
    }
}
