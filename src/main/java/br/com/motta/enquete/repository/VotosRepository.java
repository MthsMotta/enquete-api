package br.com.motta.enquete.repository;

import br.com.motta.enquete.model.Votos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VotosRepository extends JpaRepository<Votos, Long> {
    boolean existsByUsuarioIdAndEnqueteId(Long usuarioId, Long enqueteId);
}
