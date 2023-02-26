package wsb.wsb_bugtracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WsbBugtrackerApplication {

    public static void main(String[] args) {
        SpringApplication.run(WsbBugtrackerApplication.class, args);
    }

}
