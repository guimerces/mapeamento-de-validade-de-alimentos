package com.despensa.service;

import com.despensa.dto.request.ProdutoRequestDTO;
import com.despensa.dto.response.ProdutoResponseDTO;
import com.despensa.model.Produto;
import com.despensa.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.despensa.mapper.ProdutoMapper;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    // Considerando inicialmente que não existem nomes repetidos
    public ProdutoResponseDTO buscarProdutoPorNome(String nome){
        Produto produto = produtoRepository.findByNome(nome);
        return ProdutoMapper.transformarParaDTO(produto);
    }   

    public ProdutoResponseDTO buscarProdutoPorId(Long id){
        Produto produto = produtoRepository.findById(id).orElse(null);
        return ProdutoMapper.transformarParaDTO(produto);
    }

    public ProdutoResponseDTO cadastrar(ProdutoRequestDTO dto) {
        // Aqui entrarão as validações de negócio no futuro!
        Produto produtoDB = new Produto(dto.getNome(), dto.getDataValidade());
        Produto produto = produtoRepository.save(produtoDB);
        return ProdutoMapper.transformarParaDTO(produto);
    }

    public boolean deletarProdutoPorId(Long id){
        if(produtoRepository.existsById(id)){
            produtoRepository.deleteById(id);
            return true;
        }
        else{
            return false;
        }
    }
}