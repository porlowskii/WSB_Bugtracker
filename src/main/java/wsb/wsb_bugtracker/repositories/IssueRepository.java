package wsb.wsb_bugtracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import wsb.wsb_bugtracker.models.Issue;
import wsb.wsb_bugtracker.models.IssueStatus;
import wsb.wsb_bugtracker.models.IssueType;
import wsb.wsb_bugtracker.models.Person;

import java.util.List;

public interface IssueRepository extends JpaRepository <Issue, Long>, JpaSpecificationExecutor <Issue> {

    List<Issue> findIssueByProject(Long projectId);


    @Query("SELECT i FROM Issue i WHERE i.code = :code")
    List<Issue> findByCode(@Param("code") String code);

    List<Issue> findByPerson(Person person);

    List<Issue> findByType(IssueType type);

    List<Issue> findByStatus(IssueStatus status);
}
