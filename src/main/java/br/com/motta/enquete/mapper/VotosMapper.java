package br.com.motta.enquete.mapper;

import br.com.motta.enquete.dto.VotosRequestDTO;
import br.com.motta.enquete.dto.VotosResponseDTO;
import br.com.motta.enquete.model.OpcoesVoto;
import br.com.motta.enquete.model.Usuario;
import br.com.motta.enquete.model.Votos;

import java.time.LocalDate;

public class VotosMapper {

    public static VotosResponseDTO toDto(Votos votos){
        return new VotosResponseDTO(votos.getId(),
                votos.getEnquete().getId(),
                votos.getOpcoesVoto().getId(),
                votos.getUsuario().getId(),
                votos.getDataVoto());
    }

    public static Votos toEntity(VotosRequestDTO dto, OpcoesVoto opcoesVoto, Usuario usuario){
        Votos votos = new Votos();
        votos.setOpcoesVoto(opcoesVoto);
        votos.setEnquete(opcoesVoto.getEnquete());
        votos.setUsuario(usuario);
        votos.setDataVoto(LocalDate.now());
        return votos;
    }
}
