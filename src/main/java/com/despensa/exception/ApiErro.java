package com.despensa.exception;

import java.time.LocalDateTime;
import java.util.List;

public class ApiErro {

    private LocalDateTime timestamp;
    private Integer status;
    private String erro;
    private List<String> mensagens;

    public ApiErro(Integer status, String erro, List<String> mensagens) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.erro = erro;
        this.mensagens = mensagens;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public String getErro() {
        return erro;
    }

    public List<String> getMensagens() {
        return mensagens;
    }
}