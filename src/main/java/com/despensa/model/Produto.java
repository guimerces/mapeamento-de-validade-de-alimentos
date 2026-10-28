package com.despensa.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDate;

import com.despensa.model.enums.StatusValidade;

@Entity
@Table(name = "tb_produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(name = "data_validade", nullable = false)
    private LocalDate dataValidade;

    @Column(name = "tempo_aviso_em_dias", nullable = false)
    private Integer tempoAvisoEmDias;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pessoa_id", nullable = false)
    private Pessoa pessoa;


    public StatusValidade calcularStatusValidade() {
        LocalDate hoje = LocalDate.now();
        if (this.dataValidade.isBefore(hoje)) {
            return StatusValidade.VENCIDO;
        } 

        // Garante que se for null, assume 0 dias de aviso sem travar o sistema
        int diasAviso = 0;
        if (this.tempoAvisoEmDias != null) {
            diasAviso = this.tempoAvisoEmDias;
        }

        LocalDate dataAviso = this.dataValidade.minusDays(diasAviso);

        if (hoje.isBefore(dataAviso)) {
            return StatusValidade.EM_DIA;
        } 
        else {
            return StatusValidade.ALERTA;
        }
    }


    // Construtores, getters e setters


    public Produto() {
    }

    public Produto(String nome, LocalDate dataValidade, Integer tempoAvisoEmDias, Pessoa pessoa) {
        this.nome = nome;
        this.dataValidade = dataValidade;
        this.tempoAvisoEmDias = tempoAvisoEmDias;
        this.pessoa = pessoa;
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

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
}