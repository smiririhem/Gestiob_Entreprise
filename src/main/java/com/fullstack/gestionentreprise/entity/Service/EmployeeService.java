package com.fullstack.gestionentreprise.entity.Service;

import com.fullstack.gestionentreprise.Repository.EmployeeRepository;
import com.fullstack.gestionentreprise.Token.ConfirmationToken;
import com.fullstack.gestionentreprise.Token.ConfirmationTokenService;
import com.fullstack.gestionentreprise.entity.Employee;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@Service
public class EmployeeService implements UserDetailsService {

    private final static String USER_NOT_FOUND_MSG =
            "user with email %s not found";
    private final EmployeeRepository employeeRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return employeeRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
    } //charger les détails de l'utilisateur en utilisant l'adresse e-mail comme identifiant unique
    public String signUpUser(Employee employee) {
        boolean userExists = employeeRepository
                .findByEmail(employee.getEmail())
                .isPresent();

        if (userExists) {
            // TODO check of attributes are the same and
            // TODO if email not confirmed send confirmation email.

            throw new IllegalStateException("email already taken");
        }

        String encodedPassword = bCryptPasswordEncoder
                .encode(employee.getPassword());

        employee.setPassword(encodedPassword);

        employeeRepository.save(employee);

        String token = UUID.randomUUID().toString();

        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                employee
        );

        confirmationTokenService.saveConfirmationToken(
                confirmationToken);
//        TODO: SEND EMAIL

        return token;
    }
    public int enableAppUser(String email) {
        return employeeRepository.enableAppUser(email);
    }
}