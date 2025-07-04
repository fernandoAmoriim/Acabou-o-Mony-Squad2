package contas.service.contas_service.dto.cliente;

import contas.service.contas_service.enums.Genero;

import java.time.LocalDate;

public record FisicaAtualizacaoDto(
        String nome,
        String email,
        String senha,
        LocalDate dataNascimento,
        Genero genero
) {
}
