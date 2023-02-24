package wsb.wsb_bugtracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import wsb.wsb_bugtracker.models.Person;

public interface PersonRepository extends JpaRepository <Person, Long> {
}
