package com.gestao.biblioteca.services;

import com.gestao.biblioteca.models.EmprestimoModel;
import com.gestao.biblioteca.models.LivroModel;
import com.gestao.biblioteca.models.UsuarioModel;
import com.gestao.biblioteca.models.enums.StatusEnum;
import com.gestao.biblioteca.repositories.EmprestimoRepository;
import com.gestao.biblioteca.repositories.LivroRepository;
import com.gestao.biblioteca.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EmprestimoService {

    private final EmprestimoRepository emprestimoRepository;
    private final LivroRepository livroRepository;
    private final UsuarioRepository usuarioRepository;

    public EmprestimoService(EmprestimoRepository emprestimoRepository, LivroRepository livroRepository, UsuarioRepository usuarioRepository) {
        this.emprestimoRepository = emprestimoRepository;
        this.livroRepository = livroRepository;
        this.usuarioRepository = usuarioRepository;

    }
    public EmprestimoModel post(Long livroId, Long usuarioId) {
        if(emprestimoRepository.existsByLivroIdAndStatus(livroId, StatusEnum.ATIVO)) {
            throw new RuntimeException("Livro já emprestado");
        }
        // colocar validação do status dentro da validação se existe livro
        LivroModel livroModel = livroRepository.
                findById(livroId).
                orElseThrow(() -> new RuntimeException("Livro não encontrado"));

        UsuarioModel usuarioModel = usuarioRepository.
                findById(usuarioId).
                orElseThrow(()-> new RuntimeException("Usuário não encontrado"));

        EmprestimoModel emprestimo = new EmprestimoModel();
        emprestimo.setUsuario(usuarioModel);
        emprestimo.setLivro(livroModel);
        emprestimo.setDataEmprestimo(LocalDateTime.now());
        emprestimo.setStatus(StatusEnum.ATIVO);

        return emprestimoRepository.save(emprestimo);
    }

    public List<EmprestimoModel> getAll(){
        return emprestimoRepository.findAll();
    }

    public Optional<EmprestimoModel> getById(Long id) {
        return emprestimoRepository.findById(id);
    }

    public void delete(Long id) {
        emprestimoRepository.deleteById(id);
    }

    public EmprestimoModel put(EmprestimoModel emprestimo, Long id) {
        EmprestimoModel emprestimoExistente = emprestimoRepository.
                findById(id).
                orElseThrow(()-> new RuntimeException("Emprestimo não encontrado"));

        emprestimoExistente.setUsuario(emprestimo.getUsuario());
        emprestimoExistente.setLivro(emprestimo.getLivro());
        emprestimoExistente.setStatus(emprestimo.getStatus());

        return emprestimoRepository.save(emprestimoExistente);
    }
}
