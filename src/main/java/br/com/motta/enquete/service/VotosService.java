package br.com.motta.enquete.service;

import br.com.motta.enquete.dto.VotosRequestDTO;
import br.com.motta.enquete.dto.VotosResponseDTO;

public interface VotosService {
    VotosResponseDTO registar(Long enqueteId, VotosRequestDTO dto);
}
