package com.despensa.controller;

import com.despensa.dto.request.ProdutoRequestDTO;
import com.despensa.dto.response.ProdutoResponseDTO;
import com.despensa.service.ProdutoService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    // ResponseEntity é uma classe definida para o spring web que facilita a resposta da requisição

    @GetMapping(params = "nome")
    public ResponseEntity<ProdutoResponseDTO> recuperarProdutoPorNome(@RequestParam("nome") String nome) {
        ProdutoResponseDTO produtoResponse = produtoService.buscarProdutoPorNome(nome);

        if(produtoResponse != null){ 
            return ResponseEntity.ok(produtoResponse);
        }
        else{
            return ResponseEntity.notFound().build();
        } 
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> buscarProdutoPorId(@PathVariable Long id) {
        ProdutoResponseDTO produtoResponse = produtoService.buscarProdutoPorId(id);

        if(produtoResponse != null){ 
            return ResponseEntity.ok(produtoResponse);
        }
        else{
            return ResponseEntity.notFound().build();
        } 

    }

    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> cadastrarProduto(@RequestBody ProdutoRequestDTO dto) {
        
        // 2. O Controller apenas repassa o DTO para a camada de negócio
        ProdutoResponseDTO produtoResponse = produtoService.cadastrar(dto);

        // 3. Devolve a resposta ao cliente HTTP
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProdutoPorId(@PathVariable Long id) {
        if(produtoService.deletarProdutoPorId(id)) {
            return ResponseEntity.noContent().build();
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
}