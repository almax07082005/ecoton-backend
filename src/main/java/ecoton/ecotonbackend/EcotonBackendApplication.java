package ecoton.ecotonbackend;

import ecoton.ecotonbackend.entity.Role;
import ecoton.ecotonbackend.entity.User;
import ecoton.ecotonbackend.repository.RoleRepository;
import ecoton.ecotonbackend.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class EcotonBackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(EcotonBackendApplication.class, args);
    }

    @Bean
    public CommandLineRunner run(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (roleRepository.findByAuthority("ADMIN").isPresent()) {
                return;
            }

            roleRepository.save(new Role("USER"));
            Role admin = roleRepository.save(new Role("ADMIN"));
            Role organizer = roleRepository.save(new Role("ORGANIZER"));
        };
    }

}
