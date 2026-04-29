package com.gestao.biblioteca.repositories;

import com.gestao.biblioteca.models.EmprestimoModel;
import com.gestao.biblioteca.models.enums.StatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmprestimoRepository extends JpaRepository<EmprestimoModel, Long> {
    Boolean existsByLivroIdAndStatus(Long livroId, StatusEnum statusEnum);
}
