package br.com.apirest.sisEnade.models.utils;
import java.util.List;

import br.com.apirest.sisEnade.models.Curso;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PagingResponseCurso {

  private Long count;
  private Long pageNumber;
  private Long pageSize;
  private Long pageOffset;
  private Long pageTotal;
  private List<Curso> results;
}