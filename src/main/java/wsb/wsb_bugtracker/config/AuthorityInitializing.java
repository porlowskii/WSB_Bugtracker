package wsb.wsb_bugtracker.config;

import org.springframework.beans.factory.InitializingBean;
import wsb.wsb_bugtracker.models.Authority;
import wsb.wsb_bugtracker.models.AuthorityName;
import wsb.wsb_bugtracker.repositories.AuthorityRepository;
import wsb.wsb_bugtracker.repositories.PersonRepository;

import java.util.Optional;

public class AuthorityInitializing implements InitializingBean {

    private final AuthorityRepository authorityRepository;
    private final PersonRepository personRepository;

    public AuthorityInitializing(AuthorityRepository authorityRepository,
                                 PersonRepository personRepository) {
        this.authorityRepository = authorityRepository;
        this.personRepository = personRepository;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        for (AuthorityName authorityName : AuthorityName.values()) {
            Optional<Authority> authority = authorityRepository.findByAuthority(authorityName);

            if (authority.isEmpty()) {
                Authority a = new Authority(authorityName);
                authorityRepository.save(a);
            }
        }

    }
}
