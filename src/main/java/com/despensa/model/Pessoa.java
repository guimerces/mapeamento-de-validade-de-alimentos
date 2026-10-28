package com.despensa.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_pessoa")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String login;

    @Column(nullable = false)
    private String senha;

    @Column(name = "chat_id_telegram")
    private Long chatIdTelegram;

    // Relacionamento 1:N com Produto
    @OneToMany(mappedBy = "pessoa")
    private List<Produto> produtos = new ArrayList<>();

    // Construtor padrão obrigatório para o JPA/Hibernate
    public Pessoa() {
    }

    public Pessoa(String login, String senha, Long chatIdTelegram) {
        this.login = login;
        this.senha = senha;
        this.chatIdTelegram = chatIdTelegram;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Long getChatIdTelegram() {
        return chatIdTelegram;
    }

    public void setChatIdTelegram(Long chatIdTelegram) {
        this.chatIdTelegram = chatIdTelegram;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
}