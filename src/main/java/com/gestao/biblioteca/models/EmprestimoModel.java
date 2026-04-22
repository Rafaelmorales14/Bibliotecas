package com.gestao.biblioteca.models;

import com.gestao.biblioteca.models.enums.StatusEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "loan")
public class EmprestimoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuarioId")
    private UsuarioModel usuario;

    @ManyToOne
    @JoinColumn(name = "livroId")
    private LivroModel livro;

    private LocalDateTime dataEmprestimo;
    private LocalDateTime dataDevolucao;
    private StatusEnum status;
}
