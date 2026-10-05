package com.despensa.controller;

import com.despensa.dto.ProdutoDTO;
import com.despensa.model.Produto;
import com.despensa.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    // @GetMapping
    // public String RecuperarProdutoPorNome(@RequestParam("nome") String parametroNome) {
    //     Produto produtoRecuperado = produtoService.buscarProdutoPorNome(parametroNome);
    //     LocalDate validade = produtoRecuperado.getDataValidade();
    //     String nome = produtoRecuperado.getNome();
    //     return "O produto: " + nome + " tem validade: " + validade;
    // }

    @GetMapping("/{id}")
    public String buscarProdutoPorId(@PathVariable Long id) {
        Produto produto = produtoService.buscarProdutoPorId(id);

        if(produto != null){ 
            return "Produto: " + produto.getNome() + 
            " com id: " +  id + " tem validade: " + produto.getDataValidade();
        }
        else{
            return "O produto de id: " + id + 
            "não foi encontrado no sistema.";
        } 

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