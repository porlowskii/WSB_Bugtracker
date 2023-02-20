package wsb.wsb_bugtracker.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
@Entity
public class Issue {

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @NotNull
    @Size(min = 2, max = 50, message = "{size.issue.code.err}")
    private String code;

    @NotNull
    @Size(min = 3, max = 255, message = "{size.issue.title.err}")
    private String title;

    private String content;

    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private IssueType type = IssueType.TASK;

    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private IssuePriority priority = IssuePriority.NORMAL;

    @Id
    @GeneratedValue
    private Long id;
}
