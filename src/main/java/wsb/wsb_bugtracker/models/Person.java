package wsb.wsb_bugtracker.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.util.Date;
import java.util.Set;

@Entity
@Data
public class Person {

    @Id
    @Column(nullable = false)
    private Long id;

    @Column(unique = true)
    @Size(min = 2, max = 50)
    private String username;

    @Column(nullable = false)
    @Size(min = 5, max = 50)
    private String password;

    private Boolean enabled = true;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    @Email
    private String email;

    @ManyToMany (cascade = CascadeType.MERGE)
    @JoinTable (name = "person_authorities",
        joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn (name = "authority_id"))
    private Set<Authority> authorities;
}
