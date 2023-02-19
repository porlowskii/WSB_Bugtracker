package wsb.wsb_bugtracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import wsb.wsb_bugtracker.models.Issue;

public interface IssueRepository extends JpaRepository <Issue, Long> {
}
