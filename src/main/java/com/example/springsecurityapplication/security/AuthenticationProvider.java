//package com.example.springsecurityapplication.security;
//
//import com.example.springsecurityapplication.services.PersonDetailsService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//
//import java.util.Collections;

/*@Component
public class AuthenticationProvider implements org.springframework.security.authentication.AuthenticationProvider {

    private final PersonDetailsService personDetailsService;

    @Autowired
    public AuthenticationProvider(PersonDetailsService personDetailsService) {
        this.personDetailsService = personDetailsService;
    }

    // Логика по аутентификации в приложении
    // За нас SpringSecurity сам возьмет объект из формы и передаст его сюда
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // Получаем логин с формы аутентификации
        String login = authentication.getName();

        // Получаем пароль с формы аутентификации
        String password = authentication.getCredentials().toString();

        // Так как данный метод возвращает объект интерфейса UserDetails, то и объект мы создаем через интерфейс
        UserDetails person = personDetailsService.loadUserByUsername(login);

        // Если пароль с формы аутентификации не равен паролю пользователя из БД, который найден по логину, то выбрасывает исключение
        if (!password.equals(person.getPassword())){
            throw new BadCredentialsException("Введен некорректный пароль");
        }

        // Возвращаем объект аутентификации. В данном объекте будет лежать пользователь, который аутентифицировался, его пароль и его права доступа (список ролей emptyList)
        return new UsernamePasswordAuthenticationToken(person, password, Collections.emptyList());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}*/
