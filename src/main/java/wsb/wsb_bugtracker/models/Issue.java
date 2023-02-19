package wsb.wsb_bugtracker.models;

import jakarta.persistence.*;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
public class Issue {

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @NotNull
    @Size(min = 3)
    private String code;

    @NotNull
    @Size(min = 5)
    private String title;

    private String content;

    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private IssueType type = IssueType.TASK;

    @Id
    @GeneratedValue
    private Long id;
}
