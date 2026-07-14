package br.com.motta.enquete.mapper;

import br.com.motta.enquete.dto.OpcoesVotoRequestDTO;
import br.com.motta.enquete.dto.OpcoesVotoResponseDTO;
import br.com.motta.enquete.model.OpcoesVoto;

public class OpcoesVotoMapper {

    public static OpcoesVotoResponseDTO toDto(OpcoesVoto opcoesVoto) {
        return new OpcoesVotoResponseDTO(opcoesVoto.getId(), opcoesVoto.getTexto(),  opcoesVoto.getQuantidadeVotos());
    }

    public static OpcoesVoto toEntity(OpcoesVotoRequestDTO dto){
        OpcoesVoto opcoesVoto = new OpcoesVoto();
        opcoesVoto.setTexto(dto.texto());
        return opcoesVoto;
    }
}
