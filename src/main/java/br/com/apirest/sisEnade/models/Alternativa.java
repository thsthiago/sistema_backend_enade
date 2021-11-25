package br.com.apirest.sisEnade.models;

import javax.persistence.*;

@Entity
@Table(name = "alternativas")
public class Alternativa {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false, length = 10)
    private String letra;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String enunciado;

    @ManyToOne
    @JoinColumn(name = "questao_id")
    private Questao questao;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public Questao getQuestao() {
        return questao;
    }

    public void setQuestao(Questao questao) {
        this.questao = questao;
    }
}
