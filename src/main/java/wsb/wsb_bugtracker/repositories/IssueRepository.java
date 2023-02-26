package wsb.wsb_bugtracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import wsb.wsb_bugtracker.models.Issue;

import java.util.List;

public interface IssueRepository extends JpaRepository <Issue, Long>, JpaSpecificationExecutor <Issue> {

    List<Issue> findIssueByProject(Long projectId);
}
