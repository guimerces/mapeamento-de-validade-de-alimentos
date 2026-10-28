package com.despensa.dto.response;

public class PessoaResponseDTO {

    private Long id;
    private String login;
    private Long chatIdTelegram;

    public PessoaResponseDTO() {
    }

    public PessoaResponseDTO(Long id, String login, Long chatIdTelegram) {
        this.id = id;
        this.login = login;
        this.chatIdTelegram = chatIdTelegram;
    }

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

    public Long getChatIdTelegram() {
        return chatIdTelegram;
    }

    public void setChatIdTelegram(Long chatIdTelegram) {
        this.chatIdTelegram = chatIdTelegram;
    }
}