package br.com.apirest.sisEnade.services;

import java.util.List;
import java.util.Objects;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import br.com.apirest.sisEnade.models.Curso;
import br.com.apirest.sisEnade.models.utils.PagingHeaders;
import br.com.apirest.sisEnade.models.utils.PagingResponseCurso;
import br.com.apirest.sisEnade.repository.CursoRepository;

@Service
public class CursoService {

    private final CursoRepository cursoRepository;

    @Autowired
    public CursoService(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    public void delete(Integer id) {
        Curso entity = cursoRepository.findById(id)
                                            .orElseThrow(() -> new EntityNotFoundException(String.format("Can not find the entity curse (%s = %s).", "id", id.toString())));
        cursoRepository.delete(entity);
    }

    public Curso get(Integer id) {
        return cursoRepository.findById(id)
                            .orElseThrow(() -> new EntityNotFoundException(String.format("Can not find the entity curse (%s = %s).", "id", id.toString())));
    }

    public PagingResponseCurso get(Specification<Curso> spec, HttpHeaders headers, Sort sort) {
        if (isRequestPaged(headers)) {
            return get(spec, buildPageRequest(headers, sort));
        } else {
            List<Curso> entities = get(spec, sort);
            return new PagingResponseCurso((long) entities.size(), 0L, 0L, 0L, 0L, entities);
        }
    }

    private boolean isRequestPaged(HttpHeaders headers) {
        return headers.containsKey(PagingHeaders.PAGE_NUMBER.getName()) && headers.containsKey(PagingHeaders.PAGE_SIZE.getName());
    }

    private Pageable buildPageRequest(HttpHeaders headers, Sort sort) {
        int page = Integer.parseInt(Objects.requireNonNull(headers.get(PagingHeaders.PAGE_NUMBER.getName())).get(0));
        int size = Integer.parseInt(Objects.requireNonNull(headers.get(PagingHeaders.PAGE_SIZE.getName())).get(0));
        return PageRequest.of(page, size, sort);
    }

    public PagingResponseCurso get(Specification<Curso> spec, Pageable pageable) {
        Page<Curso> page = cursoRepository.findAll(spec, pageable);
        List<Curso> content = page.getContent();
        return new PagingResponseCurso(page.getTotalElements(), (long) page.getNumber(), (long) page.getNumberOfElements(), pageable.getOffset(), (long) page.getTotalPages(), content);
    }

    public List<Curso> get(Specification<Curso> spec, Sort sort) {
        return cursoRepository.findAll(spec, sort);
    }

    public Curso create(Curso item) {
        return save(item);
    }

    public Curso update(Integer id, Curso item) {
        if (item.getId() == null) {
            throw new RuntimeException("Can not update entity, entity without ID.");
        } else if (!id.equals(item.getId())) {
            throw new RuntimeException(String.format("Can not update entity, the resource ID (%d) not match the objet ID (%d).", id, item.getId()));
        }
        return save(item);
    }

    protected Curso save(Curso item) {
        return cursoRepository.save(item);
    }
}