package br.com.apirest.sisEnade.models;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cursos")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false, length = 200)
    private String nome;

    @ElementCollection
    @CollectionTable(name = "edicoes", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "edicao")
    private Set<Integer> edicoes = new HashSet<>();

    @CreationTimestamp()
    private LocalDateTime createdAt;

    @UpdateTimestamp()
    private LocalDateTime updatedAt;
}
