package com.despensa.mapper;

import com.despensa.dto.response.ProdutoResponseDTO;
import com.despensa.model.Produto;
import com.despensa.model.enums.StatusValidade;

public class ProdutoMapper {

    public static ProdutoResponseDTO transformarParaDTO(Produto produto, StatusValidade status) {
        if (produto == null) {
            return null;
        }
        return new ProdutoResponseDTO(
            produto.getId(),
            produto.getNome(),
            produto.getDataValidade(),
            produto.getTempoAvisoEmDias(),
            produto.getPessoa() != null ? produto.getPessoa().getId() : null,
            status
        );
    }
}