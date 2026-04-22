package com.gestao.biblioteca.models;

import com.gestao.biblioteca.models.enums.CategoriaEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.AnyDiscriminatorImplicitValues;
import org.w3c.dom.stylesheets.LinkStyle;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "book")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LivroModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String autor;
    private String isbn;
    private LocalDateTime dataPublicacao;
    private LocalDateTime dataExclusao;
    private CategoriaEnum categoria;

    @OneToMany(mappedBy = "livro")
    private List<EmprestimoModel> emprestimos;
}
