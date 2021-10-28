package br.com.apirest.sisEnade.resources;

import br.com.apirest.sisEnade.model.Disciplina;
import br.com.apirest.sisEnade.repository.DisciplinaRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
@Api(value = "API REST Disciplinas")
@CrossOrigin(origins = "*")
public class DisciplinaResource {

    @Autowired
    DisciplinaRepository disciplinaRepository;

    @GetMapping("/disciplinas")
    @ApiOperation(value = "Lista todas as disciplinas")
    public List<Disciplina> listarDisciplinas(){
        return disciplinaRepository.findAll();
    }

    @GetMapping("/disciplina/{id}")
    @ApiOperation(value = "Lista uma unica Disciplina")
    public Disciplina listarDisciplinaUnica(@PathVariable(value = "id")long id){
        return disciplinaRepository.findById(id);
    }

    @PostMapping("/disciplina")
    @ApiOperation(value = "Salva uma disciplina")
    public Disciplina salvarDisciplina(@RequestBody Disciplina disciplina){
        return disciplinaRepository.save(disciplina);
    }

    @DeleteMapping("/disciplina")
    @ApiOperation(value = "Delata uma disciplina")
    public void deletaDisciplina(@RequestBody Disciplina disciplina){
        disciplinaRepository.delete(disciplina);
    }

    @PutMapping("/disciplina")
    @ApiOperation(value = "Atualiza uma disciplina")
    public Disciplina altualizarDisciplina(@RequestBody Disciplina disciplina){
        return disciplinaRepository.save(disciplina);
    }

}
