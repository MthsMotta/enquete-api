package br.com.motta.enquete.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.List;

public record EnqueteRequestDTO(@NotBlank @Size(max = 100) String titulo,
                                @NotBlank @Size(max = 500) String pergunta,
                                @Valid @Size(min = 2) List<OpcoesVotoRequestDTO> opcoes,
                                @NotNull @Positive Long usuarioId
                                ) {
}
