package br.com.fh.comprasfh.pedidos.infra;

import br.com.fh.comprasfh.pedidos.dtos.ErroResponseDTO;
import br.com.fh.comprasfh.pedidos.exceptions.PedidoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroResponseDTO> handleValidationErrors(MethodArgumentNotValidException ex) {

        String detalhe = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(field -> String.format("%s: %s",
                        field.getField(),
                        field.getDefaultMessage()))
                .collect(Collectors.joining("; "));

        return ResponseEntity
                .badRequest()
                .body(new ErroResponseDTO(
                        "Erro de validação.",
                        detalhe
                ));
    }

    @ExceptionHandler(PedidoException.class)
    public ResponseEntity<ErroResponseDTO> handlePedidoException(PedidoException ex) {
        return ResponseEntity
                .badRequest()
                .body(new ErroResponseDTO(
                        ex.getMessage(),
                        ex.getDetalhe()
                ));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroResponseDTO> handleException(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErroResponseDTO(
                        "Erro interno no servidor.",
                        ex.getMessage()
                ));
    }
}