package br.com.motta.enquete.dto;

import br.com.motta.enquete.model.Status;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.List;

public record EnqueteResponseDTO(Long id,
                                 String titulo,
                                 String pergunta,
                                 Integer totalVotos,
                                 List<OpcoesVotoResponseDTO> opcoes,
                                 Status status,
                                 @JsonFormat(pattern = "dd/MM/yyyy") LocalDate dataCriacao,
                                 @JsonFormat(pattern = "dd/MM/yyyy") LocalDate dataEncerramento,
                                 String nomeCriador,
                                 Long idCriador
                                 ) {
}
