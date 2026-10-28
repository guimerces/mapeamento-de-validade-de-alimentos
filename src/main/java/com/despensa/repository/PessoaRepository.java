package com.despensa.repository;

import com.despensa.model.Pessoa;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    // facilita o tratamento se não tiver login no bd, evolução da v0
    Optional<Pessoa> findByLogin(String login);

}