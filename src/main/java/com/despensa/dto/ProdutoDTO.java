package com.despensa.dto;

import java.time.LocalDate;

public class ProdutoDTO {

    private String nome;
    private LocalDate dataValidade;

    // Construtor vazio padrão
    public ProdutoDTO() {
    }

    // Construtor com parâmetros
    public ProdutoDTO(String nome, LocalDate dataValidade) {
        this.nome = nome;
        this.dataValidade = dataValidade;
    }

    // Getters e Setters
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