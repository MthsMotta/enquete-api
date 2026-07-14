package br.com.motta.enquete.service;

import br.com.motta.enquete.dto.UsuarioRequestDTO;
import br.com.motta.enquete.dto.UsuarioResponseDTO;
import br.com.motta.enquete.exception.RegraDeNegocioException;
import br.com.motta.enquete.mapper.UsuarioMapper;
import br.com.motta.enquete.model.Usuario;
import br.com.motta.enquete.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }


    @Override
    public UsuarioResponseDTO cadastrar(UsuarioRequestDTO dto) {
        if (usuarioRepository.existsByEmail(dto.email())){
            throw new RegraDeNegocioException("Ja existe um usuario cadastrado com esse email");
        }
        Usuario usuario = UsuarioMapper.toEntity(dto);
        return UsuarioMapper.toDto(usuarioRepository.save(usuario));
    }
}
