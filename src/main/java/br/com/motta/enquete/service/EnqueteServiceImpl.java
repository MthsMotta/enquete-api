package br.com.motta.enquete.service;

import br.com.motta.enquete.dto.EnqueteRequestDTO;
import br.com.motta.enquete.dto.EnqueteResponseDTO;
import br.com.motta.enquete.dto.EnqueteResultadoResponseDTO;
import br.com.motta.enquete.dto.OpcaoResultadoDTO;
import br.com.motta.enquete.exception.RecursoNaoEncontradoException;
import br.com.motta.enquete.exception.RegraDeNegocioException;
import br.com.motta.enquete.mapper.EnqueteMapper;
import br.com.motta.enquete.mapper.OpcoesVotoMapper;
import br.com.motta.enquete.model.Enquete;
import br.com.motta.enquete.model.OpcoesVoto;
import br.com.motta.enquete.model.Status;
import br.com.motta.enquete.model.Usuario;
import br.com.motta.enquete.repository.EnqueteRepository;
import br.com.motta.enquete.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

@Service
public class EnqueteServiceImpl implements EnqueteService {

    private final EnqueteRepository enqueteRepository;
    private final UsuarioRepository usuarioRepository;

    public EnqueteServiceImpl(EnqueteRepository enqueteRepository, UsuarioRepository usuarioRepository) {
        this.enqueteRepository = enqueteRepository;
        this.usuarioRepository = usuarioRepository;
    }


    @Override
    @Transactional
    public EnqueteResponseDTO cadastrar(EnqueteRequestDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.usuarioId()).orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado"));
        Enquete enquete = EnqueteMapper.toEntity(dto, usuario);
        return EnqueteMapper.toDTO(enqueteRepository.save(enquete));
    }

    @Override
    public List<EnqueteResponseDTO> listar() {
        List<Enquete> enquetes = enqueteRepository.findAll();
        return enquetes.stream().map(EnqueteMapper::toDTO).toList();
    }

    @Override
    public EnqueteResponseDTO buscarPorId(Long id) {
        Enquete enquete = enqueteRepository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Enquete não encontrada"));
        return EnqueteMapper.toDTO(enquete);
    }

    @Override
    @Transactional
    public EnqueteResponseDTO encerrar(Long id) {
        Enquete enqueteEncerrada = enqueteRepository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Enquete não encontrada"));
        if (enqueteEncerrada.getStatus() != Status.ABERTA){
            throw new RegraDeNegocioException("Só é possível encerrar uma enquete aberta");
        }
        enqueteEncerrada.setDataEncerramento(LocalDate.now());
        enqueteEncerrada.setStatus(Status.ENCERRADA);
        return EnqueteMapper.toDTO(enqueteEncerrada);
    }

    @Override
    @Transactional
    public EnqueteResponseDTO cancelar(Long id) {
        Enquete enqueteCancelada = enqueteRepository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Enquete não encontrada"));
        if (enqueteCancelada.getStatus() != Status.ABERTA){
            throw new RegraDeNegocioException("Só é possível cancelar uma enquete aberta");
        }
        enqueteCancelada.setDataEncerramento(LocalDate.now());
        enqueteCancelada.setStatus(Status.CANCELADA);
        return EnqueteMapper.toDTO(enqueteCancelada);
    }

    @Override
    public EnqueteResultadoResponseDTO resultado(Long enqueteId) {
        Enquete enquete = enqueteRepository.findById(enqueteId).orElseThrow(() -> new RecursoNaoEncontradoException("Enquete não encontrada"));
        List<OpcaoResultadoDTO> opcoesResultado = enquete.getOpcoes().stream().map(opcao -> {
            double percentualCalculado =  (double )opcao.getQuantidadeVotos() / enquete.getTotalVotos() * 100;
            return OpcoesVotoMapper.toOpcaoResultadoDTO(opcao, percentualCalculado);
        }).sorted(Comparator.comparingInt(OpcaoResultadoDTO::quantidadeVotos).reversed()).toList();
        String vencedora = opcoesResultado.getFirst().texto();
        return EnqueteMapper.toResultadoDTO(enquete.getTotalVotos(), opcoesResultado, vencedora);
    }
}
