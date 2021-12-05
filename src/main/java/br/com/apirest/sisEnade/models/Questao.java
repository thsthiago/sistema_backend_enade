package br.com.apirest.sisEnade.models;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "questoes")
public class Questao {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String tipoQuestao;

    @Column(nullable = false, length = 11)
    private int numQuestao;

    @Column(nullable = false, length = 50)
    private int edicao;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String enunciado;

    @Column(nullable = false, length = 100)
    private String resposta;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "alternativas", joinColumns = @JoinColumn(name = "questao_id"))
    @AttributeOverrides({
        @AttributeOverride(name = "letra", column = @Column(name = "letra")),
        @AttributeOverride(name = "enunciado", column = @Column(name = "enunciado"))
    })
    private Set<Alternativa> alternativas = new HashSet<>();
    
    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;

    @ManyToMany
    private List<Disciplina> disciplina;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "anotacoes", joinColumns = @JoinColumn(name = "questao_id"))
    @AttributeOverrides({
        @AttributeOverride(name = "anotacao", column = @Column(name = "anotacao")),
        @AttributeOverride(name = "createdAt", column = @Column(name = "createdAt"))
    })
    private Set<Anotacao> anotacoes  = new HashSet<>();

    @CreationTimestamp()
    private LocalDateTime createdAt;

    @UpdateTimestamp()
    private LocalDateTime updatedAt;
}
