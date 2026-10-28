package com.despensa.service;

import com.despensa.dto.request.ProdutoRequestDTO;
import com.despensa.dto.response.ProdutoResponseDTO;
import com.despensa.mapper.ProdutoMapper;
import com.despensa.model.Pessoa;
import com.despensa.model.Produto;
import com.despensa.model.enums.StatusValidade;
import com.despensa.repository.PessoaRepository;
import com.despensa.repository.ProdutoRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;


    public List<ProdutoResponseDTO> listarPorPessoaId(Long pessoaId) {
        List<Produto> entidades = produtoRepository.findByPessoaId(pessoaId);
        List<ProdutoResponseDTO> dtos = new ArrayList<>();
        
        for (Produto p : entidades) {
            // Padrão Expert
            StatusValidade status = p.calcularStatusValidade();
            dtos.add(ProdutoMapper.transformarParaDTO(p, status));
        }
        return dtos;
    }


    public ProdutoResponseDTO buscarPorNomeEPessoaId(String nome, Long pessoaId) {
        Produto produto = produtoRepository.findByNomeAndPessoaId(nome, pessoaId)
                .orElse(null);

        if (produto == null) {
            return null;
        }

        StatusValidade status = produto.calcularStatusValidade();
        return ProdutoMapper.transformarParaDTO(produto, status);
    }

    public ProdutoResponseDTO buscarProdutoPorId(Long id) {
        Produto produto = produtoRepository.findById(id).orElse(null);
        if (produto == null) {
            return null;
        }

        StatusValidade status = produto.calcularStatusValidade();
        return ProdutoMapper.transformarParaDTO(produto, status);
    }

    public ProdutoResponseDTO cadastrar(ProdutoRequestDTO dto) {
        Pessoa pessoa = pessoaRepository.findById(dto.getPessoaId())
                .orElseThrow(() -> new RuntimeException("Pessoa proprietária não encontrada"));

        Produto novoProduto = new Produto(
                dto.getNome(),
                dto.getDataValidade(),
                dto.getTempoAvisoEmDias(),
                pessoa
        );

        Produto produtoSalvo = produtoRepository.save(novoProduto);

        StatusValidade status = produtoSalvo.calcularStatusValidade();
        return ProdutoMapper.transformarParaDTO(produtoSalvo, status);
    }

    public ProdutoResponseDTO atualizar(Long id, ProdutoRequestDTO dto) {
        Produto produto = produtoRepository.findByIdAndPessoaId(id, dto.getPessoaId())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado para esta pessoa"));

        produto.setNome(dto.getNome());
        produto.setDataValidade(dto.getDataValidade());
        produto.setTempoAvisoEmDias(dto.getTempoAvisoEmDias());

        Produto produtoAtualizado = produtoRepository.save(produto);
        StatusValidade status = produtoAtualizado.calcularStatusValidade();

        return ProdutoMapper.transformarParaDTO(produtoAtualizado, status);
    }

    public boolean deletarProdutoPorId(Long id) {
        if (produtoRepository.existsById(id)) {
            produtoRepository.deleteById(id);
            return true;
        }
        return false;
    }

}