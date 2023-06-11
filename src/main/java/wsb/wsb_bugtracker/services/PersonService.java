package wsb.wsb_bugtracker.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import wsb.wsb_bugtracker.models.Person;
import wsb.wsb_bugtracker.repositories.AuthorityRepository;
import wsb.wsb_bugtracker.repositories.PersonRepository;

@Service
public class PersonService {
//    @Value("${my.admin.username}")
//    private String username;
//    @Value("${my.admin.password}")
//    private String password;
//    @Value("${my.admin.firstName}")
//    private String fullname;
//    @Value("${my.admin.email}")
//    private String email;

    private final AuthorityRepository authorityRepository;
    private final PersonRepository personRepository;
    private PasswordEncoder passwordEncoder;



    @Autowired
    public PersonService(AuthorityRepository authorityRepository, PersonRepository personRepository ) {
        this.authorityRepository = authorityRepository;
        this.personRepository = personRepository;
    }


    public void savePerson(Person person){
        String hashedPassword = "{bcrypt}"+ new BCryptPasswordEncoder().encode(person.getPassword());
        person.setPassword(hashedPassword);
        personRepository.save(person);
    }
}
