package com.despensa.model.enums;

public enum StatusValidade {
    EM_DIA,      // antes da data de aviso
    ALERTA,      // entre o aviso e a validade
    VENCIDO      // depois da validade
}