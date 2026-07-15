package br.com.motta.enquete.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record VotosRequestDTO(@NotNull @Positive Long opcaoId,
                              @NotNull @Positive Long usuarioId){
}
