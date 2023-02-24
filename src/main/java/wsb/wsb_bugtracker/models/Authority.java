package wsb.wsb_bugtracker.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Authority {

    @Id
    @GeneratedValue
    Long id;

    @Column(nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    AuthorityName authority;

    public Authority(AuthorityName authority) {
        this.authority = authority;
    }

    public Authority () {}

    public Long getId() {
        return  id;
    }

    public AuthorityName getAuthority() {
        return authority;
    }
}
