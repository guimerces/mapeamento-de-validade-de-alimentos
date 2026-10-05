package com.despensa.dto.response;

import java.time.LocalDate;

public class ProdutoResponseDTO {

    private Long id; // única mudança, pois esse projeto é muito simples.
    private String nome;
    private LocalDate dataValidade;

    // Construtor padrão (obrigatório para serializadores)
    public ProdutoResponseDTO() {
    }

    // Construtor completo
    public ProdutoResponseDTO(Long id, String nome, LocalDate dataValidade) {
        this.id = id;
        this.nome = nome;
        this.dataValidade = dataValidade;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(LocalDate dataValidade) {
        this.dataValidade = dataValidade;
    }
}