package com.despensa.dto.request;

public class PessoaRequestDTO {

    private String login;
    private String senha;
    private Long chatIdTelegram;

    public PessoaRequestDTO() {
    }

    public PessoaRequestDTO(String login, String senha, Long chatIdTelegram) {
        this.login = login;
        this.senha = senha;
        this.chatIdTelegram = chatIdTelegram;
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
}