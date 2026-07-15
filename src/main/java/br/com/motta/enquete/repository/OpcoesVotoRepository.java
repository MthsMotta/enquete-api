package br.com.motta.enquete.repository;

import br.com.motta.enquete.model.OpcoesVoto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OpcoesVotoRepository extends JpaRepository<OpcoesVoto, Long> {
    boolean existsByIdAndEnqueteId(Long id, Long enqueteId);
}
