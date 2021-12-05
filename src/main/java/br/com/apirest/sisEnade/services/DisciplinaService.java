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

import br.com.apirest.sisEnade.models.Disciplina;
import br.com.apirest.sisEnade.models.utils.PagingHeaders;
import br.com.apirest.sisEnade.models.utils.PagingResponseDisciplina;
import br.com.apirest.sisEnade.repository.DisciplinaRepository;

@Service
public class DisciplinaService {

    private final DisciplinaRepository disciplinaRepository;

    @Autowired
    public DisciplinaService(DisciplinaRepository disciplinaRepository) {
        this.disciplinaRepository = disciplinaRepository;
    }

    public void delete(Integer id) {
        Disciplina entity = disciplinaRepository.findById(id)
                                            .orElseThrow(() -> new EntityNotFoundException(String.format("Can not find the entity question (%s = %s).", "id", id.toString())));
                                            disciplinaRepository.delete(entity);
    }

    public Disciplina get(Integer id) {
        return disciplinaRepository.findById(id)
                            .orElseThrow(() -> new EntityNotFoundException(String.format("Can not find the entity question (%s = %s).", "id", id.toString())));
    }

    public PagingResponseDisciplina get(Specification<Disciplina> spec, HttpHeaders headers, Sort sort) {
        if (isRequestPaged(headers)) {
            return get(spec, buildPageRequest(headers, sort));
        } else {
            List<Disciplina> entities = get(spec, sort);
            return new PagingResponseDisciplina((long) entities.size(), 0L, 0L, 0L, 0L, entities);
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

    public PagingResponseDisciplina get(Specification<Disciplina> spec, Pageable pageable) {
        Page<Disciplina> page = disciplinaRepository.findAll(spec, pageable);
        List<Disciplina> content = page.getContent();
        return new PagingResponseDisciplina(page.getTotalElements(), (long) page.getNumber(), (long) page.getNumberOfElements(), pageable.getOffset(), (long) page.getTotalPages(), content);
    }

    public List<Disciplina> get(Specification<Disciplina> spec, Sort sort) {
        return disciplinaRepository.findAll(spec, sort);
    }

    public Disciplina create(Disciplina item) {
        return save(item);
    }

    public Disciplina update(Integer id, Disciplina item) {
        if (item.getId() == null) {
            throw new RuntimeException("Can not update entity, entity without ID.");
        } else if (!id.equals(item.getId())) {
            throw new RuntimeException(String.format("Can not update entity, the resource ID (%d) not match the objet ID (%d).", id, item.getId()));
        }
        return save(item);
    }

    protected Disciplina save(Disciplina item) {
        return disciplinaRepository.save(item);
    }
}