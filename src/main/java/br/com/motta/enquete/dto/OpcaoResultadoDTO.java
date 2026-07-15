package br.com.motta.enquete.dto;

public record OpcaoResultadoDTO(Long id,
                                String texto,
                                Integer quantidadeVotos,
                                Double percentual) {
}
