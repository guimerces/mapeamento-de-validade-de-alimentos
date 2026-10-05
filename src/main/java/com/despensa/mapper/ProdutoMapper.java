package com.despensa.mapper;

import com.despensa.dto.response.ProdutoResponseDTO;
import com.despensa.model.Produto;

public class ProdutoMapper {

    // Método estático utilitário: recebe a Entidade e devolve o ResponseDTO montado
    public static ProdutoResponseDTO transformarParaDTO(Produto produto) {
        if (produto == null) {
            return null;
        }
        return new ProdutoResponseDTO(
            produto.getId(),
            produto.getNome(),
            produto.getDataValidade()
        );
    }
}