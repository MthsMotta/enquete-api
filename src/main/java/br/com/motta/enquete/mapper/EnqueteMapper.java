package br.com.motta.enquete.mapper;

import br.com.motta.enquete.dto.EnqueteRequestDTO;
import br.com.motta.enquete.dto.EnqueteResponseDTO;
import br.com.motta.enquete.dto.EnqueteResultadoResponseDTO;
import br.com.motta.enquete.dto.OpcaoResultadoDTO;
import br.com.motta.enquete.model.Enquete;
import br.com.motta.enquete.model.OpcoesVoto;
import br.com.motta.enquete.model.Status;
import br.com.motta.enquete.model.Usuario;

import java.time.LocalDate;
import java.util.List;


public class EnqueteMapper {

    public static EnqueteResponseDTO toDTO(Enquete enquete) {
        return new EnqueteResponseDTO(enquete.getId(),
                enquete.getTitulo(),
                enquete.getPergunta(),
                enquete.getTotalVotos(),
                enquete.getOpcoes().stream().map(OpcoesVotoMapper::toDto).toList(),
                enquete.getStatus(),
                enquete.getDataCriacao(),
                enquete.getDataEncerramento(),
                enquete.getUsuario().getNome(),
                enquete.getUsuario().getId());
    }

    public static Enquete toEntity(EnqueteRequestDTO dto, Usuario usuario) {
        Enquete enquete = new Enquete();
        enquete.setTitulo(dto.titulo());
        enquete.setPergunta(dto.pergunta());
        enquete.setOpcoes(dto.opcoes().stream().map(opcaoDto -> {
            OpcoesVoto opcoesConvertidas = OpcoesVotoMapper.toEntity(opcaoDto);
            opcoesConvertidas.setEnquete(enquete);
            return opcoesConvertidas;
        }).toList());
        enquete.setTotalVotos(0);
        enquete.setStatus(Status.ABERTA);
        enquete.setDataCriacao(LocalDate.now());
        enquete.setUsuario(usuario);
        return enquete;
    }

    public static EnqueteResultadoResponseDTO toResultadoDTO(Integer totalVotos, List<OpcaoResultadoDTO> opcoes, String vencedora){
        return new EnqueteResultadoResponseDTO(totalVotos, opcoes, vencedora);
    }
}
