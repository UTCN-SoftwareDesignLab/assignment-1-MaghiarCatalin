package com.lab4.demo;

import com.lab4.demo.book.BookRepository;
import com.lab4.demo.book.model.Book;
import com.lab4.demo.security.AuthService;
import com.lab4.demo.security.dto.SignupRequest;
import com.lab4.demo.user.RoleRepository;
import com.lab4.demo.user.UserRepository;
import com.lab4.demo.user.model.ERole;
import com.lab4.demo.user.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class Bootstrapper implements ApplicationListener<ApplicationReadyEvent> {

    private final RoleRepository roleRepository;

    private final UserRepository userRepository;

    private final AuthService authService;

    private final BookRepository bookRepository;

    @Value("${app.bootstrap}")
    private Boolean bootstrap;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        if (bootstrap) {
            bookRepository.deleteAll();
            userRepository.deleteAll();
            roleRepository.deleteAll();
            for (ERole value : ERole.values()) {
                roleRepository.save(
                        Role.builder()
                                .name(value)
                                .build()
                );
            }
            authService.register(SignupRequest.builder()
                    .email("admin@email.com")
                    .username("admin")
                    .password("Admintest1!")
                    .roles(Set.of("ADMIN"))
                    .build());
            authService.register(SignupRequest.builder()
                    .email("user@email.com")
                    .username("user")
                    .password("Usertest1!")
                    .roles(Set.of("USER"))
                    .build());

            Book b = Book.builder()
                    .title("Title1")
                    .author("Author1")
                    .genre("Genre1")
                    .price(100)
                    .quantity(3)
                    .build();

            Book b1 = Book.builder()
                    .title("Title2")
                    .author("Author2")
                    .genre("Genre1")
                    .price(30)
                    .quantity(0)
                    .build();

            bookRepository.save(b);
            bookRepository.save(b1);
        }
    }
}
