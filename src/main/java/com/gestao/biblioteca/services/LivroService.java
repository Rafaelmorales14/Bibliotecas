package com.gestao.biblioteca.services;

import com.gestao.biblioteca.models.LivroModel;
import com.gestao.biblioteca.repositories.LivroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivroService {

    private final LivroRepository repository;

    public LivroService(LivroRepository repository) {
        this.repository = repository;
    }

    public LivroModel post(LivroModel livro) {
        return repository.save(livro);
    }

    public List<LivroModel> getAll() {
        return repository.findAll();
    }

    public Optional<LivroModel> getById(Long id){
        return repository.findById(id);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public LivroModel put(LivroModel livro, Long id) {
        LivroModel livroExistente = repository.
                findById(id).
                orElseThrow(()-> new RuntimeException("Livro não encontrado"));

        livroExistente.setTitulo(livro.getTitulo());
        livroExistente.setAutor(livro.getAutor());
        livroExistente.setIsbn(livro.getIsbn());
        livroExistente.setCategoria(livro.getCategoria());

        return repository.save(livroExistente);
    }
}
