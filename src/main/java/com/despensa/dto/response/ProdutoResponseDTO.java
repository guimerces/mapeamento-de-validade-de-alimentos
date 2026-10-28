package com.despensa.dto.response;

import com.despensa.model.enums.StatusValidade;

import java.time.LocalDate;

public class ProdutoResponseDTO {

    private Long id;
    private String nome;
    private LocalDate dataValidade;
    private Integer tempoAvisoEmDias;
    private Long pessoaId;
    private StatusValidade statusValidade;

    public ProdutoResponseDTO() {
    }

    public ProdutoResponseDTO(Long id, String nome, LocalDate dataValidade, 
                              Integer tempoAvisoEmDias, Long pessoaId, StatusValidade statusValidade) {
        this.id = id;
        this.nome = nome;
        this.dataValidade = dataValidade;
        this.tempoAvisoEmDias = tempoAvisoEmDias;
        this.pessoaId = pessoaId;
        this.statusValidade = statusValidade;
    }

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

    public StatusValidade getStatusValidade() { 
        return statusValidade; 
    }

    public void setStatusValidade(StatusValidade statusValidade) { 
        this.statusValidade = statusValidade; 
    }

}