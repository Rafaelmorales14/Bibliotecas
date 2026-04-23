package com.gestao.biblioteca.controllers;

import com.gestao.biblioteca.models.UsuarioModel;
import com.gestao.biblioteca.services.UsuarioService;
import org.springframework.data.repository.config.RepositoryConfigurationSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/api/usuarios")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<UsuarioModel> post(@RequestBody UsuarioModel usuario) {
        UsuarioModel request = service.post(usuario);
        URI uri = ServletUriComponentsBuilder.
                fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(request.getId())
                .toUri();

        return ResponseEntity.created(uri).body(request);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioModel>> getAll() {
        List<UsuarioModel> usuarios = service.getAll();

        return ResponseEntity.ok().body(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioModel> getById(@PathVariable Long id) {
        return service.
                getById(id).
                map(ResponseEntity::ok).
                orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if(service.getById(id).isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioModel> put(@RequestBody UsuarioModel usuario, @PathVariable Long id) {
        if(service.getById(id).isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(service.put(usuario, id));
    }
}
