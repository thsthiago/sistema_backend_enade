package br.com.apirest.sisEnade.models.utils;
import java.util.List;

import br.com.apirest.sisEnade.models.Disciplina;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PagingResponseDisciplina {

  private Long count;
  private Long pageNumber;
  private Long pageSize;
  private Long pageOffset;
  private Long pageTotal;
  private List<Disciplina> results;
}