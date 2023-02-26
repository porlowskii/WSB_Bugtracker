package wsb.wsb_bugtracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import wsb.wsb_bugtracker.models.Authority;
import wsb.wsb_bugtracker.models.AuthorityName;

import java.util.Optional;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    Optional<Authority> findByAuthority(AuthorityName authorityName);
}
