package wsb.wsb_bugtracker.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;
import java.util.Set;

@Entity
@Data
public class Person {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long id;

    @Column(unique = true)
    @Size(min = 2)
    private String username;

    @Column(nullable = false)
    @Size(min = 5)
    private String password;

    private Boolean enabled = true;

    @Column(nullable = false)
    @Size(min = 2)
    private String fullName;

    @Column(nullable = false)
    @Email
    private String email;

    @Column(nullable = false)
    private Date lastUpdate = new Date();


    @ManyToMany (cascade = CascadeType.MERGE)
    @JoinTable (name = "person_authorities",
        joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn (name = "authority_id"))
    private Set<Authority> authorities;

}

