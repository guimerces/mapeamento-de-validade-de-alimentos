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

    // Considerando inicialmente que não existem nomes repetidos
    public Produto buscarProdutoPorNome(String parametroNome){
        return produtoRepository.findByNome(parametroNome);
    }   

    public Produto buscarProdutoPorId(Long id){
        return produtoRepository.findById(id).orElse(null);
    }

    public Produto cadastrar(ProdutoDTO dto) {
        // Aqui entrarão as validações de negócio no futuro!
        Produto novoProduto = new Produto(dto.getNome(), dto.getDataValidade());
        return produtoRepository.save(novoProduto);
    }
}