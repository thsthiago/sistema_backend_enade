package br.com.apirest.sisEnade.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Anotacao {
  private String anotacao;
  private String createdAt;
}

