package com.despensa.controller;

import com.despensa.dto.ProdutoDTO;
import com.despensa.model.Produto;
import com.despensa.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    // 1. Injeta o SERVICE (e não mais o Repository)
    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public String status() {
        return "Servidor rodando e pronto para receber cadastros!";
    }

    @PostMapping
    public String cadastrarProduto(@RequestBody ProdutoDTO dto) {
        
        // 2. O Controller apenas repassa o DTO para a camada de negócio
        Produto produtoSalvo = produtoService.cadastrar(dto);

        // 3. Devolve a resposta ao cliente HTTP
        return "Produto " + produtoSalvo.getNome() + 
               ", com validade " + produtoSalvo.getDataValidade() + 
               " cadastrado com sucesso! (ID no banco: " + produtoSalvo.getId() + ")";
    }
}