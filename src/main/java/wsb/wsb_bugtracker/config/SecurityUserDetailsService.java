package wsb.wsb_bugtracker.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wsb.wsb_bugtracker.models.Authority;
import wsb.wsb_bugtracker.models.Person;
import wsb.wsb_bugtracker.repositories.PersonRepository;

import java.util.*;

@Service

public class SecurityUserDetailsService implements UserDetailsService {

    private PersonRepository personRepository;
    public SecurityUserDetailsService (PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Person> person = Optional.ofNullable(personRepository.findByUsername(username));
        if(person.isEmpty()){
            throw new UsernameNotFoundException(username);
        }
        List<GrantedAuthority> authorities = getUserAuthorities(person);
        return new User(person.get().getUsername(), person.get().getPassword(), authorities);
    }

    private List<GrantedAuthority> getUserAuthorities(Optional<Person> person) {
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

        for (Authority authority : person.get().getAuthorities()){
            grantedAuthorities.add(new SimpleGrantedAuthority(authority.getAuthority().name()));
        }
        return new ArrayList<>(grantedAuthorities);
    }
}
