package com.gestao.biblioteca.repositories;

import com.gestao.biblioteca.models.EmprestimoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmprestimoRepository extends JpaRepository<Long, EmprestimoModel> {
}
