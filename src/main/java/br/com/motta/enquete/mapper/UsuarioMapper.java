package br.com.motta.enquete.mapper;

import br.com.motta.enquete.dto.UsuarioRequestDTO;
import br.com.motta.enquete.dto.UsuarioResponseDTO;
import br.com.motta.enquete.model.Usuario;

public class UsuarioMapper {

    public static UsuarioResponseDTO toDto(Usuario usuario) {
        return new  UsuarioResponseDTO(usuario.getId(), usuario.getNome(), usuario.getEmail());
    }

    public static Usuario toEntity(UsuarioRequestDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        return usuario;
    }
}
