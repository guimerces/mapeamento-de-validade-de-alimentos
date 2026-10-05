package com.despensa.repository;

import com.despensa.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    Produto findByNome(String nome);

    // Os métodos chamados no service e não implementados aqui, pertencem a classe pai JpaReposity. Verifique lá.

}