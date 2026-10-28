package com.despensa.mapper;

import com.despensa.dto.response.PessoaResponseDTO;
import com.despensa.model.Pessoa;

public class PessoaMapper {

    public static PessoaResponseDTO transformarParaDTO(Pessoa pessoa) {
        if (pessoa == null) {
            return null;
        }
        return new PessoaResponseDTO(
            pessoa.getId(),
            pessoa.getLogin(),
            pessoa.getChatIdTelegram()
        );
    }
}