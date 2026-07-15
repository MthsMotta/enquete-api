package br.com.motta.enquete.service;

import br.com.motta.enquete.dto.VotosRequestDTO;
import br.com.motta.enquete.dto.VotosResponseDTO;
import br.com.motta.enquete.exception.RecursoNaoEncontradoException;
import br.com.motta.enquete.exception.RegraDeNegocioException;
import br.com.motta.enquete.mapper.VotosMapper;
import br.com.motta.enquete.model.*;
import br.com.motta.enquete.repository.EnqueteRepository;
import br.com.motta.enquete.repository.OpcoesVotoRepository;
import br.com.motta.enquete.repository.UsuarioRepository;
import br.com.motta.enquete.repository.VotosRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VotosServiceImpl implements VotosService {

    private final VotosRepository votosRepository;
    private final EnqueteRepository enqueteRepository;
    private final UsuarioRepository usuarioRepository;
    private final OpcoesVotoRepository opcoesVotoRepository;

    public VotosServiceImpl(VotosRepository votosRepository, EnqueteRepository enqueteRepository, UsuarioRepository usuarioRepository, OpcoesVotoRepository opcoesVotoRepository) {
        this.votosRepository = votosRepository;
        this.enqueteRepository = enqueteRepository;
        this.usuarioRepository = usuarioRepository;
        this.opcoesVotoRepository = opcoesVotoRepository;
    }

    @Override
    @Transactional
    public VotosResponseDTO registar(Long enqueteId, VotosRequestDTO dto) {
        Enquete enquete = enqueteRepository.findById(enqueteId).orElseThrow(() -> new RecursoNaoEncontradoException("Enquete nao encontrada"));
        Usuario usuario = usuarioRepository.findById(dto.usuarioId()).orElseThrow(() -> new RecursoNaoEncontradoException("Usuario nao encontrado"));
        if (enquete.getStatus() != Status.ABERTA){
            throw new RegraDeNegocioException("Só é possível votar em uma enquete aberta");
        }
        if (votosRepository.existsByUsuarioIdAndEnqueteId(dto.usuarioId(), enqueteId)) {
            throw new RegraDeNegocioException("É permitido apenas 1 voto por enquete");
        }
        OpcoesVoto opcoesVoto = opcoesVotoRepository.findById(dto.opcaoId()).orElseThrow(() -> new RecursoNaoEncontradoException("Opcao nao encontrada"));
        if (!opcoesVotoRepository.existsByIdAndEnqueteId(dto.opcaoId(), enqueteId)) {
            throw new RecursoNaoEncontradoException("Opção nao encontrada");
        }
        opcoesVoto.setQuantidadeVotos(opcoesVoto.getQuantidadeVotos() + 1);
        enquete.setTotalVotos(enquete.getTotalVotos() + 1);
        Votos votos = VotosMapper.toEntity(dto, opcoesVoto, usuario);
        return VotosMapper.toDto(votosRepository.save(votos));
    }
}
