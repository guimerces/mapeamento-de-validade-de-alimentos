package com.despensa.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 1. Captura as falhas de anotações do Bean Validation (@NotBlank, @FutureOrPresent, etc.)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErro> capturarErrosDeValidacao(MethodArgumentNotValidException ex) {
        List<String> mensagens = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(erro -> erro.getField() + ": " + erro.getDefaultMessage())
                .collect(Collectors.toList());

        ApiErro apiErro = new ApiErro(
                HttpStatus.BAD_REQUEST.value(),
                "Erro de Validação",
                mensagens
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiErro);
    }

    // 2. Captura erro de formato do Jackson (como o 8/10/26 ou JSON com chaves quebradas)
    // Captura erro de sintaxe/formato do JSON na leitura do Jackson
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiErro> capturarErroDeFormato(HttpMessageNotReadableException ex) {
        ApiErro apiErro = new ApiErro(
                HttpStatus.BAD_REQUEST.value(),
                "Corpo da requisição inválido",
                List.of("O corpo da requisição é malformado ou contém valores incompatíveis com o contrato da API.")
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiErro);
    }
}