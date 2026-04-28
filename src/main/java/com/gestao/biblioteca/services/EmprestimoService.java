package com.gestao.biblioteca.services;

import com.gestao.biblioteca.models.EmprestimoModel;
import com.gestao.biblioteca.repositories.EmprestimoRepository;
import com.gestao.biblioteca.repositories.LivroRepository;
import com.gestao.biblioteca.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmprestimoService {

    private final EmprestimoRepository repository;

    public EmprestimoService(EmprestimoRepository repository) {
        this.repository = repository;
    }

    public EmprestimoModel post(EmprestimoModel emprestimo) {
        return repository.save(emprestimo);
    }

    public List<EmprestimoModel> getAll(){
        return repository.findAll();
    }

    public Optional<EmprestimoModel> getById(Long id) {
        return repository.findById(id);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public EmprestimoModel put(EmprestimoModel emprestimo, Long id) {
        EmprestimoModel emprestimoExistente = repository.
                findById(id).
                orElseThrow(()-> new RuntimeException("Emprestimo não encontrado"));

        emprestimoExistente.setUsuario(emprestimo.getUsuario());
        emprestimoExistente.setLivro(emprestimo.getLivro());
        emprestimoExistente.setStatus(emprestimo.getStatus());

        return repository.save(emprestimoExistente);
    }
}
