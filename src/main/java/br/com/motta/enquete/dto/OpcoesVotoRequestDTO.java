package br.com.motta.enquete.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record OpcoesVotoRequestDTO(@NotBlank @Size(max = 250) String texto) {
}
