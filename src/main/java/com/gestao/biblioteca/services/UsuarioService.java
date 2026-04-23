package com.gestao.biblioteca.services;

import com.gestao.biblioteca.models.UsuarioModel;
import com.gestao.biblioteca.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository){
        this.repository = repository;
    }

    public UsuarioModel post(UsuarioModel usuario) {
        return repository.save(usuario);
    }

    public List<UsuarioModel> getAll() {
        return repository.findAll();
    }

    public Optional<UsuarioModel> getById(Long id){
        return repository.findById(id);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public UsuarioModel put(UsuarioModel usuario, Long id) {
        UsuarioModel usuarioExistente = repository.
                findById(id).
                orElseThrow(()-> new RuntimeException("Usuario não encontrado"));

        usuarioExistente.setNome(usuario.getNome());
        usuarioExistente.setEmail(usuario.getEmail());
        usuarioExistente.setTelefone(usuario.getTelefone());

        return repository.save(usuarioExistente);
    }

}
