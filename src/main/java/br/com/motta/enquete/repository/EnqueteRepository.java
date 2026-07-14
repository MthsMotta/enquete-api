package br.com.motta.enquete.repository;

import br.com.motta.enquete.model.Enquete;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnqueteRepository extends JpaRepository<Enquete, Long> {
}
