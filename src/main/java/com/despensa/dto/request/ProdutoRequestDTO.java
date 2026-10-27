package com.despensa.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ProdutoRequestDTO {

    @NotBlank(message = "O nome do produto é obrigatório")
    private String nome;

    @NotNull(message = "A data de validade é obrigatória")
    @FutureOrPresent(message = "A data de validade deve ser de hoje ou futura")
    private LocalDate dataValidade;

    // Construtor vazio padrão
    public ProdutoRequestDTO() {
    }

    // Construtor com parâmetros
    public ProdutoRequestDTO(String nome, LocalDate dataValidade) {
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