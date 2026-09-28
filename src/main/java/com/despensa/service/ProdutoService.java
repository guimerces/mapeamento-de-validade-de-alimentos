package com.despensa.service;

import com.despensa.dto.ProdutoDTO;
import com.despensa.model.Produto;
import com.despensa.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto cadastrar(ProdutoDTO dto) {
        // Aqui entrarão as validações de negócio no futuro!
        Produto novoProduto = new Produto(dto.getNome(), dto.getDataValidade());
        return produtoRepository.save(novoProduto);
    }
}