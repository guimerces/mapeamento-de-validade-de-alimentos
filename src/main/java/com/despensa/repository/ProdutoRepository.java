package com.despensa.repository;

import com.despensa.model.Produto;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    // Os métodos chamados no service e não implementados aqui, pertencem a classe pai JpaReposity. Verifique lá.

    List<Produto> findByPessoaId(Long pessoaId);

    Optional<Produto> findByNomeAndPessoaId(String nome, Long pessoaId);

    Optional<Produto> findByIdAndPessoaId(Long id, Long pessoaId);

}