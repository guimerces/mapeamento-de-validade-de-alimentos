package com.despensa.controller;

import com.despensa.dto.request.ProdutoRequestDTO;
import com.despensa.dto.response.ProdutoResponseDTO;

import com.despensa.service.ProdutoService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    // ResponseEntity é uma classe definida para o spring web que facilita a resposta da requisição com seus status

    // Get Mappings
    
    @GetMapping
    public ResponseEntity<List<ProdutoResponseDTO>> listarOuFiltrar(
            @RequestParam(name = "pessoaId") Long pessoaId, // Obrigatório por padrão
            @RequestParam(name = "nome", required = false) String nome) {

        // 1. Se informou nome, busca o produto específico daquela pessoa
        if (nome != null && !nome.isBlank()) {
            ProdutoResponseDTO produto = produtoService.buscarPorNomeEPessoaId(nome, pessoaId);
            if (produto == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(List.of(produto));
        }

        // 2. Se não informou nome, lista todos os produtos da pessoa
        return ResponseEntity.ok(produtoService.listarPorPessoaId(pessoaId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> buscarPorId(@PathVariable Long id) {
        ProdutoResponseDTO response = produtoService.buscarProdutoPorId(id);
        if (response == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response);
    }

    // Post Mappings
    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> cadastrar(@RequestBody @Valid ProdutoRequestDTO dto) {
        ProdutoResponseDTO response = produtoService.cadastrar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // 4. PUT /produtos/{id}
    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid ProdutoRequestDTO dto) {
        ProdutoResponseDTO response = produtoService.atualizar(id, dto);
        return ResponseEntity.ok(response);
    }

    // Delete Mappings
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable Long id) {
        boolean deletado = produtoService.deletarProdutoPorId(id);
        if (!deletado) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}