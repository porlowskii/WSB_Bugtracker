package wsb.wsb_bugtracker.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import wsb.wsb_bugtracker.models.Authority;
import wsb.wsb_bugtracker.models.Person;
import wsb.wsb_bugtracker.repositories.PersonRepository;

import java.util.Collection;

//public class SecurityUserDetailsService implements UserDetailsService {
//
//    @Autowired
//    private PersonRepository personRepository;
//
////    @Override
////    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
////        Person person = personRepository.findByUsername(username);
////        User user = new User(person.getUsername(), person.getPassword(), (Collection<? extends GrantedAuthority>) person.getAuthorities());
////        return user;
////    }
//}
