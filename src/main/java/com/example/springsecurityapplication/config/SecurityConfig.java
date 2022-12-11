package com.example.springsecurityapplication.config;

//import com.example.springsecurityapplication.security.AuthenticationProvider;
import com.example.springsecurityapplication.services.PersonDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

// Основной конфиг для конфигурации безопасности в приложении
@EnableWebSecurity
// Аннотация EnableGlobalMethodSecurity сообщает, что в приложении доступно разграничение ролей на основе аннотаций
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    /*private final AuthenticationProvider authenticationProvider;

    @Autowired
    public SecurityConfig(AuthenticationProvider authenticationProvider) {
        this.authenticationProvider = authenticationProvider;
    }*/

    private final PersonDetailsService personDetailsService;

    @Autowired
    public SecurityConfig(PersonDetailsService personDetailsService) {
        this.personDetailsService = personDetailsService;
    }

    // Метод по настройке аутентификации
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
//        authenticationManagerBuilder.authenticationProvider(authenticationProvider);

        // Производим аутентификацию с помощью сервиса
        authenticationManagerBuilder.userDetailsService(personDetailsService)
                .passwordEncoder(getPasswordEncoder());
    }

    // Конфигурируем сам Spring Security. Конфигурируем авторизацию
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        // Отключаем csrf защиту от межсайтовой подделки запросов
        http
                // Указываем, что все страницы должны быть защищены аутентификацией
                .authorizeRequests()
                // Указываем, что /admin доступен пользователю с ролью администратор
//                .antMatchers("/admin").hasAnyRole("ADMIN")
                // Указываем, что неаутентифицированные пользователи могут заходить на страницу с формой аутентификации и на объект ошибки. С помощью permitAll указываем, что данные страницы по умолчанию доступны всем пользователям
                .antMatchers("/authentication/login", "/error", "/authentication/registration", "/product", "/catalog", "/product/info/{id}", "/img/**", "/product/search", "/user", "/home", "/catalog", "/authentication/change").permitAll()
                // Указываем, что все остальные страницы доступны пользователю с ролями user и admin
                .anyRequest().hasAnyRole("USER", "ADMIN", "SELLER")
                /*// Указываем, что для всех остальных страниц необходимо вызывать метод authenticated, который открывает форму аутентификации
                .anyRequest().authenticated()*/
                // Указываем, что дальше конфигурироваться будет аутентификация и соединяем аутентификацию с настройкой доступа
                .and()
                // Указываем, какой URL-запрос будет отправляться при заходе на закрытые страницы
                 .formLogin().loginPage("/authentication/login")
                // Указываем, на какой URL-адрес будут отправляться данные с формы. Нам уже не нужно будет создавать метод в контроллере и обрабатывать данные с формы. Мы задали URL по умолчанию, который позволяет обрабатывать форму аутентификации в Spring Security. Spring Security будет ждать логин и пароль с формы, а затем сверять их с данными в БД
                .loginProcessingUrl("/process_login")
                // Указываем, на какой URL необходимо отправить пользователя после успешной аутентификациии. Вторым аргументом ставим true, чтобы перенаправление на данную страницу шло в любом случае при успешной аутентификации
                .defaultSuccessUrl("/home", true)
                // Указываем, куда необходимо перенаправить пользователя при неуспешной аутентификации
                // В URL будет передан объект. Данный объект мы будем проверять на форме, и если он существует, будет выведено сообщение: "Неправильный логин или пароль"
                .failureUrl("/authentication/login?error")
                .and()
                // Указываем, что при переходе на /logout будет очищена сессия пользователя и перенаправление на /authentication/login
                .logout().logoutUrl("/logout").logoutSuccessUrl("/authentication/login");
    }

    @Override
    public void configure(WebSecurity web){
        web.ignoring()
                .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/img/**", "/icon/**", "/script/**");
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){

        return new BCryptPasswordEncoder();
    }
}
