package br.com.motta.enquete.service;

import br.com.motta.enquete.dto.UsuarioRequestDTO;
import br.com.motta.enquete.dto.UsuarioResponseDTO;

public interface UsuarioService {
    UsuarioResponseDTO cadastrar(UsuarioRequestDTO dto);
}
