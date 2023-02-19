package wsb.wsb_bugtracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import wsb.wsb_bugtracker.models.Project;

public interface ProjectRepository extends JpaRepository <Project, Long> {
}
