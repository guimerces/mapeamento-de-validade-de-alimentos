package com.despensa.dto.request;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public class ProdutoRequestDTO {

    @NotBlank(message = "O nome do produto é obrigatório")
    private String nome;

    @NotNull(message = "A data de validade é obrigatória")
    @FutureOrPresent(message = "A data de validade deve ser de hoje ou futura")
    private LocalDate dataValidade;

    @NotNull(message = "O tempo de aviso em dias é obrigatório")
    @Min(value = 1, message = "O aviso deve ser de pelo menos 1 dia antes")
    private Integer tempoAvisoEmDias;

    @NotNull(message = "O ID da pessoa proprietária é obrigatório")
    private Long pessoaId;

    public ProdutoRequestDTO() {
    }

    public ProdutoRequestDTO(String nome, LocalDate dataValidade, Integer tempoAvisoEmDias, Long pessoaId) {
        this.nome = nome;
        this.dataValidade = dataValidade;
        this.tempoAvisoEmDias = tempoAvisoEmDias;
        this.pessoaId = pessoaId;
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

    public Integer getTempoAvisoEmDias() {
        return tempoAvisoEmDias;
    }

    public void setTempoAvisoEmDias(Integer tempoAvisoEmDias) {
        this.tempoAvisoEmDias = tempoAvisoEmDias;
    }

    public Long getPessoaId() {
        return pessoaId;
    }

    public void setPessoaId(Long pessoaId) {
        this.pessoaId = pessoaId;
    }
}