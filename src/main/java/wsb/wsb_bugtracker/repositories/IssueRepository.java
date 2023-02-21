package wsb.wsb_bugtracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import wsb.wsb_bugtracker.models.Issue;

import java.util.List;

public interface IssueRepository extends JpaRepository <Issue, Long> {

    List<Issue> findIssueByProject(Long projectId);
}
