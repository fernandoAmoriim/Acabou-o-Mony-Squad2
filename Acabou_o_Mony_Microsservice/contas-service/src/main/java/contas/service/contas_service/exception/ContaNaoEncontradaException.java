package contas.service.contas_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ContaNaoEncontradaException extends RuntimeException {
    public ContaNaoEncontradaException(String message) {
    }
}
