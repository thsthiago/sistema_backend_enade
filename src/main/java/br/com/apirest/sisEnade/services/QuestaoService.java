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

import br.com.apirest.sisEnade.models.Questao;
import br.com.apirest.sisEnade.models.utils.PagingHeaders;
import br.com.apirest.sisEnade.models.utils.PagingResponseQuestao;
import br.com.apirest.sisEnade.repository.QuestaoRepository;

@Service
public class QuestaoService {

    private final QuestaoRepository questaoRepository;

    @Autowired
    public QuestaoService(QuestaoRepository questaoRepository) {
        this.questaoRepository = questaoRepository;
    }

    public void delete(Integer id) {
        Questao entity = questaoRepository.findById(id)
                                            .orElseThrow(() -> new EntityNotFoundException(String.format("Can not find the entity question (%s = %s).", "id", id.toString())));
                                            questaoRepository.delete(entity);
    }

    public Questao get(Integer id) {
        return questaoRepository.findById(id)
                            .orElseThrow(() -> new EntityNotFoundException(String.format("Can not find the entity question (%s = %s).", "id", id.toString())));
    }

    public PagingResponseQuestao get(Specification<Questao> spec, HttpHeaders headers, Sort sort) {
        if (isRequestPaged(headers)) {
            return get(spec, buildPageRequest(headers, sort));
        } else {
            List<Questao> entities = get(spec, sort);
            return new PagingResponseQuestao((long) entities.size(), 0L, 0L, 0L, 0L, entities);
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

    public PagingResponseQuestao get(Specification<Questao> spec, Pageable pageable) {
        Page<Questao> page = questaoRepository.findAll(spec, pageable);
        List<Questao> content = page.getContent();
        return new PagingResponseQuestao(page.getTotalElements(), (long) page.getNumber(), (long) page.getNumberOfElements(), pageable.getOffset(), (long) page.getTotalPages(), content);
    }

    public List<Questao> get(Specification<Questao> spec, Sort sort) {
        return questaoRepository.findAll(spec, sort);
    }

    public Questao create(Questao item) {
        return save(item);
    }

    public Questao update(Integer id, Questao item) {
        if (item.getId() == null) {
            throw new RuntimeException("Can not update entity, entity without ID.");
        } else if (!id.equals(item.getId())) {
            throw new RuntimeException(String.format("Can not update entity, the resource ID (%d) not match the objet ID (%d).", id, item.getId()));
        }
        return save(item);
    }

    protected Questao save(Questao item) {
        return questaoRepository.save(item);
    }
}