package br.com.motta.enquete.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record VotosResponseDTO(Long id,
                               Long enqueteId,
                               Long opcaoId,
                               Long usuarioId,
                               @JsonFormat(pattern = "dd/MM/yyyy") LocalDate dataVoto) {
}
