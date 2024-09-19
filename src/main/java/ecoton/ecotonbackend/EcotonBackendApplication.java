package ecoton.ecotonbackend;

import ecoton.ecotonbackend.entity.roles.Role;
import ecoton.ecotonbackend.repository.AuthRoleRepository;
import ecoton.ecotonbackend.repository.AuthUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class EcotonBackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(EcotonBackendApplication.class, args);
    }

    @Bean
    public CommandLineRunner run(AuthRoleRepository roleRepository, AuthUserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (roleRepository.findByAuthority("ADMIN").isPresent()) {
                return;
            }

            roleRepository.save(new Role("USER"));
            Role organizer = roleRepository.save(new Role("ORGANIZER"));
            Role official = roleRepository.save(new Role("OFFICIAL"));
            Role admin = roleRepository.save(new Role("ADMIN"));
        };
    }

}
