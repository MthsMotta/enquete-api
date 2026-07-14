package br.com.motta.enquete.service;

import br.com.motta.enquete.dto.EnqueteRequestDTO;
import br.com.motta.enquete.dto.EnqueteResponseDTO;

import java.util.List;

public interface EnqueteService {
    EnqueteResponseDTO cadastrar(EnqueteRequestDTO dto);
    List<EnqueteResponseDTO> listar();
    EnqueteResponseDTO buscarPorId(Long id);
    EnqueteResponseDTO encerrar(Long id);
    EnqueteResponseDTO cancelar(Long id);
}
