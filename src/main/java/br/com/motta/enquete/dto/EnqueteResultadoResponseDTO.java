package br.com.motta.enquete.dto;

import java.util.List;

public record EnqueteResultadoResponseDTO(Integer totalVotos,
                                          List<OpcaoResultadoDTO> opcoes,
                                          String vencedora) {
}
