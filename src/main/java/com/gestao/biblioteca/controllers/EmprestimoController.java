package com.gestao.biblioteca.controllers;

import com.gestao.biblioteca.models.EmprestimoModel;
import com.gestao.biblioteca.services.EmprestimoService;
import org.apache.catalina.LifecycleState;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/emprestimos")
public class EmprestimoController {
    private final EmprestimoService service;

    public EmprestimoController(EmprestimoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<EmprestimoModel> post(@RequestBody EmprestimoModel emprestimo) {
        EmprestimoModel request = service.post(emprestimo);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(request.getId()).toUri();

        return ResponseEntity.created(uri).body(request);
    }

    @GetMapping
    public ResponseEntity<List<EmprestimoModel>> getAll() {
        List<EmprestimoModel> emprestimos = service.getAll();

        return ResponseEntity.ok().body(emprestimos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmprestimoModel> getById(@PathVariable Long id) {
        return service.getById(id).
                map(ResponseEntity::ok).
                orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        if (service.getById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmprestimoModel> put(@PathVariable Long id, @RequestBody EmprestimoModel emprestimo) {
        if (service.getById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(service.put(emprestimo, id));
    }
}
