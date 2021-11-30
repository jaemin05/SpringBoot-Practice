package Entity;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CertificationRepository extends CrudRepository<Certification, String> {
    Optional<Certification> findByEmail(String email);
}
