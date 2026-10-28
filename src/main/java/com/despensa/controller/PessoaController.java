package com.despensa.controller;

import com.despensa.dto.request.PessoaRequestDTO;
import com.despensa.dto.request.LoginRequestDTO;
import com.despensa.dto.response.PessoaResponseDTO;

import com.despensa.service.PessoaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    // Post Mappings
    @PostMapping
    public ResponseEntity<PessoaResponseDTO> cadastrar(@RequestBody @Valid PessoaRequestDTO dto) {
        PessoaResponseDTO response = pessoaService.cadastrar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<PessoaResponseDTO> login(@RequestBody @Valid LoginRequestDTO dto) {
        PessoaResponseDTO response = pessoaService.autenticar(dto);
        return ResponseEntity.ok(response);
    }

    // Get Mappings

    // usado para verificações no front
    @GetMapping("/{id}")
    public ResponseEntity<PessoaResponseDTO> buscarPorId(@PathVariable Long id) {
        PessoaResponseDTO response = pessoaService.buscarPorId(id);
        if (response == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response);
    }

    // não existe GET /login pois não é seguro. o bot acessa de outra forma
    
}


